# Correction des Exercices d'Instrumentation : Caractéristiques statiques et linéarisation

Ce document détaille la résolution des exercices du chapitre 2 portant sur la linéarisation de capteurs, les convertisseurs ADC et l'étude du bruit thermique.

---

## 1.1 Linéarisation d'une fonction simple

**Donnée :** Caractéristique non linéaire $Q(v) = 2 \cdot v^2$. Point de fonctionnement $v_0 = 2 \text{ [m/s]}$.

**1. Linéarisation autour de $v_0 = 2 \text{ [m/s]}$**
La linéarisation d'une fonction $f(x)$ autour d'un point $x_0$ utilise le développement de Taylor au premier ordre :
$$Q_{lin}(v) = Q(v_0) + \left. \frac{dQ}{dv} \right|_{v=v_0} \cdot (v - v_0)$$
- Valeur au point de fonctionnement : $Q(2) = 2 \cdot (2)^2 = 8 \text{ [m}^3\text{/s]}$
- Dérivée (pente) : $\frac{dQ}{dv} = 4 \cdot v$
- Pente en $v_0$ : $\left. \frac{dQ}{dv} \right|_{v=2} = 4 \cdot 2 = 8$
**Expression linéarisée :** 
$$Q_{lin}(v) = 8 + 8 \cdot (v - 2) = \mathbf{8v - 8}$$

**2. Calcul de $Q_{lin}(2.2)$ et erreur relative**
- **Valeur exacte :** $Q(2.2) = 2 \cdot (2.2)^2 = 2 \cdot 4.84 = \mathbf{9.68}$
- **Valeur linéarisée :** $Q_{lin}(2.2) = 8 \cdot (2.2) - 8 = 17.6 - 8 = \mathbf{9.60}$
- **Erreur absolue :** $E_{abs} = |Q(2.2) - Q_{lin}(2.2)| = |9.68 - 9.60| = 0.08$
- **Erreur relative :** $\epsilon_{rel} = \frac{E_{abs}}{Q(2.2)} = \frac{0.08}{9.68} \approx 0.00826 = \mathbf{0.83\%}$

---

## 1.2 Linéarisation d'une thermistance CTP

**Données :** $R(T) = R_0(1 + \alpha T + \beta T^2)$, avec $R_0 = 1000 \text{ } \Omega$, $\alpha = 7.5 \cdot 10^{-3} \text{ [1/}^\circ\text{C]}$, $\beta = 1.0 \cdot 10^{-5} \text{ [1/}^\circ\text{C}^2]$. $T_0 = 20^\circ\text{C}$.

**1. Linéarisation autour de $T_0 = 20^\circ\text{C}$**
- Valeur exacte en $T_0$ :
  $$R(20) = 1000 \cdot (1 + 7.5\cdot 10^{-3} \cdot 20 + 1.0\cdot 10^{-5} \cdot 400) = 1000 \cdot (1 + 0.15 + 0.004) = \mathbf{1154 \text{ } \Omega}$$
- Dérivée par rapport à T : $R'(T) = R_0(\alpha + 2\beta T)$
- Pente S en $T_0$ :
  $$S = R'(20) = 1000 \cdot (7.5\cdot 10^{-3} + 2 \cdot 1.0\cdot 10^{-5} \cdot 20) = 1000 \cdot (0.0075 + 0.0004) = \mathbf{7.9 \text{ } \Omega/^\circ\text{C}}$$
- **Expression linéarisée :**

𝐿 𝑥 = 𝑓 𝑎 + 𝑓′(𝑎)(𝑥 − 𝑎)
  $$R_{lin}(T) = R(20) + S \cdot (T - 20) = \mathbf{1154 + 7.9 \cdot (T - 20)}$$

**2. Erreur absolue et relative à $T = 80^\circ\text{C}$**
- **Valeur exacte :** $R(80) = 1000 \cdot (1 + 7.5\cdot 10^{-3} \cdot 80 + 1.0\cdot 10^{-5} \cdot 80^2) = 1000 \cdot (1 + 0.6 + 0.064) = 1664 \text{ } \Omega$
- **Valeur linéarisée :** $R_{lin}(80) = 1154 + 7.9 \cdot (80 - 20) = 1154 + 474 = 1628 \text{ } \Omega$
- **Erreur absolue :** $E_{abs} = |1664 - 1628| = \mathbf{36 \text{ } \Omega}$
- **Erreur relative :** $\epsilon_{rel} = \frac{36}{1664} \approx 0.0216 = \mathbf{2.16\%}$

**3. Plage de température pour une erreur relative < 1%**
L'écart entre le modèle exact et la linéarisation correspond au terme du 2e ordre du développement de Taylor : $E_{abs}(T) = R_0 \cdot \beta \cdot (T - T_0)^2 = 1000 \cdot 10^{-5} \cdot (T - 20)^2 = 0.01 \cdot (T - 20)^2$.
On cherche T tel que $\frac{E_{abs}(T)}{R(T)} < 0.01$. En approximant $R(T)$ par sa valeur centrale $R(20) = 1154 \text{ } \Omega$ pour simplifier :
$$\frac{0.01 \cdot (T - 20)^2}{1154} < 0.01 \implies (T - 20)^2 < 1154 \implies |T - 20| < 34^\circ\text{C}$$
La plage est donc approximativement **$[-14^\circ\text{C} ; +54^\circ\text{C}]$**.

---

## 1.3 Convertisseur analogique-numérique (ADC)

1. **Classe de mesure :** Il s'agit d'une mesure **indirecte par comparaison** (ou mesure discrète/quantifiée). Le convertisseur compare la tension d'entrée à une référence interne pour en déduire un code numérique.
2. **Tension d'entrée en fonction du code :**
   $$V_{in} = \text{Code} \cdot \frac{V_{ref}}{2^N}$$
3. **Plus petite variation détectable (Résolution ou LSB) :**
   $$\Delta V_{min} = \text{1 LSB} = \frac{V_{ref}}{2^N}$$

---

## 1.4 Bruit thermique sur une mesure de température (NTC)

### 1.4.1 Modélisation du bruit
**1.1 Unités de base et homogénéité de $e_n = \sqrt{4k_B T R \Delta f}$**
- $k_B$ en Joules/Kelvin [J/K]. Or, $1\text{ J} = 1\text{ W}\cdot\text{s} = 1\text{ V}\cdot\text{A}\cdot\text{s}$.
- $T$ en Kelvin [K].
- $R$ en Ohms [$\Omega$], ce qui équivaut à [V/A].
- $\Delta f$ en Hertz [Hz], ce qui équivaut à [1/s].
*Vérification sous la racine :* $(V \cdot A \cdot s / K) \cdot K \cdot (V / A) \cdot (1 / s) = V^2$.
La racine carrée de $V^2$ donne bien des **Volts [V]**. L'équation est homogène.

**1.2 Calcul numérique de $e_n$**
Avec $R=10^4 \text{ } \Omega$, $T=25^\circ\text{C} = 298.15 \text{ K}$, $\Delta f = 1000 \text{ Hz}$ :
$e_n = \sqrt{4 \cdot 1.381\cdot 10^{-23} \cdot 298.15 \cdot 10000 \cdot 1000} \approx \sqrt{1.647 \cdot 10^{-13}} \approx 4.06 \cdot 10^{-7} \text{ V} = \mathbf{0.41 \text{ }\mu\text{V}}$
L'ordre de grandeur est le **microvolt ($\mu$V)**.

### 1.4.2 Influence du bruit sur la sortie du pont
**2.1 Théorème de superposition**
- Contribution de $R_{pull}$ : $v_{out1} = e_{n,pull} \cdot \frac{R_{ntc}}{R_{pull} + R_{ntc}}$
- Contribution de $R_{ntc}$ : $v_{out2} = e_{n,ntc} \cdot \frac{R_{pull}}{R_{pull} + R_{ntc}}$
*(Note : Vcc étant idéal, il est vu comme une masse dynamique (0V) pour les signaux de bruit).*

**2.2 Sommation quadratique**
On somme les carrés car les deux sources de bruit (agitation thermique dans deux résistances distinctes) sont des événements aléatoires **décorrélés** (indépendants). La variance totale est la somme des variances. L'équation de $R_{eq}$ prouve que le bruit perçu au point milieu équivaut au bruit généré par la résistance équivalente de Thévenin ($R_{pull} // R_{ntc}$).

**2.3 Calcul de $e_{n,out}$ à $25^\circ\text{C}$**
À $25^\circ\text{C}$, $R_{ntc} = R_{25} = 10 \text{ k}\Omega$. $R_{eq} = \frac{10\text{k} \cdot 10\text{k}}{10\text{k} + 10\text{k}} = 5 \text{ k}\Omega$.
En utilisant la formule du bruit avec $R = 5000 \text{ } \Omega$ :
$e_{n,out} = e_n(10k\Omega) / \sqrt{2} \approx 4.06 \cdot 10^{-7} / 1.414 \approx \mathbf{2.87 \cdot 10^{-7} \text{ V} = 0.287 \text{ }\mu\text{V}}$

### 1.4.3 Calcul de l'influence du bruit sur la mesure
**3.1 Expression de $V_{out}$**
$$V_{out} = V_{cc} \cdot \frac{R_{ntc}(T)}{R_{pull} + R_{ntc}(T)}$$

**3.2 Sensibilité $S(T)$ (règle de la chaîne)**
- Dérivée du pont diviseur : $\frac{dV_{out}}{dR_{ntc}} = V_{cc} \cdot \frac{R_{pull}}{(R_{pull} + R_{ntc})^2}$
- Dérivée de la NTC : $\frac{dR_{ntc}}{dT} = R_{25} \exp\left(\beta\left(\frac{1}{T}-\frac{1}{T_{25}}\right)\right) \cdot \left(-\frac{\beta}{T^2}\right) = -\frac{\beta}{T^2} \cdot R_{ntc}(T)$

**3.3 Incertitude $\sigma_T$**
Si $\Delta V = S(T) \cdot \Delta T$, alors une variation de tension due au bruit ($e_{n,out}$) engendre une incertitude sur la température mesurée correspondante :
$$\sigma_T(T) = \frac{e_{n,out}(T)}{|S(T)|}$$

**3.4 Calcul numérique à $T = 25^\circ\text{C}$ (298 K)**
- $\frac{dV_{out}}{dR_{ntc}} = 3.3 \cdot \frac{10000}{(20000)^2} = 3.3 \cdot \frac{1}{40000} = 8.25 \cdot 10^{-5} \text{ V/}\Omega$
- $\frac{dR_{ntc}}{dT} = -\frac{3965}{298^2} \cdot 10000 \approx -44.65 \text{ }\Omega\text{/K}$
- $S(298) = 8.25 \cdot 10^{-5} \cdot (-44.65) \approx -0.00368 \text{ V/K} = -3.68 \text{ mV/K}$
- $\sigma_T = \frac{0.287 \cdot 10^{-6} \text{ V}}{0.00368 \text{ V/K}} \approx 7.8 \cdot 10^{-5} \text{ K} = \mathbf{78 \text{ }\mu\text{K}}$

**Commentaire :** L'incertitude due au bruit thermique fondamental est de l'ordre de $78\text{ }\mu\text{K}$. C'est une valeur extrêmement négligeable comparée aux autres sources d'erreur classiques : la tolérance intrinsèque de la thermistance (souvent $\pm 0.1^\circ\text{C}$ ou $\pm 0.2^\circ\text{C}$) ou l'erreur de quantification de l'ADC (un ADC 12 bits sur 3.3V donne des pas d'environ 0.8 mV, ce qui équivaut à un pas de température d'environ 0.2°C).
```eof
