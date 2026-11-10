# Fiche de Révision : Analyse 3 - Séries 21 à 24

## 1. Lexique des Formules Utiles

Ce lexique regroupe toutes les formules fondamentales à connaître par cœur pour aborder les exercices.

### 1.1 Séries de Fourier Réelles (Période $T$, Pulsation $\omega_0 = \frac{2\pi}{T}$)

- **Développement :**
    
    $$f(t) \sim \frac{a_0}{2} + \sum_{k=1}^{\infty} \left( a_k \cos(k\omega_0 t) + b_k \sin(k\omega_0 t) \right)$$
- **Coefficients (Formules générales sur une période complète) :** _À utiliser par défaut si la fonction n'a pas de symétrie particulière, en intégrant sur_ $[0, T]$ _ou_ $[-\frac{T}{2}, \frac{T}{2}]$_._
    
    $$a_0 = \frac{2}{T} \int_{0}^{T} f(t) dt \quad ; \quad a_k = \frac{2}{T} \int_{0}^{T} f(t) \cos(k\omega_0 t) dt \quad ; \quad b_k = \frac{2}{T} \int_{0}^{T} f(t) \sin(k\omega_0 t) dt$$
- **Formules de la demi-période (Pour les fonctions Paires ou Impaires) :**
    
    > 💡 **QUAND L'UTILISER ?** Tu **dois** utiliser ces formules dans deux situations précises :
    > 
    > 1. Pour **gagner du temps** lorsque l'énoncé précise (ou que tu remarques) que la fonction est paire ou impaire.
    >     
    > 2. **OBLIGATOIREMENT** lorsque l'expression mathématique de ta fonction (ex: $f(t) = t(\pi - t)$) n'est définie **que sur une demi-période** (ex: sur $[0, \pi]$ pour une période de $2\pi$). _Le principe : on calcule l'intégrale sur la moitié du graphe (de_ $0$ _à_ $T/2$_) et on multiplie par 2 (ce qui transforme le_ $\frac{2}{T}$ _en_ $\frac{4}{T}$_)._
    >     
    
    - **Si** $f(t)$ **est PAIRE** ($b_k = 0$, la série n'a que des cosinus) :
        
        $$a_0 = \frac{4}{T} \int_{0}^{T/2} f(t) dt \quad \text{et} \quad a_k = \frac{4}{T} \int_{0}^{T/2} f(t) \cos(k\omega_0 t) dt$$
    - **Si** $f(t)$ **est IMPAIRE** ($a_0 = 0$ et $a_k = 0$, la série n'a que des sinus) :
        
        $$b_k = \frac{4}{T} \int_{0}^{T/2} f(t) \sin(k\omega_0 t) dt$$

### 1.2 Séries de Fourier Complexes

- **Développement :**
    
    $$f(t) \sim \sum_{k=-\infty}^{\infty} c_k e^{ik\omega_0 t}$$
- **Coefficients :**
    
    $$c_k = \frac{1}{T} \int_{0}^{T} f(t) e^{-ik\omega_0 t} dt$$

### 1.3 Théorème de Parseval (Puissance moyenne $P_m$)

- **Forme complexe :**  
    
    $$P_m = \frac{1}{T} \int_{0}^{T} \vert{}f(t)\vert{}^2 dt = \sum_{k=-\infty}^{\infty} \vert{}c_k\vert{}^2$$
- **Forme réelle :**  
    
    $$P_m = \frac{a_0^2}{4} + \sum_{k=1}^{\infty} \frac{a_k^2 + b_k^2}{2} = P_0 + \sum_{k=1}^{\infty} P_k$$

### 1.4 Systèmes et Réponse Fréquentielle

- **Sortie stationnaire** $y(t)$ **pour une entrée** $x(t) = A \cos(\omega_0 t)$ **:**  
    
    $$y(t) = A \cdot \vert{}H(\omega_0)\vert{} \cos(\omega_0 t + \arg(H(\omega_0)))$$
    
    _(Où_ $H(\omega)$ _est la fonction de transfert du système)._
    

### 1.5 Transformée de Fourier

- **Transformée (Directe) :**  
    
    $$\hat{f}(\omega) = \int_{-\infty}^{\infty} f(t) e^{-i\omega t} dt$$
- **Intégrale de Fourier (Transformée Inverse) :**  
    
    $$f(t) = \frac{1}{2\pi} \int_{-\infty}^{\infty} \hat{f}(\omega) e^{i\omega t} d\omega$$
- **Égalité de Parseval (Énergie) :**  
    
    $$\int_{-\infty}^{\infty} \vert{}f(t)\vert{}^2 dt = \frac{1}{2\pi} \int_{-\infty}^{\infty} \vert{}\hat{f}(\omega)\vert{}^2 d\omega$$

## 2. Rappels Mathématiques Préalables

**Parité des Fonctions (TRÈS IMPORTANT pour simplifier les calculs) :**

### 2.1 Simplifications Trigonométriques et Exponentielles ($k \in \mathbb{Z}$)

Lors de l'évaluation des bornes d'intégration (en $\pi$, $2\pi$, etc.), utilise directement ces réflexes de simplification :

- **Sinus et Cosinus entiers :**
    
    - $\sin(k\pi) = 0 \quad \forall k \in \mathbb{Z}$  
        
    - $\cos(k\pi) = (-1)^k$  
        
        - Si $k$ est **pair** ($k = 2l$) $\implies \cos(2l\pi) = 1$  
            
        - Si $k$ est **impair** ($k = 2l+1$) $\implies \cos((2l+1)\pi) = -1$  
            
    - $\sin(2k\pi) = 0 \quad$ et $\quad \cos(2k\pi) = 1$  
        
- **Angles en quart de période (**$\frac{k\pi}{2}$**) :**
    
    - $\sin\left(\frac{k\pi}{2}\right) = \begin{cases} 0 & \text{si } k \text{ est pair} \\ (-1)^l & \text{si } k = 2l+1 \text{ est impair (donne } 1, -1, 1, -1 \dots\text{)} \end{cases}$  
        
    - $\cos\left(\frac{k\pi}{2}\right) = \begin{cases} (-1)^l & \text{si } k = 2l \text{ est pair} \\ 0 & \text{si } k = 2l+1 \text{ est impair} \end{cases}$  
        
- **Forme Complexe (Euler) :**
    
    - $e^{ik\pi} = \cos(k\pi) + i\sin(k\pi) = (-1)^k$  
        
    - $e^{-ik\pi} = (-1)^k$  
        
    - $e^{i2k\pi} = 1$  
        
    - $e^{-i2k\pi} = 1$


- **Fonction Paire :** $f(-t) = f(t)$. Graphe symétrique par rapport à l'axe des ordonnées (Ex: $\cos(t)$, $t^2$, $\vert{}t\vert{}$).
    
    - _Conséquence Fourier :_ Tous les $b_k = 0$. La série ne contient que des cosinus.
        
    - _Astuce intégrale :_ $\int_{-a}^{a} f(t) dt = 2 \int_{0}^{a} f(t) dt$.
        
- **Fonction Impaire :** $f(-t) = -f(t)$. Graphe symétrique par rapport à l'origine (Ex: $\sin(t)$, $t$, $t^3$).
    
    - _Conséquence Fourier :_ $a_0 = 0$ et tous les $a_k = 0$. La série ne contient que des sinus.
        
    - _Astuce intégrale :_ Sur un intervalle symétrique $[-a, a]$, $\int_{-a}^{a} f(t) dt = 0$.
        
- _Règles de multiplication :_ Paire $\times$ Paire = Paire ; Impaire $\times$ Impaire = Paire ; Paire $\times$ Impaire = Impaire. _(Utile pour savoir si_ $f(t)\cos(k\omega_0t)$ _ou_ $f(t)\sin(k\omega_0t)$ _s'annule sur_ $[-T/2, T/2]$_)._

- **Intégration par parties (IPP) :** $\int u' v = u v - \int u v'$  
- **La règle d'or :** Tu dois **dériver** $u$ (pour en réduire la complexité, souvent le polynôme) et **intégrer** $dv$ (souvent la fonction trigonométrique ou exponentielle). Ne jamais intégrer les deux !

- **Cas 1 : L'IPP simple (Ex:** $\int_0^{2\pi} \pi t \sin(kt) dt$**)**
    
    1. Choisir $u$ (à dériver) : $u = \pi t \implies du = \pi \, dt$. _(Attention : ne pas l'intégrer en_ $\frac{\pi t^2}{2}$_)_.
        
    2. Choisir $dv$ (à intégrer) : $dv = \sin(kt) \, dt \implies v = -\frac{1}{k}\cos(kt)$. _(Attention au signe négatif et à la division par_ $k$_)_.
        
    3. Construire la formule $[u \cdot v] - \int v \, du$ :  
        
        $$\left[ -\frac{\pi t}{k} \cos(kt) \right]_0^{2\pi} - \int_0^{2\pi} \left( -\frac{\pi}{k} \cos(kt) \right) dt$$
        
        _Il ne reste plus qu'à évaluer le crochet et calculer l'intégrale très simple du cosinus._
        
- **Cas 2 : L'IPP en cascade (Ex:** $\int t^2 \sin(kt) dt$**)** Lorsqu'on a un $t^2$, une seule IPP laissera un $t$ dans l'intégrale. Il faut répéter l'opération pour faire disparaître totalement le polynôme en le dérivant successivement ($t^2 \rightarrow 2t \rightarrow 2$).
    
    1. **Première IPP :** $u_1 = t^2 \implies du_1 = 2t \, dt$ et $dv_1 = \sin(kt) dt \implies v_1 = -\frac{1}{k}\cos(kt)$. On obtient : $\left[ -\frac{t^2}{k} \cos(kt) \right] - \int \left( -\frac{2t}{k} \cos(kt) \right) dt$.
        
    2. **Deuxième IPP (sur l'intégrale restante** $\int \frac{2t}{k} \cos(kt) dt$**) :** $u_2 = \frac{2t}{k} \implies du_2 = \frac{2}{k} dt$ et $dv_2 = \cos(kt) dt \implies v_2 = \frac{1}{k}\sin(kt)$. Le polynôme a désormais disparu, on peut résoudre l'intégrale finale.
    
- **Primitives courantes :**
    
    - $\int \cos(at) dt = \frac{1}{a} \sin(at)$  
        
    - $\int \sin(at) dt = -\frac{1}{a} \cos(at)$  
        
    - $\int e^{at} dt = \frac{1}{a} e^{at}$  
        
- **Formules d'Euler :** $\cos(t) = \frac{e^{it} + e^{-it}}{2}$ et $\sin(t) = \frac{e^{it} - e^{-it}}{2i}$  
    
- **Linéarisation :** $\cos^2(t) = \frac{1 + \cos(2t)}{2}$ et $\sin^2(t) = \frac{1 - \cos(2t)}{2}$  
    
- **Nombres complexes :** $Z = a + ib \implies \vert{}Z\vert{} = \sqrt{a^2 + b^2}$ et $\arg(Z) = \arctan(\frac{b}{a})$.
    
- **Règle de L'Hôpital (pour les limites en** $\frac{0}{0}$**) :** $\lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)}$  
    

## 3. Progression Thématique et Exemples d'Exercices

### 3.1 Séries de Fourier réelles (Série 21)

**Concept A : Translations temporelles et d'échelle** Déduire une nouvelle série sans recalculer d'intégrale en modifiant $t$ dans la série connue.

- **Exemple (Série 21, Ex 3) :** On a $f_1(t) = -\frac{4}{\pi} \sum_{k \text{ impair}} \frac{1}{k^2} \cos(kt)$. Pour $f_4(t) = f_1(t + \frac{\pi}{2})$, on remplace $t$ par $(t + \frac{\pi}{2})$ dans la somme : $\cos(k(t + \frac{\pi}{2}))$. Par trigonométrie, cela devient une série en sinus avec alternance de signes. **Résultat :** $f_4(t) = \frac{4}{\pi} \sum_{l=0}^{\infty} \frac{(-1)^l}{(2l+1)^2} \sin((2l+1)t)$.
    

**Concept B : Évaluation de séries numériques infinies** En choisissant un $t$ astucieux (ex: $t = \frac{\pi}{2}$), la série de Fourier se transforme en une somme numérique dont on connaît le résultat grâce à $f(t)$.

- **Exemple (Série 21, Ex 7) :** Série de $f(t) = t(\pi-t)$ : $f(t) \sim \frac{8}{\pi} \sum_{k \text{ impair}} \frac{1}{k^3} \sin(kt)$. En $t = \frac{\pi}{2}$, $f(\frac{\pi}{2}) = \frac{\pi^2}{4}$. Dans la série, $\sin(k\frac{\pi}{2})$ alterne pour $k$ impair. **Résultat :** $\frac{\pi^2}{4} = \frac{8}{\pi} \left( 1 - \frac{1}{3^3} + \frac{1}{5^3} - \dots \right) \implies 1 - \frac{1}{3^3} + \frac{1}{5^3} - \dots = \frac{\pi^3}{32}$.
    

### 3.2 Séries complexes et Parseval (Série 22)

**Concept A : Calcul des coefficients complexes** $c_k$  

- **Exemple (Série 22, Ex 3) :** $f(t) = e^t$ sur $[0, 1[$ ($T=1$, $\omega_0 = 2\pi$). $c_k = \int_0^1 e^t e^{-ik2\pi t} dt = \int_0^1 e^{(1-ik2\pi)t} dt = \frac{e^{(1-ik2\pi)} - 1}{1-ik2\pi}$. Comme $e^{-ik2\pi} = 1$, $c_k = \frac{e-1}{1-ik2\pi}$. On multiplie par le conjugué pour le format standard.
    

**Concept B : Filtrage idéal et Puissance** Un filtre passe-bas supprime les harmoniques dont $\omega_k > \omega_c$.

- **Exemple (Série 22, Ex 4) :** Entrée $x(t)$ avec des pulsations $\omega_k = k\frac{\pi}{4}$. Le filtre coupe à $\omega_c = 2$. Pour $k=1, \omega_1 = \frac{\pi}{4} \approx 0.78 \le 2$ (Passe). Pour $k=3, \omega_3 = \frac{3\pi}{4} \approx 2.35 > 2$ (Coupé). Seules la composante continue et la première harmonique subsistent dans le signal de sortie $y(t)$.
    

### 3.3 Réponse fréquentielle des systèmes (Série 23)

**Concept : Trouver** $H(\omega)$ **et déduire le signal de sortie**

- **Exemple (Série 23, Ex 1) :** Équation : $y''(t) + 5y'(t) + 4y(t) = 4x(t)$. En remplaçant par $e^{i\omega t}$, on trouve $H(\omega) = \frac{4}{-\omega^2 + 5i\omega + 4}$. Pour une entrée $x(t) = \cos(t)$ ($\omega=1$), on évalue $H(1) = \frac{4}{3 + 5i}$. Module $\vert{}H(1)\vert{} = \frac{4}{\sqrt{34}} \approx 0.686$. Phase $\arg(H(1)) = -\arctan(5/3) \approx -1.03$. **Résultat :** Le signal de sortie est $y(t) = 0.686 \cos(t - 1.03)$.
    

### 3.4 Transformée de Fourier (Série 24)

**Concept A : L'Intégrale de Fourier (Transformée Inverse)**

- **Exemple (Série 24, Ex 1) :** Pour $f(t) = e^{-\vert{}t\vert{}}$, on trouve $\hat{f}(\omega) = \frac{2}{\omega^2 + 1}$. L'application de la formule inverse donne $e^{-\vert{}t\vert{}} = \frac{1}{2\pi} \int_{-\infty}^{\infty} \frac{2}{\omega^2 + 1} e^{i\omega t} d\omega$. Par symétrie (fonction paire), cela permet de résoudre l'intégrale impropre : $e^{-\vert{}t\vert{}} = \frac{2}{\pi} \int_0^{\infty} \frac{\cos(\omega t)}{\omega^2 + 1} d\omega$.
    

**Concept B : Étude de Continuité (L'Hôpital)**

- **Exemple (Série 24, Ex 2) :** $\hat{f}(\omega) = \frac{2\omega \sin(\pi\omega)}{1-\omega^2}$. Non défini en $\omega = 1$ ($\frac{0}{0}$). Par la limite (L'Hôpital) : $\lim_{\omega \to 1} \frac{(2\omega \sin(\pi\omega))'}{(1-\omega^2)'} = \frac{2\sin(\pi\omega) + 2\pi\omega\cos(\pi\omega)}{-2\omega} = \pi$. Cette limite correspond au calcul direct de l'intégrale $\hat{f}(1)$, prouvant la continuité.