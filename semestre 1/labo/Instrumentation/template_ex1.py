import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path

try:
    script_dir = Path(__file__).resolve().parent
except NameError:
    script_dir = Path.cwd()

# --- 1. Étalonnage -------------------------------------------------
X_ref, V_etal = np.loadtxt(
    script_dir / "data/etalonnage_capteur.csv", delimiter=",", skiprows=1, unpack=True
)

# TODO 1 : tracer V_etal en fonction de X_ref (nuage de points)

# --- 2. Identification de a et b (méthode des 2 points extrêmes) ---
# TODO 2 : Trouvez un moyen d'identifier les paramètres de la droite

# TODO 2 (suite) : superposer la droite obtenue au nuage de points

# --- 3 & 4. Correction du signal d'acquisition ----------------------
t, V_acq = np.loadtxt(
    script_dir / "data/acquisition_capteur.csv", delimiter=",", skiprows=1, unpack=True
)

# TODO 3 : Calculez la valeur de pression à partir de la tension

# --- 5. Représentation sur une figure
# TODO 4 : Tracez une figure

# --- 6. Comparaison au datasheet ------------------------------------
a_datasheet, b_datasheet = 0.500, 0.000
# TODO 5 : afficher/comparer a_hat, b_hat vs a_datasheet, b_datasheet
```