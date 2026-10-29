# Fiche de Révision : Électrostatique (Chapitres 1 à 5)

## Table des Matières

1. [Chapitres 1 & 2 : Charge électrique et Origine de l'Électromagnétisme](#1-chapitres-1--2--charge-électrique-et-origine-de-lélectromagnétisme)

2. [Chapitre 3 : Le Champ Électrique](#2-chapitre-3--le-champ-électrique)

3. [Chapitre 4 : Tension, Potentiel Électrique et Énergie](#3-chapitre-4--tension-potentiel-électrique-et-énergie)

4. [Chapitre 5 : Condensateurs et Diélectriques](#4-chapitre-5--condensateurs-et-diélectriques)

5. [Récapitulatif des Formules Essentielles pour l'Examen](#5-récapitulatif-des-formules-essentielles-pour-lexamen)

## 1. Chapitres 1 & 2 : Charge électrique et Origine de l'Électromagnétisme

### 1.1 La Charge Électrique

* **Nature de la matière** : La matière est constituée d'atomes formés de protons ($q = +e$), d'éléments neutres (neutrons) et d'électrons ($q = -e$).

* **Charge élémentaire** :
  

  $$
  e \approx 1{,}602 \times 10^{-19} \text{ C}
  $$

* **Quantification de la charge** : Toute charge électrique $q$ est un multiple entier de la charge élémentaire :
  

  $$
  q = n \cdot e \quad (n \in \mathbb{Z})
  $$

* **Principe de conservation** : Dans un système isolé, la charge électrique totale demeure rigoureusement constante :
  

  $$
  \sum q = \text{Cte}
  $$

### 1.2 Interactions Électrostatiques

* **Interactions de même signe / signes contraires** :

  * Deux charges de même signe se **repoussent**.

  * Deux charges de signes opposés s'**attirent**.

### 1.3 Loi de Coulomb

La force exercée par une charge ponctuelle $q_1$ située en un point $A$ sur une charge ponctuelle $q_2$ située en un point $B$ séparées par une distance $r$ dans le vide est donnée par :

$$
\vec{F}_{1 \to 2} = \frac{1}{4\pi\varepsilon_0} \frac{q_1 q_2}{r^2} \hat{u}_{1 \to 2}
$$

* $\varepsilon_0$ est la **permittivité diélectrique du vide** :
  

  $$
  \varepsilon_0 \approx 8{,}854 \times 10^{-12} \text{ F}\cdot\text{m}^{-1} \quad \left(\text{ou C}^2\cdot\text{N}^{-1}\cdot\text{m}^{-2}\right)
  $$

* La constante de Coulomb $k$ vaut :
  

  $$
  k = \frac{1}{4\pi\varepsilon_0} \approx 8{,}99 \times 10^9 \text{ N}\cdot\text{m}^2\cdot\text{C}^{-2}
  $$

## 2. Chapitre 3 : Le Champ Électrique

### 2.1 Définition du Champ Électrique

Lorsqu'une charge de épreuve $q$ est placée dans l'espace environnant d'autres charges, elle subit une force électrostatique $\vec{F}$. Le **champ électrique** $\vec{E}$ au point considéré est défini par :

$$
\vec{E} = \frac{\vec{F}}{q} \quad \Longleftrightarrow \quad \vec{F} = q \vec{E}
$$

* **Unité** : Volt par mètre ($\text{V}\cdot\text{m}^{-1}$) ou Newton par Coulomb ($\text{N}\cdot\text{C}^{-1}$).

### 2.2 Champ créé par une charge ponctuelle $Q$

Au point situé à une distance $r$ de la charge $Q$ :

$$
\vec{E}(r) = \frac{1}{4\pi\varepsilon_0} \frac{Q}{r^2} \hat{u}_r
$$

* Si $Q > 0$, le champ est **centrifuge** (s'éloigne de la charge).

* Si $Q < 0$, le champ est **centripète** (se dirige vers la charge).

### 2.3 Principe de Superposition Vectorielle

Le champ électrique total créé en un point par plusieurs charges $Q_1, Q_2, \dots, Q_n$ est la somme vectorielle des champs créés par chaque charge :

$$
\vec{E}_{\text{total}} = \sum_{i=1}^{n} \vec{E}_i = \frac{1}{4\pi\varepsilon_0} \sum_{i=1}^{n} \frac{Q_i}{r_i^2} \hat{u}_i
$$

### 2.4 Distributions Continues de Charge

Pour une distribution continue de charges :

* **Lineique** (densité $\lambda$ en $\text{C}\cdot\text{m}^{-1}$) : $dq = \lambda \, dl$

* **Surfacique** (densité $\sigma$ en $\text{C}\cdot\text{m}^{-2}$) : $dq = \sigma \, dS$

* **Volumique** (densité $\rho$ en $\text{C}\cdot\text{m}^{-3}$) : $dq = \rho \, dV$

$$
\vec{E} = \frac{1}{4\pi\varepsilon_0} \int \frac{dq}{r^2} \hat{u}_r
$$

### 2.5 Cas Particuliers usuels

* **Plan infini uniformément chargé** de densité $\sigma$ :
  

  $$
  E = \frac{\vert{}\sigma\vert{}}{2\varepsilon_0}
  $$

* **Entre deux plaques infinies parallèlement chargées (+**$\sigma$ **et -**$\sigma$**)** :

  * À l'intérieur : $E = \frac{\sigma}{\varepsilon_0}$

  * À l'extérieur : $E = 0$

* **Sphère de rayon** $R$ **uniformément chargée en volume** ($\rho = \text{Cte}$) :

  * Pour $r \le R$ : $E(r) = \frac{\rho r}{3\varepsilon_0}$

  * Pour $r \ge R$ : $E(r) = \frac{\rho R^3}{3\varepsilon_0 r^2} = \frac{Q_{\text{tot}}}{4\pi\varepsilon_0 r^2}$

## 3. Chapitre 4 : Tension, Potentiel Électrique et Énergie

### 3.1 Travail de la Force Électrostatique

La force électrostatique est une force **conservatrice**. Le travail fourni pour déplacer une charge $q$ d'un point $A$ à un point $B$ ne dépend pas du chemin suivi :

$$
W_{A \to B}(\vec{F}) = \int_{A}^{B} \vec{F} \cdot d\vec{r} = q \int_{A}^{B} \vec{E} \cdot d\vec{r}
$$

### 3.2 Tension et Potentiel Électrique

La tension électrique (différence de potentiel) entre deux points $A$ et $B$ s'exprime par :

$$
U_{AB} = V_A - V_B = \int_{A}^{B} \vec{E} \cdot d\vec{r}
$$

Dans un champ uniforme sur une distance $d$ le long d'une ligne de champ :

$$
U = E \cdot d \quad \Longleftrightarrow \quad E = \frac{U}{d}
$$

### 3.3 Relation Gradients entre Champ et Potentiel

$$
\vec{E} = -\vec{\nabla} V = -\mathbf{grad}(V)
$$

En coordonnées cartésiennes :

$$
E_x = -\frac{\partial V}{\partial x}, \quad E_y = -\frac{\partial V}{\partial y}, \quad E_z = -\frac{\partial V}{\partial z}
$$

### 3.4 Potentiel créé par une charge ponctuelle

En fixant la référence du potentiel à l'infini ($V(\infty) = 0$) :

$$
V(r) = \frac{1}{4\pi\varepsilon_0} \frac{Q}{r}
$$

Pour un ensemble de charges ponctuelle :

$$
V(P) = \frac{1}{4\pi\varepsilon_0} \sum_{i} \frac{Q_i}{r_i}
$$

### 3.5 Énergie Potentielle Électrostatique

L'énergie potentielle $E_{pot}$ d'une charge $q$ placée dans un potentiel $V$ est :

$$
E_{pot} = q \cdot V
$$

Pour un système formé de deux charges $q_1$ et $q_2$ séparées par une distance $d$ :

$$
E_{pot} = \frac{1}{4\pi\varepsilon_0} \frac{q_1 q_2}{d}
$$

### 3.6 Propriétés des Conducteurs en Équilibre Électrostatique

1. **Champ interne** : $\vec{E}_{\text{int}} = \vec{0}$.

2. **Potentiel interne** : $V = \text{Cte}$ (le conducteur est un volume équipotentiel).

3. **Charge interne** : $\rho_{\text{int}} = 0$ (les charges nettes se répartissent uniquement sur la surface).

4. **Champ de surface** : $\vec{E}_{\text{surf}}$ est perpendiculaire à la surface en tout point.

## 4. Chapitre 5 : Condensateurs et Diélectriques

### 4.1 Définition et Capacité

Un condensateur est un ensemble de deux conducteurs (armatures) portant des charges opposées $+Q$ et $-Q$, séparés par un isolant.

La **capacité** $C$ est le rapport entre la charge de l'armature positive et la tension à ses bornes :

$$
C = \frac{Q}{U}
$$

* **Unité** : Le Farad ($\text{F}$).

### 4.2 Capacités selon la Géométrie

* **Condensateur Plan** (surface $S$, écartement $d$) :
  

  $$
  C = \varepsilon_0 \frac{S}{d}
  $$

* **Condensateur Cylindrique** (rayons $R_1 < R_2$, longueur $L$) :
  

  $$
  C = \frac{2\pi\varepsilon_0 L}{\ln(R_2 / R_1)}
  $$

* **Condensateur Sphérique** (rayons $R_1 < R_2$) :
  

  $$
  C = 4\pi\varepsilon_0 \frac{R_1 R_2}{R_2 - R_1}
  $$

### 4.3 Influence des Isolants (Diélectriques)

Lorsqu'un milieu diélectrique de permittivité relative $\varepsilon_r > 1$ remplit l'espace entre les armatures :

$$
\varepsilon = \varepsilon_0 \varepsilon_r
$$

$$
C = \varepsilon_r C_0
$$

La capacité est donc multipliée par le facteur $\varepsilon_r$.

### 4.4 Association de Condensateurs

* **En Parallèle** :
  

  $$
  C_{\text{éq}} = \sum_{i} C_i = C_1 + C_2 + C_3 + \dots
  $$

  
  *(Les tensions sont identiques, les charges s'additionnent).*

* **En Série** :
  

  $$
  \frac{1}{C_{\text{éq}}} = \sum_{i} \frac{1}{C_i} \quad \Longrightarrow \quad C_{\text{éq}} = \frac{C_1 C_2}{C_1 + C_2} \quad (\text{pour 2 condensateurs})
  $$

  
  *(Les charges sont identiques, les tensions s'additionnent).*

### 4.5 Énergie Emmagasinée dans un Condensateur

L'énergie électrostatique stockée $E_c$ s'exprime sous trois formes équivalentes :

$$
E_c = \frac{1}{2} C U^2 = \frac{1}{2} \frac{Q^2}{C} = \frac{1}{2} Q U
$$

## 5. Récapitulatif des Formules Essentielles pour l'Examen

| Concept | Formule Mathématique | Remarques / Unités | 
| ----- | ----- | ----- | 
| **Force de Coulomb** | $\vec{F} = \frac{1}{4\pi\varepsilon_0}\frac{q_1 q_2}{r^2}\hat{u}$ | En Newton ($\text{N}$) | 
| **Champ (charge ponctuelle)** | $\vec{E} = \frac{1}{4\pi\varepsilon_0}\frac{Q}{r^2}\hat{u}$ | En $\text{V}\cdot\text{m}^{-1}$ ou $\text{N}\cdot\text{C}^{-1}$ | 
| **Force vs Champ** | $\vec{F} = q\vec{E}$ | Vectoriel | 
| **Plaque infinie** | $E = \frac{\sigma}{2\varepsilon_0}$ | Champ uniforme | 
| **Tension (Champ homogène)** | $U = E \cdot d$ | $d$ est la distance parallèlement au champ | 
| **Tension (Intégrale)** | $U_{AB} = \int_{A}^{B} \vec{E} \cdot d\vec{r}$ | Généralisation | 
| **Potentiel (Charge ponctuelle)** | $V = \frac{1}{4\pi\varepsilon_0}\frac{Q}{r}$ | $V(\infty) = 0$ | 
| **Énergie potentielle** | $E_{pot} = qV = \frac{1}{4\pi\varepsilon_0}\frac{q_1 q_2}{r}$ | En Joules ($\text{J}$) | 
| **Capacité Plane** | $C = \varepsilon_r \varepsilon_0 \frac{S}{d}$ | En Farads ($\text{F}$) | 
| **Énergie Condensateur** | $E_c = \frac{1}{2} C U^2 = \frac{1}{2}\frac{Q^2}{C}$ | En Joules ($\text{J}$) | 
