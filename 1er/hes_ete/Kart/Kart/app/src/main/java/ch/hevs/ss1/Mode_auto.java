package ch.hevs.ss1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.kart.AbstractKartControlActivity;

public class Mode_auto extends AbstractKartControlActivity {

    // --- CONSTANTES PHYSIQUES EN CENTIMÈTRES ---
    private static final double KART_WHEELBASE_CM = 20.0; // Empattement du kart (20 cm)
    private static final double SERVO_GAIN = 150.0;
    private static final double BASE_SPEED_CM_PER_MS = 0.05; // 0.5 m/s = 0.05 cm/ms à vitesse nominale

    public static class PhysicalSegment {
        public enum Type {
            STRAIGHT("Ligne droite"),
            TURN_RIGHT("Virage à droite"),
            TURN_LEFT("Virage à gauche"),
            RAMP("Pente / Pont (13°)");

            private final String label;
            Type(String label) { this.label = label; }
            public String getLabel() { return label; }
        }

        Type type;
        double valueCm; // Distance ou Rayon en centimètres

        public PhysicalSegment(Type type, double valueCm) {
            this.type = type;
            this.valueCm = valueCm;
        }
    }

    public static class TrackSegment {
        String typeLabel;
        int durationMs;
        int speed;
        int angle;

        public TrackSegment(String typeLabel, int durationMs, int speed, int angle) {
            this.typeLabel = typeLabel;
            this.durationMs = durationMs;
            this.speed = speed;
            this.angle = angle;
        }
    }

    private final List<TrackSegment> segmentList = new ArrayList<>();
    private ConstraintLayout mainLayout;
    private Switch switchMirror;
    private EditText etDistance, etRadius;
    private Spinner spinnerType;
    private LinearLayout layoutSegmentsList;
    private Button btnAddSegment, btnStartRace, btnLoadPreset;
    private boolean isMirrorMode = false;
    private boolean isRaceRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme);

        mainLayout = findViewById(R.id.mainLayout);
        switchMirror = findViewById(R.id.switchMirror);
        etDistance = findViewById(R.id.etDistance);
        etRadius = findViewById(R.id.etRadius);
        spinnerType = findViewById(R.id.spinnerType);
        layoutSegmentsList = findViewById(R.id.layoutSegmentsList);
        btnAddSegment = findViewById(R.id.btnAddSegment);
        btnStartRace = findViewById(R.id.btnStartRace);
        btnLoadPreset = findViewById(R.id.btnLoadPreset);

        // Chargement du Spinner
        String[] types = {
                PhysicalSegment.Type.STRAIGHT.getLabel(),
                PhysicalSegment.Type.TURN_RIGHT.getLabel(),
                PhysicalSegment.Type.TURN_LEFT.getLabel(),
                PhysicalSegment.Type.RAMP.getLabel()
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(adapter);

        // Mode Miroir (Inversion visuelle du tracé)
        switchMirror.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isMirrorMode = isChecked;
            if (isChecked) {
                mainLayout.setScaleY(-1f);
                switchMirror.setScaleY(-1f);
            } else {
                mainLayout.setScaleY(1f);
                switchMirror.setScaleY(1f);
            }
        });

        btnLoadPreset.setOnClickListener(v -> load14PhysicalStepsCm());
        btnAddSegment.setOnClickListener(v -> addSegmentFromInput());
        btnStartRace.setOnClickListener(v -> startAutonomousRace());
    }

    // --- CONVERSION CINÉMATIQUE (cm -> Vitesse & Servo) ---
    private TrackSegment convertPhysicalToMotor(PhysicalSegment phys) {
        int angle = 127; // Centré
        int speed = 10;  // Vitesse par défaut basée sur l'échelle de votre API (ex: 0 à 15)
        int durationMs = 1000;

        switch (phys.type) {
            case STRAIGHT:
                angle = 127;
                speed = phys.valueCm > 80.0 ? 15 : 10;
                double speedCmMs = BASE_SPEED_CM_PER_MS * (speed / 10.0);
                durationMs = (int) (phys.valueCm / speedCmMs);
                break;

            case TURN_RIGHT:
            case TURN_LEFT:
                // Géométrie d'Ackermann en cm : delta = atan(L / R)
                double deltaRad = Math.atan(KART_WHEELBASE_CM / phys.valueCm);
                int angleOffset = (int) Math.toDegrees(deltaRad * SERVO_GAIN);

                if (phys.type == PhysicalSegment.Type.TURN_RIGHT) {
                    angle = Math.min(255, 127 + angleOffset);
                } else {
                    angle = Math.max(0, 127 - angleOffset);
                }

                speed = Math.min(12, (int) (6 + (phys.valueCm * 0.05)));

                // Longueur arc 90° = (PI / 2) * R
                double arcLengthCm = (Math.PI / 2.0) * phys.valueCm;
                double turnSpeedCmMs = BASE_SPEED_CM_PER_MS * (speed / 10.0);
                durationMs = (int) (arcLengthCm / turnSpeedCmMs);
                break;

            case RAMP:
                angle = 127;
                speed = 15; // Puissance maximale pour monter la rampe 13°
                durationMs = (int) (phys.valueCm / (BASE_SPEED_CM_PER_MS * 1.4));
                break;
        }

        return new TrackSegment(phys.type.getLabel(), durationMs, speed, angle);
    }

    private void addSegmentFromInput() {
        int selectedTypePos = spinnerType.getSelectedItemPosition();
        String strDist = etDistance.getText().toString();
        String strRadius = etRadius.getText().toString();

        PhysicalSegment.Type selectedType;
        double valueCm = 0;

        switch (selectedTypePos) {
            case 0:
                selectedType = PhysicalSegment.Type.STRAIGHT;
                if (strDist.isEmpty()) { Toast.makeText(this, "Entrez la distance en cm", Toast.LENGTH_SHORT).show(); return; }
                valueCm = Double.parseDouble(strDist);
                break;
            case 1:
                selectedType = PhysicalSegment.Type.TURN_RIGHT;
                if (strRadius.isEmpty()) { Toast.makeText(this, "Entrez le rayon en cm", Toast.LENGTH_SHORT).show(); return; }
                valueCm = Double.parseDouble(strRadius);
                break;
            case 2:
                selectedType = PhysicalSegment.Type.TURN_LEFT;
                if (strRadius.isEmpty()) { Toast.makeText(this, "Entrez le rayon en cm", Toast.LENGTH_SHORT).show(); return; }
                valueCm = Double.parseDouble(strRadius);
                break;
            default:
                selectedType = PhysicalSegment.Type.RAMP;
                if (strDist.isEmpty()) { Toast.makeText(this, "Entrez la distance en cm", Toast.LENGTH_SHORT).show(); return; }
                valueCm = Double.parseDouble(strDist);
                break;
        }

        PhysicalSegment phys = new PhysicalSegment(selectedType, valueCm);
        TrackSegment motorSeg = convertPhysicalToMotor(phys);
        addSegmentToList(motorSeg);

        etDistance.setText("");
        etRadius.setText("");
    }

    private void load14PhysicalStepsCm() {
        segmentList.clear();
        layoutSegmentsList.removeAllViews();

        List<PhysicalSegment> physicalTrack = new ArrayList<>();
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 80.0));   // 80 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_RIGHT, 55.0)); // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 40.0));   // 40 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_LEFT, 55.0));  // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 60.0));   // 60 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_RIGHT, 80.0)); // Rayon 80 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 50.0));   // 50 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_LEFT, 55.0));  // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 40.0));   // 40 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_RIGHT, 55.0)); // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 30.0));   // 30 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_LEFT, 55.0));  // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.RAMP, 120.0));      // Pont 120 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.TURN_RIGHT, 55.0)); // Rayon 55 cm
        physicalTrack.add(new PhysicalSegment(PhysicalSegment.Type.STRAIGHT, 100.0));  // 100 cm

        for (PhysicalSegment phys : physicalTrack) {
            TrackSegment motorSeg = convertPhysicalToMotor(phys);
            addSegmentToList(motorSeg);
        }

        Toast.makeText(this, "14 étapes chargées !", Toast.LENGTH_SHORT).show();
    }

    private void addSegmentToList(TrackSegment segment) {
        segmentList.add(segment);
        TextView tvStep = new TextView(this);
        tvStep.setText("Étape " + segmentList.size() + " [" + segment.typeLabel + "] : "
                + segment.durationMs + "ms | Vitesse: " + segment.speed + " | Angle Servo: " + segment.angle);
        tvStep.setTextColor(getResources().getColor(android.R.color.white));
        tvStep.setTextSize(14);
        tvStep.setPadding(0, 6, 0, 6);
        layoutSegmentsList.addView(tvStep);
    }

    // --- EXÉCUTION DU PILOTAGE SUR LE KART PHYSIQUE ---
    private void startAutonomousRace() {
        if (segmentList.isEmpty()) {
            Toast.makeText(this, "Aucune étape à exécuter", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isRaceRunning) return;
        isRaceRunning = true;

        Handler handler = new Handler(Looper.getMainLooper());
        long accumulatedDelay = 0;

        for (int i = 0; i < segmentList.size(); i++) {
            TrackSegment segment = segmentList.get(i);
            int stepIndex = i + 1;

            handler.postDelayed(() -> {
                int targetAngle = segment.angle;

                // Inversion de l'angle du servomoteur si le mode miroir est activé
                if (isMirrorMode) {
                    targetAngle = 255 - targetAngle;
                }

                // Pilotage effectif des moteurs via l'objet 'kart' hérité d'AbstractKartControlActivity
                if (kart != null) {
                    kart.setSteeringTargetPosition(targetAngle); // Direction (0 à 255)
                    kart.setDriveSpeed(segment.speed);           // Propulsion moteur
                }

                Toast.makeText(Mode_auto.this, "Étape " + stepIndex + ": " + segment.typeLabel, Toast.LENGTH_SHORT).show();
            }, accumulatedDelay);

            accumulatedDelay += segment.durationMs;
        }

        // Arrêt automatique en fin de parcours
        handler.postDelayed(() -> {
            if (kart != null) {
                kart.setDriveSpeed(0);            // Arrêt moteur
                kart.setSteeringTargetPosition(127); // Volant au centre
            }
            isRaceRunning = false;
            Toast.makeText(Mode_auto.this, "Course terminée !", Toast.LENGTH_LONG).show();
        }, accumulatedDelay);
    }
}