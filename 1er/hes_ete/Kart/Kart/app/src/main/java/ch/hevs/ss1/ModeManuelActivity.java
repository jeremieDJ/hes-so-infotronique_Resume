package ch.hevs.ss1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Locale;

import ch.hevs.kart.AbstractKartControlActivity;
import ch.hevs.kart.Kart;
import ch.hevs.kart.KartListener;

public class ModeManuelActivity extends AbstractKartControlActivity implements KartListener {

    private static final int MAX_SPEED = 15;
    private static final int MIN_SPEED = -15;
    private static final float DEADZONE = 0.12f; // Légère augmentation de la zone morte pour éviter les parasites

    // Bornes matérielles de sécurité pour la direction (180 à 250)
    private static final int SERVO_CENTER = 215;    // Centre neutre
    private static final int SERVO_MIN_LIMIT = 180; // Butée physique gauche
    private static final int SERVO_MAX_LIMIT = 250; // Butée physique droite

    private TextView tvGamepadStatus;
    private TextView tvManualSpeed;
    private TextView tvManualAngle;
    private TextView tvLimitsDisplay;
    private Button btnSetLimits;

    // Affichage de la batterie et de la consommation
    private ProgressBar progressBarBattery;
    private TextView tvBatteryPercentage;
    private TextView tvBatteryVoltage;
    private TextView tvBatteryCurrent;

    private int currentSpeed = 0;
    private int currentAngleDegrees = 0; // 0° = Centre
    private int currentServoValue = SERVO_CENTER; // 215 = Centre

    // Maximum d'angle autorisé de chaque côté (par défaut 30°)
    private int maxAngleDegrees = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_manuel);

        tvGamepadStatus = findViewById(R.id.tvGamepadStatus);
        tvManualSpeed = findViewById(R.id.tvManualSpeed);
        tvManualAngle = findViewById(R.id.tvManualAngle);
        tvLimitsDisplay = findViewById(R.id.tvLimitsDisplay);
        btnSetLimits = findViewById(R.id.btnSetLimits);

        // Composants batterie
        progressBarBattery = findViewById(R.id.progressBarBattery);
        tvBatteryPercentage = findViewById(R.id.tvBatteryPercentage);
        tvBatteryVoltage = findViewById(R.id.tvBatteryVoltage);
        tvBatteryCurrent = findViewById(R.id.tvBatteryCurrent);

        if (btnSetLimits != null) {
            btnSetLimits.setOnClickListener(v -> showMaxAngleDialog());
        }

        updateDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Réinitialisation explicite de l'état du moteur
        currentSpeed = 0;

        Kart kart = getKart();
        if (kart != null) {
            kart.addKartListener(this);
            kart.setDriveSpeed(0); // Forcer le déblocage moteur
            updateBatteryUI(kart);
        }

        // Sécurité : recentrer à 215 à l'ouverture de l'écran
        sendSafeSteeringPosition(SERVO_CENTER);
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            Kart kart = getKart();
            if (kart != null) {
                kart.removeKartListener(this);
                kart.setDriveSpeed(0);
            }
        } catch (Exception ignored) {}

        sendSafeSteeringPosition(SERVO_CENTER);
    }

    // =========================================================================
    // IMPLÉMENTATION DE KARTLISTENER (BATTERIE / TENSION / COURANT)
    // =========================================================================
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

    @Override
    public void hallSensorCountChanged(@NonNull Kart kart, int sensorIndex, int count) {}

    @Override
    public void ultrasonicRangerDistanceChanged(@NonNull Kart kart, double distance) {}

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

    // =========================================================================
    // ENVOI SÉCURISÉ DU SERVO
    // =========================================================================
    private void sendSafeSteeringPosition(int rawValue) {
        currentServoValue = Math.max(SERVO_MIN_LIMIT, Math.min(SERVO_MAX_LIMIT, rawValue));

        Kart kart = getKart();
        if (kart != null) {
            kart.setSteeringTargetPosition(currentServoValue);
        }
    }

    // =========================================================================
    // BOÎTE DE DIALOGUE : CONFIGURATION DU BRAQUAGE MAXIMAL
    // =========================================================================
    private void showMaxAngleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Régler le braquage max (en degrés)");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        final EditText etMaxDegrees = new EditText(this);
        etMaxDegrees.setHint("Angle max de chaque côté (ex: 15 ou 30)");
        etMaxDegrees.setInputType(InputType.TYPE_CLASS_NUMBER);
        etMaxDegrees.setText(String.valueOf(maxAngleDegrees));
        layout.addView(etMaxDegrees);

        builder.setView(layout);

        builder.setPositiveButton("Valider", (dialog, which) -> {
            try {
                int inputDegrees = Integer.parseInt(etMaxDegrees.getText().toString());

                if (inputDegrees < 1 || inputDegrees > 30) {
                    Toast.makeText(this, "L'angle doit être entre 1° et 30°", Toast.LENGTH_LONG).show();
                    return;
                }

                maxAngleDegrees = inputDegrees;

                if (Math.abs(currentAngleDegrees) > maxAngleDegrees) {
                    currentAngleDegrees = currentAngleDegrees < 0 ? -maxAngleDegrees : maxAngleDegrees;
                    recalculateServoValue();
                    sendSafeSteeringPosition(currentServoValue);
                }

                Toast.makeText(this, "Braquage max réglé à ±" + maxAngleDegrees + "°", Toast.LENGTH_SHORT).show();
                updateDisplay();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Nombre invalide", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // =========================================================================
    // CAPTURE DES MOUVEMENTS DE LA MANETTE (JOYSTICK & GÂCHETTES)
    // =========================================================================
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK
                && event.getAction() == MotionEvent.ACTION_MOVE) {

            if (tvGamepadStatus != null) {
                tvGamepadStatus.setText("Statut : Manette connectée");
            }

            // --- 1. GESTION DE LA VITESSE (Sécurisée) ---
            float rTrigger = event.getAxisValue(MotionEvent.AXIS_RTRIGGER);
            if (rTrigger == 0f) rTrigger = event.getAxisValue(MotionEvent.AXIS_GAS);

            float lTrigger = event.getAxisValue(MotionEvent.AXIS_LTRIGGER);
            if (lTrigger == 0f) lTrigger = event.getAxisValue(MotionEvent.AXIS_BRAKE);

            float joyY = -event.getAxisValue(MotionEvent.AXIS_Y);

            float rawSpeed = 0f;

            // Priorité claire pour éviter le blocage croisé des axes
            if (rTrigger > DEADZONE && rTrigger >= lTrigger) {
                rawSpeed = rTrigger * MAX_SPEED;
            } else if (lTrigger > DEADZONE) {
                rawSpeed = -lTrigger * Math.abs(MIN_SPEED);
            } else if (Math.abs(joyY) > DEADZONE) {
                rawSpeed = joyY * MAX_SPEED;
            }

            int targetSpeed = Math.round(rawSpeed);

            // Mise à jour si la vitesse a changé
            if (targetSpeed != currentSpeed) {
                currentSpeed = targetSpeed;
                Kart kart = getKart();
                if (kart != null) {
                    kart.setDriveSpeed(currentSpeed);
                }
            }

            // --- 2. GESTION DE LA DIRECTION ---
            float joyX = event.getAxisValue(MotionEvent.AXIS_X);
            if (Math.abs(joyX) < DEADZONE) {
                joyX = 0f;
            }

            int targetDegrees = Math.round(joyX * maxAngleDegrees);

            if (targetDegrees != currentAngleDegrees) {
                currentAngleDegrees = targetDegrees;
                recalculateServoValue();
                sendSafeSteeringPosition(currentServoValue);
            }

            updateDisplay();
            return true;
        }

        return super.onGenericMotionEvent(event);
    }

    private void recalculateServoValue() {
        float normalized = (float) currentAngleDegrees / maxAngleDegrees;

        int targetMotorValue;
        if (normalized <= 0) {
            targetMotorValue = Math.round(SERVO_CENTER - (normalized * (SERVO_MAX_LIMIT - SERVO_CENTER)));
        } else {
            targetMotorValue = Math.round(SERVO_CENTER - (normalized * (SERVO_CENTER - SERVO_MIN_LIMIT)));
        }

        currentServoValue = Math.max(SERVO_MIN_LIMIT, Math.min(SERVO_MAX_LIMIT, targetMotorValue));
    }

    // =========================================================================
    // CAPTURE DES BOUTONS DE LA MANETTE
    // =========================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((event.getSource() & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD) {

            if (keyCode == KeyEvent.KEYCODE_BUTTON_A) {
                currentSpeed = 0;
                currentAngleDegrees = 0;

                Kart kart = getKart();
                if (kart != null) {
                    kart.setDriveSpeed(0);
                }
                sendSafeSteeringPosition(SERVO_CENTER);

                Toast.makeText(this, "ARRÊT D'URGENCE", Toast.LENGTH_SHORT).show();
                updateDisplay();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BUTTON_X) {
                Kart kart = getKart();
                if (kart != null) {
                    kart.blinkLed(0, 200);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void updateDisplay() {
        runOnUiThread(() -> {
            if (tvManualSpeed != null) {
                tvManualSpeed.setText(String.format(Locale.getDefault(), "Vitesse Moteur : %d", currentSpeed));
            }
            if (tvManualAngle != null) {
                String direction = currentAngleDegrees < 0 ? "Gauche" : (currentAngleDegrees > 0 ? "Droite" : "Centre");
                tvManualAngle.setText(String.format(Locale.getDefault(), "Angle Braquage : %d° %s (Servo: %d)", currentAngleDegrees, direction, currentServoValue));
            }
            if (tvLimitsDisplay != null) {
                tvLimitsDisplay.setText(String.format(Locale.getDefault(), "Plage autorisée : %d à %d (Centre: %d)", SERVO_MIN_LIMIT, SERVO_MAX_LIMIT, SERVO_CENTER));
            }
        });
    }

    private int clampCompat(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}