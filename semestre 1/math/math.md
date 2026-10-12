

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