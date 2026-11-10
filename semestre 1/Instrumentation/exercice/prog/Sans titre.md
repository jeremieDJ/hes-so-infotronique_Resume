
Extrait de code

````
# Résumé Complet pour l'Examen : Python, NumPy, Matplotlib & Instrumentation

Ce document synthétise l'intégralité du cours d'introduction à Python pour l'instrumentation (environnements, manipulation de données, graphiques scientifiques, correction de capteurs affines et non linéaires comme la sonde NTC).

---

## 1. Environnement et Bonnes Pratiques (`uv` & `pathlib`)

### Gestion des paquets avec `uv`
`uv` remplace avantageusement la combinaison traditionnelle `pip` + `venv` : il est extrêmement rapide et garantit la reproductibilité via un fichier de verrouillage (`uv.lock`).

* **Initialisation d'un projet :**
  
```bash
  uv init mon_projet
  cd mon_projet
  
````

- **Ajout des bibliothèques scientifiques principales :**
    
      
    

Bash

```
  uv add numpy matplotlib pandas
  
```

- **Exécution d'un script dans l'environnement virtuel :**
    
      
    

Bash

```
  uv run python mon_script.py
  
```

### Gestion robuste des chemins (`pathlib`)

Pour s'assurer que les fichiers de données (`.csv`) sont toujours trouvés, peu importe d'où l'on exécute le script, on utilise la bibliothèque standard `pathlib` :

  

Python

```
from pathlib import Path

# Répertoire du script en cours d'exécution
script_dir = Path(__file__).resolve().parent

# Chemin relatif vers le dossier data situé à côté
chemin_fichier = script_dir / "../data/mesures.csv"
```

## 2. Analyse Numérique avec NumPy

NumPy est le standard pour manipuler des tableaux de données numériques (`ndarray`) de manière performante grâce à la **vectorisation** (pas de boucles `for` lentes).

  

### Création de tableaux

- À partir d'une liste : `x = np.array([0.0, 1.0, 2.0])`
    
      
    
- Tableaux de zéros ou de uns : `np.zeros(5)`, `np.ones(5)`
    
      
    
- Suites arithmétiques :
    
      
    - `np.arange(0, 10, 2)` $\rightarrow$ `[0, 2, 4, 6, 8]` _(on fixe le pas, borne finale exclue)_.
        
          
        
    - `np.linspace(0, 10, 5)` $\rightarrow$ `[0., 2.5, 5., 7.5, 10.]` _(on fixe le nombre de points $n$, bornes incluses)_.
        
          
        

### Opérations vectorisées (Transformation affine)

Appliquer une opération mathématique à tout un tableau d'un seul coup :

  

Python

```
import numpy as np

a, b = 8.437, 0.314  # Gain et offset d'un capteur
X = np.array([0.0, 1.0, 2.0, 3.0])
Y = a * X + b  # Appliqué à tout le vecteur instantanément
```

### Statistiques descriptives de base

- `np.min(arr)` / `np.max(arr)` : Valeurs extrêmes.
    
      
    
- `np.mean(arr)` : Moyenne arithmétique.
    
      
    
- `np.var(arr)` : Variance (dispersion autour de la moyenne, unité au carré).
    
      
    
- `np.std(arr)` : Écart-type ($\sqrt{\text{variance}}$, même unité que les données).
    
      
    

## 3. Manipulation de Fichiers Tabulaires avec Pandas

Pandas est idéal pour charger des fichiers CSV comportant des en-têtes textuels et mélanger différents types de données.

  

Python

```
import pandas as pd
from pathlib import Path

script_dir = Path(__file__).resolve().parent
df = pd.read_csv(script_dir / "../data/etalonnage_capteur.csv")

# Extraire les colonnes sous forme de tableaux NumPy pour le calcul
X_ref = df["X_ref_bar"].to_numpy()
V_mes = df["V_mesure_V"].to_numpy()
```

## 4. Visualisation Graphique avec Matplotlib

### Règles de l'art pour un graphique d'instrumentation

Tout graphique doit comporter :

  

1. Un titre explicite.
    
      
    
2. Des axes étiquetés avec leurs **unités** (ex: `Pression [bar]`, `Tension [V]`).
    
      
    
3. Une grille (`ax.grid(True)`).
    
      
    
4. Une légende (`ax.legend()`) si plusieurs courbes sont tracées.
    
      
    

### Exemple complet : Figure à deux sous-graphiques (`subplots`)

Idéal pour comparer un signal brut et un signal corrigé au cours du temps.

  

Python

```
import matplotlib.pyplot as plt
import numpy as np

t = np.linspace(0, 10, 100)
V_brut = np.sin(t) + 2.0
X_corrige = V_brut * 1.5

# Création de 2 axes verticaux partageant le même axe des x
fig, (ax_haut, ax_bas) = plt.subplots(2, 1, figsize=(10, 6), sharex=True)

# Graphique supérieur (Signal brut)
ax_haut.plot(t, V_brut, color="tab:orange", label="Signal brut")
ax_haut.set_ylabel("Tension [V]")
ax_haut.grid(True, alpha=0.3)
ax_haut.legend(loc="best")

# Graphique inférieur (Signal corrigé)
ax_bas.plot(t, X_corrige, color="tab:blue", label="Grandeur physique")
ax_bas.set_xlabel("Temps [s]")
ax_bas.set_ylabel("Pression [bar]")
ax_bas.grid(True, alpha=0.3)
ax_bas.legend(loc="best")

plt.tight_layout()
plt.show()
```

### Nuages de points et barres d'erreur

- `ax.scatter(x, y, s=40)` : Affiche des points discrets (utile pour un étalonnage).
    
      
    
- `ax.errorbar(x, y, yerr=sigma, fmt="none", ecolor="gray")` : Ajoute des barres d'incertitude.
    
      
    

### Sauvegarde propre d'une figure

Pour éviter que les labels ne soient coupés lors de l'export :

  

Python

```
fig.savefig(
    script_dir / "../img/resultat.png", dpi=150, bbox_inches="tight"
)
```

## 5. Applications Pratiques en Instrumentation

### Cas 1 : Correction d'une caractéristique affine (Capteur linéaire)

1. **Modèle :** $V_{\text{mesure}} = a \cdot X + b$
    
      
    
2. **Étalonnage :** À partir des points expérimentaux, on détermine graphiquement ou numériquement la pente $\hat{a}$ et l'offset $\hat{b}$.
    
      
    
3. **Correction inverse :** Pour retrouver la grandeur physique vraie $\hat{X}$ à partir de la tension mesurée sur le terrain :
    
      
    
    $$\hat{X} = \frac{V_{\text{mesure}} - \hat{b}}{\hat{a}}$$
    

### Cas 2 : Traitement d'un capteur non linéaire (Sonde NTC)

Une thermistance NTC (Negative Temperature Coefficient) voit sa résistance diminuer lorsque la température augmente. Sa relation n'est pas linéaire, ce qui nécessite une inversion mathématique en plusieurs étapes (chaîne de traitement).

  

#### 1. Le montage en pont diviseur de tension

La NTC est insérée dans un pont diviseur avec une résistance fixe $R_{\text{fixe}}$ et alimentée sous une tension $V_{\text{cc}}$ :

  

$$V_{\text{out}} = V_{\text{cc}} \cdot \frac{R_{\text{ntc}}}{R_{\text{fixe}} + R_{\text{ntc}}}$$

#### 2. Étape 1 : Remonter à la résistance de la NTC ($V_{\text{out}} \rightarrow R_{\text{ntc}}$)

On isole mathématiquement $R_{\text{ntc}}$ de la formule du pont diviseur :

  

$$R_{\text{ntc}} = \frac{R_{\text{fixe}} \cdot V_{\text{out}}}{V_{\text{cc}} - V_{\text{out}}}$$

#### 3. Étape 2 : Convertir la résistance en température (Loi Beta / Steinhart-Hart)

La caractéristique non linéaire de la NTC suit la loi Beta :

  

$$R_{\text{ntc}}(T) = R_0 \cdot \exp\left(B \left(\frac{1}{T} - \frac{1}{T_0}\right)\right)$$

- Avec $T$ et $T_0$ en **Kelvin** ($T[\text{K}] = T[^\circ\text{C}] + 273.15$).
    
      
    
- $R_0$ la résistance à la température de référence $T_0$ (souvent $25^\circ\text{C} = 298.15\text{ K}$).
    
      
    
- $B$ le coefficient caractéristique de la sonde (en Kelvin).
    
      
    

En inversant cette relation, on obtient l'expression de la température absolue $T$ :

  

$$\frac{1}{T} = \frac{1}{T_0} + \frac{1}{B} \cdot \ln\left(\frac{R_{\text{ntc}}}{R_0}\right)$$

$$T[\text{K}] = \left[ \frac{1}{T_0} + \frac{1}{B} \cdot \ln\left(\frac{R_{\text{ntc}}}{R_0}\right) \right]^{-1}$$

Enfin, on convertit le résultat en Celsius :

$$T[^\circ\text{C}] = T[\text{K}] - 273.15$$

#### Exemple de code Python pour l'inversion NTC :

Python

```
import numpy as np


def tension_vers_resistance(Vout, Vcc, Rfixe):
  # Étape 1 : Inversion du pont diviseur
  return (Rfixe * Vout) / (Vcc - Vout)


def resistance_vers_temperature(R, R0, T0_C, B):
  # Étape 2 : Inversion de la loi Beta
  T0_K = T0_C + 273.15
  inv_T = (1.0 / T0_K) + (1.0 / B) * np.log(R / R0)
  T_K = 1.0 / inv_T
  return T_K - 273.15  # Conversion en °C
```