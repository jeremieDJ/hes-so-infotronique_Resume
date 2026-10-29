# Fiche de Révision : Électrostatique (Chapitres 1 à 5)

## Table des Matières

1. [Chapitres 1 & 2 : Charge électrique et Origine de l'Électromagnétisme](#1-chapitres-1--2--charge-électrique-et-origine-de-lélectromagnétisme)
2. [Chapitre 3 : Le Champ Électrique](#2-chapitre-3--le-champ-électrique)
3. [Chapitre 4 : Tension, Potentiel Électrique et Énergie](#3-chapitre-4--tension-potentiel-électrique-et-énergie)
4. [Chapitre 5 : Condensateurs et Diélectriques](#4-chapitre-5--condensateurs-et-diélectriques)
5. [Récapitulatif des Formules Essentielles pour l'Examen](#5-récapitulatif-des-formules-essentielles-pour-lexamen)

---

## 1. Chapitres 1 & 2 : Charge électrique et Origine de l'Électromagnétisme

### 1.1 La Charge Électrique

* **Nature de la matière** : La matière est constituée d'atomes formés de protons ($q = +e$), de neutrons (neutres, $q = 0$) et d'électrons ($q = -e$).
* **Charge élémentaire** : 
  $$e \approx 1{,}602 \times 10^{-19} \text{ C}$$
  * $e$ : valeur absolue de la charge électrique minimale indivisible (Coulombs, $\text{C}$).

* **Quantification de la charge** :
  $$q = n \cdot e$$
  * $q$ : charge électrique totale d'un corps (Coulombs, $\text{C}$).
  * $n$ : nombre entier relatif de charges élémentaires ($n \in \mathbb{Z}$).
  * $e$ : charge élémentaire ($1{,}602 \times 10^{-19} \text{ C}$).

* **Principe de conservation** :
  $$\sum q = \text{Cte}$$
  * $\sum q$ : somme algébrique de toutes les charges d'un système isolé, qui reste constante au cours du temps.

---

### 1.2 Interactions Électrostatiques

* Deux charges de **même signe** ($q_1 \cdot q_2 > 0$) se **repoussent**.
* Deux charges de **signes opposés** ($q_1 \cdot q_2 < 0$) s'**attirent**.

---

### 1.3 Loi de Coulomb

La force exercée par une charge ponctuelle $q_1$ (située en $A$) sur une charge ponctuelle $q_2$ (située en $B$) séparées par une distance $r$ est :

$$\vec{F}_{1 \to 2} = \frac{1}{4\pi\varepsilon_0} \frac{q_1 q_2}{r^2} \hat{u}_{1 \to 2}$$

**Explication des termes :**
* $\vec{F}_{1 \to 2}$ : vecteur force électrostatique exercé par $q_1$ sur $q_2$ (Newtons, $\text{N}$).
* $q_1, q_2$ : valeurs algébriques des deux charges électriques (Coulombs, $\text{C}$).
* $r$ : distance séparant les deux charges $q_1$ et $q_2$ (mètres, $\text{m}$).
* $\varepsilon_0$ : permittivité diélectrique du vide ($\varepsilon_0 \approx 8{,}854 \times 10^{-12} \text{ F}\cdot\text{m}^{-1}$ ou $\text{C}^2\cdot\text{N}^{-1}\cdot\text{m}^{-2}$).
* $\hat{u}_{1 \to 2}$ : **vecteur unitaire** sans dimension (norme $\|\hat{u}_{1 \to 2}\| = 1$), orienté depuis la charge $q_1$ vers la charge $q_2$. Il donne uniquement la direction et le sens de la ligne d'action.
* $k = \frac{1}{4\pi\varepsilon_0} \approx 8{,}99 \times 10^9 \text{ N}\cdot\text{m}^2\cdot\text{C}^{-2}$ : constante de Coulomb dans le vide.

---

## 2. Chapitre 3 : Le Champ Électrique

### 2.1 Définition du Champ Électrique

$$\vec{E} = \frac{\vec{F}}{q} \quad \Longleftrightarrow \quad \vec{F} = q \vec{E}$$

**Explication des termes :**
* $\vec{E}$ : vecteur champ électrique au point d'observation (Volts par mètre, $\text{V}\cdot\text{m}^{-1}$ ou Newtons par Coulomb, $\text{N}\cdot\text{C}^{-1}$).
* $\vec{F}$ : vecteur force subie par la charge d'épreuve (Newtons, $\text{N}$).
* $q$ : valeur de la charge d'épreuve placée au point considéré (Coulombs, $\text{C}$).

---

### 2.2 Champ créé par une charge ponctuelle $Q$

$$\vec{E}(r) = \frac{1}{4\pi\varepsilon_0} \frac{Q}{r^2} \hat{u}_r$$

**Explication des termes :**
* $\vec{E}(r)$ : vecteur champ électrique à une distance $r$ de la charge source ($\text{V}\cdot\text{m}^{-1}$).
* $Q$ : charge ponctuelle source créant le champ (Coulombs, $\text{C}$).
* $r$ : distance entre la charge source $Q$ et le point où l'on calcule le champ (mètres, $\text{m}$).
* $\hat{u}_r$ : **vecteur unitaire radial** orienté de la charge $Q$ vers le point d'observation ($\|\hat{u}_r\| = 1$).
  * Si $Q > 0$, $\vec{E}$ est dans le même sens que $\hat{u}_r$ (**centrifuge**).
  * Si $Q < 0$, $\vec{E}$ est dans le sens opposé à $\hat{u}_r$ (**centripète**).

---

### 2.3 Principe de Superposition Vectorielle

$$\vec{E}_{\text{total}} = \sum_{i=1}^{n} \vec{E}_i = \frac{1}{4\pi\varepsilon_0} \sum_{i=1}^{n} \frac{Q_i}{r_i^2} \hat{u}_i$$

**Explication des termes :**
* $\vec{E}_{\text{total}}$ : vecteur champ électrique résultant au point considéré ($\text{V}\cdot\text{m}^{-1}$).
* $Q_i$ : valeur de la $i$-ième charge de la distribution (Coulombs, $\text{C}$).
* $r_i$ : distance entre la $i$-ième charge et le point considéré (mètres, $\text{m}$).
* $\hat{u}_i$ : vecteur unitaire dirigé de la $i$-ième charge vers le point d'observation.

---

### 2.4 Distributions Continues de Charge

Élément de charge infinitésimal $dq$ selon le type de distribution :
* **Linéique** : $dq = \lambda \, dl$
  * $dq$ : charge de l'élément de longueur (Coulombs, $\text{C}$).
  * $\lambda$ : densité linéique de charge (Coulombs par mètre, $\text{C}\cdot\text{m}^{-1}$).
  * $dl$ : longueur infinitésimale de l'élément (mètres, $\text{m}$).
* **Surfacique** : $dq = \sigma \, dS$
  * $\sigma$ : densité surfacique de charge (Coulombs par mètre carré, $\text{C}\cdot\text{m}^{-2}$).
  * $dS$ : surface infinitésimale de l'élément (mètres carrés, $\text{m}^2$).
* **Volumique** : $dq = \rho \, dV$
  * $\rho$ : densité volumique de charge (Coulombs par mètre cube, $\text{C}\cdot\text{m}^{-3}$).
  * $dV$ : volume infinitésimal de l'élément (mètres cubes, $\text{m}^3$).

 Champ électrique total :
$$\vec{E} = \frac{1}{4\pi\varepsilon_0} \int \frac{dq}{r^2} \hat{u}_r$$

---

### 2.5 Cas Particuliers Usuels

* **Plan infini uniformément chargé** :
  $$E = \frac{|\sigma|}{2\varepsilon_0}$$
  * $E$ : norme (grandeur) du champ électrique ($\text{V}\cdot\text{m}^{-1}$).
  * $|\sigma|$ : valeur absolue de la densité surfacique de charge ($\text{C}\cdot\text{m}^{-2}$).
  * Le champ est uniforme, perpendiculaire au plan, et s'en éloigne si $\sigma > 0$ (s'en approche si $\sigma < 0$).

* **Entre deux plaques infinies parallèles armées (+$\sigma$ et -$\sigma$)** :
  * À l'intérieur : $E = \frac{\sigma}{\varepsilon_0}$
  * À l'extérieur : $E = 0$

* **Sphère de rayon $R$ chargée uniformément en volume ($\rho = \text{Cte}$)** :
  * À l'intérieur ($r \le R$) : $E(r) = \frac{\rho r}{3\varepsilon_0}$
  * À l'extérieur ($r \ge R$) : $E(r) = \frac{\rho R^3}{3\varepsilon_0 r^2} = \frac{Q_{\text{tot}}}{4\pi\varepsilon_0 r^2}$
    * $R$ : rayon de la sphère (mètres, $\text{m}$).
    * $r$ : distance entre le centre de la sphère et le point d'observation (mètres, $\text{m}$).
    * $Q_{\text{tot}} = \rho \cdot \frac{4}{3}\pi R^3$ : charge totale contenue dans la sphère (Coulombs, $\text{C}$).

---

## 3. Chapitre 4 : Tension, Potentiel Électrique et Énergie

### 3.1 Travail de la Force Électrostatique

$$W_{A \to B}(\vec{F}) = \int_{A}^{B} \vec{F} \cdot d\vec{r} = q \int_{A}^{B} \vec{E} \cdot d\vec{r}$$

**Explication des termes :**
* $W_{A \to B}(\vec{F})$ : travail de la force électrostatique pour déplacer $q$ de $A$ vers $B$ (Joules, $\text{J}$).
* $d\vec{r}$ : vecteur déplacement élémentaire le long de la trajectoire (mètres, $\text{m}$).
* $\cdot$ : symbole du **produit scalaire** entre deux vecteurs ($\vec{A} \cdot \vec{B} = \|\vec{A}\| \|\vec{B}\| \cos\theta$).

---

### 3.2 Tension et Potentiel Électrique

$$U_{AB} = V_A - V_B = \int_{A}^{B} \vec{E} \cdot d\vec{r}$$

**Explication des termes :**
* $U_{AB}$ : tension électrique ou différence de potentiel entre $A$ et $B$ (Volts, $\text{V}$).
* $V_A, V_B$ : potentiel électrostatique au point $A$ et au point $B$ (Volts, $\text{V}$).

Dans le cas d'un **champ uniforme** dirigé de $A$ vers $B$ sur une distance $d$ :
$$U = E \cdot d \quad \Longleftrightarrow \quad E = \frac{U}{d}$$
* $U$ : tension entre les deux points (Volts, $\text{V}$).
* $E$ : norme du champ électrique ($\text{V}\cdot\text{m}^{-1}$).
* $d$ : distance séparant les deux points parallèlement aux lignes de champ (mètres, $\text{m}$).

---

### 3.3 Relation entre Champ et Potentiel (Gradient)

$$\vec{E} = -\vec{\nabla} V = -\mathbf{grad}(V)$$

**Explication des termes :**
* $\vec{\nabla}$ (ou $\mathbf{grad}$) : **opérateur gradient**, regroupant les dérivées partielles de l'espace. En cartésien :
  $$E_x = -\frac{\partial V}{\partial x}, \quad E_y = -\frac{\partial V}{\partial y}, \quad E_z = -\frac{\partial V}{\partial z}$$
* Le signe **moins** ($-$) indique que le champ électrique $\vec{E}$ est toujours orienté dans le sens des **potentiels décroissants**.

---

### 3.4 Potentiel créé par des charges ponctuelles

Pour une charge ponctuelle unique $Q$ avec référence à l'infini ($V(\infty) = 0$) :
$$V(r) = \frac{1}{4\pi\varepsilon_0} \frac{Q}{r}$$
* $V(r)$ : potentiel électrique à une distance $r$ (Volts, $\text{V}$).
* $Q$ : charge source (Coulombs, $\text{C}$).
* $r$ : distance à la charge source (mètres, $\text{m}$).

Pour plusieurs charges ponctuelles :
$$V(P) = \frac{1}{4\pi\varepsilon_0} \sum_{i} \frac{Q_i}{r_i}$$

---

### 3.5 Énergie Potentielle Électrostatique

Pour une charge $q$ dans un potentiel $V$ :
$$E_{pot} = q \cdot V$$
* $E_{pot}$ : énergie potentielle de la charge (Joules, $\text{J}$).
* $q$ : valeur de la charge (Coulombs, $\text{C}$).
* $V$ : potentiel au point où se situe la charge (Volts, $\text{V}$).

Pour deux charges $q_1$ et $q_2$ séparées par une distance $d$ :
$$E_{pot} = \frac{1}{4\pi\varepsilon_0} \frac{q_1 q_2}{d}$$
* $d$ : distance séparant les deux charges (mètres, $\text{m}$).

---

### 3.6 Propriétés d'un Conducteur en Équilibre Électrostatique

1. **Champ interne** : $\vec{E}_{\text{int}} = \vec{0}$
2. **Potentiel interne** : $V = \text{Cte}$ (le conducteur est un volume équipotentiel).
3. **Densité de charge interne** : $\rho_{\text{int}} = 0$ (les charges excédentaires migrent à la surface).
4. **Champ de surface** : $\vec{E}_{\text{surf}} = \frac{\sigma}{\varepsilon_0} \hat{n}$ (perpendiculaire à la surface, où $\hat{n}$ est le vecteur unitaire normal sortant).

---

## 4. Chapitre 5 : Condensateurs et Diélectriques

### 4.1 Définition et Capacité

$$C = \frac{Q}{U}$$

**Explication des termes :**
* $C$ : capacité du condensateur (Farads, $\text{F}$).
* $Q$ : charge électrique accumulée sur l'armature positive (Coulombs, $\text{C}$).
* $U$ : tension (différence de potentiel $V_+ - V_-$) entre les deux armatures (Volts, $\text{V}$).

---

### 4.2 Capacités selon la Géométrie (dans le vide)

* **Condensateur Plan** :
  $$C = \varepsilon_0 \frac{S}{d}$$
  * $S$ : surface en vis-à-vis des armatures (mètres carrés, $\text{m}^2$).
  * $d$ : distance d'écartement entre les armatures (mètres, $\text{m}$).

* **Condensateur Cylindrique** :
  $$C = \frac{2\pi\varepsilon_0 L}{\ln(R_2 / R_1)}$$
  * $L$ : longueur axiale des cylindres (mètres, $\text{m}$).
  * $R_1, R_2$ : rayons respectifs du cylindre interne et externe avec $R_2 > R_1$ (mètres, $\text{m}$).
  * $\ln$ : logarithme népérien.

* **Condensateur Sphérique** :
  $$C = 4\pi\varepsilon_0 \frac{R_1 R_2}{R_2 - R_1}$$
  * $R_1, R_2$ : rayons des sphères concentriques interne et externe avec $R_2 > R_1$ (mètres, $\text{m}$).

---

### 4.3 Influence des Isolants (Diélectriques)

$$\varepsilon = \varepsilon_0 \cdot \varepsilon_r$$
$$C = \varepsilon_r C_0$$

**Explication des termes :**
* $\varepsilon$ : permittivité diélectrique absolue du milieu ($\text{F}\cdot\text{m}^{-1}$).
* $\varepsilon_r$ : permittivité relative de l'isolant (grandeur adimensionnelle, $\varepsilon_r > 1$).
* $C_0$ : capacité du condensateur à vide (Farads, $\text{F}$).
* $C$ : nouvelle capacité augmentée avec diélectrique (Farads, $\text{F}$).

---

### 4.4 Association de Condensateurs

* **En Parallèle** :
  $$C_{\text{éq}} = \sum_{i} C_i = C_1 + C_2 + C_3 + \dots$$
  * $C_{\text{éq}}$ : capacité équivalente (Farads, $\text{F}$).
  * La tension $U$ est la même aux bornes de chaque condensateur.

* **En Série** :
  $$\frac{1}{C_{\text{éq}}} = \sum_{i} \frac{1}{C_i} \quad \Longrightarrow \quad C_{\text{éq}} = \frac{C_1 C_2}{C_1 + C_2} \quad (\text{pour 2 condensateurs})$$
  * La charge $Q$ est identique sur tous les condensateurs en série.

---

### 4.5 Énergie Emmagasinée dans un Condensateur

$$E_c = \frac{1}{2} C U^2 = \frac{1}{2} \frac{Q^2}{C} = \frac{1}{2} Q U$$

**Explication des termes :**
* $E_c$ : énergie potentielle électrostatique emmagasinée (Joules, $\text{J}$).
* $C$ : capacité du condensateur (Farads, $\text{F}$).
* $U$ : tension entre ses armatures (Volts, $\text{V}$).
* $Q$ : charge stockée (Coulombs, $\text{C}$).

---

## 5. Récapitulatif des Formules Essentielles pour l'Examen

| Formule | Signification des Symboles | Unités SI |
| :--- | :--- | :--- |
| $\vec{F} = \frac{1}{4\pi\varepsilon_0}\frac{q_1 q_2}{r^2}\hat{u}$ | $\vec{F}$: Force Coulomb, $q$: Charges, $r$: Distance, $\hat{u}$: Vecteur unitaire dirigé de 1 vers 2, $\varepsilon_0$: Permittivité du vide | $\text{N}$, $\text{C}$, $\text{m}$, $\text{F}\cdot\text{m}^{-1}$ |
| $\vec{E} = \frac{\vec{F}}{q}$ | $\vec{E}$: Champ électrique, $\vec{F}$: Force subie, $q$: Charge d'épreuve | $\text{V}\cdot\text{m}^{-1}$, $\text{N}$, $\text{C}$ |
| $E = \frac{|\sigma|}{2\varepsilon_0}$ | $E$: Champ plan infini, $\sigma$: Densité de charge surfacique | $\text{V}\cdot\text{m}^{-1}$, $\text{C}\cdot\text{m}^{-2}$ |
| $U = E \cdot d$ | $U$: Tension, $E$: Champ uniforme, $d$: Distance parallèle au champ | $\text{V}$, $\text{V}\cdot\text{m}^{-1}$, $\text{m}$ |
| $V = \frac{1}{4\pi\varepsilon_0}\frac{Q}{r}$ | $V$: Potentiel à distance $r$, $Q$: Charge ponctuelle source | $\text{V}$, $\text{C}$, $\text{m}$ |
| $E_{pot} = qV$ | $E_{pot}$: Énergie potentielle d'une charge dans un potentiel $V$ | $\text{J}$, $\text{C}$, $\text{V}$ |
| $C = \frac{Q}{U}$ | $C$: Capacité, $Q$: Charge de l'armature $+$, $U$: Tension | $\text{F}$, $\text{C}$, $\text{V}$ |
| $C = \varepsilon_r \varepsilon_0 \frac{S}{d}$ | $S$: Surface utile, $d$: Épaisseur d'isolant, $\varepsilon_r$: Permittivité relative | $\text{F}$, $\text{m}^2$, $\text{m}$ |
| $E_c = \frac{1}{2} C U^2$ | $E_c$: Énergie stockée dans un condensateur | $\text{J}$, $\text{F}$, $\text{V}$ |