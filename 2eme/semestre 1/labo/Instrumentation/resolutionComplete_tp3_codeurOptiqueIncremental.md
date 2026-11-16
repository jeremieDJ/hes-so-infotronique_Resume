# Résolution Complète : TP Codeur Optique Incrémental (HEDL-5540)

## 1. Exercice 2 : Caractéristiques du Codeur HEDL-5540

1. **Nombre de stries :** $N = 500\text{ stries/tour}$ (ou $1024$ selon variante).
2. **Nombre de signaux :** **3 signaux TTL** ($A$, $B$, et $I$).
3. **Utilité des 2 voies $A$ et $B$ :** Les voies $A$ et $B$ sont déphasées électriquement de $90^\circ$ (en quadrature). Cela permet de **déterminer le sens de rotation** (si $A$ est en avance sur $B \implies$ sens horaire; si $B$ est en avance sur $A \implies$ sens anti-horaire).
4. **Rôle du signal $I$ (Index) :** Délivre une impulsion unique par tour complet. Utilisé pour la **remise à zéro** du compteur de position et le recalage de l'origine mécanique.
5. **Résolution angulaire :**
   * *Flancs montants $A$ seuls ($N=500$) :* $\mathbf{\Delta \theta = \frac{360^\circ}{500} = 0.72^\circ}$
   * *Tous les flancs ($A$ et $B$, quadruple évaluation) :* $\mathbf{\Delta \theta = \frac{360^\circ}{4 \times 500} = 0.18^\circ}$

---

## 2. Exercice 3 : Mesure de Vitesse à Vitesse Constante

L'équation fondamentale de la vitesse angulaire discrète s'écrit :

$$\omega(k) = \frac{\Delta \theta}{\Delta t}$$

### 3.1 Méthode 1 : Comptage d'impulsions dans une fenêtre temporelle fixe $T_E$

* **Formulation mathématique :**
  
  $$\Delta t = T_E = \text{constante} \quad (1\text{ ms})$$

  $$\Delta \theta = \frac{2\pi \cdot \Delta P}{K} \quad (K = \text{nombre de flancs par tour})$$

  $$\mathbf{\omega_1 = \frac{2\pi \cdot \Delta P}{K \cdot T_E}}$$

* **Vitesse minimale mesurable ($\Delta P = 1$ flanc) :**
  
  $$\omega_{1,\min} = \frac{2\pi}{K \cdot T_E}\ [\text{rad/s}]$$

* **Résolution :** La résolution est **constante** sur toute la gamme de vitesse :
  
  $$\Delta \omega_1 = \frac{2\pi}{K \cdot T_E}$$

---

### 3.2 Méthode 2 : Comptage de la durée d'une impulsion (Horloge $f_{\text{clk}} = 1\text{ MHz}$)

* **Formulation mathématique :**
  
  $$\Delta \theta = \frac{2\pi}{K} = \text{constante (déplacement d'un flanc)}$$

  $$\Delta t = \frac{N_{\text{clk}}}{f_{\text{clk}}}$$

  $$\mathbf{\omega_2 = \frac{2\pi \cdot f_{\text{clk}}}{K \cdot N_{\text{clk}}}}$$

* **Vitesse minimale mesurable (Compteur 16 bits : $N_{\text{clk,max}} = 65535$) :**
  
  $$\mathbf{\omega_{2,\min} = \frac{2\pi \cdot 10^6}{K \cdot 65535}\ [\text{rad/s}]}$$

---

## 3. Exercice 4 : Étude Dynamique et Traitement sous Python

### Script Python Complété de Traitement Hors-Ligne (`script_analyse_vitesse.py`)

```python
import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path

# --- Configuration des Paramètres du Codeur
N_stries = 500
K = 4 * N_stries  # Quadruple évaluation (2000 incréments/tour)
T_E = 0.001  # Période d'échantillonnage Méthode 1 (1 ms)
f_clk = 1e6  # Fréquence d'horloge Méthode 2 (1 MHz)

# --- Chargement des Données
fichier = Path(__file__).resolve().parent / "trace_acceleration.csv"
# Format attendu : [t, A, deltaP, period]
data = np.loadtxt(fichier, delimiter=",", skiprows=1)

t = data[:, 0]
deltaP = data[:, 2]
period = data[:, 3]

# --- Calcul Méthode 1 : Fenêtre fixe T_E
# Vitesse en rad/s puis conversion en tours/min (RPM)
w_methode1_rad = (2 * np.pi * deltaP) / (K * T_E)
rpm_methode1 = w_methode1_rad * (60 / (2 * np.pi))

# --- Calcul Méthode 2 : Durée entre fronts (Horloge f_clk)
# Éviter la division par zéro si period == 0
period_safe = np.where(period == 0, 1, period)
w_methode2_rad = (2 * np.pi * f_clk) / (K * period_safe)
rpm_methode2 = w_methode2_rad * (60 / (2 * np.pi))
rpm_methode2[period == 0] = 0

# --- Tracé Comparatif Matplotlib
fig, ax = plt.subplots(figsize=(10, 5))
ax.plot(t, rpm_methode1, label="Méthode 1 (Fenêtre $T_E=1$ ms)", alpha=0.7)
ax.plot(t, rpm_methode2, label="Méthode 2 (Durée inter-fronts)", alpha=0.7)

ax.set_xlabel("Temps [s]")
ax.set_ylabel("Vitesse [RPM]")
ax.set_title("Comparaison Dynamique des Méthodes de Mesure de Vitesse")
ax.grid(True, alpha=0.3)
ax.legend()

plt.tight_layout()
plt.show()
```

---

## 4. Réponses à l'Analyse du Bruit et Croisement

### 4.1 Très basse vitesse
* **Méthode exploitable :** **Méthode 2**.
* **Explication :** À très basse vitesse, $\Delta P$ dans la Méthode 1 vaut 0 ou 1 à chaque milliseconde, générant un bruit de quantification massif (oscillations brutes entre 0 et la vitesse minimale). La Méthode 2 compte un très grand nombre de coups d'horloge $N_{\text{clk}}$, offrant une très haute résolution temporelle.

### 4.2 Très haute vitesse
* **Méthode exploitable :** **Méthode 1**.
* **Explication :** À haute vitesse, $\Delta P$ devient grand ($\Delta P \gg 10$), rendant la quantification négligeable. La Méthode 2 voit $N_{\text{clk}}$ devenir très petit, entraînant des sauts de quantification temporelle importants ($N_{\text{clk}}$ passe de 5 à 4, induisant un saut de $25\%$ sur la vitesse calculée).

### 4.3 Vitesse de croisement (Optimum de basculement)

La résolution absolue de la Méthode 1 est constante :

$$\Delta \omega_1 = \frac{2\pi}{K \cdot T_E}$$

Pour la Méthode 2, la variation de vitesse induite par une incertitude d'un coup d'horloge ($\Delta N_{\text{clk}} = 1$) vaut :

$$\Delta \omega_2(w) \approx \frac{K \cdot \omega^2}{2\pi \cdot f_{\text{clk}}}$$

L'égalité des résolutions $\Delta \omega_1 = \Delta \omega_2$ donne la **vitesse de croisement optimale $\omega^*$** :

$$\frac{2\pi}{K \cdot T_E} = \frac{K \cdot (\omega^*)^2}{2\pi \cdot f_{\text{clk}}} \quad \implies \quad \mathbf{\omega^* = \frac{2\pi}{K} \cdot \sqrt{\frac{f_{\text{clk}}}{T_E}}}$$

### Strategie Optimal (Codeur Hybride) :
* Si $\omega < \omega^*$ : Utiliser la **Méthode 2** (Comptage de période).
* Si $\omega \ge \omega^*$ : Basculer sur la **Méthode 1** (Comptage dans fenêtre $T_E$).