Voici le récapitulatif complet et mis à jour de ton cours et de tes exercices d'**Analyse 3 (HES-SO)**, incluant tout ce que nous avons traité jusqu'à présent (Séries 21, 22, 23) ainsi que toute la théorie nécessaire pour la **Série 24 (Transformée de Fourier)**.

  

# 📚 Fiche de Synthèse et Récapitulatif d'Analyse 3

## 1. Rappels & Formules de Base

  

  

### 1.1 Paramètres fondamentaux

  

  

- **Période $T$** : Durée d'un motif complet.
    
      
    
- **Pulsation fondamentale $\omega$** (ou $\omega_0$) :
    
      
    
    $$\omega = \frac{2\pi}{T}$$
    
      
    
- **Valeur moyenne $a_0$ / $\langle f \rangle$** :
    
      
    
    $$\bar{f} = \frac{1}{T} \int_{0}^{T} f(t) \, \mathrm{d}t$$
    
      
    

  

  

### 1.2 Astuces de calcul rapide

  

  

- $\int_0^T \sin(k\omega t) \, \mathrm{d}t = 0$ et $\int_0^T \cos(k\omega t) \, \mathrm{d}t = 0$ sur une période entière.
    
      
    
- La valeur moyenne du carré d'une sinusoïde pure est toujours **$\frac{1}{2}$** :
    
      
    
    $$\overline{\cos^2(k\omega t)} = \frac{1}{2}, \quad \overline{\sin^2(k\omega t)} = \frac{1}{2}$$
    

## 2. Séries de Fourier Réelles & Complexe

  

  

### 2.1 Forme Réelle (Trigonométrique)

  

  

$$f(t) \sim a_0 + \sum_{k=1}^{\infty} \Big( a_k \cos(k\omega t) + b_k \sin(k\omega t) \Big)$$

  

- **Composante continue** : $a_0 = \frac{1}{T} \int_0^T f(t) \, \mathrm{d}t$
    
      
    
- **Coefficients $a_k$** : $a_k = \frac{2}{T} \int_0^T f(t) \cos(k\omega t) \, \mathrm{d}t$
    
      
    
- **Coefficients $b_k$** : $b_k = \frac{2}{T} \int_0^T f(t) \sin(k\omega t) \, \mathrm{d}t$
    
      
    

  

  

#### **Règles de Parité (Symétries)**

  

  

- **Si $f(t)$ est Paire** ($f(-t) = f(t)$) :
    
      
    
    $$b_k = 0 \quad \forall k, \qquad a_k = \frac{4}{T} \int_0^{T/2} f(t) \cos(k\omega t) \, \mathrm{d}t$$
    
      
    
- **Si $f(t)$ est Impaire** ($f(-t) = -f(t)$) :
    
      
    
    $$a_0 = 0, \quad a_k = 0 \quad \forall k, \qquad b_k = \frac{4}{T} \int_0^{T/2} f(t) \sin(k\omega t) \, \mathrm{d}t$$
    
      
    

  

  

### 2.2 Forme Complexe (Exponentielle)

  

  

$$f(t) \sim \sum_{k=-\infty}^{+\infty} c_k e^{i k \omega t} \quad \text{avec} \quad c_k = \frac{1}{T} \int_0^T f(t) e^{-i k \omega t} \, \mathrm{d}t$$

  

  

  

#### **Passage Forme Complexe $\iff$ Forme Réelle**

  

  

- $c_0 = a_0$
    
      
    
- $c_k = \frac{a_k - i b_k}{2} \quad (k > 0)$
    
      
    
- $c_{-k} = \overline{c_k} = \frac{a_k + i b_k}{2}$
    
      
    
- **Réciproque** : $a_k = 2 \operatorname{Re}(c_k) = c_k + c_{-k}$ et $b_k = -2 \operatorname{Im}(c_k) = i(c_k - c_{-k})$.
    
      
    

## 3. Puissance & Théorème de Parseval

  

  

### 3.1 Puissance moyenne dissipée dans $R$

  

  

- **Puissance totale du signal réel** :
    
      
    
    $$P_m = \frac{1}{T R} \int_0^T (f(t))^2 \, \mathrm{d}t$$
    
      
    
- **Puissance de la composante continue** ($a_0$) :
    
      
    
    $$P_0 = \frac{a_0^2}{R}$$
    
      
    
- **Puissance de la $k$-ième harmonique** ($k \ge 1$) :
    
      
    
    $$P_k = \frac{a_k^2 + b_k^2}{2R}$$
    
      
    

  

  

### 3.2 Formule de Parseval (Conservation de l'énergie)

  

  

$$\frac{1}{T} \int_0^T \vert{}f(t)\vert{}^2 \, \mathrm{d}t = a_0^2 + \frac{1}{2} \sum_{k=1}^{\infty} (a_k^2 + b_k^2) = \sum_{k=-\infty}^{+\infty} \vert{}c_k\vert{}^2$$

  

## 4. Synthèse des Exercices Clés Réalisés

  

  

### **Série 21**

  

  

1. **Pentes & Linéarisation complexe** :
    
      
    - Pour $f(t) = \cos^2(t) + 5\cos(t)\sin^2(t)$ : linéarisation directe par Euler $\implies f(t) = \frac{1}{2} + \frac{5}{4}\cos(t) + \frac{1}{2}\cos(2t) - \frac{5}{4}\cos(3t)$.
        
          
        
2. **Transformations de signaux ($f_1 \to f_2, f_3, f_4$)** :
    
      
    - Décalage vertical : $f(t) + C \implies a_0 \to a_0 + C$.
        
          
        
    - Changement d'échelle : $A \cdot f(t) \implies$ tous les coefficients multipliés par $A$.
        
          
        
    - Contraction de fréquence : $f(\omega t) \implies$ pulsation multipliée par $\omega$.
        
          
        
3. **Signal triangulaire / paraboles** :
    
      
    - Pour $f(t) = t(\pi - t)$ sur $[0, \pi]$ (impair) $\implies b_k = \frac{8}{\pi k^3}$ pour $k$ impair.
        
          
        
    - Évaluation en $t = \frac{\pi}{2}$ (Théorème de Dirichlet) $\implies \sum_{l=0}^{\infty} \frac{(-1)^l}{(2l+1)^3} = \frac{\pi^3}{32}$.
        
          
        

  

  

### **Série 22**

  

  

1. **Signal triangulaire $U(t)$ de hauteur $U_0$ et période $T$** :
    
      
    - $U(t) = \frac{U_0}{2} - \frac{4U_0}{\pi^2} \sum_{l=0}^{\infty} \frac{1}{(2l+1)^2} \cos\left((2l+1)\frac{2\pi}{T}t\right)$.
        
          
        
    - Puissance totale : $P_m = \frac{U_0^2}{3R}$.
        
          
        
    - Composante continue : $P_0 = \frac{U_0^2}{4R}$ ($75\%$ de la puissance).
        
          
        
    - Fondamentale ($k=1$) : $24.64\%$ de la puissance.
        
          
        
2. **Fonction $f(t) = \cos^4(t)$ ($\pi$-périodique)** :
    
      
    - Forme complexe : $f(t) = \frac{1}{16}e^{-i4t} + \frac{1}{4}e^{-i2t} + \frac{3}{8} + \frac{1}{4}e^{i2t} + \frac{1}{16}e^{i4t}$.
        
          
        
    - Application de Parseval : $\frac{1}{\pi}\int_0^\pi \cos^8(t)\,\mathrm{d}t = \sum \vert{}c_k\vert{}^2 = \frac{35}{128} \implies \int_0^\pi \cos^8(t)\,\mathrm{d}t = \frac{35\pi}{128}$.
        
          
        

  

  

### **Série 23 : Systèmes Linéaires & Filtres Passe-Bas**

  

  

- Pour un système régi par une équation différentielle avec une réponse fréquentielle $H(\omega)$ :
    
      
    - Si l'entrée est $x(t) = A \cos(\omega t)$, la réponse stationnaire est :
        
          
        
        $$y(t) = A \vert{}H(\omega)\vert{} \cos(\omega t + \arg(H(\omega)))$$
        
          
        
    - **Pour une série de Fourier en entrée** : On applique la réponse $H(k\omega)$ terme à terme par le principe de superposition.
        
          
        

## 5. Théorie pour la Série 24 : La Transformée de Fourier

Quand un signal $f(t)$ **n'est pas périodique**, la série de Fourier devient une **intégrale de Fourier** (le spectre passe d'un ensemble de fréquences discrètes à un spectre continu en $\omega$).

  

  

  

### 5.1 Définition de la Transformée de Fourier

  

  

$$\hat{f}(\omega) = \mathcal{F}[f(t)] = \int_{-\infty}^{+\infty} f(t) e^{-i \omega t} \, \mathrm{d}t$$

  

  

  

### 5.2 Définition de la Transformée Inverse (Intégrale de Fourier)

  

  

$$f(t) = \mathcal{F}^{-1}[\hat{f}(\omega)] = \frac{1}{2\pi} \int_{-\infty}^{+\infty} \hat{f}(\omega) e^{i \omega t} \, \mathrm{d}\omega$$

  

  

  

### 5.3 Théorème de Parseval pour la Transformée de Fourier

  

  

$$\int_{-\infty}^{+\infty} \vert{}f(t)\vert{}^2 \, \mathrm{d}t = \frac{1}{2\pi} \int_{-\infty}^{+\infty} \vert{}\hat{f}(\omega)\vert{}^2 \, \mathrm{d}\omega$$

  

  

  

### 5.4 Propriétés fondamentales de la Transformée

  

  

|**Opération / Propriété**|**Domaines temporel f(t)f(t)f(t)**|**Domaine fréquentiel f^(ω)\hat{f}(\omega)f^​(ω)**|
|---|---|---|
|**Linéarité**|$\alpha f(t) + \beta g(t)$|$\alpha \hat{f}(\omega) + \beta \hat{g}(\omega)$|
|**Changement d'échelle**|$f(at) \quad (a \neq 0)$|$\frac{1}{\Vert{}a\Vert{}} \hat{f}\left(\frac{\omega}{a}\right)$|
|**Décalage temporel**|$f(t - a)$|$e^{-i \omega a} \hat{f}(\omega)$|
|**Modulation**|$e^{i a t} f(t)$|$\hat{f}(\omega - a)$|
|**Dérivation en temps**|$\frac{\mathrm{d}^n}{\mathrm{d}t^n} f(t)$|$(i\omega)^n \hat{f}(\omega)$|
|**Multiplication par $-it$**|$-it \cdot f(t)$|$\frac{\mathrm{d}}{\mathrm{d}\omega} \hat{f}(\omega)$|

  

  

### 5.5 Propriétés de Symétrie (Signal réel)

  

  

Si $f(t)$ est une fonction **réelle** :

  

1. $\hat{f}(-\omega) = \overline{\hat{f}(\omega)}$ (le spectre d'amplitude $\vert{}\hat{f}(\omega)\vert{}$ est **pair**, le spectre de phase est **impair**).
    
      
    
2. Si $f(t)$ est **réelle et paire**, alors $\hat{f}(\omega)$ est **réelle et paire** :
    
      
    
    $$\hat{f}(\omega) = 2 \int_{0}^{+\infty} f(t) \cos(\omega t) \, \mathrm{d}t$$
    
3. Si $f(t)$ est **réelle et impaire**, alors $\hat{f}(\omega)$ est **purement imaginaire et impaire** :
    
    $$\hat{f}(\omega) = -2i \int_{0}^{+\infty} f(t) \sin(\omega t) \, \mathrm{d}t$$