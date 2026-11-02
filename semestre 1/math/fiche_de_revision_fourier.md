# Fiche de Révision : Analyse 3 - Séries 21 à 24

## 0. Rappels Mathématiques Préalables

### Intégration et Primitives usuelles

* **Intégration par parties (IPP) :** Très utile pour intégrer des fonctions comme $t \sin(t)$ ou $t e^t$.
  

  $$
  \int u' v = u v - \int u v'
  $$

* **Primitives courantes :**

  * $\int \cos(at) dt = \frac{1}{a} \sin(at)$

  * $\int \sin(at) dt = -\frac{1}{a} \cos(at)$

  * $\int e^{at} dt = \frac{1}{a} e^{at}$

### Identités Trigonométriques et Nombres Complexes

* **Formules d'Euler :** $\cos(t) = \frac{e^{it} + e^{-it}}{2}$ et $\sin(t) = \frac{e^{it} - e^{-it}}{2i}$

* **Puissances (Linéarisation) :** $\cos^2(t) = \frac{1 + \cos(2t)}{2}$ et $\sin^2(t) = \frac{1 - \cos(2t)}{2}$

* **Module et Argument :** Pour un nombre complexe $Z = a + ib$, le module est $\vert{}Z\vert{} = \sqrt{a^2 + b^2}$ et la phase (argument) est $\arg(Z) = \arctan(\frac{b}{a})$ (en ajustant selon le quadrant).

### Limites (Règle de L'Hôpital)

Lorsqu'un calcul de limite donne une forme indéterminée comme $\frac{0}{0}$ (très fréquent pour étudier la continuité des transformées de Fourier), on dérive le numérateur et le dénominateur :

$$
\lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)}
$$

## 1. Séries de Fourier réelles (Série 21)

### Formule générale et calcul des coefficients

Le développement d'une fonction $T$-périodique s'écrit :

$$
f(t) \sim \frac{a_0}{2} + \sum_{k=1}^{\infty} \left( a_k \cos(k\omega_0 t) + b_k \sin(k\omega_0 t) \right) \quad \text{avec} \quad \omega_0 = \frac{2\pi}{T}
$$

* **Exemple (Série 21, Ex 4) :** Pour la fonction $2\pi$-périodique définie par $f(t) = 2t + 1$ sur $]-\pi, \pi]$, on calcule :

  * $a_0 = 2$ (la valeur moyenne est $1$, donc $a_0/2 = 1$).

  * $a_k = 0$ (pour $k \ge 1$).

  * $b_k = -4 \frac{(-1)^k}{k}$.

  * **Résultat :** $g(t) \sim 1 - 4 \sum_{k=1}^{\infty} \frac{(-1)^k}{k} \sin(kt)$.

### Translations et changements d'échelle

Déduire une nouvelle série sans recalculer d'intégrale en appliquant un décalage temporel ou un facteur d'échelle à une série connue.

* **Exemple (Série 21, Ex 3) :** On connaît $f_1(t) = -\frac{4}{\pi} \sum_{k \text{ impair}} \frac{1}{k^2} \cos(kt)$.
  Pour $f_4(t)$ qui est décalée d'un quart de période : $f_4(t) = f_1(t + \frac{\pi}{2})$.
  On remplace $t$ par $(t + \frac{\pi}{2})$ dans la somme, ce qui donne $\cos(k(t + \frac{\pi}{2}))$, et par trigonométrie on déduit :
  **Résultat :** $f_4(t) = \frac{4}{\pi} \sum_{l=0}^{\infty} \frac{(-1)^l}{(2l+1)^2} \sin((2l+1)t)$.

### Évaluation de séries numériques infinies

En choisissant un $t$ astucieux, la série de Fourier devient une somme numérique dont on connaît le résultat exact.

* **Exemple (Série 21, Ex 7) :** $f(t) = t(\pi-t)$. Sa série est $f(t) \sim \frac{8}{\pi} \sum_{k \text{ impair}} \frac{1}{k^3} \sin(kt)$.
  On évalue en $t = \frac{\pi}{2}$ : la fonction vaut $f(\frac{\pi}{2}) = \frac{\pi}{2}(\pi - \frac{\pi}{2}) = \frac{\pi^2}{4}$.
  Dans la série, $\sin(k\frac{\pi}{2})$ alterne entre $1$ et $-1$ pour les $k$ impairs.
  **Résultat :** $\frac{\pi^2}{4} = \frac{8}{\pi} \left( 1 - \frac{1}{3^3} + \frac{1}{5^3} - \dots \right) \implies 1 - \frac{1}{3^3} + \frac{1}{5^3} - \dots = \frac{\pi^3}{32}$.

## 2. Séries complexes et Théorème de Parseval (Série 22)

### Forme complexe

Série sous forme exponentielle : $f(t) \sim \sum_{k=-\infty}^{\infty} c_k e^{ik\omega_0 t}$ où $c_k = \frac{1}{T} \int_0^T f(t) e^{-ik\omega_0 t} dt$.

* **Exemple (Série 22, Ex 3) :** $f(t) = e^t$ pour $t \in [0, 1[$, donc $T=1$ et $\omega_0 = 2\pi$.
  $c_k = \int_0^1 e^t e^{-ik2\pi t} dt = \int_0^1 e^{(1-ik2\pi)t} dt = \frac{e^{(1-ik2\pi)} - 1}{1-ik2\pi}$.
  En simplifiant (car $e^{-ik2\pi} = 1$), on obtient $c_k = \frac{e-1}{1-ik2\pi}$.
  **Résultat :** En multipliant par le conjugué, $c_k = \frac{(e-1)(1+i k 2\pi)}{1+4k^2\pi^2}$.

### Théorème de Parseval et proportions de puissance

La puissance moyenne du signal correspond à la somme des puissances de ses harmoniques : $P_m = \sum \vert{}c_k\vert{}^2$ ou $P_m = P_0 + \sum_{k=1}^{\infty} P_k$.

* **Exemple (Série 22, Ex 1) :** Pour une tension triangulaire $U(t)$ sur une résistance $R$.
  Puissance moyenne totale calculée par l'intégrale : $P_m = \frac{1}{3}\frac{U_0^2}{R}$.
  Puissance de la composante continue ($k=0$) : $P_0 = \frac{1}{4}\frac{U_0^2}{R}$.
  **Proportion :** $\frac{P_0}{P_m} = \frac{1/4}{1/3} = \frac{3}{4} = 75\%$. La composante fondamentale ($k=1$) contient $\approx 24.6\%$.

### Filtre passe-bas idéal

Le filtre supprime toutes les fréquences $\vert{}\omega\vert{} > \omega_c$.

* **Exemple (Série 22, Ex 4) :** Entrée $x(t) = 2 - \frac{16}{\pi^2} \sum_{k \text{ impair}} \frac{1}{k^2} \cos(\frac{k\pi t}{4})$.
  Le filtre laisse passer $\vert{}\omega\vert{} \le 2$. Les pulsations sont $\omega_k = k\frac{\pi}{4}$.
  Pour $k=0, \omega=0 \le 2$. Pour $k=1, \omega_1 = \frac{\pi}{4} \approx 0.78 \le 2$. Pour $k=3, \omega_3 = \frac{3\pi}{4} \approx 2.35 > 2$.
  **Résultat :** Seuls les termes $k=0$ et $k=1$ survivent. Sortie $y(t) = 2 - \frac{16}{\pi^2} \cos(\frac{\pi t}{4})$.

## 3. Réponse fréquentielle des systèmes (Série 23)

### Fonction de transfert $H(\omega)$

On l'obtient en remplaçant l'entrée par $e^{i\omega t}$ et la sortie par $H(\omega)e^{i\omega t}$ (et ses dérivées par $i\omega H(\omega)e^{i\omega t}$, etc.) dans l'équation différentielle.

* **Exemple (Série 23, Ex 1) :** Équation du système : $y''(t) + 5y'(t) + 4y(t) = 4x(t)$.
  En substituant : $H(\omega)(i\omega)^2 e^{i\omega t} + 5H(\omega)(i\omega)e^{i\omega t} + 4H(\omega)e^{i\omega t} = 4e^{i\omega t}$.
  **Résultat :** $H(\omega) = \frac{4}{-\omega^2 + 5i\omega + 4}$.

### Solution stationnaire (Sortie)

Si l'entrée est $A \cos(\omega_0 t)$, la sortie est $A \cdot \vert{}H(\omega_0)\vert{} \cos(\omega_0 t + \arg(H(\omega_0)))$.

* **Exemple (Série 23, Ex 1b) :** L'entrée est $x(t) = \cos(t)$. Ici $\omega_0 = 1$.
  On évalue $H(1) = \frac{4}{-(1)^2 + 5i(1) + 4} = \frac{4}{3 + 5i}$.
  Module (Gain) : $\vert{}H(1)\vert{} = \frac{4}{\sqrt{3^2 + 5^2}} = \frac{4}{\sqrt{34}} \approx 0.68599$.
  Phase (Déphasage) : L'angle du numérateur est $0$. L'angle du dénominateur est $\arctan(5/3) \approx 1.0304$ rad. Donc $\arg(H(1)) = 0 - 1.0304 = -1.0304$.
  **Résultat :** $y(t) = 0.68599 \cos(t - 1.0304)$.

## 4. Transformée de Fourier (Série 24)

### Calcul de la Transformée

La fonction passe du domaine temporel au domaine fréquentiel : $\hat{f}(\omega) = \int_{-\infty}^{\infty} f(t) e^{-i\omega t} dt$.

* **Exemple (Série 24, Ex 1) :** $f(t) = e^{-\vert{}t\vert{}}$.
  En séparant l'intégrale en deux (de $-\infty$ à $0$ puis de $0$ à $\infty$) et en intégrant des exponentielles :
  **Résultat :** $\hat{f}(\omega) = \frac{2}{\omega^2 + 1}$.

### Intégrale de Fourier (Transformée inverse)

Permet de retrouver le signal original : $f(t) = \frac{1}{2\pi} \int_{-\infty}^{\infty} \hat{f}(\omega) e^{i\omega t} d\omega$. Très utile pour évaluer des intégrales compliquées.

* **Exemple (Suite Ex 1) :** On applique la formule inverse à $\hat{f}(\omega) = \frac{2}{\omega^2 + 1}$.
  $e^{-\vert{}t\vert{}} = \frac{1}{2\pi} \int_{-\infty}^{\infty} \frac{2}{\omega^2 + 1} e^{i\omega t} d\omega$.
  Comme la fonction $\frac{2}{\omega^2 + 1}$ est paire, on peut réduire de $0$ à l'infini en multipliant par 2, et la partie imaginaire ($\sin$) s'annule.
  **Résultat :** $e^{-\vert{}t\vert{}} = \frac{2}{\pi} \int_0^{\infty} \frac{\cos(\omega t)}{\omega^2 + 1} d\omega$.

### Continuité et Limites

Parfois le calcul donne une expression non définie en un point (souvent $\omega = 0$ ou $\omega = 1$). On doit calculer l'intégrale originelle à ce point et utiliser la règle de L'Hôpital sur la formule trouvée pour vérifier la continuité.

* **Exemple (Série 24, Ex 2) :** $\hat{f}(\omega) = \frac{2\omega \sin(\pi\omega)}{1-\omega^2}$ (non défini pour $\omega = \pm 1$).
  Calcul par l'intégrale à $\omega = 1$ : $\hat{f}(1) = \int_{-\pi}^{\pi} \cos(t) e^{-it} dt = \pi$.
  Calcul de limite par L'Hôpital : $\lim_{\omega \to 1} \frac{2\omega \sin(\pi\omega)}{1-\omega^2} = \lim_{\omega \to 1} \frac{2\sin(\pi\omega) + 2\pi\omega\cos(\pi\omega)}{-2\omega} = \frac{0 + 2\pi(-1)}{-2} = \pi$.
  **Résultat :** La limite correspond à la valeur au point, donc $\hat{f}(\omega)$ est continue en $\omega = 1$.