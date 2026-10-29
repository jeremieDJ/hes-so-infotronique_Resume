ppgh

# Fiche de Révision : Analyse 3 — Séries de Fourier

> [!abstract] Synthèse du cours
> 
> Cette fiche couvre l'étude des [[Fonction périodique|fonctions périodiques]], la décomposition en [[Série de Fourier|séries de Fourier]] (formes réelle et complexe), les propriétés de symétrie, l'analyse harmonique ainsi que l'[[Égalité de Parseval|égalité de Parseval]].
> 
>   

## 1. Fonctions Périodiques

### 1.1 Définition et paramètres fondamentaux

> [!definition] Fonction T-périodique
> 
> Une fonction $f(t)$ est dite **$T$-périodique** s'il existe un réel $T > 0$ tel que pour tout $t \in \mathbb{R}$ :
> 
>   
> 
> $$f(t + T) = f(t)$$

- **Période fondamentale ($T$)** : La plus petite valeur positive de $T$ vérifiant la propriété.
    
      
    
- **Vitesse angulaire / Pulsation ($\omega$)** :
    
      
    
    $$\omega = \frac{2\pi}{T}$$
    
- **Fréquence ($f$)** :
    
      
    
    $$f = \frac{1}{T} = \frac{\omega}{2\pi}$$
    

### 1.2 Propriétés des fonctions périodiques

1. **Combinaison linéaire** : Si $f(t)$ et $g(t)$ sont $T$-périodiques, alors $\alpha f(t) + \beta g(t)$ est également $T$-périodique.
    
      
    
2. **Multiple de la période** : Si $f(t)$ est $T$-périodique, elle est aussi $2T$-périodique (et $kT$-périodique pour $k \in \mathbb{Z}^*$).
    
      
    
3. **Invariance par translation de l'intervalle d'intégration** :
    
      
    
    $$\int_{0}^{T} f(t) \, dt = \int_{a}^{a+T} f(t) \, dt = \int_{-T/2}^{T/2} f(t) \, dt$$
    
4. **Valeur moyenne $\langle f \rangle$** :
    
      
    
    $$\langle f \rangle = \frac{1}{T} \int_{0}^{T} f(t) \, dt$$
    

> [!note] Extension périodique
> 
> Pour une fonction définie sur un intervalle restreint (ex. $[0, T]$), l'**extension périodique** consiste à répéter le motif de $f(t)$ sur tout l'axe réel $\mathbb{R}$.
> 
>   

## 2. Série de Fourier (Forme Trigo/Réelle)

> [!concept] Objectif
> 
> Exprimer une fonction $T$-périodique $f(t)$ comme une somme infinie de fonctions sinusoïdales ($\sin$ et $\cos$), de manière analogue au développement en [[Série de Taylor|série de Taylor]] pour les polynômes.
> 
>   

### 2.1 Formule générale

$$f(t) = \frac{a_0}{2} + \sum_{k=1}^{\infty} \left( a_k \cos(k\omega t) + b_k \sin(k\omega t) \right)$$

où $\omega = \frac{2\pi}{T}$.

  

### 2.2 Calcul des coefficients de Fourier

- **Composante continue (Valeur moyenne)** :
    
      
    
    $$a_0 = \frac{2}{T} \int_{-T/2}^{T/2} f(t) \, dt \quad \implies \quad \frac{a_0}{2} = \langle f \rangle$$
    
- **Coefficients des cosinus ($a_k$)** :
    
      
    
    $$a_k = \frac{2}{T} \int_{-T/2}^{T/2} f(t) \cos(k\omega t) \, dt \quad (k \ge 1)$$
    
- **Coefficients des sinus ($b_k$)** :
    
      
    
    $$b_k = \frac{2}{T} \int_{-T/2}^{T/2} f(t) \sin(k\omega t) \, dt \quad (k \ge 1)$$
    

### 2.3 Simplifications selon la parité

> [!tip] Règle de parité
> 
>   
> 
> - Si $f(t)$ est **paire** ($f(-t) = f(t)$) :
>     
>       
>     
>     $$b_k = 0, \quad a_k = \frac{4}{T} \int_{0}^{T/2} f(t) \cos(k\omega t) \, dt$$
>     
> - Si $f(t)$ est **impaire** ($f(-t) = -f(t)$) :
>     
>       
>     
>     $$a_k = 0, \quad b_k = \frac{4}{T} \int_{0}^{T/2} f(t) \sin(k\omega t) \, dt$$
>     
> - Si $f(t) = C$ (constante) :
>     
>       
>     
>     $$a_0 = 2C, \quad a_k = 0, \quad b_k = 0 \quad (k \ge 1)$$
>     

### 2.4 Propriété de linéarité

$$\mathcal{S}_F[\alpha f(t) + \beta g(t)] = \alpha \mathcal{S}_F[f(t)] + \beta \mathcal{S}_F[g(t)]$$

> [!example] Application pratique
> 
> Si une fonction $f(t)$ s'écrit comme la somme d'une constante $d$ et d'une fonction impaire $g(t)$ :
> 
>   
> 
> $$f(t) = d + g(t)$$
> 
> alors $a_0 = 2d$, $a_k = 0$ ($k \ge 1$), et les coefficients $b_k$ sont ceux de $g(t)$.
> 
>   

## 3. Analyse Harmonique

Chaque terme de la somme réécrit la fonction sous forme de composantes fréquentielles :

  

$$\text{Terme } k\text{-ième} : \quad a_k \cos(k\omega t) + b_k \sin(k\omega t) = A_k \cos(k\omega t + \phi_k)$$

- **Harmonique fondamentale** : Le terme pour $k = 1$.
    
      
    
- **Harmonique de rang $k$** : Le terme pour $k > 1$.
    
      
    
- **Amplitude de la $k$-ième harmonique** :
    
      
    
    $$A_k = \sqrt{a_k^2 + b_k^2}$$
    
- **Déphasage de la $k$-ième harmonique** :
    
      
    
    $$\phi_k = \arctan\left(-\frac{b_k}{a_k}\right)$$
    

## 4. Série de Fourier Complexe

### 4.1 Formulation exponentielle

Pour une fonction $T$-périodique $f(t)$, sa série de Fourier sous forme complexe s'écrit :

  

$$f(t) = \sum_{k=-\infty}^{+\infty} c_k e^{i k \omega t}$$

où :

  

$$c_k = \frac{1}{T} \int_{-T/2}^{T/2} f(t) e^{-i k \omega t} \, dt, \quad k \in \mathbb{Z}$$

### 4.2 Relations entre coefficients réels et complexes

$$a_0 = 2c_0$$

$$a_k = c_k + c_{-k}$$

$$b_k = i (c_k - c_{-k})$$

Réciproquement :

  

$$c_k = \frac{a_k - i b_k}{2}, \quad c_{-k} = \frac{a_k + i b_k}{2} \quad (k > 0)$$

### 4.3 Propriétés des coefficients complexes pour $f(t)$ réelle

- $c_{-k} = \overline{c_k}$ (conjugaison complexe).
    
      
    
- Si $f(t)$ est **paire** : $c_k \in \mathbb{R}$ et $c_k = c_{-k}$.
    
      
    
- Si $f(t)$ est **impaire** : $c_k \in i\mathbb{R}$ (purement imaginaire) et $c_k = -c_{-k}$.
    
      
    

## 5. Formule de Parseval

> [!important] Théorème de Parseval (Conservation de l'énergie)
> 
> L'énergie moyenne d'un signal périodique est égale à la somme des énergies de ses composantes harmoniques.
> 
>   

### 5.1 Formulation mathématique

$$\frac{1}{T} \int_{-T/2}^{T/2} (f(t))^2 \, dt = \left(\frac{a_0}{2}\right)^2 + \frac{1}{2} \sum_{k=1}^{\infty} (a_k^2 + b_k^2) = \sum_{k=-\infty}^{+\infty} \vert{}c_k\vert{}^2$$

### 5.2 Application classique : Calcul de sommes de séries

> [!example] Exemple d'application
> 
> Pour un signal crenau / créneau triangulaire $4$-périodique avec $a_0 = 1$, $b_k = 0$ et $a_k = \frac{2}{\pi k} \sin\left(\frac{k\pi}{2}\right)$ :
> 
>   
> 
> En appliquant l'égalité de Parseval, on obtient l'identité remarquable :
> 
>   
> 
> $$\sum_{l=0}^{\infty} \frac{1}{(2l+1)^2} = \frac{\pi^2}{8}$$

#cours #analyse3 #mathematiques #fourier #signal

# Fiche de Synthèse : Séries de Fourier & Valeurs Moyennes

Ce document rassemble l'ensemble des notions théoriques, des propriétés et des fiches méthodologiques nécessaires pour résoudre la série d'exercices d'Analyse 3.

---

## 1. Valeur Moyenne et Valeur Efficace

### 1.1 Définitions fondamentales
Pour une fonction $T$-périodique $f(t)$, sa **valeur moyenne** $\bar{f}$ (ou $a_0$) sur une période $[a, a+T]$ est donnée par :
$$\bar{f} = \frac{1}{T} \int_{a}^{a+T} f(t) \, \mathrm{d}t$$

La **valeur moyenne du carré** (liée à la valeur efficace) est :
$$\overline{f^2} = \frac{1}{T} \int_{a}^{a+T} f^2(t) \, \mathrm{d}t$$

---

### 1.2 Propriétés & Astuces de calcul rapide
* **Signaux sinusoïdaux purs** : La valeur moyenne de $\sin(\omega t)$ ou $\cos(\omega t)$ sur une période entière est **toujours nulle** ($\bar{f} = 0$).
* **Carré d'un signal sinusoïdal** : En utilisant les formules de linéarisation :
  $$\sin^2(\theta) = \frac{1 - \cos(2\theta)}{2}, \quad \cos^2(\theta) = \frac{1 + \cos(2\theta)}{2}$$
  La valeur moyenne de $\sin^2(\omega t)$ ou $\cos^2(\omega t)$ sur une période complète vaut **toujours $\frac{1}{2}$**.
* **Lien avec la pulsation $\omega$** : 
  $$T = \frac{2\pi}{\omega} \iff \omega = \frac{2\pi}{T}$$

---

## 2. Développement en Série de Fourier

### 2.1 Formule générale
Pour une fonction $2\pi$-périodique $f(t)$, la série de Fourier s'écrit :
$$S_f(t) = a_0 + \sum_{k=1}^{\infty} \left( a_k \cos(kt) + b_k \sin(kt) \right)$$

* **Composante continue (valeur moyenne)** :
  $$a_0 = \frac{1}{2\pi} \int_{-\pi}^{\pi} f(t) \, \mathrm{d}t$$
  *(Note : Selon la convention $S_f(t) = \frac{a_0}{2} + \sum \dots$, le coefficient $a_0$ s'exprime avec $\frac{1}{\pi} \int_{-\pi}^{\pi} f(t) \, \mathrm{d}t$)*.

* **Coefficients des cosinus ($a_k$)** :
  $$a_k = \frac{1}{\pi} \int_{-\pi}^{\pi} f(t) \cos(kt) \, \mathrm{d}t, \quad k \ge 1$$

* **Coefficients des sinus ($b_k$)** :
  $$b_k = \frac{1}{\pi} \int_{-\pi}^{\pi} f(t) \sin(kt) \, \mathrm{d}t, \quad k \ge 1$$

---

### 2.2 Simplifications selon la Parité (Symétrie)

* **Règles de multiplication des parités** :
  * $\text{Paire} \times \text{Paire} = \text{Paire}$
  * $\text{Impaire} \times \text{Impaire} = \text{Paire}$
  * $\text{Impaire} \times \text{Paire} = \text{Impaire}$

| Type de fonction | Propriété géométrique | Conséquence sur la Série de Fourier | Formules simplifiées |
| :--- | :--- | :--- | :--- |
| **Fonction Paire** | $f(-t) = f(t)$ (Axe de symétrie vertical) | $b_k = 0$ (Uniquement des cosinus) | $a_0 = \frac{1}{\pi} \int_0^{\pi} f(t) \mathrm{d}t$<br>$a_k = \frac{2}{\pi} \int_0^{\pi} f(t) \cos(kt) \mathrm{d}t$ |
| **Fonction Impaire** | $f(-t) = -f(t)$ (Symétrie centrale par rapport à l'origine) | $a_0 = 0$ et $a_k = 0$ (Uniquement des sinus) | $b_k = \frac{2}{\pi} \int_0^{\pi} f(t) \sin(kt) \mathrm{d}t$ |

---

### 2.3 Transformations géométriques de signaux

Si $f(t)$ a pour série $S_f(t) = a_0 + \sum (a_k \cos(kt) + b_k \sin(kt))$ :

1. **Décalage vertical (Hauteur)** :  
   $$g(t) = f(t) + C \implies a_{0, \text{nouveau}} = a_0 + C$$
   *(Les termes oscillants $a_k$ et $b_k$ pour $k \ge 1$ restent inchangés).*

2. **Changement d'échelle d'amplitude** :  
   $$g(t) = A \cdot f(t) \implies \text{Chaque coefficient est multiplié par } A$$

3. **Changement de fréquence (Dilatation/Contraction)** :  
   $$g(t) = f(\omega t) \implies \text{Remplacer } t \text{ par } \omega t \text{ dans tous les terme sin/cos}$$

4. **Déphasage temporel (Glissement horizontal)** :  
   $$g(t) = f(t + t_0) \implies \text{Remplacer } t \text{ par } (t + t_0) \text{ puis simplifier avec la trigonométrie}$$

---

## 3. Méthodologie : L'Intégration par Parties (IPP)

### 3.1 La Formule
$$\int u \, \mathrm{d}v = u \cdot v - \int v \, \mathrm{d}u$$
En bornes définies :
$$\int_{a}^{b} u(t) v'(t) \, \mathrm{d}t = \left[ u(t) v(t) \right]_{a}^{b} - \int_{a}^{b} u'(t) v(t) \, \mathrm{d}t$$

### 3.2 Choix des variables (Règle ALPES)
Pour choisir quelle partie poser égale à $u$ (à dériver), suivre la priorité **ALPES** :
1. **A** — Arc (fonctions réciproques : $\arctan$, $\arcsin$)
2. **L** — Logarithmes ($\ln$)
3. **P** — Polynômes / Puissances ($t, t^2, 2t+1 \dots$) $\leftarrow$ *Diminue de degré lors de la dérivation*
4. **E** — Exponentielles ($e^t$)
5. **S** — Sinus / Cosinus ($\sin(kt), \cos(kt)$)

### 3.3 Exemple type de calcul pour $b_k$
Pour calculer $\int_{0}^{\pi} t \sin(kt) \, \mathrm{d}t$ :
* On pose $u = t \implies \mathrm{d}u = \mathrm{d}t$
* On pose $\mathrm{d}v = \sin(kt) \mathrm{d}t \implies v = -\frac{\cos(kt)}{k}$

$$\begin{aligned}
\int_{0}^{\pi} t \sin(kt) \, \mathrm{d}t &= \left[ -\frac{t \cos(kt)}{k} \right]_{0}^{\pi} - \int_{0}^{\pi} \left( -\frac{\cos(kt)}{k} \right) \mathrm{d}t \\
&= \left( -\frac{\pi \cos(k\pi)}{k} - 0 \right) + \frac{1}{k} \left[ \frac{\sin(kt)}{k} \right]_{0}^{\pi} \\
&= -\frac{\pi (-1)^k}{k} + 0 = -\frac{\pi (-1)^k}{k}
\end{aligned}$$

---

## 4. Spectre d'Amplitude

Le **spectre d'amplitude** est un graphique en bâtons représentant l'amplitude de chaque fréquence présentée dans le signal :

* **En abscisse ($\omega$)** : La fréquence/pulsation $k\omega$ (les entiers $k = 0, 1, 2, 3 \dots$).
* **En ordonnée ($c_k$)** : L'amplitude donnée par :
  $$c_0 = |a_0|$$
  $$c_k = \sqrt{a_k^2 + b_k^2} \quad (k \ge 1)$$

---

## 5. Théorème de Dirichlet (Somme de séries numériques)

Si une fonction $f$ est continue en un point $t_0$, sa série de Fourier converge vers la valeur de la fonction :
$$S_f(t_0) = f(t_0)$$

**Technique pour calculer la somme d'une série numérique (ex: $\sum \frac{(-1)^l}{(2l+1)^3}$)** :
1. Poser la série de Fourier $S_f(t)$ obtenue.
2. Évaluer la série en une valeur particulière de $t$ (souvent $t = 0, \frac{\pi}{2}$ ou $\pi$).
3. Égaler $S_f(t_0)$ à $f(t_0)$ et isoler algébriquement la somme recherchée.
```eof

J'ai résumé toute la théorie, les propriétés de parité, les règles de transformation, la méthode d'intégration par parties et la construction du spectre d'amplitude dans le fichier Markdown ci-dessus. N'hésite pas si tu as des questions sur un point précis !