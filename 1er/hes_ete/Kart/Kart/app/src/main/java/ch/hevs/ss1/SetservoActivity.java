package ch.hevs.ss1;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ch.hevs.kart.AbstractKartControlActivity;
import ch.hevs.kart.Kart;
import ch.hevs.kart.KartListener;
import ch.hevs.kart.KartRegisters;

public class SetservoActivity extends AbstractKartControlActivity implements KartListener {

    // Constantes PWM standard (1000 µs à 2000 µs) pour les micros-servos (LED5 à LED8)
    private static final int MIN_PULSE_US = 1000;
    private static final int MAX_PULSE_US = 2000;

    // Vos bornes de sécurité pour le servo de direction du Kart (180 à 250)
    private static final int ABSOLUTE_MIN = 180;
    private static final int ABSOLUTE_MAX = 250;
    private static final int CENTER_DEFAULT = 210;

    // Registres des 4 servos secondaires
    private final int[] SERVO_REGISTERS = {
            KartRegisters.V2.Control.Sensors.LED5.ADDRESS,
            KartRegisters.V2.Control.Sensors.LED6.ADDRESS,
            KartRegisters.V2.Control.Sensors.LED7.ADDRESS,
            KartRegisters.V2.Control.Sensors.LED8.ADDRESS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setservo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupSteeringControls();
        setupAuxiliaryServo(0, R.id.sbS1Center, R.id.sbS1Range, R.id.sbS1Test, R.id.tvS1Center, R.id.tvS1Range, R.id.tvS1Test);
        setupAuxiliaryServo(1, R.id.sbS2Center, R.id.sbS2Range, R.id.sbS2Test, R.id.tvS2Center, R.id.tvS2Range, R.id.tvS2Test);
        setupAuxiliaryServo(2, R.id.sbS3Center, R.id.sbS3Range, R.id.sbS3Test, R.id.tvS3Center, R.id.tvS3Range, R.id.tvS3Test);
        setupAuxiliaryServo(3, R.id.sbS4Center, R.id.sbS4Range, R.id.sbS4Test, R.id.tvS4Center, R.id.tvS4Range, R.id.tvS4Test);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Kart kart = getKart();
        if (kart != null) {
            // S'inscrire aux événements du kart
            kart.addKartListener(this);

            // Envoyer la position centrale sécurisée (210) au démarrage
            sendSafeSteeringPosition(CENTER_DEFAULT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Kart kart = getKart();
        if (kart != null) {
            kart.removeKartListener(this);
        }
    }

    // =========================================================================
    // MÉTHODE D'ENVOI SÉCURISÉE DE LA DIRECTION
    // =========================================================================
    private void sendSafeSteeringPosition(int rawValue) {
        // Bride stricte entre 180 et 250
        int safeValue = Math.max(ABSOLUTE_MIN, Math.min(ABSOLUTE_MAX, rawValue));

        Kart kart = getKart();
        if (kart != null) {
            kart.setSteeringTargetPosition(safeValue);
        }
    }

    // =========================================================================
    // CONFIGURATION DIRECTION
    // =========================================================================
    private void setupSteeringControls() {
        final SeekBar sbCenter = findViewById(R.id.sbSteeringCenter);
        final SeekBar sbTest = findViewById(R.id.sbSteeringTest);

        final TextView tvCenter = findViewById(R.id.tvSteeringCenter);
        final TextView tvMaxAngle = findViewById(R.id.tvSteeringMaxAngle);
        final TextView tvTest = findViewById(R.id.tvSteeringTest);

        if (sbCenter == null || sbTest == null) return;

        sbCenter.setMin(ABSOLUTE_MIN);
        sbCenter.setMax(ABSOLUTE_MAX);
        sbCenter.setProgress(CENTER_DEFAULT);

        sbTest.setMax(200);
        sbTest.setProgress(100);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int centerValue = sbCenter.getProgress();
                int testProgress = sbTest.getProgress();

                float normalized = (testProgress - 100f) / 100f;
                float angleDeg = normalized * 30.0f;

                int targetMotorValue;
                if (normalized <= 0) {
                    targetMotorValue = Math.round(centerValue - (normalized * (ABSOLUTE_MAX - centerValue)));
                } else {
                    targetMotorValue = Math.round(centerValue - (normalized * (centerValue - ABSOLUTE_MIN)));
                }

                sendSafeSteeringPosition(targetMotorValue);

                int currentSafeValue = Math.max(ABSOLUTE_MIN, Math.min(ABSOLUTE_MAX, targetMotorValue));

                if (tvCenter != null) {
                    tvCenter.setText(String.format("Centre : %d", centerValue));
                }
                if (tvMaxAngle != null) {
                    tvMaxAngle.setText(String.format("Plage Sécurisée : %d à %d", ABSOLUTE_MIN, ABSOLUTE_MAX));
                }
                if (tvTest != null) {
                    tvTest.setText(String.format("Angle : %+.1f° (Registre : %d)", angleDeg, currentSafeValue));
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        sbCenter.setOnSeekBarChangeListener(listener);
        sbTest.setOnSeekBarChangeListener(listener);

        listener.onProgressChanged(sbTest, sbTest.getProgress(), false);
    }
    // =========================================================================
    // CONFIGURATION DES SERVOS AUXILIAIRES (LED5 à LED8)
    // =========================================================================
    private void setupAuxiliaryServo(int index, int centerId, int rangeId, int testId, int tvCenterId, int tvRangeId, int tvTestId) {
        SeekBar sbCenter = findViewById(centerId);
        SeekBar sbRange = findViewById(rangeId);
        SeekBar sbTest = findViewById(testId);

        TextView tvCenter = findViewById(tvCenterId);
        TextView tvRange = findViewById(tvRangeId);
        TextView tvTest = findViewById(tvTestId);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int centerPulse = sbCenter.getProgress();
                int rangePulse = sbRange.getProgress();
                int testProgress = sbTest.getProgress();

                float normalized = (testProgress - 100f) / 100f;
                int finalPulse = Math.round(centerPulse + (normalized * rangePulse));

                finalPulse = Math.max(MIN_PULSE_US, Math.min(MAX_PULSE_US, finalPulse));

                tvCenter.setText(String.format("Centre: %d µs", centerPulse));
                tvRange.setText(String.format("Angle Max: ±%d µs", rangePulse));
                tvTest.setText(String.format("Impulsion envoyée: %d µs (%.2f ms)", finalPulse, finalPulse / 1000.0));

                Kart kart = getKart();
                if (kart != null) {
                    kart.setControlRegister(SERVO_REGISTERS[index], finalPulse);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        sbCenter.setOnSeekBarChangeListener(listener);
        sbRange.setOnSeekBarChangeListener(listener);
        sbTest.setOnSeekBarChangeListener(listener);

        listener.onProgressChanged(sbTest, sbTest.getProgress(), false);
    }
}