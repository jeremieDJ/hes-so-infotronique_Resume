package ch.hevs.ss1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;

import java.util.Locale;

import ch.hevs.kart.AbstractKartControlActivity;
import ch.hevs.kart.Kart;
import ch.hevs.kart.KartListener;
import ch.hevs.kart.KartRegisters;

public class MyKartRemote extends AbstractKartControlActivity implements KartListener, InputManager.InputDeviceListener {

    private static final int MAX_SPEED = 15;
    private static final int MIN_SPEED = -15;
    private static final float ALPHA = 0.15f;

    private static final float DEFAULT_MAX_ANGLE = 30.0f;

    private static final int DIRECTION_SEEKBAR_MAX = 250;
    private static final int DIRECTION_SEEKBAR_CENTER = 215;
    private static final int STEERING_RANGE = 35;

    private static final int STEERING_NEUTRAL_MOTOR = 215;
    private static final int STEERING_MIN_MOTOR = 180;
    private static final int STEERING_MAX_MOTOR = 250;

    // --- REGISTRES PWM FPGA POUR LES SERVOS (0° à 110°) ---
    private static final int SERVO_COUNT_0_DEG   = 10000; // ~1.0 ms
    private static final int SERVO_COUNT_110_DEG = 22222; // ~2.22 ms

    // --- REGISTRES PWM FPGA POUR LE SERVO 8 (20° à 120°) ---
    private static final int SERVO_COUNT_20_DEG  = 12222;
    private static final int SERVO_COUNT_120_DEG = 23333;

    private static final double WHEEL_RADIUS_CM = 3.5;
    private static final long STOP_TIMEOUT_MS = 600;

    private static final float TRIGGER_DEADZONE = 0.05f;
    private static final long GAMEPAD_TIMEOUT_MS = 250;

    private int currentSpeed = 0;
    private boolean isLedsOn = false;
    private boolean isBlinking = false;
    private boolean isGyroEnabled = false;
    private boolean isGamepadEnabled = false;

    // Angles initiaux des petits servos
    private int angleLed5 = 110; // Max 110
    private int angleLed6 = 0;   // Max 110
    private int angleLed7 = 20;  // Inverse de 120 (base à 20)
    private int angleLed8 = 120; // Base demandée à 120 (plage 20-120)

    // --- FILTRAGE ANTI-TREMBLEMENT ---
    private int lastSentAngle5 = -1;
    private int lastSentAngle6 = -1;
    private int lastSentAngle7 = -1;
    private int lastSentAngle8 = -1;

    // =========================================================================
    // PARAMÈTRES ET CONFIGURATION DE LA SÉQUENCE DU TOIT
    // =========================================================================
    private static final int ROOF_COVER_CLOSED = 110;
    private static final int ROOF_COVER_OPEN   = 0;

    private static final int ROOF_TOP_RETRACTED = 110;
    private static final int ROOF_TOP_DEPLOYED  = 0;

    private static final long DELAY_STEP_1_TO_2 = 400;
    private static final long DELAY_STEP_2_TO_3 = 500;
    private static final long DELAY_STEP_3_END  = 400;

    private final Handler roofHandler = new Handler(Looper.getMainLooper());
    private boolean isRoofBusy = false;
    private boolean isRoofOpen = false;

    private boolean isPhare7Raised = false;
    private boolean isPhare8Raised = false;

    private long lastSpeedUpdateTime = 0;
    private long lastUiUpdateTime = 0;
    private float filteredX = 0f;
    private float filteredY = 0f;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener gyroListener;
    private InputManager inputManager;

    private TextView tvRealSpeed;
    private ToggleButton btnToggleLed;
    private ToggleButton btnBlink;

    private SeekBar seekBarDirection;
    private TextView tvAngle;
    private TextView textViewDistance;

    private SeekBar seekBarSpeed;
    private TextView tvSpeed;

    private SeekBar seekBarLed5;
    private SeekBar seekBarLed6;
    private SeekBar seekBarLed7;
    private SeekBar seekBarLed8;
    private TextView tvAngleLed5;
    private TextView tvAngleLed6;
    private TextView tvAngleLed7;
    private TextView tvAngleLed8;

    private Button btnAnimRoof;
    private Button btnAnimPhare7;
    private Button btnAnimPhare8;

    private ProgressBar progressBarBattery;
    private TextView tvBatteryPercentage;
    private TextView tvBatteryVoltage;
    private TextView tvBatteryCurrent;

    private ToggleButton btnGyro;
    private ToggleButton btnGamepad;

    private static final int PULSES_PER_REVOLUTION = 2;
    private static final int MOVING_AVERAGE_SIZE = 5;
    private final java.util.LinkedList<Double> speedBuffer = new java.util.LinkedList<>();

    private int lastHallCount = -1;
    private long lastHallTimestamp = 0;
    private double filteredSpeedMs = 0.0;

    private final Handler stopHandler = new Handler(Looper.getMainLooper());
    private final Runnable stopRunnable = () -> {
        filteredSpeedMs = 0.0;
        lastHallCount = -1;
        lastHallTimestamp = 0;
        speedBuffer.clear();
        if (tvRealSpeed != null) {
            tvRealSpeed.setText("Vitesse réelle : 0.00 m/s");
        }
    };

    private final Handler gamepadWatchdogHandler = new Handler(Looper.getMainLooper());
    private final Runnable gamepadTimeoutRunnable = () -> {
        if (isGamepadEnabled && currentSpeed != 0) {
            currentSpeed = 0;
            applySpeedChange();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_kart_remote);

        inputManager = (InputManager) getSystemService(Context.INPUT_SERVICE);

        initViews();
        setupLedControls();
        setupSteeringControl();
        setupServoSeekBars();
        setupAnimationControls();
        setupSpeedControls();
        setupGyroControl();
        setupGamepadControl();

        if (seekBarDirection != null) {
            seekBarDirection.setMin(180);
            seekBarDirection.setMax(DIRECTION_SEEKBAR_MAX);
            seekBarDirection.setProgress(DIRECTION_SEEKBAR_CENTER);
        }
        updateAngleDisplay(DIRECTION_SEEKBAR_CENTER);
        updateUiElementStates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (inputManager != null) {
            inputManager.registerInputDeviceListener(this, null);
        }

        Kart kart = getKart();
        if (kart != null) {
            kart.addKartListener(this);
            kart.setSteeringTargetPosition(STEERING_NEUTRAL_MOTOR);
            updateBatteryUI(kart);
            updateAllAuxServos();
        }

        if (sensorManager != null && accelerometer != null && gyroListener != null) {
            sensorManager.registerListener(gyroListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    private void updateAllAuxServos() {
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED5.ADDRESS, angleLed5, 100);
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED6.ADDRESS, angleLed6, 100);
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED7.ADDRESS, angleLed7, 120);
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED8.ADDRESS, angleLed8, 120);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (inputManager != null) {
            inputManager.unregisterInputDeviceListener(this);
        }

        gamepadWatchdogHandler.removeCallbacks(gamepadTimeoutRunnable);
        roofHandler.removeCallbacksAndMessages(null);

        if (currentSpeed != 0) {
            currentSpeed = 0;
            applySpeedChange();
        }

        try {
            Kart kart = getKart();
            if (kart != null) {
                kart.removeKartListener(this);
            }
        } catch (Exception ignored) {}

        if (sensorManager != null && gyroListener != null) {
            sensorManager.unregisterListener(gyroListener);
        }
        stopHandler.removeCallbacks(stopRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gamepadWatchdogHandler.removeCallbacks(gamepadTimeoutRunnable);
        roofHandler.removeCallbacksAndMessages(null);

        if (currentSpeed != 0) {
            currentSpeed = 0;
            applySpeedChange();
        }

        try {
            Kart kart = getKart();
            if (kart != null) {
                kart.removeKartListener(this);
            }
        } catch (Exception ignored) {}
        stopHandler.removeCallbacks(stopRunnable);
    }

    @Override public void onInputDeviceAdded(int deviceId) {}
    @Override public void onInputDeviceChanged(int deviceId) {}

    @Override
    public void onInputDeviceRemoved(int deviceId) {
        if (isGamepadEnabled) {
            gamepadWatchdogHandler.removeCallbacks(gamepadTimeoutRunnable);
            currentSpeed = 0;
            applySpeedChange();
        }
    }

    private void initViews() {
        btnToggleLed = findViewById(R.id.btnToggleLed);
        btnBlink = findViewById(R.id.btnBlink);

        progressBarBattery = findViewById(R.id.progressBarBattery);
        tvBatteryPercentage = findViewById(R.id.tvBatteryPercentage);
        tvBatteryVoltage = findViewById(R.id.tvBatteryVoltage);
        tvBatteryCurrent = findViewById(R.id.tvBatteryCurrent);

        seekBarDirection = findViewById(R.id.seekBar);
        tvAngle = findViewById(R.id.tvAngle);

        textViewDistance = findViewById(R.id.tvDistance);

        seekBarSpeed = findViewById(R.id.seekBarSpeed);
        tvSpeed = findViewById(R.id.tvSpeed);
        tvRealSpeed = findViewById(R.id.tvRealSpeed);

        seekBarLed5 = findViewById(R.id.seekBarLed5);
        seekBarLed6 = findViewById(R.id.seekBarLed6);
        seekBarLed7 = findViewById(R.id.seekBarLed7);
        seekBarLed8 = findViewById(R.id.seekBarLed8);

        tvAngleLed5 = findViewById(R.id.tvAngleLed5);
        tvAngleLed6 = findViewById(R.id.tvAngleLed6);
        tvAngleLed7 = findViewById(R.id.tvAngleLed7);
        tvAngleLed8 = findViewById(R.id.tvAngleLed8);

        btnAnimRoof = findViewById(R.id.btnAnimServo5);
        btnAnimPhare7 = findViewById(R.id.btnAnimServo6);
        btnAnimPhare8 = findViewById(R.id.btnAnimServos78);

        btnGyro = findViewById(R.id.toggleButtonGyro);
        btnGamepad = findViewById(R.id.toggleButtonGamepad);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    // --- SÉQUENCE SÉQUENTIELLE DU TOIT ---

    private void executeRoofSequence() {
        if (isRoofBusy) return;
        isRoofBusy = true;

        if (!isRoofOpen) {
            angleLed5 = ROOF_COVER_OPEN;
            applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED5.ADDRESS, angleLed5, 100);
            updateServoUiFields();

            roofHandler.postDelayed(() -> {
                angleLed6 = ROOF_TOP_RETRACTED;
                applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED6.ADDRESS, angleLed6, 100);
                updateServoUiFields();

                roofHandler.postDelayed(() -> {
                    angleLed5 = ROOF_COVER_CLOSED;
                    applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED5.ADDRESS, angleLed5, 100);
                    updateServoUiFields();

                    roofHandler.postDelayed(() -> {
                        isRoofOpen = true;
                        isRoofBusy = false;
                    }, DELAY_STEP_3_END);

                }, DELAY_STEP_2_TO_3);

            }, DELAY_STEP_1_TO_2);

        } else {
            angleLed5 = ROOF_COVER_OPEN;
            applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED5.ADDRESS, angleLed5, 100);
            updateServoUiFields();

            roofHandler.postDelayed(() -> {
                angleLed6 = ROOF_TOP_DEPLOYED;
                applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED6.ADDRESS, angleLed6, 100);
                updateServoUiFields();

                roofHandler.postDelayed(() -> {
                    angleLed5 = ROOF_COVER_CLOSED;
                    applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED5.ADDRESS, angleLed5, 100);
                    updateServoUiFields();

                    roofHandler.postDelayed(() -> {
                        isRoofOpen = false;
                        isRoofBusy = false;
                    }, DELAY_STEP_3_END);

                }, DELAY_STEP_2_TO_3);

            }, DELAY_STEP_1_TO_2);
        }
    }

    private void togglePhare7Rapide() {
        isPhare7Raised = !isPhare7Raised;
        angleLed7 = isPhare7Raised ? 120 : 20;
        angleLed8 = 140 - angleLed7; // Inversion synchronisée
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED7.ADDRESS, angleLed7, 120);
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED8.ADDRESS, angleLed8, 120);
        updateServoUiFields();
    }

    private void togglePhare8Rapide() {
        isPhare8Raised = !isPhare8Raised;
        angleLed8 = isPhare8Raised ? 120 : 20;
        angleLed7 = 140 - angleLed8; // Inversion synchronisée
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED8.ADDRESS, angleLed8, 120);
        applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED7.ADDRESS, angleLed7, 120);
        updateServoUiFields();
    }

    private void setupAnimationControls() {
        if (btnAnimRoof != null) {
            btnAnimRoof.setText("Toit Séquentiel");
            btnAnimRoof.setOnClickListener(v -> executeRoofSequence());
        }
        if (btnAnimPhare7 != null) {
            btnAnimPhare7.setText("Phare G (LED7)");
            btnAnimPhare7.setOnClickListener(v -> togglePhare7Rapide());
        }
        if (btnAnimPhare8 != null) {
            btnAnimPhare8.setText("Phare D (LED8)");
            btnAnimPhare8.setOnClickListener(v -> togglePhare8Rapide());
        }
    }

    private void updateServoUiFields() {
        runOnUiThread(() -> {
            if (seekBarLed5 != null) seekBarLed5.setProgress(angleLed5);
            if (seekBarLed6 != null) seekBarLed6.setProgress(angleLed6);
            if (seekBarLed7 != null) seekBarLed7.setProgress(angleLed7);
            if (seekBarLed8 != null) seekBarLed8.setProgress(angleLed8);

            if (tvAngleLed5 != null) tvAngleLed5.setText(String.format(Locale.getDefault(), "Coffre (LED5) : %d°", angleLed5));
            if (tvAngleLed6 != null) tvAngleLed6.setText(String.format(Locale.getDefault(), "Toit (LED6) : %d°", angleLed6));
            if (tvAngleLed7 != null) tvAngleLed7.setText(String.format(Locale.getDefault(), "Phare Gauche (LED7) : %d°", angleLed7));
            if (tvAngleLed8 != null) tvAngleLed8.setText(String.format(Locale.getDefault(), "Phare Droit (LED8) : %d°", angleLed8));
        });
    }

    // --- KartListener ---

    @Override
    public void hallSensorCountChanged(@NonNull Kart kart, int sensorIndex, int count) {
        if (sensorIndex == 0) {
            long currentTimeNano = System.nanoTime();
            if (lastHallTimestamp != 0 && lastHallCount != -1) {
                int deltaCount = Math.abs(count - lastHallCount);

                if (deltaCount > 0) {
                    long deltaTimeNano = currentTimeNano - lastHallTimestamp;
                    double deltaTimeSec = deltaTimeNano / 1_000_000_000.0;

                    if (deltaTimeSec > 0.002) {
                        double circumferenceMeters = (2 * Math.PI * WHEEL_RADIUS_CM) / 100.0;
                        double totalDistanceMeters = (deltaCount / (double) PULSES_PER_REVOLUTION) * circumferenceMeters;
                        double rawSpeedMs = totalDistanceMeters / deltaTimeSec;

                        speedBuffer.addLast(rawSpeedMs);
                        if (speedBuffer.size() > MOVING_AVERAGE_SIZE) {
                            speedBuffer.removeFirst();
                        }

                        double sum = 0.0;
                        for (double speed : speedBuffer) {
                            sum += speed;
                        }
                        double averageSpeed = sum / speedBuffer.size();

                        filteredSpeedMs = filteredSpeedMs + 0.15 * (averageSpeed - filteredSpeedMs);
                        double displayedSpeed = (filteredSpeedMs < 0.05) ? 0.00 : filteredSpeedMs;

                        runOnUiThread(() -> {
                            if (tvRealSpeed != null) {
                                tvRealSpeed.setText(String.format(Locale.getDefault(), "Vitesse réelle : %.2f m/s", displayedSpeed));
                            }
                        });

                        lastHallTimestamp = currentTimeNano;
                        lastHallCount = count;
                    }
                }
            } else {
                lastHallTimestamp = currentTimeNano;
                lastHallCount = count;
            }

            stopHandler.removeCallbacks(stopRunnable);
            stopHandler.postDelayed(stopRunnable, STOP_TIMEOUT_MS);
        }
    }

    @Override
    public void ultrasonicRangerDistanceChanged(@NonNull Kart kart, double distance) {
        runOnUiThread(() -> {
            if (textViewDistance != null) {
                textViewDistance.setText(String.format(Locale.getDefault(), "Distance : %.1f cm", distance * 100.0));
            }
        });
    }

    @Override
    public void batteryLevelChanged(@NonNull Kart kart, double level) {
        int percentage = clampCompat((int) Math.round(level * 100.0), 0, 100);
        runOnUiThread(() -> {
            if (progressBarBattery != null) progressBarBattery.setProgress(percentage);
            if (tvBatteryPercentage != null) tvBatteryPercentage.setText(String.format(Locale.getDefault(), "%d%%", percentage));
        });
    }

    @Override
    public void batteryVoltageChanged(@NonNull Kart kart, double voltage) {
        runOnUiThread(() -> {
            if (tvBatteryVoltage != null) tvBatteryVoltage.setText(String.format(Locale.getDefault(), "%.2f V", voltage));
        });
    }

    @Override
    public void currentChanged(@NonNull Kart kart, double current) {
        runOnUiThread(() -> {
            if (tvBatteryCurrent != null) tvBatteryCurrent.setText(String.format(Locale.getDefault(), "%.2f A", current));
        });
    }

    // --- DIRECTION ---

    private void setupSteeringControl() {
        if (seekBarDirection == null) return;
        seekBarDirection.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateAngleDisplay(progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void updateAngleDisplay(int progress) {
        int displayedAngle = calculateDisplayedAngle(progress);
        int motorAngle = calculateMotorAngle(progress);

        if (tvAngle != null) {
            tvAngle.setText(String.format(Locale.getDefault(), "Angle : %d° (FPGA : %d)", displayedAngle, motorAngle));
        }

        Kart kart = getKart();
        if (kart != null) {
            kart.setSteeringTargetPosition(motorAngle);
        }
    }

    private int calculateDisplayedAngle(int progress) {
        float normalized = (progress - (float) DIRECTION_SEEKBAR_CENTER) / (float) STEERING_RANGE;
        return Math.round(normalized * DEFAULT_MAX_ANGLE);
    }

    private int calculateMotorAngle(int progress) {
        float normalized = (progress - (float) DIRECTION_SEEKBAR_CENTER) / (float) STEERING_RANGE;
        int motorValue;
        if (normalized >= 0) {
            motorValue = Math.round(STEERING_NEUTRAL_MOTOR + normalized * (STEERING_MAX_MOTOR - STEERING_NEUTRAL_MOTOR));
        } else {
            motorValue = Math.round(STEERING_NEUTRAL_MOTOR + normalized * (STEERING_NEUTRAL_MOTOR - STEERING_MIN_MOTOR));
        }
        return clampCompat(motorValue, STEERING_MIN_MOTOR, STEERING_MAX_MOTOR);
    }

    // --- CONFIGURATION DES SEEKBARS DES SERVOS (AVEC ANTI-SPAM ET FLUIDITÉ) ---

    private void setupServoSeekBars() {
        if (seekBarLed7 != null) {
            seekBarLed7.setMin(20);
            seekBarLed7.setMax(100); // Max spécifique pour le servo 7
            seekBarLed7.setProgress(angleLed7);
            seekBarLed7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (!fromUser) return;
                    angleLed7 = progress;
                    if (tvAngleLed7 != null)
                        tvAngleLed7.setText(String.format(Locale.getDefault(), "Phare Gauche (LED7) : %d°", angleLed7));

                    // Envoi uniquement pour le servo 7 avec son propre max (110)
                    applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED7.ADDRESS, angleLed7, 100);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }

        if (seekBarLed8 != null) {
            seekBarLed8.setMin(20);
            seekBarLed8.setMax(120); // Max spécifique pour le servo 8
            seekBarLed8.setProgress(angleLed8);
            seekBarLed8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (!fromUser) return;
                    angleLed8 = progress;
                    if (tvAngleLed8 != null)
                        tvAngleLed8.setText(String.format(Locale.getDefault(), "Phare Droit (LED8) : %d°", angleLed8));

                    // Envoi uniquement pour le servo 8 avec son propre max (120)
                    applyAuxServoPulse(KartRegisters.V2.Control.Sensors.LED8.ADDRESS, angleLed8, 120);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    private void applyAuxServoPulse(int registerAddress, int angleDegree, int maxLimit) {
        Kart kart = getKart();
        if (kart == null) return;

        int safeAngle = clampCompat(angleDegree, (maxLimit == 120 ? 20 : 0), maxLimit);

        // Anti-rebond / anti-spam : n'envoie la trame que si l'angle change
        if (registerAddress == KartRegisters.V2.Control.Sensors.LED5.ADDRESS) {
            if (safeAngle == lastSentAngle5) return;
            lastSentAngle5 = safeAngle;
        } else if (registerAddress == KartRegisters.V2.Control.Sensors.LED6.ADDRESS) {
            if (safeAngle == lastSentAngle6) return;
            lastSentAngle6 = safeAngle;
        } else if (registerAddress == KartRegisters.V2.Control.Sensors.LED7.ADDRESS) {
            if (safeAngle == lastSentAngle7) return;
            lastSentAngle7 = safeAngle;
        } else if (registerAddress == KartRegisters.V2.Control.Sensors.LED8.ADDRESS) {
            if (safeAngle == lastSentAngle8) return;
            lastSentAngle8 = safeAngle;
        }

        int countTarget;
        if (maxLimit == 120) {
            countTarget = SERVO_COUNT_20_DEG + Math.round(((safeAngle - 20) / 100.0f) * (SERVO_COUNT_120_DEG - SERVO_COUNT_20_DEG));
        } else {
            countTarget = SERVO_COUNT_0_DEG + Math.round((safeAngle / 100.0f) * (SERVO_COUNT_110_DEG - SERVO_COUNT_0_DEG));
        }

        try {
            kart.setControlRegister(registerAddress, countTarget);
        } catch (Exception ignored) {}
    }

    // --- AUTRES CONTRÔLES ---

    private void setupGamepadControl() {
        if (btnGamepad == null) return;
        btnGamepad.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isGamepadEnabled = isChecked;
            if (!isChecked) {
                gamepadWatchdogHandler.removeCallbacks(gamepadTimeoutRunnable);
                if (currentSpeed != 0) {
                    currentSpeed = 0;
                    applySpeedChange();
                }
            }
            if (isChecked && isGyroEnabled && btnGyro != null) btnGyro.setChecked(false);
            updateUiElementStates();
        });
    }

    @Override
    public boolean onGenericMotionEvent(@NonNull MotionEvent event) {
        if (!isGamepadEnabled) return super.onGenericMotionEvent(event);

        if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK && event.getAction() == MotionEvent.ACTION_MOVE) {

            gamepadWatchdogHandler.removeCallbacks(gamepadTimeoutRunnable);
            gamepadWatchdogHandler.postDelayed(gamepadTimeoutRunnable, GAMEPAD_TIMEOUT_MS);

            float rightTrigger = event.getAxisValue(MotionEvent.AXIS_RTRIGGER);
            if (rightTrigger <= TRIGGER_DEADZONE) rightTrigger = event.getAxisValue(MotionEvent.AXIS_GAS);

            float leftTrigger = event.getAxisValue(MotionEvent.AXIS_LTRIGGER);
            if (leftTrigger <= TRIGGER_DEADZONE) leftTrigger = event.getAxisValue(MotionEvent.AXIS_BRAKE);

            int targetSpeed = 0;
            if (rightTrigger > TRIGGER_DEADZONE) {
                targetSpeed = Math.round(rightTrigger * MAX_SPEED);
            } else if (leftTrigger > TRIGGER_DEADZONE) {
                targetSpeed = Math.round(-leftTrigger * Math.abs(MIN_SPEED));
            }

            targetSpeed = clampCompat(targetSpeed, MIN_SPEED, MAX_SPEED);
            if (targetSpeed != currentSpeed) {
                currentSpeed = targetSpeed;
                applySpeedChange();
            }

            float joyX = event.getAxisValue(MotionEvent.AXIS_X);
            if (Math.abs(joyX) < 0.1f) joyX = 0f;
            int progress = Math.round(DIRECTION_SEEKBAR_CENTER + joyX * STEERING_RANGE);
            progress = clampCompat(progress, STEERING_MIN_MOTOR, DIRECTION_SEEKBAR_MAX);

            int motorAngle = calculateMotorAngle(progress);
            int displayedAngle = calculateDisplayedAngle(progress);

            Kart kart = getKart();
            if (kart != null) kart.setSteeringTargetPosition(motorAngle);

            final int finalProgress = progress;
            final int finalAngle = displayedAngle;
            final int finalMotorAngle = motorAngle;
            runOnUiThread(() -> {
                if (seekBarDirection != null) seekBarDirection.setProgress(finalProgress);
                if (tvAngle != null) tvAngle.setText(String.format(Locale.getDefault(), "Angle : %d° (FPGA : %d)", finalAngle, finalMotorAngle));
            });

            return true;
        }
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (!isGamepadEnabled) return super.onKeyDown(keyCode, event);
        if ((event.getSource() & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BUTTON_A:
                    isLedsOn = !isLedsOn;
                    if (btnToggleLed != null) runOnUiThread(() -> btnToggleLed.setChecked(isLedsOn));
                    syncAllLeds();
                    return true;
                case KeyEvent.KEYCODE_BUTTON_L1:
                case KeyEvent.KEYCODE_BUTTON_R1:
                    isBlinking = !isBlinking;
                    if (btnBlink != null) runOnUiThread(() -> btnBlink.setChecked(isBlinking));
                    syncAllLeds();
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private long lastSteeringUpdateTime = 0;

    private void setupGyroControl() {
        gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (!isGyroEnabled) return;
                long currentTime = System.currentTimeMillis();

                // Filtrage doux des valeurs du capteur
                filteredX = filteredX + ALPHA * (event.values[0] - filteredX);
                filteredY = filteredY + ALPHA * (event.values[1] - filteredY);

                // --- 1. Gestion de la Direction (Limitée à toutes les 100ms / ~10 fois par sec) ---
                if (currentTime - lastSteeringUpdateTime > 100) {
                    lastSteeringUpdateTime = currentTime;

                    float yVal = filteredY;
                    if (Math.abs(yVal) < 0.5f) yVal = 0f;
                    float normalized = (float) clampCompat(Math.round((yVal / 5.0f) * 100), -100, 100) / 100.0f;

                    int progress = Math.round(DIRECTION_SEEKBAR_CENTER + normalized * STEERING_RANGE);
                    progress = clampCompat(progress, STEERING_MIN_MOTOR, DIRECTION_SEEKBAR_MAX);

                    int motorAngle = calculateMotorAngle(progress);
                    int angleDegrees = calculateDisplayedAngle(progress);

                    // Envoi sécurisé de l'angle pour ne pas saturer l'UART du dongle BLE
                    Kart kart = getKart();
                    if (kart != null) {
                        try {
                            kart.setSteeringTargetPosition(motorAngle);
                        } catch (Exception ignored) {}
                    }

                    // Mise à jour de l'UI
                    final int finalProgress = progress;
                    final int finalAngle = angleDegrees;
                    final int finalMotorAngle = motorAngle;
                    runOnUiThread(() -> {
                        if (seekBarDirection != null) seekBarDirection.setProgress(finalProgress);
                        if (tvAngle != null) tvAngle.setText(String.format(Locale.getDefault(), "Angle : %d° (FPGA : %d)", finalAngle, finalMotorAngle));
                    });
                }

                // --- 2. Gestion de la Vitesse (Limitée à toutes les 350ms) ---
                if (currentTime - lastSpeedUpdateTime > 350) {
                    lastSpeedUpdateTime = currentTime;
                    float xVal = filteredX;
                    if (Math.abs(xVal) < 0.6f) xVal = 0f;
                    int targetSpeed = Math.round(-xVal * (MAX_SPEED / 5.0f));
                    targetSpeed = clampCompat(targetSpeed, MIN_SPEED, MAX_SPEED);

                    if (targetSpeed != currentSpeed) {
                        currentSpeed = targetSpeed;
                        applySpeedChange();
                    }
                }
            }

            @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        if (btnGyro != null) {
            btnGyro.setOnCheckedChangeListener((buttonView, isChecked) -> {
                isGyroEnabled = isChecked;
                if (isChecked && isGamepadEnabled && btnGamepad != null) {
                    btnGamepad.setChecked(false);
                }

                filteredX = 0f;
                filteredY = 0f;

                // Sécurité : si on désactive le mode Gyro, on stoppe net le kart et on centre les roues
                if (!isChecked) {
                    currentSpeed = 0;
                    applySpeedChange();

                    Kart kart = getKart();
                    if (kart != null) {
                        try {
                            kart.setSteeringTargetPosition(STEERING_NEUTRAL_MOTOR);
                        } catch (Exception ignored) {}
                    }
                }

                updateUiElementStates();
            });
        }
    }
    private void setupSpeedControls() {
        if (seekBarSpeed == null) return;

        seekBarSpeed.setMax(30);
        seekBarSpeed.setProgress(15);

        seekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentSpeed = progress - 15;
                applySpeedChange();
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        updateSpeedDisplay();
    }

    private void applySpeedChange() {
        updateSpeedDisplay();
        Kart kart = getKart();
        if (kart != null) kart.setDriveSpeed(currentSpeed);
    }

    private void updateSpeedDisplay() {
        runOnUiThread(() -> {
            if (tvSpeed != null) tvSpeed.setText(String.format(Locale.getDefault(), "Vitesse: %d", currentSpeed));
            if (seekBarSpeed != null && seekBarSpeed.getProgress() != (currentSpeed + 15)) {
                seekBarSpeed.setProgress(currentSpeed + 15);
            }
        });
    }

    private void setupLedControls() {
        if (btnToggleLed != null) {
            btnToggleLed.setOnCheckedChangeListener((buttonView, isChecked) -> {
                isLedsOn = isChecked;
                syncAllLeds();
            });
        }
        if (btnBlink != null) {
            btnBlink.setOnCheckedChangeListener((buttonView, isChecked) -> {
                isBlinking = isChecked;
                syncAllLeds();
            });
        }
    }

    private void syncAllLeds() {
        Kart kart = getKart();
        if (kart == null) return;
        try {
            for (int i = 0; i < 4; i++) {
                if (isLedsOn) {
                    if (isBlinking) kart.blinkLed(i, 100);
                    else kart.setLedState(i, true);
                } else {
                    kart.setLedState(i, false);
                }
            }
        } catch (Exception ignored) {}
    }

    private void updateBatteryUI(Kart kart) {
        if (kart == null) return;
        try {
            double voltage = kart.getBatteryVoltage();
            double level = kart.getBatteryLevel();
            double current = kart.getCurrent();
            int batteryPercent = clampCompat((int) Math.round(level * 100.0), 0, 100);

            runOnUiThread(() -> {
                if (progressBarBattery != null) progressBarBattery.setProgress(batteryPercent);
                if (tvBatteryPercentage != null) tvBatteryPercentage.setText(String.format(Locale.getDefault(), "%d%%", batteryPercent));
                if (tvBatteryVoltage != null) tvBatteryVoltage.setText(String.format(Locale.getDefault(), "%.2f V", voltage));
                if (tvBatteryCurrent != null) tvBatteryCurrent.setText(String.format(Locale.getDefault(), "%.2f A", current));
            });
        } catch (Exception ignored) {}
    }

    private void updateUiElementStates() {
        boolean controlsDisabled = isGyroEnabled || isGamepadEnabled;

        if (seekBarDirection != null) seekBarDirection.setEnabled(!controlsDisabled);
        if (seekBarSpeed != null) seekBarSpeed.setEnabled(!controlsDisabled);

        if (seekBarLed5 != null) seekBarLed5.setEnabled(!controlsDisabled);
        if (seekBarLed6 != null) seekBarLed6.setEnabled(!controlsDisabled);
        if (seekBarLed7 != null) seekBarLed7.setEnabled(!controlsDisabled);
        if (seekBarLed8 != null) seekBarLed8.setEnabled(!controlsDisabled);
    }

    private int clampCompat(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}