import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path

try:
    script_dir = Path(__file__).resolve().parent
except NameError:
    script_dir = Path.cwd()

# --- Paramètres du montage et de la sonde (donnés dans l'énoncé) ---
Vcc     = 5.0        # V
R_fixe  = 10_000.0   # ohm
R0      = 10_000.0   # ohm, résistance NTC à T0
T0_C    = 25.0        # °C
T0_K    = T0_C + 273.15
B       = 3950.0     # K

def tension_vers_resistance(Vout, Vcc, Rfixe):
    """Inverse la formule du pont diviseur : renvoie R_ntc (ohm)."""
    # TODO 1
    ...

def resistance_vers_temperature(R, R0, T0_K, B):
    """Inverse la loi Beta : renvoie la température en °C."""
    # TODO 2
    ...

# --- Chargement des mesures -----------------------------------------
t, V_out = np.loadtxt(
    script_dir / "data/sonde_ntc_bouilloire.csv", delimiter=",", skiprows=1, unpack=True
)

# TODO 3 : tracer V_out(t) (temps en minutes, tension en V)

# TODO 4 : R_ntc = tension_vers_resistance(V_out, Vcc, R_fixe)
#          T_C   = resistance_vers_temperature(R_ntc, R0, T0_K, B)

# TODO 5 : tracer T_C(t) (temps en minutes, température en °C)
#          rechercher le maximum avec np.argmax / np.max
