# Correction du Contrôle Continu de Physique — SE3
**Date :** 05 novembre 2024  
**Filière :** Systèmes Industriels & Énergie / Technologie Environnementale  
**Durée :** 60 minutes  

---

## Directives méthodologiques de l'examen

1. **Lois et principes :** Énoncer explicitement la loi physique utilisée avant tout calcul.
2. **Schéma :** Illustrer la situation physique de manière claire.
3. **Calcul littéral :** Mener tous les développements de manière algébrique avant de faire l'application numérique.

---

## Exercice 1 : Champ électrique de deux plaques infinies croisées (10 points)

### Énoncé
Deux plaques infiniment grandes portent une densité de charge surfacique uniforme $\sigma > 0$. Elles sont orthogonales et se coupent au niveau des axes de coordonnées $(x=0)$ et $(y=0)$.

---

### Principes et lois appliqués
- **Théorème de Gauss / Champ d'un plan infini :** Une plaque plane infinie portée à une densité de charge surfacique uniforme $\sigma$ produit un champ électrique uniforme, perpendiculaire à la plaque, dirigé vers l'extérieur (car $\sigma > 0$) et de norme :
  $$E_{\text{plaque}} = \frac{\sigma}{2\epsilon_0}$$
- **Principe de superposition :** Le champ électrique total $\vec{E}$ en tout point de l'espace est la somme vectorielle des champs produits par la plaque verticale ($\vec{E}_1$) et la plaque horizontale ($\vec{E}_2$) :
  $$\vec{E} = \vec{E}_1 + \vec{E}_2$$

---

### (a) Représentation du champ électrique dans toutes les régions
L'espace est divisé en 4 quadrants par les deux plaques :

```
                  y
                  ^
    Quadrant II   |   Quadrant I
   E = (-i + j)   |   E = (+i + j)
                  |
  ----------------+----------------> x
                  |
   Quadrant III   |   Quadrant IV
   E = (-i - j)   |   E = (+i - j)
                  |
```

- **Quadrant I ($x > 0, y > 0$) :**  
  $\vec{E}_1 = +\frac{\sigma}{2\epsilon_0}\hat{i}$, $\vec{E}_2 = +\frac{\sigma}{2\epsilon_0}\hat{j} \implies \vec{E} = \frac{\sigma}{2\epsilon_0}(\hat{i} + \hat{j})$ (orienté à $45^\circ$)
- **Quadrant II ($x < 0, y > 0$) :**  
  $\vec{E}_1 = -\frac{\sigma}{2\epsilon_0}\hat{i}$, $\vec{E}_2 = +\frac{\sigma}{2\epsilon_0}\hat{j} \implies \vec{E} = \frac{\sigma}{2\epsilon_0}(-\hat{i} + \hat{j})$ (orienté à $135^\circ$)
- **Quadrant III ($x < 0, y < 0$) :**  
  $\vec{E}_1 = -\frac{\sigma}{2\epsilon_0}\hat{i}$, $\vec{E}_2 = -\frac{\sigma}{2\epsilon_0}\hat{j} \implies \vec{E} = \frac{\sigma}{2\epsilon_0}(-\hat{i} - \hat{j})$ (orienté à $225^\circ$)
- **Quadrant IV ($x > 0, y < 0$) :**  
  $\vec{E}_1 = +\frac{\sigma}{2\epsilon_0}\hat{i}$, $\vec{E}_2 = -\frac{\sigma}{2\epsilon_0}\hat{j} \implies \vec{E} = \frac{\sigma}{2\epsilon_0}(\hat{i} - \hat{j})$ (orienté à $315^\circ$)

---

### (b) Amplitude (norme) du champ électrique dans toutes les régions
Pour chacun des 4 quadrants, la norme vaut :
$$E = \|\vec{E}\| = \sqrt{E_x^2 + E_y^2} = \sqrt{\left(\frac{\sigma}{2\epsilon_0}\right)^2 + \left(\frac{\sigma}{2\epsilon_0}\right)^2} = \frac{\sigma}{2\epsilon_0}\sqrt{2} = \frac{\sigma}{\sqrt{2}\epsilon_0}$$

La norme du champ électrique est donc **uniforme et identique dans tout l'espace** (hors des plaques).

---

### (c) Évaluation du champ électrique aux points A et B

- **Point A $(x_A = R, y_A = R)$ :** situé dans le Quadrant I.
  $$\vec{E}_A = \frac{\sigma}{2\epsilon_0}\hat{i} + \frac{\sigma}{2\epsilon_0}\hat{j}$$
  $$E_A = \|\vec{E}_A\| = \frac{\sqrt{2}\sigma}{2\epsilon_0}$$

- **Point B $(x_B = 2R, y_B = 2R)$ :** situé également dans le Quadrant I.
  Puisque le champ créé par des plans infinis ne dépend pas de la distance aux plaques :
  $$\vec{E}_B = \frac{\sigma}{2\epsilon_0}\hat{i} + \frac{\sigma}{2\epsilon_0}\hat{j} = \vec{E}_A$$
  $$E_B = E_A = \frac{\sqrt{2}\sigma}{2\epsilon_0}$$

---

## Exercice 2 : Ions Sodium et Potassium dans un champ homogène (10 points)

### Données
- Tension : $U = 12\text{ V}$
- Distance entre électrodes : $L = 10\text{ cm} = 0,10\text{ m}$
- Charges des ions : $q_{\text{Na}^+} = q_{\text{K}^+} = +e \approx 1,602 \times 10^{-19}\text{ C}$
- Position initiale : au milieu ($x = L/2 = 5\text{ cm}$)

---

### (a) Schéma de la situation
```
      Anode (+)                          Cathode (-)
       x = 0                              x = L
       +------------------------------------+
       |  +E>                               |
       |  --->    Na+, K+                   |
       |          (x = L/2) -- F ->         |
       |                                    |
       +------------------------------------+
       |<-------------- L ----------------->|
```

- Le champ électrique $\vec{E}$ est dirigé de l'anode ($+$) vers la cathode ($-$).
- La force électrostatique $\vec{F} = q\vec{E}$ s'exerce vers la droite (vers la cathode) car $q = +e > 0$.

---

### (b) Expression algébrique de la force

1. **Relation champ-tension :** Dans un champ uniforme, la norme du champ électrique s'écrit :
   $$E = \frac{U}{L}$$
2. **Force de Lorentz (partie électrostatique) :**
   $$F = q \cdot E = e \cdot \frac{U}{L}$$

Note : La force subie dépend uniquement de la charge de l'ion et du champ électrique, et **non de la masse de l'ion**. Ainsi, la force algébrique est la même pour $\text{Na}^+$ et $\text{K}^+$.

---

### (c) Application numérique

$$F_{\text{Na}^+} = F_{\text{K}^+} = (1,602 \times 10^{-19}\text{ C}) \times \frac{12\text{ V}}{0,10\text{ m}} = 1,9224 \times 10^{-17}\text{ N}$$

$$\mathbf{F_{\text{Na}^+} = F_{\text{K}^+} \approx 1,92 \times 10^{-17}\text{ N}}$$

---

### (d) Comparaison du temps d'arrivée à l'électrode

1. **Deuxième loi de Newton :**
   $$F = m \cdot a \implies a = \frac{F}{m} = \frac{e U}{m L}$$
2. **Équation du mouvement uniforme accéléré (sans vitesse initiale sur une distance $d = L/2$) :**
   $$d = \frac{1}{2} a t^2 \implies t = \sqrt{\frac{2d}{a}} = \sqrt{\frac{2(L/2)}{\frac{e U}{m L}}} = L \sqrt{\frac{m}{e U}}$$
3. **Analyse des masses :**
   - Les deux ions possèdent la même charge $+e$, mais la masse atomique du Sodium ($M_{\text{Na}} \approx 23\text{ g/mol}$) est strictement inférieure à celle du Potassium ($M_{\text{K}} \approx 39,1\text{ g/mol}$).
   - $m_{\text{Na}^+} < m_{\text{K}^+} \implies a_{\text{Na}^+} > a_{\text{K}^+} \implies t_{\text{Na}^+} < t_{\text{K}^+}$.

**Conclusion :** L'ion **Sodium ($\text{Na}^+$)** arrivera le premier à la cathode car, subissant la même force que le Potassium, sa masse plus faible lui donne une accélération plus grande.

---

## Exercice 3 : Énergie potentielle de configurations de charges (10 points)

### Loi appliquée
L'énergie potentielle électrostatique d'un système de $N$ charges ponctuelles s'obtient en sommant les énergies d'interaction de toutes les paires distinctes $(i, j)$ avec $i < j$ :
$$U = \frac{1}{4\pi\epsilon_0} \sum_{1 \le i < j \le N} \frac{q_i q_j}{r_{ij}} = k \sum_{i<j} \frac{q_i q_j}{r_{ij}}$$

Pour un carré de côté $d$ :
- Distance entre 2 sommets adjacents : $d$ (4 paires)
- Distance entre 2 sommets opposés (diagonale) : $d\sqrt{2}$ (2 paires)

---

### (a) Calcul de l'énergie potentielle des configurations A et B

#### Configuration A : Charges $+Q, -Q, +Q, -Q$ aux sommets alternés
- **Paires adjacentes (4 paires à distance $d$) :**
  - Haut : $(+Q)(-Q) = -Q^2$
  - Droite : $(-Q)(+Q) = -Q^2$
  - Bas : $(+Q)(-Q) = -Q^2$
  - Gauche : $(-Q)(+Q) = -Q^2$
  $$\sum_{\text{adj}} = 4 \times \left(-\frac{Q^2}{d}\right) = -4\frac{Q^2}{d}$$

- **Paires diagonales (2 paires à distance $d\sqrt{2}$) :**
  - Diagonale 1 : $(+Q)(+Q) = +Q^2$
  - Diagonale 2 : $(-Q)(-Q) = +Q^2$
  $$\sum_{\text{diag}} = 2 \times \left(\frac{Q^2}{d\sqrt{2}}\right) = \frac{\sqrt{2}Q^2}{d}$$

- **Énergie totale $U_A$ :**
  $$U_A = \frac{Q^2}{4\pi\epsilon_0 d} \left( -4 + \sqrt{2} \right) \approx -2,586 \frac{Q^2}{4\pi\epsilon_0 d}$$

---

#### Configuration B : Charges $+Q, +Q, +Q, -Q$ (trois positives, une négative)
- **Paires adjacentes (4 paires à distance $d$) :**
  - Deux paires entre charges de même signe ($+Q, +Q$) : $+Q^2$ chacune.
  - Deux paires entre charges de signe opposé ($+Q, -Q$) : $-Q^2$ chacune.
  $$\sum_{\text{adj}} = \frac{+Q^2 + Q^2 - Q^2 - Q^2}{d} = 0$$

- **Paires diagonales (2 paires à distance $d\sqrt{2}$) :**
  - Une paire $(+Q, +Q)$ : $+Q^2$
  - Une paire $(+Q, -Q)$ : $-Q^2$
  $$\sum_{\text{diag}} = \frac{+Q^2 - Q^2}{d\sqrt{2}} = 0$$

- **Énergie totale $U_B$ :**
  $$U_B = 0$$

*(Note : Si la configuration B représentait 2 charges $+Q$ en haut et 2 charges $-Q$ en bas, on aurait $U_B = -\sqrt{2} \frac{Q^2}{4\pi\epsilon_0 d} \approx -1,414 \frac{Q^2}{4\pi\epsilon_0 d}$).*

---

### (b) Classement par ordre croissant d'énergie potentielle

Puisque $U_A < 0$ et $U_B = 0$ (ou $U_A < U_B < 0$ dans l'autre disposition) :
$$\mathbf{U_A < U_B}$$

---

## Exercice 4 : Sphère isolante chargée en volume (10 points)

### Énoncé & Rappel
Pour $r < R$, la norme du champ radial vaut :
$$E(r) = \frac{\rho r}{3\epsilon_0}$$

---

### (a) Tension entre le centre A ($r=0$) et la surface B ($r=R$)

Par définition de la différence de potentiel (tension) :
$$U_{AB} = V(A) - V(B) = \int_{0}^{R} E(r) \, dr$$

Calcul de l'intégrale :
$$U_{AB} = \int_{0}^{R} \frac{\rho r}{3\epsilon_0} \, dr = \frac{\rho}{3\epsilon_0} \left[ \frac{r^2}{2} \right]_{0}^{R} = \mathbf{\frac{\rho R^2}{6\epsilon_0}}$$

---

### (b) Tension entre la surface B ($r=R$) et l'infini C ($r \to \infty$)

1. **Champ électrique à l'extérieur de la sphère ($r \ge R$) :**  
   D'après le théorème de Gauss, la sphère chargée interagit à l'extérieur comme une charge ponctuelle concentrant sa charge totale $Q$ en son centre :
   $$Q = \rho \cdot V = \rho \cdot \frac{4}{3}\pi R^3$$
   $$E(r) = \frac{Q}{4\pi\epsilon_0 r^2} = \frac{\rho \frac{4}{3}\pi R^3}{4\pi\epsilon_0 r^2} = \frac{\rho R^3}{3\epsilon_0 r^2}$$

2. **Calcul de la tension $U_{BC} = V(B) - V(C)$ :**
   $$U_{BC} = \int_{R}^{\infty} E(r) \, dr = \int_{R}^{\infty} \frac{\rho R^3}{3\epsilon_0 r^2} \, dr$$
   $$U_{BC} = \frac{\rho R^3}{3\epsilon_0} \left[ -\frac{1}{r} \right]_{R}^{\infty} = \frac{\rho R^3}{3\epsilon_0} \left( 0 - \left(-\frac{1}{R}\right) \right) = \mathbf{\frac{\rho R^2}{3\epsilon_0}}$$

---

## Exercice 5 (BONUS) : Analyse dimensionnelle (4 points)

### Énoncé
Exprimer les unités du champ électrique $\vec{E}$ et de la capacité $C$ uniquement en fonction des unités de base : $[\text{m}, \text{kg}, \text{s}, \text{C}]$.

---

### 1. Champ électrique $\vec{E}$

- **Loi physique :** Force de Coulomb / Lorentz : $\vec{F} = q\vec{E} \implies E = \frac{F}{q}$
- **Mécanique (2ème loi de Newton) :** $F = m \cdot a \implies [F] = \text{kg} \cdot \text{m} \cdot \text{s}^{-2}$
- **Déduction dimensionnelle :**
  $$[E] = \frac{[F]}{[q]} = \frac{\text{kg} \cdot \text{m} \cdot \text{s}^{-2}}{\text{C}} = \mathbf{\text{m} \cdot \text{kg} \cdot \text{s}^{-2} \cdot \text{C}^{-1}}$$

---

### 2. Capacité d'un condensateur $C$

- **Loi physique :** Définition de la capacité : $C = \frac{Q}{U}$
- **Tension électrique $U$ :** Lien travail / énergie : $W = q \cdot U \implies U = \frac{W}{q}$
- **Travail d'une force :** $W = F \cdot d \implies [W] = \text{kg} \cdot \text{m}^2 \cdot \text{s}^{-2}$
- **Unité de la tension $U$ :**
  $$[U] = \frac{[W]}{[q]} = \text{m}^2 \cdot \text{kg} \cdot \text{s}^{-2} \cdot \text{C}^{-1}$$
- **Déduction dimensionnelle de $C$ :**
  $$[C] = \frac{[Q]}{[U]} = \frac{\text{C}}{\text{m}^2 \cdot \text{kg} \cdot \text{s}^{-2} \cdot \text{C}^{-1}} = \mathbf{\text{m}^{-2} \cdot \text{kg}^{-1} \cdot \text{s}^{2} \cdot \text{C}^{2}}$$
