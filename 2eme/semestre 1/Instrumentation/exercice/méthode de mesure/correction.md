 # Correction des Exercices d'Instrumentation : Méthodes de Mesure

Ce document propose une correction détaillée des exercices du chapitre 1, incluant la méthode de résolution, les explications physiques et les calculs mathématiques.

---

## 1.1 Direct ou indirect ?

**Méthodologie :**
Une mesure est dite **directe** lorsqu'on compare la grandeur à mesurer avec un étalon de même nature (ex: mesurer une longueur avec une règle). 
Une mesure est dite **indirecte** lorsqu'on déduit la valeur de la grandeur recherchée en mesurant une ou plusieurs autres grandeurs physiques, liées par une relation mathématique ou physique.

**Classification et principes :**

1. **Un thermomètre à mercure**
   - **Principe :** Utilise la dilatation thermique d'un liquide. Le volume du mercure change avec la température, ce qui se traduit par une variation de hauteur dans un tube capillaire.
   - **Type :** **Indirecte** (on mesure une hauteur/volume pour déduire une température).
   - **Chaîne de mesure :** Capteur (bulbe de mercure) $\rightarrow$ Mise en forme (tube capillaire amplifiant la variation de volume en longueur) $\rightarrow$ Interface (échelle graduée sur le verre).

2. **Un pèse-personne à ressort**
   - **Principe :** Basé sur la loi de Hooke ($F = k \cdot \Delta x$). Le poids déforme un ressort.
   - **Type :** **Indirecte** (on mesure un déplacement/déformation pour déduire une force/masse).
   - **Chaîne de mesure :** Capteur (ressort) $\rightarrow$ Mise en forme (mécanisme de transmission par engrenages/leviers) $\rightarrow$ Interface (cadran et aiguille).

3. **Un télémètre laser**
   - **Principe :** Mesure du temps de vol ($t$) d'une impulsion lumineuse pour parcourir l'aller-retour. Distance $d = \frac{c \cdot t}{2}$.
   - **Type :** **Indirecte** (on mesure un temps pour déduire une distance).
   - **Chaîne de mesure :** Capteur (Diode laser émettrice et photodiode réceptrice) $\rightarrow$ Mise en forme (horloge haute fréquence, compteur de temps) $\rightarrow$ Interface (écran numérique).

4. **Un manomètre à tube de Bourdon**
   - **Principe :** Un tube métallique aplati et courbé tend à se redresser sous l'effet de la pression interne.
   - **Type :** **Indirecte** (on mesure une déformation mécanique pour déduire une pression).
   - **Chaîne de mesure :** Capteur (tube de Bourdon) $\rightarrow$ Mise en forme (système de biellettes et secteur denté) $\rightarrow$ Interface (cadran gradué et aiguille).

5. **Une balance électronique à jauge de contrainte**
   - **Principe :** La déformation d'un support sous le poids modifie la résistance électrique d'une jauge collée dessus (effet piézorésistif).
   - **Type :** **Indirecte** (on mesure une variation de résistance électrique pour déduire une masse).
   - **Chaîne de mesure :** Capteur (jauge de contrainte sur corps d'épreuve) $\rightarrow$ Mise en forme (Pont de Wheatstone et amplificateur) $\rightarrow$ Interface (Convertisseur analogique-numérique et écran LCD).

---

## 1.2 Temps de réponse et bande passante

**Donnée :** Réponse du premier ordre $y(t) = x_1(1 - e^{-t/\tau})$ avec $\tau = 4 \text{ s}$.

**1. Tracé et explication de la réponse non instantanée**
- **Méthode :** La courbe part de 0 (à $t=0$) et tend asymptotiquement vers $x_1$. À $t=\tau$, elle atteint $63\%$ de $x_1$. À $t=3\tau$, elle atteint $95\%$.
- **Explication :** La réponse n'est pas instantanée en raison de l'inertie thermique de la sonde. Il faut du temps pour que la chaleur du bain soit transférée à la sonde (résistance thermique) et pour que le matériau de la sonde s'échauffe (capacité thermique).

**2. Temps d'attente pour 90% et 99%**
- **Méthode :** Résoudre l'équation pour $y(t) = 0.9 x_1$ et $y(t) = 0.99 x_1$.
- Pour 90% :
  $$0.9 = 1 - e^{-t/4} \implies e^{-t/4} = 0.1 \implies -t/4 = \ln(0.1)$$
  $$t = -4 \cdot \ln(0.1) \approx 4 \cdot 2.30 = \mathbf{9.21 \text{ s}}$$
- Pour 99% :
  $$0.99 = 1 - e^{-t/4} \implies e^{-t/4} = 0.01 \implies -t/4 = \ln(0.01)$$
  $$t = -4 \cdot \ln(0.01) \approx 4 \cdot 4.60 = \mathbf{18.42 \text{ s}}$$

**3. Suivi d'une oscillation de période 2 [s]**
- **Méthode :** Comparer la période du signal ($T = 2\text{ s}$) à la constante de temps ($\tau = 4\text{ s}$).
- **Discussion :** Pour qu'un capteur du premier ordre suive correctement un signal, il faut que sa constante de temps soit beaucoup plus petite que la période du signal ($\tau \ll T$). Ici, c'est l'inverse ($\tau > T$). La sonde est beaucoup trop lente : elle n'aura pas le temps de s'échauffer ni de se refroidir. L'amplitude mesurée sera fortement atténuée (filtrage passe-bas) et déphasée. Elle indiquera pratiquement la température moyenne du bain.

**4. Modifications physiques pour réduire $\tau$**
- Réduire la masse ou le volume du capteur (diminue la capacité thermique).
- Utiliser un matériau d'enveloppe plus fin ou avec une meilleure conductivité thermique (ex: métal plutôt que plastique, pour diminuer la résistance thermique).
- Augmenter la surface de contact avec le fluide.

---

## 1.3 Conception d'une chaîne de mesure

**1. Relation physique entre pression et hauteur**
La relation est donnée par la loi de la statique des fluides (pression hydrostatique) :
$$\Delta p = \rho \cdot g \cdot h$$
*(où $\Delta p$ est la différence de pression entre le fond et la surface en Pascal, $\rho$ la masse volumique de l'eau, $g$ la gravité et $h$ la hauteur).*

**2. Mesure directe ou indirecte ?**
C'est une mesure **indirecte**. Le capteur réagit à une pression (force sur une surface), et nous utilisons une loi physique pour en déduire mathématiquement une hauteur.

**3. Chaîne de mesure et sources d'erreurs**
- **Capteur (ex: membrane piézorésistive) :** Convertit la pression en déformation mécanique/variation de résistance. 
  - *Source d'erreur :* Dérive thermique (changement de température de l'eau), hystérésis de la membrane.
- **Mise en forme (ex: pont conditionneur et amplificateur) :** Convertit la variation du capteur en un signal électrique lisible (ex: 4-20 mA ou 0-10V).
  - *Source d'erreur :* Bruit électronique, non-linéarité de l'amplificateur, tension de décalage (offset).
- **Interface (ex: microcontrôleur avec ADC et afficheur) :** Numérise le signal et calcule la hauteur via la formule $\Delta p / (\rho g)$.
  - *Source d'erreur :* Erreur de quantification (résolution de l'ADC), erreur d'arrondi dans le calcul.

**4. Précision requise du capteur**
- **Méthode :** Utiliser la dérivée de la relation ou un simple calcul de variation : $\Delta p_{erreur} = \rho \cdot g \cdot \Delta h_{erreur}$.
- **Calcul absolu (en Pa) :** 
  Pour $\Delta h = 1 \text{ cm} = 0.01 \text{ m}$ :
  $$\Delta p = 1000 \text{ kg/m}^3 \cdot 9.81 \text{ m/s}^2 \cdot 0.01 \text{ m} = \mathbf{98.1 \text{ Pa}}$$
  Le capteur doit avoir une précision d'au moins $\pm 98.1 \text{ Pa}$.
- **Calcul relatif (en %) :**
  La hauteur maximale est de 3 m, ce qui correspond à une pression pleine échelle (Full Scale, FS) de :
  $$p_{max} = 1000 \cdot 9.81 \cdot 3 = 29430 \text{ Pa}$$
  L'erreur tolérée en pourcentage de la pleine échelle est :
  $$\frac{98.1}{29430} \approx 0.0033 = \mathbf{0.33\%}$$

---

## 1.4 Jouer avec les équations

**1. Distance Mars-Terre au moment de l'observation**
- **Méthode :** Utiliser la trigonométrie élémentaire (parallaxe). On a un triangle avec pour base $d = 7000 \text{ km}$ et un angle au sommet $\alpha = 25 \text{ secondes d'arc}$. Pour les très petits angles, la distance $D$ s'approxime par $D \approx \frac{d}{\alpha \text{ (en radians)}}$.
- **Calcul de l'angle en radians :**
  $$\alpha = \frac{25}{3600} \text{ degrés} = \left(\frac{25}{3600} \cdot \frac{\pi}{180}\right) \text{ rad} \approx 1.212 \cdot 10^{-4} \text{ rad}$$
- **Distance Terre-Mars ($D$) :**
  $$D = \frac{7000}{1.212 \cdot 10^{-4}} \approx \mathbf{5.77 \cdot 10^7 \text{ km}}$$ (soit environ 57.7 millions de km).

**2. Distance Terre-Soleil**
- **Méthode :** Lors de l'opposition, la Terre est exactement entre le Soleil et Mars. La distance Mars-Soleil ($r_M$) vaut donc : $r_M = r_T + D$ (où $r_T$ est la distance Terre-Soleil).
- D'après la 3ème loi de Kepler : $\frac{T_T^2}{r_T^3} = \frac{T_M^2}{r_M^3}$
- On peut réécrire ceci : $\left(\frac{r_T}{r_M}\right)^3 = \left(\frac{T_T}{T_M}\right)^2 \implies \frac{r_T}{r_M} = \left(\frac{T_T}{T_M}\right)^{2/3}$
- Posons $k = \left(\frac{T_T}{T_M}\right)^{2/3}$. Avec $T_T = 365.25 \text{ jours}$ et $T_M = 687 \text{ jours}$ :
  $$k = \left(\frac{365.25}{687}\right)^{2/3} \approx (0.5316)^{2/3} \approx 0.656$$
- On substitue $r_M = r_T + D$ :
  $$r_T = k \cdot (r_T + D) \implies r_T - k \cdot r_T = k \cdot D \implies r_T (1 - k) = k \cdot D$$
  $$r_T = D \cdot \frac{k}{1 - k}$$
- **Calcul final :**
  $$r_T = 5.77 \cdot 10^7 \cdot \frac{0.656}{1 - 0.656} = 5.77 \cdot 10^7 \cdot \frac{0.656}{0.344} \approx \mathbf{1.10 \cdot 10^8 \text{ km}}$$
*(Note : La valeur historique/réelle de l'unité astronomique est plus proche de 1.5e8 km. L'exercice utilise des valeurs simplifiées pour $d$, mais la méthode de déduction est celle attendue).*

**3. Vitesse de la Terre sur son orbite**
- **Méthode :** En supposant une orbite circulaire, la distance parcourue en une année ($T_T$) est le périmètre $2\pi r_T$.
- **Calcul :**
  La période orbitale en secondes est : $T_T = 365.25 \cdot 24 \cdot 3600 \approx 3.155 \cdot 10^7 \text{ s}$.
  $$v = \frac{2 \cdot \pi \cdot r_T}{T_T} = \frac{2 \cdot \pi \cdot 1.10 \cdot 10^8 \text{ km}}{3.155 \cdot 10^7 \text{ s}}$$
  $$v \approx \mathbf{21.9 \text{ km/s}}$$
*(Pour information, avec la vraie valeur de la distance Terre-Soleil, la vitesse réelle approche les 29.7 km/s).*
```eof