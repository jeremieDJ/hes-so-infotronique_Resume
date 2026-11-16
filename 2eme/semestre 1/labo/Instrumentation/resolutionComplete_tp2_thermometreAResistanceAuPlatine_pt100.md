# Résolution Complète : TP Thermomètre à Résistance Au Platine (Pt100)

## 1. Exercice 4.1 : Mesure Ohmique et Résistance des Câbles

### Analyse Théorique & Calcul de la Résistance du Câble
Le raccordement utilise un câble en cuivre de longueur $L = 2.5\text{ m}$ (soit $5\text{ m}$ aller-retour), de section $S = 0.25\text{ mm}^2 = 0.25 \cdot 10^{-6}\text{ m}^2$ et de résistivité $\rho_{\text{Cu}} = 1.7 \cdot 10^{-8}\ \Omega\cdot\text{m}$.

$$\mathbf{R_{\text{câble}} = \rho_{\text{Cu}} \cdot \frac{2 \cdot L}{S} = 1.7 \cdot 10^{-8} \cdot \frac{5}{0.25 \cdot 10^{-6}} = 0.34\ \Omega}$$

### Impact de l'erreur
Pour une sonde Pt100 à $0^\circ\text{C}$, la sensibilité est $S_0 \approx 0.385\ \Omega/^\circ\text{C}$.
Une résistance additionnelle de $0.34\ \Omega$ induit une erreur systématique positive sur la température :

$$\mathbf{\Delta T = \frac{R_{\text{câble}}}{S_0} = \frac{0.34}{0.385} \approx +0.883^\circ\text{C}}$$

**Constat :** La température lue sur l'ohmmètre (en montage 2 fils) est systématiquement supérieure à la température de référence Testo 720 d'environ $0.9^\circ\text{C}$. Pour annuler cette erreur, il faut utiliser un **montage 4 fils** (Kelvin).

---

## 2. Exercice 4.2 : Relation Température-Résistance & Erreur d'Approximation

### 1. Modèle Quadratic IEC 60751 (Exact sur $0$ à $850^\circ\text{C}$)
$$R(T) = R_0 (1 + A T + B T^2)$$

Pour isoler $T$ en fonction de $R$, on résout l'équation du second degré $B T^2 + A T + \left(1 - \frac{R}{R_0}\right) = 0$ :

$$\mathbf{T(R) = \frac{-A + \sqrt{A^2 - 4 B \left(1 - \frac{R}{R_0}\right)}}{2B}}$$

*(avec $A = 3.9083 \cdot 10^{-3}\ ^\circ\text{C}^{-1}$ et $B = -5.775 \cdot 10^{-7}\ ^\circ\text{C}^{-2}$)*.

### 2. Modèle Linéaire Simplifié ($0$ à $100^\circ\text{C}$)
$$R_{\text{lin}}(T) = R_0 (1 + \alpha T) \quad \text{avec } \alpha = \frac{R_{100} - R_0}{100 \cdot R_0} = 3.85055 \cdot 10^{-3}\ ^\circ\text{C}^{-1}$$

$$\mathbf{T_{\text{lin}}(R) = \frac{R - R_0}{\alpha R_0}}$$

### 3. Calcul de l'erreur d'approximation maximale
L'erreur commise par l'utilisation de l'équation simplifiée s'écrit $E(T) = |T_{\text{exacte}} - T_{\text{lin}}|$.
Sur la plage $[0, 100^\circ\text{C}]$, l'écart maximal se situe au milieu de la plage ($T \approx 50^\circ\text{C}$) :

$$\mathbf{E_{\text{max}} \approx 0.37^\circ\text{C}}$$

---

## 3. Exercice 4.3 : Auto-Échauffement

### Formules Physiques
La puissance électrique dissipée par effet Joule dans la sonde s'écrit $P = R_{\text{sonde}} \cdot I^2$.
L'élévation de température engendrée est :

$$\Delta T = T_{\text{sonde}} - T_{\text{ambiant}} = E \cdot P_{\text{sonde}} = E \cdot R_{\text{sonde}} \cdot I^2$$

Où $E$ est le coefficient d'auto-échauffement en $[^\circ\text{C}/\text{mW}]$.

### Analyse des mesures (Courant $I = 1\text{ mA}$ à $10\text{ mA}$)
* À $I = 1\text{ mA}$ : $P = 100\ \Omega \cdot (1\text{ mA})^2 = 0.1\text{ mW} \implies \Delta T \approx 0.01^\circ\text{C}$ (Négligeable).
* À $I = 10\text{ mA}$ : $P = 100\ \Omega \cdot (10\text{ mA})^2 = 10\text{ mW} \implies \Delta T \approx E \cdot 10\text{ mW}$.

**Constat :** À partir de $I > 2\text{ mA}$, la température mesurée augmente artificiellement de façon parabolique en fonction de $I$ (linéaire en fonction de $P$).
**Détermination du coefficient $E$ :** Il s'obtient expérimentalement en calculant la pente de la droite $T_{\text{mesurée}} = f(P_{\text{sonde}})$. Dans l'eau agitée $E \approx 0.05 \text{ à } 0.1\ ^\circ\text{C}/\text{mW}$, tandis que dans l'air calme $E \approx 0.2 \text{ à } 0.5\ ^\circ\text{C}/\text{mW}$.

---

## 4. Exercice 4.4 : Tolérance selon la Norme IEC 60751 (Classe B)

Pour une sonde Pt100 de **Classe B**, la tolérance sur la température absolue est donnée par :

$$T_{\text{tol}} = \pm (0.30 + 0.005 \cdot |T|)\ [^\circ\text{C}]$$

### Calcul à $T = 100^\circ\text{C}$ :
$$T_{\text{tol}}(100) = \pm (0.30 + 0.005 \cdot 100) = \pm (0.30 + 0.50) = \mathbf{\pm 0.80^\circ\text{C}}$$

### Écart maximum entre 2 thermomètres identiques de Classe B :
Le cas le plus défavorable se produit si le premier thermomètre a l'erreur maximale positive ($+0.80^\circ\text{C}$) et le second l'erreur maximale négative ($-0.80^\circ\text{C}$) :

$$\mathbf{\Delta T_{\text{max}} = 0.80 - (-0.80) = 1.60^\circ\text{C}}$$

---

## 5. Exercice 4.5 : Calibration du Thermomètre sous LabVIEW

### Modèle de Calibration
Pour corriger l'erreur d'offset et de gain sur la plage $[30^\circ\text{C}, 40^\circ\text{C}]$, on applique une fonction de correction linéaire issue d'une régression par moindres carrés :

$$T_{\text{calibré}} = a \cdot T_{\text{brut}} + b$$

Où $a$ corrige l'erreur de gain et $b$ corrige le décalage d'offset rapporté à l'appareil de référence (Testo 720).

### Comportement Hors Plage
En dehors de la plage de calibration ($< 30^\circ\text{C}$ ou $> 40^\circ\text{C}$), l'erreur remonte car la correction linéaire locale n'est plus représentative des dérives globales de la sonde et du conditionneur.

---

## 6. Exercice 4.6 : Temps de Réponse Dynamique

Pour un système thermique modélisé par un $1^{\text{er}}$ ordre de constante de temps $\tau$ :

$$T(t) = T_{\text{final}} + (T_{\text{initial}} - T_{\text{final}}) \cdot e^{-t/\tau}$$

* **Temps à $50\%$ ($t_{0.5}$) :** $e^{-t_{0.5}/\tau} = 0.5 \implies \mathbf{t_{0.5} = \tau \cdot \ln(2) \approx 0.693 \cdot \tau}$
* **Temps à $90\%$ ($t_{0.9}$) :** $e^{-t_{0.9}/\tau} = 0.1 \implies \mathbf{t_{0.9} = \tau \cdot \ln(10) \approx 2.303 \cdot \tau}$

Dans l'eau, le transfert thermique est très efficace ($\tau$ court, $t_{0.9} \approx 2 \text{ à } 5\text{ s}$), tandis que dans l'air, $\tau$ est beaucoup plus grand dû à la faible conductivité thermique du fluide.