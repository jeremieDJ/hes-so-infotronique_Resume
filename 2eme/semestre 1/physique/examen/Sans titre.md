

## Exercice 1 : Électrostatique de charges ponctuelles

  

  

### Données du problème

  

  

- Trois charges alignées sur l'axe $x$ :
    
      
    - $q_1 = \sqrt{2}Q$ en $x_1 = -L$
        
          
        
    - $q_2 = -Q$ en $x_2 = 0$
        
          
        
    - $q_3 = \sqrt{2}Q$ en $x_3 = L$
        
          
        

  

  

### (a) Condition d'équilibre d'une charge $q$ sur l'axe $y$

  

  

#### **1. Principe & Raisonnement**

Une charge test $q$ est placée à une position $(0, y)$ sur l'axe des ordonnées.

Par principe de superposition, la force totale subie par $q$ est :

  

$$\vec{F}_{\text{tot}} = \vec{F}_1 + \vec{F}_2 + \vec{F}_3 = \frac{q}{4\pi\varepsilon_0} \left( \vec{E}_1 + \vec{E}_2 + \vec{E}_3 \right)$$

Pour que la charge ne subisse **aucune force** ($\vec{F}_{\text{tot}} = \vec{0}$), il faut que le champ électrique total produit par les trois charges au point $(0, y)$ soit nul.

  

Par symétrie par rapport à l'axe $y$ :

  

- Les composantes horizontales selon $x$ des forces créées par $q_1$ et $q_3$ se compensent exactement : $F_{1x} + F_{3x} = 0$.
    
      
    
- La composante selon $x$ de $q_2$ est nulle car $q_2$ est sur l'axe $y$.
    
      
    

Il suffit donc d'imposer que la somme des composantes verticales selon $y$ soit nulle : $F_y = 0$.

  

#### **2. Calcul des forces selon $y$**

- **Force due à $q_2 = -Q$ (située à l'origine) :**
    
    Distance à la charge test : $r_2 = \vert{}y\vert{}$.
    
      
    
    $$\vec{F}_2 = \frac{1}{4\pi\varepsilon_0} \frac{q(-Q)}{y^2} \hat{y}$$
    
- **Forces dues à $q_1 = \sqrt{2}Q$ et $q_3 = \sqrt{2}Q$ :**
    
    Distance de $q_1$ ou $q_3$ au point $(0, y)$ : $r_1 = r_3 = \sqrt{L^2 + y^2}$.
    
      
    
    Le vecteur unitaire projeté selon $y$ donne un facteur $\cos\theta = \frac{y}{\sqrt{L^2 + y^2}}$.
    
      
    
    Les deux charges étant identiques et symétriques, la force verticale combinée est :
    
      
    
    $$\vec{F}_{1,3, y} = 2 \cdot \frac{1}{4\pi\varepsilon_0} \frac{q(\sqrt{2}Q)}{L^2 + y^2} \cdot \frac{y}{\sqrt{L^2 + y^2}} \hat{y} = \frac{1}{4\pi\varepsilon_0} \frac{2\sqrt{2} q Q y}{(L^2 + y^2)^{3/2}} \hat{y}$$
    

#### **3. Équation d'équilibre et simplification**

En annulant la force totale selon $y$ :

  

$$\frac{1}{4\pi\varepsilon_0} q Q \left[ \frac{2\sqrt{2} y}{(L^2 + y^2)^{3/2}} - \frac{1}{y^2} \right] = 0$$

En simplifiant par $\frac{qQ}{4\pi\varepsilon_0}$ (pour $y \neq 0$) :

  

$$\frac{2\sqrt{2} y}{(L^2 + y^2)^{3/2}} = \frac{1}{y^2}$$

En multipliant par $y^2 (L^2 + y^2)^{3/2}$ :

  

$$2\sqrt{2} y^3 = (L^2 + y^2)^{3/2}$$

En éllevant au carré des deux côtés :

  

$$\left(2\sqrt{2} y^3\right)^2 = \left((L^2 + y^2)^{3/2}\right)^2 \implies 8 y^6 = (L^2 + y^2)^3$$

En prenant la racine cubique :

  

$$2 y^2 = L^2 + y^2 \implies y^2 = L^2 \implies y = \pm L$$

  

  

#### Vérification pour $y = L$

:

  

  

En remplaçant $y = L$ dans l'équation :

  

- Terme de $q_2$ : $\frac{1}{L^2}$
    
      
    
- Terme de $q_1, q_3$ : $\frac{2\sqrt{2} L}{(L^2 + L^2)^{3/2}} = \frac{2\sqrt{2} L}{(2L^2)^{3/2}} = \frac{2\sqrt{2} L}{2\sqrt{2} L^3} = \frac{1}{L^2}$
    
      
    

La condition est parfaitement vérifiée : $y = L$ est bien un endroit où la force s'annule.

  

  

  

### (b) Énergie électrostatique de la configuration

  

  

L'énergie potentielle électrostatique totale d'un système de $N$ charges est donnée par :

  

$$U = \frac{1}{4\pi\varepsilon_0} \sum_{i < j} \frac{q_i q_j}{r_{ij}}$$

Pour nos 3 charges :

  

1. **Interaction entre $q_1$ et $q_2$ :**
    
      
    - $q_1 = \sqrt{2}Q$, $q_2 = -Q$, distance $r_{12} = L$
        
          
        
    - $U_{12} = \frac{1}{4\pi\varepsilon_0} \frac{(\sqrt{2}Q)(-Q)}{L} = -\frac{\sqrt{2} Q^2}{4\pi\varepsilon_0 L}$
        
          
        
2. **Interaction entre $q_2$ et $q_3$ :**
    
      
    - $q_2 = -Q$, $q_3 = \sqrt{2}Q$, distance $r_{23} = L$
        
          
        
    - $U_{23} = \frac{1}{4\pi\varepsilon_0} \frac{(-Q)(\sqrt{2}Q)}{L} = -\frac{\sqrt{2} Q^2}{4\pi\varepsilon_0 L}$
        
          
        
3. **Interaction entre $q_1$ et $q_3$ :**
    
      
    - $q_1 = \sqrt{2}Q$, $q_3 = \sqrt{2}Q$, distance $r_{13} = 2L$
        
          
        
    - $U_{13} = \frac{1}{4\pi\varepsilon_0} \frac{(\sqrt{2}Q)(\sqrt{2}Q)}{2L} = \frac{2 Q^2}{4\pi\varepsilon_0 (2L)} = \frac{Q^2}{4\pi\varepsilon_0 L}$
        
          
        

#### **Somme totale :**

$$U_{\text{tot}} = U_{12} + U_{23} + U_{13} = \frac{Q^2}{4\pi\varepsilon_0 L} \left( -\sqrt{2} - \sqrt{2} + 1 \right)$$

$$U_{\text{tot}} = \frac{(1 - 2\sqrt{2}) Q^2}{4\pi\varepsilon_0 L}$$

## Exercice 2 : Équilibre d'une gouttelette chargée

  

  

### Données du problème

  

  

- Masse volumique : $\rho = 850\text{ kg/m}^3$
    
      
    
- Rayon de la gouttelette : $r = 10^{-6}\text{ m}$
    
      
    
- Charge totale : $q = -6.4 \cdot 10^{-19}\text{ C}$
    
      
    
- Distance à la plaque : $d = 12\text{ mm} = 0.012\text{ m}$
    
      
    
- Charge élémentaire : $e = 1.602 \cdot 10^{-19}\text{ C}$
    
      
    

  

  

### (a) Nombre et nature des charges élémentaires

  

  

- **Nature :** La charge $q$ est négative, il s'agit donc d'un **excès d'électrons**.
    
      
    
- **Nombre $N$ d'électrons en excès :**
    
      
    
    $$N = \frac{\vert{}q\vert{}}{e} = \frac{6.4 \cdot 10^{-19}\text{ C}}{1.602 \cdot 10^{-19}\text{ C}} = 4$$
    
    Il y a **4 électrons en excès**.
    
      
    

  

  

### (b) Schéma de la situation

  

  

Plaintext

```
       Plaque infinie (charge σ > 0)
    ==================================  y = 0
                   |
                   |  ↓ F_elec (si σ > 0, attire q < 0)
                   o  Gouttelette (q < 0)
                   |  ↓ F_pesanteur (P = m·g)
                   v
```

- **Force de pesanteur $\vec{P}$ :** orientée vers le bas.
    
      
    
- **Force électrostatique $\vec{F}_e = q\vec{E}$ :** doit compenser le poids, donc être orientée **vers le haut**.
    
      
    
- Comme la charge $q$ est négative ($q < 0$), pour que $\vec{F}_e = q\vec{E}$ soit vers le haut, le champ électrique $\vec{E}$ doit être orienté **vers le bas**.
    
      
    
- Un champ électrique orienté vers le bas au-dessus de la plaque (ou sous la plaque) implique une plaque chargée **négativement** ($\sigma < 0$) si la goutte est au-dessus, ou **positivement** ($\sigma > 0$) si la goutte est sous la plaque.
    
      
    

  

  

### (c) Détermination algébrique de la densité de charge $\sigma$

  

  

1. **Volume et masse de la gouttelette :**
    
      
    
    $$V = \frac{4}{3}\pi r^3$$
    
    $$m = \rho \cdot V = \rho \cdot \frac{4}{3}\pi r^3$$
    
2. **Force de pesanteur :**
    
      
    
    $$P = m \cdot g = \frac{4}{3}\pi \rho r^3 g$$
    
3. **Champ créé par une plaque infinie :**
    
    Le champ créé par une plaque plan infinie ayant une densité surfacique $\sigma$ est uniforme et vaut :
    
      
    
    $$E = \frac{\vert{}\sigma\vert{}}{2\varepsilon_0}$$
    
4. **Équilibre des forces :**
    
    À l'équilibre, la force électrostatique compense le poids :
    
      
    
    $$\vert{}q\vert{} E = P \implies \vert{}q\vert{} \frac{\vert{}\sigma\vert{}}{2\varepsilon_0} = \frac{4}{3}\pi \rho r^3 g$$
    
5. **Isolons $\vert{}\sigma\vert{}$ :**
    
      
    
    $$\vert{}\sigma\vert{} = \frac{8\pi \varepsilon_0 \rho r^3 g}{3 \vert{}q\vert{}}$$
    

  

  

### (d) Évaluation numérique de $\sigma$

  

  

Constants :

  

- $\varepsilon_0 \approx 8.854 \cdot 10^{-12}\text{ F/m}$
    
      
    
- $g \approx 9.81\text{ m/s}^2$
    
      
    

Calcul :

  

$$\vert{}\sigma\vert{} = \frac{8 \cdot \pi \cdot (8.854 \cdot 10^{-12}) \cdot 850 \cdot (10^{-6})^3 \cdot 9.81}{3 \cdot (6.4 \cdot 10^{-19})}$$

$$\vert{}\sigma\vert{} = \frac{8 \cdot 3.1416 \cdot 8.854 \cdot 10^{-12} \cdot 850 \cdot 10^{-18} \cdot 9.81}{1.92 \cdot 10^{-18}}$$

$$\vert{}\sigma\vert{} \approx \frac{1.854 \cdot 10^{-27}}{1.92 \cdot 10^{-18}} \approx 9.66 \cdot 10^{-10}\text{ C/m}^2$$

$$\sigma \approx -9.66 \cdot 10^{-10}\text{ C/m}^2 \quad \text{ou} \quad +9.66 \cdot 10^{-10}\text{ C/m}^2 \text{ (selon la position relatif/sens choisi)}$$

## Exercice 3 : Champ et tension entre deux plaques inclinées

  

  

### Données du problème

  

  

- Deux plaques infinies portées aux densités $\sigma > 0$ et $-\sigma$.
    
      
    
- Inclinaison relative $\alpha \ll 1$.
    
      
    

  

  

### (a) Dessin des champs pour les trois régions

  

  

Les trois régions sont :

  

1. **Région à gauche de la plaque $+\sigma$**
    
      
    
2. **Région inter-plaques (entre $+\sigma$ et $-\sigma$)**
    
      
    
3. **Région à droite de la plaque $-\sigma$**
    
      
    

- Une plaque $+\sigma$ crée un champ repoussant $\vec{E}_+ = \frac{\sigma}{2\varepsilon_0}$ sortant de la plaque.
    
      
    
- Une plaque $-\sigma$ crée un champ attirant $\vec{E}_- = \frac{\sigma}{2\varepsilon_0}$ entrant vers la plaque.
    
      
    

Plaintext

```
Région 1 (Gauche)       | Région 2 (Centre)       | Région 3 (Droite)
← E_+                   | E_+ →                   | E_+ →
E_- →                   | E_- →                   | ← E_-
```

  

  

### (b) Champ électrique total (norme, direction, sens)

  

  

Comme $\alpha \ll 1$, les deux plaques sont quasi-parallèles et perpendiculaires à l'axe horizontal.

  

- **Région 1 (à gauche) :**
    
      
    
    $$\vec{E}_{\text{tot}} = \vec{E}_+ + \vec{E}_- = -\frac{\sigma}{2\varepsilon_0}\hat{x} + \frac{\sigma}{2\varepsilon_0}\hat{x} = \vec{0}$$
    
- **Région 2 (au centre, entre les plaques) :**
    
      
    
    $$\vec{E}_{\text{tot}} = \frac{\sigma}{2\varepsilon_0}\hat{x} + \frac{\sigma}{2\varepsilon_0}\hat{x} = \frac{\sigma}{\varepsilon_0}\hat{x}$$
    
    - Norme : $E = \frac{\sigma}{\varepsilon_0}$
        
          
        
    - Direction : Horizontale (axe $x$)
        
          
        
    - Sens : Vers la droite (de la plaque positive vers la plaque négative)
        
          
        
- **Région 3 (à droite) :**
    
      
    
    $$\vec{E}_{\text{tot}} = \frac{\sigma}{2\varepsilon_0}\hat{x} - \frac{\sigma}{2\varepsilon_0}\hat{x} = \vec{0}$$
    

  

  

### (c) Calcul des tensions $U_{AB}$, $U_{BC}$, $U_{AC}$

  

  

On nous donne le champ pour cette question :

  

$$\vec{E}(x, y) = \begin{pmatrix} K x^2 \\ 0 \end{pmatrix}$$

Rappel de la définition de la tension (différence de potentiel) :

  

$$U_{XY} = V_X - V_Y = \int_X^Y \vec{E} \cdot d\vec{\ell}$$

Position des points :

  

- $A$ est l'origine des coordonnées : $x_A = 0, y_A = 0$.
    
      
    
- $B$ est sur l'axe horizontal à une distance $d$ de $A$ : $x_B = d, y_B = 0$.
    
      
    
- $C$ est au-dessus de $B$ à une distance $d$ : $x_C = d, y_C = d$.
    
      
    

#### **1. Tension $U_{AB}$ :**

Déplacement le long de l'axe $x$ de $x=0$ à $x=d$ avec $dy = 0$ :

  

$$U_{AB} = \int_{x_A}^{x_B} E_x \, dx = \int_0^d K x^2 \, dx = K \left[ \frac{x^3}{3} \right]_0^d = \frac{K d^3}{3}$$

#### **2. Tension $U_{BC}$ :**

Déplacement le long de l'axe vertical $y$ de $y=0$ à $y=d$ à $x = d$ fixe ($dx = 0$) :

  

$$U_{BC} = \int_{y_B}^{y_C} E_y \, dy = \int_0^d 0 \, dy = 0$$

#### **3. Tension $U_{AC}$ :**

Par la propriété de circulation d'un champ électrostatique (indépendance du chemin suivi) :

  

$$U_{AC} = U_{AB} + U_{BC} = \frac{K d^3}{3} + 0 = \frac{K d^3}{3}$$

## Exercice 4 : Champ et flux à travers la face d'un cube

  

  

### Données du problème

  

  

- Fil infini chargé avec une densité linéique $\lambda > 0$.
    
      
    
- Cube de côté $2a$.
    
      
    
- Le fil passe par le centre de deux faces opposées (parallèle aux arêtes horizontales).
    
      
    

  

  

### (a) Grandeur maximale du champ électrique sur la face B et endroit exact

  

  

#### **1. Expression du champ d'un fil infini :**

Par le théorème de Gauss sur un cylindre, le champ électrique à une distance $r$ d'un fil infini vaut :

  

$$E(r) = \frac{\lambda}{2\pi \varepsilon_0 r}$$

#### **2. Recherche du champ maximal sur la face B :**

Le champ $E(r)$ est maximal là où la distance $r$ au fil est **minimale**.

  

La face B est une face du cube parallèle au fil, située à une distance minimale de la ligne du fil égale à la moitié du côté du cube, soit :

  

$$r_{\text{min}} = a$$

Cette distance minimale est atteinte au **centre de la face B**.

  

#### **3. Valeur maximale du champ :**

$$E_{\text{max}} = \frac{\lambda}{2\pi \varepsilon_0 a}$$

  

  

### (b) Flux du champ électrique à travers la face B

  

  

  

  

#### 1. Application du Théorème de Gauss à l'ensemble du cube

:

  

  

La longueur du fil contenue à l'intérieur du cube de côté $2a$ est $L = 2a$.

  

La charge totale enfermée dans le cube est donc :

  

$$Q_{\text{enfermée}} = \lambda \cdot (2a)$$

D'après le théorème de Gauss, le flux total à travers toute la surface fermée du cube est :

  

$$\Phi_{\text{tot}} = \frac{Q_{\text{enfermée}}}{\varepsilon_0} = \frac{2a\lambda}{\varepsilon_0}$$

#### **2. Décomposition par symétrie :**

Le cube a 6 faces :

  

- Les **2 faces traversées par le fil** (faces traversées perpendiculairement par le fil) :
    
    Le champ $\vec{E}$ est radial par rapport au fil, donc tangent à ces deux faces. Le flux à travers ces 2 faces est **strictement nul** ($\vec{E} \cdot d\vec{S} = 0$).
    
      
    
- Les **4 faces latérales** (dont la face B) :
    
    Par symétrie axiale parfaite du problème autour du fil, le flux traversant chacune de ces 4 faces est **identique**.
    
      
    

#### **3. Calcul du flux à travers la face B :**

$$\Phi_{\text{tot}} = \Phi_{\text{face B}} \cdot 4 + 0 = \frac{2a\lambda}{\varepsilon_0}$$

$$\Phi_{\text{face B}} = \frac{\Phi_{\text{tot}}}{4} = \frac{2a\lambda}{4\varepsilon_0} = \frac{a\lambda}{2\varepsilon_0}$$

  

  

## Conseils clés pour réviser l'examen de demain

  

  

1. **Rédigez les principes physiques avant de calculer :** Mentionnez toujours "Théorème de Gauss", "Principe de superposition", ou "Équilibre des forces". Les consignes de votre épreuve accordent une part importante à la justification.
    
      
    
2. **Attention aux intégrales de ligne pour les tensions :** Seule la composante du champ parallèle au déplacement contribue à la tension ($U = \int E_x dx + E_y dy$).
    
      
    
3. **Exploitez au maximum la symétrie :** Dans l'exercice du cube, la symétrie évite de devoir calculer une intégrale de surface complexe.