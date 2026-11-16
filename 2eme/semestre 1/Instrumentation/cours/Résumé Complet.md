# Cours d'Instrumentation et Qualité de Mesure

Ce document regroupe et synthétise l'ensemble des concepts, définitions et formules du support de cours "pdf_cours_important.pdf".

---

## 1. La Chaîne de Mesure

La mesure d'une grandeur physique (ou mesurande) passe par une succession d'étapes :
1. **Grandeur physique / Mesurande :** Ce qu'on cherche à mesurer (ex: température, rythme cardiaque). Elle possède toujours une unité (SI).
2. **Capteur :** Convertit la grandeur physique en un signal souvent électrique (tension, courant, résistance).
3. **Mise en forme :** Traite le signal brut. Inclut l'amplification, le filtrage, et la conversion analogique-digitale (ADC).
4. **Interface :** Permet la transition du signal (souvent numérique) vers le reste du système.
5. **Sortie :** Affichage (valeur lue par un opérateur), Logger (enregistrement), ou Actionneur/Processus.

### Types de signaux et composantes
Le signal électrique $s$ n'est jamais parfait. Il se compose souvent de la superposition de plusieurs éléments :
* **Grandeur mesurée constante (DC) / Biais / Offset :** Décalage de base.
* **Composante sinusoïdale / Dynamique :** Issue d'une machine tournante, du secteur électrique (50 Hz), etc.
* **Dérive :** Variation lente due à la température, au vieillissement, à l'échauffement.
* **Bruit :** Fluctuations aléatoires (bruit thermique, électronique, interférences).

---

## 2. Unités et Méthodes de Mesure

### Le Système International (SI)
Adopté en 1960, il repose sur 7 unités de base dont toutes les autres découlent :
* **Mètre (m)** : Longueur
* **Kilogramme (kg)** : Masse *(défini depuis 2019 par la constante de Planck $h$)*
* **Seconde (s)** : Temps
* **Ampère (A)** : Courant électrique
* **Kelvin (K)** : Température thermodynamique
* **Mole (mol)** : Quantité de matière
* **Candela (cd)** : Intensité lumineuse

*Exemple de grandeur dérivée :* Puissance $[W] = [kg] \cdot \left[\frac{m^2}{s^3}\right]$.

### Mesure Directe vs Indirecte
* **Directe :** Comparaison de la valeur avec une référence de même nature (ex: lire une longueur sur une règle).
* **Indirecte :** Utilisation de l'effet du mesurande sur une autre grandeur (ex: mesurer une résistance pour en déduire une température avec une CTN). *La majorité des capteurs modernes sont indirects.*

---

## 3. Caractéristiques Dynamiques

* **Temps de réponse :** Temps nécessaire à l'instrument pour afficher la valeur exacte après un changement brusque du mesurande. Les capteurs se comportent souvent comme des systèmes du $1^{\text{er}}$ ordre (exponentiel, caractérisé par $\tau$) ou du $2^{\text{e}}$ ordre (avec oscillations et amortissement).
* **Bande passante :** Plage de fréquences sur laquelle le capteur peut mesurer sans atténuation ni erreur significative. Règle générale : La bande passante du système doit être largement supérieure à la fréquence la plus élevée du signal mesuré.

---

## 4. Caractéristiques Statiques

Ce sont les relations établies lorsque les signaux sont stabilisés (pas de variations rapides).

* **Sensibilité ($S_0$) :** Variation de la sortie divisée par la variation de l'entrée.
  $$S_0 = \frac{dy}{dx}$$
  *Pour un capteur linéaire, $S_0$ est constante. Pour un capteur non linéaire, elle dépend de $x$.*
* **Capteur Linéaire :** Relation proportionnelle constante. Équation :
  $$y = S_0(x - x_{\text{min}}) + y_{\text{min}}$$
* **Étendue de mesure (Full Scale, FS) :** $x_{\text{FS}} = x_{\text{max}} - x_{\text{min}}$ (souvent on suppose $x_{\text{min}}=0$).
* **Résolution :** Plus petite variation détectable. Pour un convertisseur analogique-numérique (ADC) de $N$ bits :
  $$\Delta x_{\text{min}} = \frac{\text{Plage d'entrée}}{2^N}$$

---

## 5. Qualité de la Mesure et Erreurs

### Justesse, Fidélité et Exactitude (L'image de la cible)
* **Fidélité (Répétabilité) :** Les mesures se ressemblent et sont groupées, mais pas forcément centrées sur la vraie valeur.
* **Justesse :** La moyenne des mesures est centrée sur la vraie valeur (référence).
* **Exactitude (Précision globale) :** Le système est à la fois juste ET fidèle.

### Types d'erreurs fondamentales
* **Erreurs aléatoires :** Non reproductibles, fluctuations (bruit, courants d'air). Améliorées en faisant des moyennes.
* **Erreurs systématiques :** Reproductibles, décalage fixe (mauvais étalonnage, erreur de gain/offset).

### Imperfections des capteurs
* **Offset (Décalage du zéro) :** Valeur non nulle en sortie alors que l'entrée est nulle (ex: $y = ax + b$, l'offset est $b$).
* **Erreur de gain :** La pente de la caractéristique est fausse.
* **Hystérèse :** Écart maximal entre la mesure à la montée et la mesure à la descente (ex: jeux mécaniques, magnétisme).
* **Saturation :** La sortie ne change plus même si l'entrée augmente.
* **Non-univoque :** Un même signal de sortie correspond à deux valeurs d'entrée possibles (ex: relation en cloche).

---

## 6. Linéarisation et Modélisation

Si la caractéristique n'est pas une droite (ex: Sonde Pt100 ou CTN), on peut la modéliser de plusieurs façons :
1. **Approximation locale (Tangente) :** Autour d'un point de fonctionnement.
2. **Suppression du terme quadratique :** On ignore les puissances supérieures ($T^2$).
3. **Par points extrêmes :** On trace une droite reliant le minimum et le maximum de la plage utile.
4. **Régression linéaire (Moindres carrés) :** La méthode optimale pour minimiser l'erreur globale sur une plage.
5. **Régression polynomiale :** Ajout de puissances ($x^2, x^3$) pour épouser la courbe ($y = w_0 + w_1x + w_2x^2 + \dots$).

### Régression Linéaire (Formules des moindres carrés)
Pour trouver la droite optimale $y = a \cdot x + b$ sur $N$ points :
* $S_1 = N$ (nombre de points)
* $S_x = \sum x_i$ ; $S_y = \sum y_i$
* $S_{xx} = \sum x_i^2$ ; $S_{xy} = \sum x_i \cdot y_i$
* Dénominateur $D = S_1 \cdot S_{xx} - (S_x)^2$
* Coefficient directeur (pente) : $$a = \frac{S_1 \cdot S_{xy} - S_x \cdot S_y}{D}$$
* Ordonnée à l'origine (offset) : $$b = \frac{S_y \cdot S_{xx} - S_x \cdot S_{xy}}{D}$$

### Coefficient de détermination ($R^2$)
Il indique quelle proportion de la variation de la sortie est expliquée par le modèle. $R^2 = 1$ est un fit parfait.
$$R^2 = \frac{S_{\text{moyenne}} - S_{\text{regression}}}{S_{\text{moyenne}}}$$

---

## 7. Propagation des Erreurs

Si on calcule un résultat $C$ à partir de grandeurs mesurées $A$ et $B$ possédant des erreurs absolues ($E_A, E_B$) ou relatives ($\epsilon_A = \frac{E_A}{A}, \epsilon_B = \frac{E_B}{B}$).

### Par opérations simples
* **Addition / Soustraction ($C = A \pm B$) :**
  Les erreurs absolues s'additionnent.
  $$E_C = E_A + E_B$$
* **Multiplication / Division ($C = A \cdot B$ ou $C = A / B$) :**
  Les erreurs relatives s'additionnent.
  $$E_C = C \cdot (\epsilon_A + \epsilon_B)$$

### Par dérivées partielles (Généralisation)
Pour une fonction multivariable $Y = F(X_1, X_2, \dots, X_N)$, l'erreur absolue $E_Y$ se calcule en sommant l'impact de chaque variable via sa sensibilité (dérivée partielle) évaluée au point de fonctionnement :
$$E_Y = \left| \frac{\partial Y}{\partial X_1} \right| \cdot E_{X_1} + \left| \frac{\partial Y}{\partial X_2} \right| \cdot E_{X_2} + \dots + \left| \frac{\partial Y}{\partial X_N} \right| \cdot E_{X_N}$$

---

## 8. Étalonnage et Traçabilité

L'étalonnage consiste à comparer les valeurs d'un instrument à une référence reconnue.
* **Traçabilité :** Chaîne ininterrompue de comparaisons, avec des incertitudes croissantes, qui relie votre instrument à l'unité SI fondamentale.
* **Pyramide de traçabilité (du sommet à la base) :**
  1. Unité SI (Constantes fondamentales, ex: Constante de Planck).
  2. Étalons internationaux (BIPM).
  3. Étalons nationaux (METAS en Suisse).
  4. Laboratoires accrédités (SCS, ISO 17025).
  5. Étalons industriels internes.
  6. Instrument de mesure (terrain/atelier).
