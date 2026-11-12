# Étalonnage de capteurs et régression linéaire — Correction détaillée

**Auteurs du support d'origine :** Marc Nicollier & Marion Badi  
**Date :** 22 octobre 2026  
**Document :** Correction complète des exercices du chapitre 4 (HEVS)

---

## Rappel théorique : Régression linéaire matricielle

Un modèle est dit **linéaire** lorsqu'il est linéaire par rapport à ses coefficients inconnus $a_j$. Pour $n$ couples de mesures de référence et de réponses $(x_i, y_i)$, le modèle s'écrit :

$$y = a_0 f_0(x) + a_1 f_1(x) + \dots + a_{p-1} f_{p-1}(x)$$

Sous forme matricielle $y = Xa + r$ :

$$\begin{pmatrix} y_1 \\ y_2 \\ \vdots \\ y_n \end{pmatrix} = \begin{pmatrix} f_0(x_1) & f_1(x_1) & \dots & f_{p-1}(x_1) \\ f_0(x_2) & f_1(x_2) & \dots & f_{p-1}(x_2) \\ \vdots & \vdots & \ddots & \vdots \\ f_0(x_n) & f_1(x_n) & \dots & f_{p-1}(x_n) \end{pmatrix} \begin{pmatrix} a_0 \\ a_1 \\ \vdots \\ a_{p-1} \end{pmatrix} + \begin{pmatrix} r_1 \\ r_2 \\ \vdots \\ r_n \end{pmatrix}$$

La solution au sens des moindres carrés minimise la norme du vecteur résidu $S = \|y - Xa\|^2$ via les équations normales :

$$X^T X \hat{a} = X^T y \implies \hat{a} = (X^T X)^{-1} X^T y$$

---

## Exercice 1 : Régression « à la main » avec peu de points

### Données
Étalonnage d'une cellule de charge (pont de jauges) :

| $i$ | Masse $m_i$ [kg] | Tension $U_i$ [mV] |
|:---:|:----------------:|:------------------:|
| 1   | $0.000$          | $0.0864$           |
| 2   | $0.500$          | $0.2893$           |
| 3   | $1.000$          | $0.4884$           |
| 4   | $2.000$          | $0.8840$           |
| 5   | $5.000$          | $2.0991$           |

---

### Questions de base

#### 1. Écriture du vecteur $y$ et de la matrice de conception $X$

Pour le modèle linéaire d'une droite $U = a_0 + a_1 m$, nous avons $f_0(m) = 1$ et $f_1(m) = m$.

$$y = \begin{pmatrix} 0.0864 \\ 0.2893 \\ 0.4884 \\ 0.8840 \\ 2.0991 \end{pmatrix}, \quad X = \begin{pmatrix} 1 & 0.000 \\ 1 & 0.500 \\ 1 & 1.000 \\ 1 & 2.000 \\ 1 & 5.000 \end{pmatrix}$$

#### 2. Calcul littéral et numérique de $X^T X$ et $X^T y$

**Démonstration littérale :**

$$X^T X = \begin{pmatrix} 1 & 1 & \dots & 1 \\ m_1 & m_2 & \dots & m_n \end{pmatrix} \begin{pmatrix} 1 & m_1 \\ 1 & m_2 \\ \vdots & \vdots \\ 1 & m_n \end{pmatrix} = \begin{pmatrix} \sum_{i=1}^n 1 & \sum_{i=1}^n m_i \\ \sum_{i=1}^n m_i & \sum_{i=1}^n m_i^2 \end{pmatrix} = \begin{pmatrix} n & \sum m_i \\ \sum m_i & \sum m_i^2 \end{pmatrix}$$

$$X^T y = \begin{pmatrix} 1 & 1 & \dots & 1 \\ m_1 & m_2 & \dots & m_n \end{pmatrix} \begin{pmatrix} U_1 \\ U_2 \\ \vdots \\ U_n \end{pmatrix} = \begin{pmatrix} \sum_{i=1}^n U_i \\ \sum_{i=1}^n m_i U_i \end{pmatrix}$$

**Calcul des sommes numériques ($n = 5$) :**

$$\sum m_i = 0.000 + 0.500 + 1.000 + 2.000 + 5.000 = 8.500 \text{ kg}$$

$$\sum m_i^2 = 0^2 + 0.500^2 + 1.000^2 + 2.000^2 + 5.000^2 = 0 + 0.25 + 1.00 + 4.00 + 25.00 = 30.250 \text{ kg}^2$$

$$\sum U_i = 0.0864 + 0.2893 + 0.4884 + 0.8840 + 2.0991 = 3.8472 \text{ mV}$$

$$\sum m_i U_i = (0 \times 0.0864) + (0.5 \times 0.2893) + (1 \times 0.4884) + (2 \times 0.8840) + (5 \times 2.0991)$$
$$\sum m_i U_i = 0 + 0.14465 + 0.48840 + 1.76800 + 10.49550 = 12.89655 \text{ mV}\cdot\text{kg}$$

**Matrices numériques obtenues :**

$$X^T X = \begin{pmatrix} 5 & 8.5 \\ 8.5 & 30.25 \end{pmatrix}, \quad X^T y = \begin{pmatrix} 3.8472 \\ 12.89655 \end{pmatrix}$$

---

#### 3. Résolution du système $2 \times 2$

Calcul de l'inverse de $X^T X$ avec la formule $\begin{pmatrix} a & b \\ c & d \end{pmatrix}^{-1} = \frac{1}{ad - bc} \begin{pmatrix} d & -b \\ -c & a \end{pmatrix}$ :

$$\det(X^T X) = (5 \times 30.25) - (8.5 \times 8.5) = 151.25 - 72.25 = 79$$

$$(X^T X)^{-1} = \frac{1}{79} \begin{pmatrix} 30.25 & -8.5 \\ -8.5 & 5 \end{pmatrix}$$

Calcul des coefficients $\hat{a} = (X^T X)^{-1} X^T y$ :

$$\hat{a} = \frac{1}{79} \begin{pmatrix} 30.25 \times 3.8472 - 8.5 \times 12.89655 \\ -8.5 \times 3.8472 + 5 \times 12.89655 \end{pmatrix}$$

$$a_0 = \frac{116.3778 - 109.620675}{79} = \frac{6.757125}{79} \approx 0.085533 \text{ mV}$$

$$a_1 = \frac{-32.7012 + 64.48275}{79} = \frac{31.78155}{79} \approx 0.402298 \text{ mV/kg}$$

* **Décalage à l'origine (offset) $a_0$ :** $0.0855 \text{ mV}$
* **Sensibilité $a_1$ :** $0.4023 \text{ mV/kg}$

---

### Questions d'utilisation

#### 4. Vérification en Python avec NumPy

```python
import numpy as np

m = np.array([0.000, 0.500, 1.000, 2.000, 5.000])
U = np.array([0.0864, 0.2893, 0.4884, 0.8840, 2.0991])

# Méthode 1 : lstsq
X = np.column_stack([np.ones_like(m), m])
a_lstsq, *_ = np.linalg.lstsq(X, U, rcond=None)
print(f"lstsq : a0 = {a_lstsq[0]:.6f} mV, a1 = {a_lstsq[1]:.6f} mV/kg")

# Méthode 2 : polyfit (attention : ordonné du plus haut au plus bas degré -> [a1, a0])
p_polyfit = np.polyfit(m, U, deg=1)
print(f"polyfit : a1 = {p_polyfit[0]:.6f} mV/kg, a0 = {p_polyfit[1]:.6f} mV")
```

#### 5. Mesure directe : calcul de la masse

Pour une tension mesurée $U = 1.234 \text{ mV}$, on inverse le modèle $U = a_0 + a_1 m$ :

$$m = \frac{U - a_0}{a_1} = \frac{1.234 - 0.085533}{0.402298} = \frac{1.148467}{0.402298} \approx 2.85476 \text{ kg}$$

La masse posée est donc de **$2.855 \text{ kg}$**.

---

## Exercice 2 : Tube de Pitot (Caractéristique quadratique)

### Contexte physique
* Théorème de Bernoulli pour fluide incompressible : $\Delta p = p_t - p_s = \frac{1}{2} \rho v^2$.
* Modèle réel avec coefficient d'appareil $C$ et offset $\Delta p_0$ :

$$\Delta p = \Delta p_0 + k v^2 \quad \text{avec} \quad k = \frac{C \rho}{2}$$

---

### Questions — Choix du modèle

#### 1. Forme de la courbe $\Delta p = f(v)$
Le tracé des points montre une courbure parabolique vers le haut : la pression varie proportionnellement au carré de la vitesse, ce qui est en accord direct avec l'équation de Bernoulli.

#### 2. Modèle 1 : Droite $\Delta p = a_0 + a_1 v$
* **Observation visuelle :** La droite semble visuellement s'approcher des points expérimentaux avec un coefficient $R^2$ très élevé.
* **Graphique des résidus ($r_i = \Delta p_i - \hat{\Delta p}_i$) :** Les résidus présentent une forme distincte en **U** (négatifs aux extrémités, positifs au centre). Cela démontre une dérive systématique : le modèle linéaire sous-estime la courbure et n'est pas adapté physiquement.

#### 3. Modèle 2 : Parabole complète $\Delta p = a_0 + a_1 v + a_2 v^2$
* **Analyse du terme $a_1 v$ :** La valeur numérique estimée pour $a_1$ est statistiquement négligeable et son intervalle de confiance englobe zéro.
* **Interprétation de $a_0$ :** Il s'agit du décalage de zéro (offset de pression) du capteur différentiel à vitesse nulle ($v = 0 \text{ m/s}$).

#### 4. Modèle 3 : Modèle physique $\Delta p = a_0 + a_2 v^2$
* **Matrice de conception :** $X = \begin{bmatrix} 1 & v^2 \end{bmatrix}$.
* **Comparaison des résidus :** Le graphique des résidus montre un nuage aléatoire réparti symétriquement autour de 0 Pa, sans aucune structure (pas de forme en U ni en S). C'est le meilleur modèle car il s'appuie sur la physique du problème tout en évitant le surajustement d'un terme linéaire superflu.

---

### Questions — Interprétation et utilisation

#### 5. Estimation de la masse volumique $\rho$ et du coefficient $C$

Données de l'essai :
* Température $T = 21\,^\circ\text{C} = 294.15 \text{ K}$
* Pression $P = 956 \text{ hPa} = 95600 \text{ Pa}$
* Constante $R_s = 287.05 \text{ J/(kg}\cdot\text{K)}$

Calcul de $\rho$ par la loi des gaz parfaits :

$$\rho = \frac{P}{R_s T} = \frac{95600}{287.05 \times 294.15} \approx 1.1322 \text{ kg/m}^3$$

Extraction de $C$ à partir du coefficient $a_2$ identifié par la régression :

$$a_2 = \frac{C \rho}{2} \implies C = \frac{2 a_2}{\rho}$$

#### 6. Inversion du modèle pour déterminer la vitesse
Pour un modèle ajusté $\Delta p = a_0 + a_2 v^2$, l'expression de la vitesse est :

$$v = \sqrt{\frac{\Delta p - a_0}{a_2}}$$

#### 7. Étude de sensibilité et incertitude $u(v)$

Par propagation des incertitudes :

$$u(v) = \left| \frac{\partial v}{\partial \Delta p} \right| u(\Delta p)$$

En dérivant $\Delta p = a_0 + a_2 v^2$ par rapport à $v$ :

$$\frac{d(\Delta p)}{dv} = 2 a_2 v \implies \frac{dv}{d(\Delta p)} = \frac{1}{2 a_2 v}$$

D'où la relation théorique :

$$u(v) = \frac{u(\Delta p)}{2 a_2 v}$$

```python
import numpy as np
import matplotlib.pyplot as plt

u_dp = 0.8  # Pa (incertitude type sur la pression)
a2 = 0.68   # Valeur typique pour C=1 et rho=1.13 (a2 ≈ 1.13/2)

v = np.linspace(1, 25, 200)
u_v = u_dp / (2 * a2 * v)

plt.figure(figsize=(8, 4))
plt.plot(v, u_v, 'r-', lw=2)
plt.xlabel('Vitesse v [m/s]')
plt.ylabel('Incertitude u(v) [m/s]')
plt.title('Incertitude sur la vitesse en fonction de la vitesse (Tube de Pitot)')
plt.grid(True)
plt.show()
```

* **Conclusion sur les faibles vitesses :** Lorsque $v \to 0$, l'incertitude $u(v) \propto \frac{1}{v}$ tend vers l'infini. Le tube de Pitot est donc très peu précis et peu sensible aux faibles vitesses (typiquement sous $5 \text{ m/s}$).

---

## Exercice 3 : Sonde Pt100 et choix du degré de modèle

### Contexte
Équation de Callendar-Van Dusen ($T \ge 0\,^\circ\text{C}$) :

$$R(T) = R_0 (1 + AT + BT^2)$$

Standard IEC 60751 : $R_0 = 100\,\Omega$, $A = 3.9083 \times 10^{-3}\,^\circ\text{C}^{-1}$, $B = -5.775 \times 10^{-7}\,^\circ\text{C}^{-2}$.

---

### Questions

#### 1 & 2. Étalonnage et analyse des résidus

Ajustement des polynômes $R(T) = \sum_{k=0}^d a_k T^k$ pour $d \in \{1, 2, 3, 4, 6, 8\}$ avec calcul du RMSE d'étalonnage :

$$\text{RMSE} = \sqrt{\frac{1}{n} \sum_{i=1}^n r_i^2}$$

* **Degré 1 :** Les résidus forment une parabole très nette (courbure résiduelle due au terme quadratique $B T^2$).
* **Degré 2 :** C'est le **premier degré** pour lequel les résidus ne présentent plus aucune structure déterministe (distribution purement aléatoire de moyenne nulle).

---

#### 3. Évaluation sur jeux de données Validation et Extrapolation

```python
import numpy as np

# Exemple d'implémentation de calcul des RMSE
degrees = [1, 2, 3, 4, 6, 8]
# T_etal, R_etal = ...
# T_val, R_val = ...
# T_extrap, R_extrap = ...

rmse_etal = []
rmse_val = []
rmse_extrap = []

for deg in degrees:
    coeffs = np.polyfit(T_etal, R_etal, deg)
    
    r_e = R_etal - np.polyval(coeffs, T_etal)
    r_v = R_val - np.polyval(coeffs, T_val)
    r_ex = R_extrap - np.polyval(coeffs, T_extrap)
    
    rmse_etal.append(np.sqrt(np.mean(r_e**2)))
    rmse_val.append(np.sqrt(np.mean(r_v**2)))
    rmse_extrap.append(np.sqrt(np.mean(r_ex**2)))
```

**Commentaire des résultats :**
1. **Degré 1 :** Sous-ajustement (*underfitting*). Le modèle est trop simple pour capturer la courbure de la réponse du platine.
2. **Degré 2 :** Bon compromis physique et optimal pour la validation et l'extrapolation.
3. **Degrés élevés (6 et 8) :** Surajustement (*overfitting*). Le RMSE d'étalonnage décroît vers 0 (le polynôme passe par tous les points du bruit), mais l'erreur d'extrapolation **explose à des valeurs très élevées** (phénomène de Runge aux bords du domaine).

---

#### 4. Identification des paramètres $R_0, A, B$ pour le degré 2

Le modèle ajusté fournit : $R(T) = a_0 + a_1 T + a_2 T^2$.

Par identification directe avec $R(T) = R_0 + R_0 A T + R_0 B T^2$ :

$$R_0 = a_0$$

$$A = \frac{a_1}{a_0}$$

$$B = \frac{a_2}{a_0}$$

Les coefficients identifiés sont quasi-identiques aux constantes théoriques de la norme IEC 60751 aux incertitudes de mesure près.

---

#### 5. Erreur d'extrapolation en température du degré 8

Avec une sensibilité moyenne de la sonde Pt100 $S = \frac{dR}{dT} \approx 0.38\,\Omega/^\circ\text{C}$, l'erreur équivalente en température s'obtient par :

$$\Delta T_{\text{extrap}} = \frac{\text{RMSE}_{\text{extrap}}(d=8)}{S}$$

---

## Exercice 4 : Photodiode en circuit ouvert (Piège de la linéarisation)

### Modèle physique
Pour une photodiode en circuit ouvert ($I = 0$), la tension mesurée $V_{oc}$ suit une loi logarithmique :

$$V_{oc} = n V_T \ln\left(1 + \frac{I_{ph}}{I_s}\right) \approx n V_T \ln E + \text{constante} = a + b \ln E$$

avec la tension thermique $V_T = \frac{k_B T}{q} \approx 25.69 \text{ mV}$ à $25\,^\circ\text{C}$ ($298.15 \text{ K}$).

---

### Questions

#### 1. Représentations graphiques
* **Repère linéaire ($V_{oc}$ vs $E$) :** La courbe montre une croissance rapide aux faibles éclairements puis une saturation progressive.
* **Repère semi-logarithmique ($V_{oc}$ vs $\ln E$) :** Les données s'alignent sur une droite parfaite.

#### 2. Étalonnage direct $V_{oc} = a + b \ln E$
* **Matrice de conception :** $X = \begin{bmatrix} 1 & \ln E \end{bmatrix}$.

```python
import numpy as np

# Chargement des données
# E_lux, Voc_V = ...

X = np.column_stack([np.ones_like(E_lux), np.log(E_lux)])
a_param, b_param = np.linalg.lstsq(X, Voc_V, rcond=None)[0]

print(f"Modèle : Voc = {a_param:.4f} + {b_param:.4f} * ln(E)")
```

* **Résidus :** Le bruit de mesure étant additif et constant ($1.5 \text{ mV}$ sur la mesure directe de tension $V_{oc}$), les résidus issus du modèle direct $V_{oc} = a + b \ln E$ restent homogènes sur toute la plage de mesure (**homoscédasticités préservées**).

---

#### 3. Déduction du facteur d'idéalité $n$

Le coefficient directeur issu du modèle vaut $b = n V_T$.

$$n = \frac{b}{V_T} = \frac{b}{\frac{k_B T}{q}}$$

À $T = 25\,^\circ\text{C} = 298.15 \text{ K}$ :

$$V_T = \frac{1.380649 \times 10^{-23} \times 298.15}{1.602176 \times 10^{-19}} \approx 0.02569 \text{ V} = 25.69 \text{ mV}$$

La valeur numérique calculée pour $n$ se situe dans l'intervalle physique attendu $1 \le n \le 2$.

---

## Résumé des bonnes pratiques en étalonnage

1. **Choix du modèle :** Préférer toujours un modèle fondé sur la physique du capteur.
2. **Analyse des résidus :** Un modèle valide doit présenter un graphique des résidus sans aucune structure (nuage aléatoire centré sur 0).
3. **Surajustement :** Ne pas augmenter le degré du polynôme sans raison sous prétexte de diminuer le RMSE d'étalonnage.
4. **Linéarisation et bruit :** La transformation de la variable dépendante $y$ modifie la pondération des incertitudes. Si le bruit est additif sur la mesure de départ, il vaut mieux ajuster le modèle non-linéaire directement sur les données brutes.