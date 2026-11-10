# Correction des Exercices d'Instrumentation : Erreurs de Mesure et Propagation

Ce document détaille la résolution des exercices du chapitre 3, qui portent sur l'analyse des erreurs de mesure, les calculs de propagation d'erreurs (par opérations et par linéarisation multivariable) et l'étude approfondie de fiches techniques industrielles.

---

## 1.1 Lecture d'une fiche technique (Capteur de pression)

**Données :** Plage de mesure de 0 à 10 bar, précision de $\pm 0.3\%$ FS (Full Scale / Pleine Échelle).

1. **Erreur absolue maximale sur toute la plage :**
   L'erreur de pleine échelle est fixe et calculée par rapport à l'étendue totale du capteur ($10\text{ bar}$).
   $$E_{abs} = \pm 0.3\% \times 10\text{ bar} = \pm 0.003 \times 10 = \mathbf{\pm 0.03\text{ bar}}$$

2. **Erreur relative réelle pour une mesure de 1 bar :**
   À $1\text{ bar}$, l'erreur absolue reste de $\pm 0.03\text{ bar}$ (car l'erreur FS est constante sur toute l'étendue). L'erreur relative par rapport à la valeur mesurée vaut alors :
   $$\epsilon_{rel} = \frac{0.03\text{ bar}}{1\text{ bar}} = 0.03 = \mathbf{\pm 3\%}$$

3. **Conseil si l'application mesure entre 0 et 1 bar :**
   Utiliser un capteur 0-10 bar pour mesurer seulement 1 bar dégrade fortement la précision relative ($\pm 3\%$ au lieu de $\pm 0.3\%$). Il est conseillé de **choisir un capteur dont la plage de mesure (FS) est mieux adaptée à l'application** (par exemple un capteur 0-1 bar ou 0-2 bar), afin de travailler près de la pleine échelle et de minimiser l'incertitude relative.

---

## 1.2 Propagation d'erreur par opérations (Puissance électrique)

**Données :** $P = U \cdot I$, avec $\epsilon_U = 0.5\%$ et $\epsilon_I = 1.2\%$.

1. **Expression de l'erreur relative $\epsilon_P$ sur la puissance :**
   Pour un produit de grandeurs $P = U \cdot I$, l'erreur relative maximale se calcule en additionnant les erreurs relatives des variables :
   $$\epsilon_P = \frac{\Delta P}{P} = \frac{\Delta U}{U} + \frac{\Delta I}{I} = \mathbf{\epsilon_U + \epsilon_I}$$
   Numériquement : $\epsilon_P = 0.5\% + 1.2\% = \mathbf{1.7\%}$.

2. **Calcul de $P$ et de l'erreur absolue $E_P$ pour $U = 230\text{ V}$ et $I = 4\text{ A}$ :**
   - Puissance nominale : $P = 230 \times 4 = \mathbf{920\text{ W}}$
   - Erreur absolue : $E_P = P \times \epsilon_P = 920 \times 0.017 = \mathbf{\pm 15.64\text{ W}}$

3. **Calcul de l'erreur relative $\epsilon_R$ pour la résistance $R = U / I$ :**
   Pour un quotient ou un produit, la règle de propagation des erreurs maximales est la même : on additionne les erreurs relatives.
   $$\epsilon_R = \frac{\Delta R}{R} = \frac{\Delta U}{U} + \frac{\Delta I}{I} = \epsilon_U + \epsilon_I = 1.7\%$$
   **Commentaire :** L'erreur relative $\epsilon_R$ est **égale** à l'erreur relative $\epsilon_P$. En effet, les formules de propagation d'erreur linéaire traitent de la même manière les multiplications et les divisions.

---

## 1.3 Propagation d'erreur par linéarisation (Système multivariable)

**Données :** $Q(\Delta p, \rho) = C_d \cdot A \sqrt{\frac{2\Delta p}{\rho}}$ avec $C_d = 0.62$, $A = 5 \cdot 10^{-4}\text{ m}^2$. 
Point de fonctionnement : $\Delta p_0 = 2000\text{ Pa}$, $\rho_0 = 1000\text{ kg/m}^3$. Erreurs : $E_{\Delta p} = 20\text{ Pa}$, $E_\rho = 5\text{ kg/m}^3$.

1. **Calcul de $Q_0$ :**
   $$Q_0 = 0.62 \times (5 \cdot 10^{-4}) \sqrt{\frac{2 \times 2000}{1000}} = 3.1 \cdot 10^{-4} \times \sqrt{4} = 3.1 \cdot 10^{-4} \times 2 = \mathbf{6.2 \cdot 10^{-4}\text{ m}^3\text{/s}}$$

2. **Calcul des sensibilités :**
   - $Q(\Delta p, \rho) = C_d A \sqrt{2} \cdot (\Delta p)^{1/2} \cdot (\rho)^{-1/2}$
   - Sensibilité à $\Delta p$ : 
     $$S_{\Delta p} = \frac{\partial Q}{\partial \Delta p} = C_d A \sqrt{2} \cdot \frac{1}{2} (\Delta p)^{-1/2} \rho^{-1/2} = \frac{C_d A}{\sqrt{2 \Delta p \cdot \rho}}$$
     En substituant les valeurs au point 0 :
     $$S_{\Delta p} = \frac{0.62 \times 5 \cdot 10^{-4}}{\sqrt{2 \times 2000 \times 1000}} = \frac{3.1 \cdot 10^{-4}}{\sqrt{4 \cdot 10^6}} = \frac{3.1 \cdot 10^{-4}}{2000} = \mathbf{1.55 \cdot 10^{-7}\text{ m}^3\text{/(s}\cdot\text{Pa)}}$$
   - Sensibilité à $\rho$ :
     $$S_\rho = \frac{\partial Q}{\partial \rho} = C_d A \sqrt{2} (\Delta p)^{1/2} \cdot \left(-\frac{1}{2}\right) \rho^{-3/2} = -\frac{C_d A \sqrt{\Delta p}}{\sqrt{2} \rho^{3/2}}$$
     En substituant les valeurs au point 0 :
     $$S_\rho = -\frac{0.62 \times 5 \cdot 10^{-4} \times \sqrt{2000}}{\sqrt{2} \times (1000)^{3/2}} = -\frac{3.1 \cdot 10^{-4} \times 44.721}{1.414 \times 31622.78} \approx -\frac{0.01386}{44712.8} = \mathbf{-3.1 \cdot 10^{-7}\text{ m}^3\text{/(s}\cdot\text{kg/m}^3\text{)}}$$

3. **Erreur absolue $E_Q$ et relative $\epsilon_Q$ :**
   Par la méthode de propagation par linéarisation (somme quadratique ou addition linéaire des incertitudes absolues maximales) :
   $$E_Q = \vert{}S_{\Delta p}\vert{} \cdot E_{\Delta p} + \vert{}S_\rho\vert{} \cdot E_\rho$$
   $$E_Q = (1.55 \cdot 10^{-7} \times 20) + (3.1 \cdot 10^{-7} \times 5) = 3.1 \cdot 10^{-6} + 1.55 \cdot 10^{-6} = \mathbf{4.65 \cdot 10^{-6}\text{ m}^3\text{/s}}$$
   Erreur relative :
   $$\epsilon_Q = \frac{E_Q}{Q_0} = \frac{4.65 \cdot 10^{-6}}{6.2 \cdot 10^{-4}} = \mathbf{0.0075 = 0.75\%}$$

4. **Source d'erreur dominante et conseils :**
   - Contribution de $\Delta p$ : $3.1 \cdot 10^{-6}$ (soit 67% de l'erreur totale).
   - Contribution de $\rho$ : $1.55 \cdot 10^{-6}$ (soit 33% de l'erreur totale).
   - **Source dominante :** C'est la mesure de la perte de charge ($\Delta p$).
   - **Conseil :** Pour améliorer la précision globale, il faut en priorité utiliser un capteur de pression différentielle $\Delta p$ plus précis.

---

## 1.4 Lecture d'une fiche technique et conception de la chaîne de mesure (PT-500X)

**Contexte :** Pression variable de 0 à 8 bar. Objectif d'erreur $< 2\%$ à 8 bar. Température ambiante $15^\circ\text{C}$ à $35^\circ\text{C}$. ADC 16 bits (0-5 V), résistance de précision de $250\text{ }\Omega$ ($\pm 0.1\%$).

1. **Paramètres indispensables vs ignorés :**
   - *Indispensables :* Plage de mesure (10 bar), type de sortie (4-20 mA), précision ($\pm 0.25\%$ / $\pm 0.5\%$), dérive thermique du zéro et du span, température de référence ($25^\circ\text{C}$).
   - *Exemple de 3 paramètres ignorés :* 
     1. La température de stockage (inutile car l'appareil est en service, pas stocké).
     2. L'indice de protection IP67 (donne l'étanchéité physique mais n'intervient pas dans les calculs métrologiques de précision de la chaîne).
     3. Le poids de 150 g (n'influence pas la