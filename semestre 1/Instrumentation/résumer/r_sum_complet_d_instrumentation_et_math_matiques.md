# Master Class & Manuel de Révision : Instrumentation, Mathématiques & Python

Ce document constitue la synthèse intégrale du cours d'instrumentation. Il combine la théorie des capteurs, la métrologie, la pratique du traitement de données sous Python, ainsi que les développements et démonstrations mathématiques exhaustifs.

---

## TABLE DES MATIÈRES
1. [La Chaîne de Mesure et Signaux](#1-la-chaîne-de-mesure-et-signaux)
2. [Système d'Unités et Méthodes de Mesure](#2-système-dunités-et-méthodes-de-mesure)
3. [Caractéristiques Statiques et Dynamiques](#3-caractéristiques-statiques-et-dynamiques)
4. [Métrologie, Erreurs et Qualité de Mesure](#4-métrologie-erreurs-et-qualité-de-mesure)
5. [Développements Mathématiques Approfondis & Liste des Cas](#5-développements-mathématiques-approfondis--liste-des-cas)
   - 5.1 [Propagation des Incertitudes (Modèles et Liste Exhaustive)](#51-propagation-des-incertitudes-modèles-et-liste-exhaustive)
   - 5.2 [Méthodes Mathématiques de Linéarisation](#52-méthodes-mathématiques-de-linéarisation)
   - 5.3 [Inversion Analytique Complète d'une Chaîne NTC](#53-inversion-analytique-complète-dune-chaîne-ntc)
   - 5.4 [Modélisation du Bruit Thermique (Johnson-Nyquist)](#54-modélisation-du-bruit-thermique-johnson-nyquist)
   - 5.5 [Mathématiques des ADC et Quantification](#55-mathématiques-des-adc-et-quantification)
6. [Guide Pratique Python pour l'Instrumentation](#6-guide-pratique-python-pour-linstrumentation)

---

## 1. La Chaîne de Mesure et Signaux

Un système de mesure convertit une grandeur physique inconnue (mesurande) en un résultat exploitable.

```
+---------------+    +---------+    +---------------+    +-----------+    +------------------------+
| Grandeur Phys.| -> | Capteur | -> | Mise en forme | -> | Interface | -> | Sortie (Afficheur,     |
|  (Mesurande)  |    +---------+    | (Amp, Filt, ADC)|   +-----------+    |  Logger, Processus)    |
+---------------+                   +---------------+                     +------------------------+
```

### Rôle des étages
1. **Mesurande ($x$) :** Propriété physique à déterminer (ex: température, pression, force).
2. **Capteur :** Transducteur qui convertit la mesurande en une grandeur physique secondaire (souvent électrique : tension $V$, courant $I$, résistance $R$, capacité $C$).
3. **Mise en forme :**
   - **Amplification :** Adapte l'amplitude du signal faible aux gammes des convertisseurs (ex: amplificateur d'instrumentation pour pont de jauges).
   - **Filtrage :** Élimine le bruit et les fréquences indésirables (filtre passe-bas).
   - **Conversion Analogique-Numérique (ADC) :** Numérise le signal continu.
4. **Interface & Exploitation :** Traitement informatique (microcontrôleur/ordinateur) pour la correction, le stockage (Logger) ou la régulation.

### Structure réelle d'un signal
Un signal mesuré $s(t)$ est la combinaison de plusieurs composantes :
$$s(t) = s_{\text{utile}}(t) + \text{Offset} + \text{Dérive}(t) + \text{Bruit}(t)$$
- **Offset (DC) :** Décalage constant au zéro.
- **Dérive (Harmonique/Lente) :** Fluctuations d'origine thermique, vieillissement des composants.
- **Bruit :** Fluctuations haute fréquence (bruit blanc, interférences électromagnétiques).

---

## 2. Système d'Unités et Méthodes de Mesure

### Les 7 Unités de Base du Système International (SI)
Toute grandeur dérivée s'exprime en fonction de ces 7 unités fondamentales :
- **Mètre [m]** : Longueur
- **Kilogramme [kg]** : Masse *(défini via la constante de Planck $h = 6.62607015 \cdot 10^{-34} \text{ J}\cdot\text{s}$)*
- **Seconde [s]** : Temps
- **Ampère [A]** : Courant électrique
- **Kelvin [K]** : Température thermodynamique ($T[\text{K}] = T[^\circ\text{C}] + 273.15$)
- **Mole [mol]** : Quantité de matière
- **Candela [cd]** : Intensité lumineuse

*Exemple de grandeur dérivée :* 
$$\text{Force [N]} = [\text{kg} \cdot \text{m} \cdot \text{s}^{-2}]$$
$$\text{Pression [Pa]} = \left[\frac{\text{N}}{\text{m}^2}\right] = [\text{kg} \cdot \text{m}^{-1} \cdot \text{s}^{-2}]$$

### Mesure Directe vs Indirecte
- **Mesure Directe :** Comparaison immédiate avec un étalon de même nature (ex: règle graduée pour une longueur, balance à fléau).
- **Mesure Indirecte :** Déduction de la valeur par mesure d'une autre grandeur physique liée par une loi de comportement (ex: calcul du niveau d'eau $h$ via la pression hydrostatique $\Delta p = \rho g h$). *La quasi-totalité des capteurs électroniques sont indirects.*

---

## 3. Caractéristiques Statiques et Dynamiques

### Caractéristiques Statiques
Valables lorsque le mesurande est en régime permanent (stabilisé).

- **Sensibilité ($S_0$) :** Pente de la caractéristique $y = f(x)$ :
  $$S_0 = \frac{dy}{dx}$$
  *(Unité : [Unité de sortie] / [Unité d'entrée], ex: $\text{mV/bar}$, $\Omega/^\circ\text{C}$)*.
- **Étendue de mesure (Full Scale Input, $x_{\text{FS}}$) :** $x_{\text{FS}} = x_{\text{max}} - x_{\text{min}}$.
- **Sortie Pleine Échelle (Full Scale Output, $y_{\text{FS}}$) :** $y_{\text{FS}} = y(x_{\text{max}}) - y(x_{\text{min}})$.
- **Résolution :** Plus petite variation de la mesurande capable de produire un changement détectable du signal de sortie.

### Caractéristiques Dynamiques
Valables lorsque le mesurande varie au cours du temps.

- **Réponse du $1^{\text{er}}$ ordre :** Définie par la constante de temps $\tau$.
  $$y(t) = y_{\text{final}} \cdot (1 - e^{-t/\tau})$$
  - À $t = \tau$ : $63.2\%$ de la valeur finale.
  - À $t = 3\tau$ : $95\%$ de la valeur finale.
  - À $t = 4.6\tau$ : $99\%$ de la valeur finale.
- **Réponse du $2^{\text{e}}$ ordre :** Définie par la pulsation propre $\omega_0$ et le facteur d'amortissement $\xi$. Présente un risque de dépassement oscillatoire si $\xi < 1$.
- **Bande Passante ($\Delta f$) :** Plage de fréquences $[\text{Hz}]$ dans laquelle le gain du capteur reste constant (généralement à $-3\text{ dB}$).
  $$\text{Règle de conception : } f_{\text{bande passante, capteur}} \gg f_{\text{max, signal}}$$

---

## 4. Métrologie, Erreurs et Qualité de Mesure

### Fidélité, Justesse, Exactitude
- **Fidélité (Précision de répétabilité) :** Étroitesse de l'accord entre des mesures répétées dans les mêmes conditions (faible variance).
- **Justesse :** Étroitesse de l'accord entre la moyenne des mesures et la valeur vraie de référence (absence d'erreur systématique).
- **Exactitude (Précision globale) :** Qualité d'un instrument à la fois **juste et fidèle**.

### Sources d'erreurs et imperfections
- **Erreurs aléatoires :** Fluctuations imprévisibles (bruit thermique, vibrations). Réduites par la moyenne statistique.
- **Erreurs systématiques :** Biais reproductibles (erreur de calibrage, décalage d'offset, erreur de gain). Correctibles par étalonnage.
- **Hystérèse :** Écart maximal observée en un même point selon que la grandeur est atteinte par valeurs croissantes ou décroissantes.
- **Non-linéarité ($NL_{\text{max}}$) :** Écart maximal par rapport à une droite de référence, exprimé en $\% \text{FS}$ :
  $$NL_{\text{max}} = \frac{|y_{\text{réel}} - y_{\text{droite}}|_{\text{max}}}{y_{\text{FS}}} \cdot 100\%$$

---

## 5. Développements Mathématiques Approfondis & Liste des Cas

### 5.1 Propagation des Incertitudes (Modèles et Liste Exhaustive)

Soit une grandeur de sortie $Y = f(X_1, X_2, \dots, X_N)$ dépendant de $N$ variables d'entrée entachées d'incertitudes.

#### Développement de Taylor au $1^{\text{er}}$ ordre :
$$Y \approx f(X_{1,0}, \dots, X_{N,0}) + \sum_{i=1}^{N} S_i \cdot \Delta X_i \quad \text{avec } S_i = \left. \frac{\partial f}{\partial X_i} \right|_{0}$$

#### Formules générales de propagation :
- **Pire des cas (Somme absolue) :**
  $$E_Y = \sum_{i=1}^{N} \left| \frac{\partial f}{\partial X_i} \right| \cdot E_{X_i}$$
- **Quadratique / Décorrélée (Incertitude-type combinée) :**
  $$\sigma_Y = \sqrt{\sum_{i=1}^{N} \left( \frac{\partial f}{\partial X_i} \cdot \sigma_{X_i} \right)^2}$$

---

#### LISTE EXHAUSTIVE DES CAS DE PROPAGATION

| Cas Mathématique         | Fonction $Y = f(A, B)$ | Erreur Absolue $E_Y$ (Pire des cas) | Erreur Relative $\epsilon_Y = \frac{E_Y}{Y}$ | Incertitude-type $\sigma_Y$ (Quadratique) |        |                                     |                           |                                                                                  |                      |                   |
| :----------------------- | :--------------------- | :---------------------------------- | :------------------------------------------- | :---------------------------------------- | ------ | ----------------------------------- | ------------------------- | -------------------------------------------------------------------------------- | -------------------- | ----------------- |
| **Somme**                | $Y = A + B$            | $E_A + E_B$                         | $\frac{E_A + E_B}{A + B}$                    | $\sqrt{\sigma_A^2 + \sigma_B^2}$          |        |                                     |                           |                                                                                  |                      |                   |
| **Soustraction**         | $Y = A - B$            | $E_A + E_B$                         | $\frac{E_A + E_B}{A - B}$                    | $\sqrt{\sigma_A^2 + \sigma_B^2}$          |        |                                     |                           |                                                                                  |                      |                   |
| **Produit**              | $Y = A \cdot B$        | $                                   | B                                            | E_A +                                     | A      | E_B$                                | $\epsilon_A + \epsilon_B$ | $Y \sqrt{\left(\frac{\sigma_A}{A}\right)^2 + \left(\frac{\sigma_B}{B}\right)^2}$ |                      |                   |
| **Quotient**             | $Y = \frac{A}{B}$      | $\frac{                             | B                                            | E_A +                                     | A      | E_B}{B^2}$                          | $\epsilon_A + \epsilon_B$ | $Y \sqrt{\left(\frac{\sigma_A}{A}\right)^2 + \left(\frac{\sigma_B}{B}\right)^2}$ |                      |                   |
| **Puissance**            | $Y = A^n$              | $                                   | n                                            | A^{n-1} E_A$                              | $      | n                                   | \epsilon_A$               | $                                                                                | n                    | A^{n-1} \sigma_A$ |
| **Logarithme**           | $Y = \ln(A)$           | $\frac{E_A}{A} = \epsilon_A$        | $\frac{E_A}{A \ln(A)}$                       | $\frac{\sigma_A}{A}$                      |        |                                     |                           |                                                                                  |                      |                   |
| **Exponentielle**        | $Y = e^{a A}$          | $                                   | a                                            | e^{a A} E_A$                              | $      | a                                   | E_A$                      | $                                                                                | a                    | e^{a A} \sigma_A$ |
| **Général Monovariable** | $Y = f(A)$             | $\left                              | \frac{df}{dA} \right                         | E_A$                                      | $\left | \frac{A}{f(A)} \frac{df}{dA} \right | \epsilon_A$               | $\left                                                                           | \frac{df}{dA} \right | \sigma_A$         |
|                          |                        |                                     |                                              |                                           |        |                                     |                           |                                                                                  |                      |                   |

---

### 5.2 Méthodes Mathématiques de Linéarisation

#### Cas 1 : Tangente locale (Série de Taylor en $x_0$)
$$y_{\text{lin}}(x) = f(x_0) + f'(x_0) \cdot (x - x_0)$$
*Erreur d'approximation :* $E(x) \approx \frac{1}{2} |f''(x_0)| (x - x_0)^2$.

#### Cas 2 : Points extrêmes (Secante sur $[x_{\min}, x_{\max}]$)
$$a = \frac{f(x_{\max}) - f(x_{\min})}{x_{\max} - x_{\min}}, \quad b = f(x_{\min}) - a \cdot x_{\min}$$
$$y_{\text{lin}}(x) = a \cdot x + b$$

#### Cas 3 : Troncature polynomiale
Pour $R(T) = R_0(1 + \alpha T + \beta T^2)$, la linéarisation par négligence du terme d'ordre 2 donne :
$$R_{\text{lin}}(T) = R_0(1 + \alpha T) \quad \text{avec une erreur absolue } E(T) = R_0 \beta T^2$$

#### Cas 4 : Moindres Carrés (Régression Linéaire)
Pour un ensemble de $N$ points $(x_i, y_i)$, la droite $y = a x + b$ est obtenue par :
$$a = \frac{N \sum (x_i y_i) - (\sum x_i)(\sum y_i)}{N \sum x_i^2 - (\sum x_i)^2}$$
$$b = \frac{(\sum y_i)(\sum x_i^2) - (\sum x_i)(\sum x_i y_i)}{N \sum x_i^2 - (\sum x_i)^2}$$

#### Cas 5 : Régression Polynomiale et $R^2$
$$y = w_0 + w_1 x + w_2 x^2 + \dots + w_d x^d$$
$$R^2 = 1 - \frac{S_{\text{résidus}}}{S_{\text{totale}}} = 1 - \frac{\sum_{i=1}^N (y_i - \hat{y}_i)^2}{\sum_{i=1}^N (y_i - \bar{y})^2}$$

---

### 5.3 Inversion Analytique Complète d'une Chaîne NTC

```
 [ EAU ]                [ CAPTEUR NTC ]               [ PONT DIVISEUR ]
  Température T  --->  Résistance R_ntc(T)  --->  Tension Mesurée V_out
```

#### Équations Directes
1. **Montage Pont Diviseur ($R_{\text{ntc}}$ vers le bas, $R_{\text{pull}}$ en haut) :**
   $$V_{\text{out}} = V_{cc} \cdot \frac{R_{\text{ntc}}(T)}{R_{\text{pull}} + R_{\text{ntc}}(T)}$$
2. **Loi Beta de la NTC :**
   $$R_{\text{ntc}}(T) = R_0 \cdot \exp\left( B \left( \frac{1}{T} - \frac{1}{T_0} \right) \right) \quad (T, T_0 \text{ en Kelvin})$$

#### DÉMONSTRATION DE L'INVERSION

**Étape 1 : Calcul de $R_{\text{ntc}}$ à partir de $V_{\text{out}}$**
$$V_{\text{out}} (R_{\text{pull}} + R_{\text{ntc}}) = V_{cc} R_{\text{ntc}}$$
$$V_{\text{out}} R_{\text{pull}} = V_{cc} R_{\text{ntc}} - V_{\text{out}} R_{\text{ntc}} = R_{\text{ntc}} (V_{cc} - V_{\text{out}})$$
$$\mathbf{R_{\text{ntc}} = \frac{R_{\text{pull}} \cdot V_{\text{out}}}{V_{cc} - V_{\text{out}}}}$$

**Étape 2 : Calcul de $T$ à partir de $R_{\text{ntc}}$**
$$\frac{R_{\text{ntc}}}{R_0} = \exp\left( B \left( \frac{1}{T} - \frac{1}{T_0} \right) \right)$$
$$\ln\left( \frac{R_{\text{ntc}}}{R_0} \right) = B \left( \frac{1}{T} - \frac{1}{T_0} \right)$$
$$\frac{1}{T} = \frac{1}{T_0} + \frac{1}{B} \ln\left( \frac{R_{\text{ntc}}}{R_0} \right)$$
$$\mathbf{T[\text{K}] = \left[ \frac{1}{T_0} + \frac{1}{B} \ln\left( \frac{R_{\text{ntc}}}{R_0} \right) \right]^{-1}}$$
$$\mathbf{T[^\circ\text{C}] = T[\text{K}] - 273.15}$$

#### DÉMONSTRATION DE LA SENSIBILITÉ GLOBALE $S(T) = \frac{dV_{\text{out}}}{dT}$
En appliquant la règle de dérivation en chaîne :
$$S(T) = \frac{dV_{\text{out}}}{dR_{\text{ntc}}} \cdot \frac{dR_{\text{ntc}}}{dT}$$

1. Dérivée du pont diviseur :
   $$\frac{dV_{\text{out}}}{dR_{\text{ntc}}} = \frac{d}{dR_{\text{ntc}}} \left[ V_{cc} R_{\text{ntc}} (R_{\text{pull}} + R_{\text{ntc}})^{-1} \right] = \frac{V_{cc} R_{\text{pull}}}{(R_{\text{pull}} + R_{\text{ntc}})^2}$$
2. Dérivée de la NTC :
   $$\frac{dR_{\text{ntc}}}{dT} = R_{\text{ntc}}(T) \cdot \frac{d}{dT} \left[ B \left( T^{-1} - T_0^{-1} \right) \right] = -\frac{B}{T^2} R_{\text{ntc}}(T)$$
3. Produit final :
   $$\mathbf{S(T) = -\frac{V_{cc} \cdot R_{\text{pull}} \cdot B \cdot R_{\text{ntc}}(T)}{T^2 \cdot (R_{\text{pull}} + R_{\text{ntc}}(T))^2}}$$

---

### 5.4 Modélisation du Bruit Thermique (Johnson-Nyquist)

#### Formule de base
Toute résistance $R$ génère une tension de bruit aléatoire RMS due à l'agitation thermique :
$$e_n = \sqrt{4 k_B T R \Delta f}$$
- $k_B = 1.381 \cdot 10^{-23} \text{ J/K}$ (Constante de Boltzmann)
- $T$ : Température en Kelvin [K]
- $R$ : Résistance [$\Omega$]
- $\Delta f$ : Bande passante de mesure [Hz]

#### Bruit au point milieu d'un pont diviseur
Pour deux résistances $R_{\text{pull}}$ et $R_{\text{ntc}}$, la résistance équivalente de Thévenin vue par la sortie est :
$$R_{\text{eq}} = R_{\text{pull}} \parallel R_{\text{ntc}} = \frac{R_{\text{pull}} \cdot R_{\text{ntc}}}{R_{\text{pull}} + R_{\text{ntc}}}$$
Le bruit de tension résultant en sortie vaut :
$$e_{n,\text{out}} = \sqrt{4 k_B T R_{\text{eq}} \Delta f}$$

#### Conversion Bruit de Tension $\to$ Incertitude sur la Température
$$\sigma_T(T) = \frac{e_{n,\text{out}}(T)}{|S(T)|}$$

---

### 5.5 Mathématiques des ADC et Quantification

Un convertisseur ADC numérise une tension continue sur $N$ bits.

- **Nombre de pas (codes possibles) :** $2^N$
- **Résolution / LSB (Quantum $q$) :**
  $$q = \text{1 LSB} = \frac{V_{\text{ref}}}{2^N}$$
- **Erreur de quantification maximale :**
  $$E_{\text{quant}} = \pm \frac{q}{2} = \pm \frac{V_{\text{ref}}}{2^{N+1}}$$
- **Incertitude-type de quantification (Loi uniforme de largeur $q$) :**
  $$\sigma_{\text{quant}} = \frac{q}{\sqrt{12}} = \frac{V_{\text{ref}}}{2^N \sqrt{12}}$$

---

## 6. Guide Pratique Python pour l'Instrumentation

### Configuration de l'environnement avec `uv`
```bash
# Initialiser et configurer le projet
uv init tp_instrumentation
cd tp_instrumentation
uv add numpy matplotlib pandas

# Exécuter un script avec l'environnement virtuel synchronisé
uv run python script.py
```

### Chemins de fichiers universels avec `pathlib`
```python
from pathlib import Path
import pandas as pd

# Trouver automatiquement le dossier du script
script_dir = Path(__file__).resolve().parent
chemin_data = script_dir / "../data/mesures.csv"

# Charger les données avec Pandas
df = pd.read_csv(chemin_data)
t = df["t_s"].to_numpy()
V_out = df["V_pont_V"].to_numpy()
```

### Inversion complète NTC sous NumPy (Vectorisée)
```python
import numpy as np

# Paramètres
Vcc = 5.0
Rpull = 10000.0
R0 = 10000.0
T0_C = 25.0
T0_K = T0_C + 273.15
B = 3950.0


def corriger_ntc(V_mesure):
  # 1. Tension -> Resistance
  R_ntc = (Rpull * V_mesure) / (Vcc - V_mesure)

  # 2. Resistance -> Température (Kelvin -> Celsius)
  inv_T = (1.0 / T0_K) + (1.0 / B) * np.log(R_ntc / R0)
  T_K = 1.0 / inv_T
  return T_K - 273.15


# Application vectorielle instantanée sur tout le tableau de mesures
T_celsius = corriger_ntc(V_out)
```

### Tracé Graphique selon les Règles de l'Art (Matplotlib)
```python
import matplotlib.pyplot as plt

fig, (ax1, ax2) = plt.subplots(2, 1, figsize=(9, 6), sharex=True)

# Graphique brut
ax1.plot(t / 60.0, V_out, color="tab:orange", label="Signal brut $V_{out}$")
ax1.set_ylabel("Tension [V]")
ax1.grid(True, alpha=0.3)
ax1.legend(loc="upper right")
ax1.set_title("Acquisition de la Sonde NTC")

# Graphique corrigé
ax2.plot(
    t / 60.0, T_celsius, color="tab:blue", label="Température calculée $T$"
)
ax2.set_xlabel("Temps [min]")
ax2.set_ylabel("Température [°C]")
ax2.grid(True, alpha=0.3)
ax2.legend(loc="lower right")

plt.tight_layout()
fig.savefig(script_dir / "../img/resultat_ntc.png", dpi=150, bbox_inches="tight")
plt.show()
```