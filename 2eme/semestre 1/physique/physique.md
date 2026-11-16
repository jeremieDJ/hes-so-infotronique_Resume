Voici un résumé complet de cours structuré au format Markdown pour réviser et préparer vos examens d'électromagnétisme. Il synthétise l'ensemble des théorèmes, équations et principes fondamentaux contenus dans vos documents.

# Fiche de Synthèse & Révision : Électromagnétisme

## Table des Matières

1. [Électrostatique & Champ Électrique](https://www.google.com/search?q=%231-%C3%A9lectrostatique--champ-%C3%A9lectrique&authuser=1)
2. [Tension, Potentiel & Énergie Électrostatique](https://www.google.com/search?q=%232-tension-potentiel--%C3%A9nergie-%C3%A9lectrostatique&authuser=1)
3. [Condensateurs & Diélectriques](https://www.google.com/search?q=%233-condensateurs--di%C3%A9lectriques&authuser=1)
4. [Courant Continue, Résistance & Puissance](https://www.google.com/search?q=%234-courant-continu-r%C3%A9sistance--puissance&authuser=1)
5. [Magnétostatique & Forces Magnétiques](https://www.google.com/search?q=%235-magn%C3%A9tostatique--forces-magn%C3%A9tiques&authuser=1)
6. [Génération du Champ Magnétique](https://www.google.com/search?q=%236-g%C3%A9n%C3%A9ration-du-champ-magn%C3%A9tique&authuser=1)
7. [Induction Électromagnétique & Self-induction](https://www.google.com/search?q=%237-induction-%C3%A9lectromagn%C3%A9tique--self-induction&authuser=1)
8. [Lois de Gauss & Ampère (Formes Intégrales)](https://www.google.com/search?q=%238-lois-de-gauss--amp%C3%A8re-formes-int%C3%A9grales&authuser=1) 
9. [Équations de Maxwell](https://www.google.com/search?q=%239-%C3%A9quations-de-maxwell&authuser=1)
## 1. Électrostatique & Champ Électrique

### Constantes Fondamentales

- Charge élémentaire : $e = 1{,}602 \times 10^{-19}\text{ C}$[cite: 76]
    
- Permittivité du vide : $\epsilon_0 = 8{,}854 \times 10^{-12}\text{ C}^2/(\text{N}\cdot\text{m}^2) = 8{,}854 \times 10^{-12}\text{ F/m}$[cite: 77]

### Loi de Coulomb

La force d'attraction ou de répulsion entre deux charges ponctuelles $q_1$ et $q_2$ séparées d'une distance $r$ s'exprime par :

  

$$F = \frac{1}{4\pi\epsilon_0} \frac{\vert{}q_1 q_2\vert{}}{r^2}$$

### Champ Électrique $\vec{E}$

- **Définition** : Force subie par une charge test $q$[cite: 79] :
    $$\vec{E} = \frac{\vec{F}}{q} \quad [\text{N/C} \text{ ou } \text{V/m}]$$
- **Charge ponctuelle $Q$**[cite: 79] :
    $$E(r) = \frac{1}{4\pi\epsilon_0} \frac{Q}{r^2}$$
- **Fil rectiligne infini (densité linéaire $\lambda$)**[cite: 78, 80] :
    $$E(r) = \frac{\lambda}{2\pi\epsilon_0 r}$$
- **Plaque infinie (densité surfacique $\sigma$)**[cite: 85] :
    $$E = \frac{\sigma}{2\epsilon_0}$$
- **Principe de superposition** : Les champs électriques produits par plusieurs charges s'additionnent vectoriellement[cite: 70, 84] :
    $$\vec{E}_{\text{total}} = \sum \vec{E}_i$$
### Lignes de Champ

- Sortent des charges positives ($+$) et entrent dans les charges négatives ($-$)[cite: 70, 81].
    
- La densité des lignes reflète l'intensité du champ[cite: 70].
    
- Ne se croisent jamais[cite: 70].
    
## 2. Tension, Potentiel & Énergie Électrostatique

### Tension Électrique $U$

La tension $U_{BC}$ entre deux points $B$ et $C$ est la circulation du champ électrique[cite: 87] :

$$U_{BC} = \int_{B}^{C} \vec{E} \cdot d\vec{r} \quad [\text{V}]$$

### Potentiel Électrique $V$ et Énergie Potentielle

- **Différence de potentiel** : $V_B - V_C = U_{BC}$[cite: 89]
	 
- **Potentiel d'une charge ponctuelle** (avec $V(\infty) = 0$)[cite: 91] :
    $$V(r) = \frac{1}{4\pi\epsilon_0} \frac{Q}{r}$$
- **Travail de la force électrique** sur une charge $q$[cite: 88] :
    $$A = q U$$
- **Énergie potentielle** : $E_{\text{pot}} = q V$[cite: 89, 171]
    
- **Propriété** : En suivant une ligne de champ, le potentiel **décroît**[cite: 89].
    
- La surface d'un conducteur en équilibre électrostatique est une **surface équipotentielle** ($\vec{E}$ y est perpendiculaire)[cite: 90].

## 3. Condensateurs & Diélectriques

### Définition & Capacité

Un condensateur emmagasine une charge $Q$ sous une tension $U$[cite: 93] :

  

$$Q = C U \quad \implies \quad C = \frac{Q}{U} \quad [\text{F} = \text{C/V}]$$


### Géométries Classiques

- **Condensateur plan** (surface $S$, écartement $d$)[cite: 94] :
    
 $$C = \epsilon_0 \frac{S}{d}$$

- **Condensateur sphérique** (rayons $R_1 < R_2$)[cite: 95] :
    
 $$C = 4\pi\epsilon_0 \frac{R_1 R_2}{R_2 - R_1}$$

- **Condensateur cylindrique** (longueur $h$, rayons $R_1 < R_2$)[cite: 96] :

    $$C = \frac{2\pi\epsilon_0 h}{\ln(R_2 / R_1)}$$
### Effet d'un Isolant (Diélectrique)

L'insertion d'un diélectrique de permittance relative $\epsilon_r > 1$ augmente la capacité[cite: 98] :

$$C = \epsilon_r C_0$$

### Association de Condensateurs

- **Parallèle** : $C_{\text{éq}} = C_1 + C_2 + \dots$[cite: 96]
    
- **Série** : $\frac{1}{C_{\text{éq}}} = \frac{1}{C_1} + \frac{1}{C_2} + \dots$[cite: 97]

### Énergie & Densité d'Énergie

- **Énergie stockée dans un condensateur**[cite: 99] :  
    $$W_E = \frac{1}{2} C U^2 = \frac{1}{2} \frac{Q^2}{C}$$
- **Densité d'énergie du champ électrique**[cite: 99] :
    $$w_E = \frac{1}{2} \epsilon_0 E^2 \quad [\text{J/m}^3]$$ 
## 4. Courant Continu, Résistance & Puissance

### Courant $I$ et Densité de Courant $j$

$$I = \frac{dQ}{dt} \quad [\text{A} = \text{C/s}]$$

[cite: 101]

  

$$I = q \cdot n \cdot v \cdot S \quad \implies \quad j = \frac{I}{S} = q \cdot n \cdot v \quad [\text{A/m}^2]$$

[cite: 101]

_(où $n$ est la densité volumique des porteurs de charge et $v$ leur vitesse de dérive)_[cite: 101].

  

### Loi d'Ohm & Résistivité

$$U = R I \quad [\Omega = \text{V/A}]$$

$$R = \rho \frac{L}{S}$$

_(où $\rho$ est la résistivité du matériau en $\Omega\cdot\text{m}$)_[cite: 103].

### Associations de Résistances

- **Série** : $R_{\text{éq}} = R_1 + R_2 + \dots$[cite: 104]
    
- **Parallèle** : $\frac{1}{R_{\text{éq}}} = \frac{1}{R_1} + \frac{1}{R_2} + \dots$[cite: 104]
    
### Puissance Électrique & Effet Joule

- **Loi générale** : $P = U I$[cite: 107]  
    
- **Effet Joule dans une résistance** : $P = R I^2 = \frac{U^2}{R}$[cite: 107, 108]
    
## 5. Magnétostatique & Forces Magnétiques

### Champ/Induction Magnétique $\vec{B}$

Mesuré en Tesla ($\text{T}$)[cite: 111]. Unité secondaire : $1\text{ Gauss} = 10^{-4}\text{ T}$[cite: 111].

  
### Force de Lorentz

Force exercée sur une particule de charge $q$ animée d'une vitesse $\vec{v}$[cite: 111] :

$$\vec{F}_L = q (\vec{v} \times \vec{B})$$

- **Trajectoire circulaire** (si $\vec{v} \perp \vec{B}$)[cite: 112] :  
    $$R = \frac{m v}{\vert{}q\vert{} B}$$

### Force de Laplace

Force subie par un segment de conducteur rectiligne $\vec{L}$ parcouru par un courant $I$[cite: 116] :

  

$$\vec{F} = I (\vec{L} \times \vec{B})$$

### Effet Hall

Différence de potentiel de Hall $U_H$ établie transversalement à un conducteur de largeur $h$ et d'épaisseur $a$[cite: 114] :

  

$$U_H = \frac{I B}{q n a}$$

## 6. Génération du Champ Magnétique

### Relation Fondamentale dans la Matière

$$\vec{B} = \mu_0 \mu_r \vec{H} = \mu_0 (\vec{H} + \vec{M})$$


- Perméabilité du vide : $\mu_0 = 4\pi \times 10^{-7}\text{ T}\cdot\text{m/A}$[cite: 122]
    
- $\vec{H}$ : Excitation magnétique[cite: 122] ($\text{A/m}$)
    
- $\vec{M}$ : Aimantation de la matière[cite: 128] ($\text{A/m}$)
    
- Propriétés des matériaux :
    
    - **Diamagnétique** : $\chi < 0$ ($\mu_r \lesssim 1$)[cite: 131, 132]  
        
    - **Paramagnétique** : $\chi > 0$ ($\mu_r \gtrsim 1$)[cite: 132]
        
    - **Ferromagnétique** : $\mu_r \gg 1$ (présente une courbe d'hystérésis et un champ rémanent $B_r$)[cite: 132, 134]

### Loi de Biot-Savart

Champs $\vec{H}$ et $\vec{B}$ créés par un élément de courant $I d\vec{s}$[cite: 122] :

  

$$d\vec{H} = \frac{I}{4\pi} \frac{d\vec{s} \times \vec{r}}{r^3} \quad \implies \quad d\vec{B} = \frac{\mu_0 I}{4\pi} \frac{d\vec{s} \times \vec{r}}{r^3}$$

## 7. Induction Électromagnétique & Self-induction

### Flux Magnétique $\Phi$

$$\Phi = \iint_S \vec{B} \cdot d\vec{S} \quad [\text{Wb} = \text{T}\cdot\text{m}^2]$$

### Loi d'Induction de Faraday & Règle de Lenz

La tension induite $U_0$ le long d'un circuit fermé est donnée par[cite: 141] :


$$U_0 = -\frac{d\Phi}{dt}$$

- **Règle de Lenz** : Le courant induit circule dans un sens tel qu'il s'oppose par ses effets à la cause qui lui a donné naissance[cite: 143].
    
### Applications de l'Induction

- **Transformateur idéal** (rapport de transformation)[cite: 145] :
    $$\frac{U_2}{U_1} = \frac{N_2}{N_1} = \frac{I_1}{I_2}$$
### Self-induction (Inductance $L$)

- **Flux propre** : $\Phi = L I \quad \implies \quad L = \frac{\Phi}{I} \quad [\text{H} = \text{V}\cdot\text{s/A}]$[cite: 147]

- **Tension d'auto-induction** :
    $$U_0 = -L \frac{dI}{dt}$$
- **Inductance d'un solénoïde** (longueur $l$, section $S$, $N$ spires)[cite: 148] :
	$$L = \mu_0 \frac{N^2 S}{l}$$

- **Énergie magnétique stockée dans une bobine**[cite: 150] :
    $$W_M = \frac{1}{2} L I^2$$
- **Densité d'énergie du champ magnétique**[cite: 151] : 
    $$w_M = \frac{1}{2\mu_0} B^2 \quad [\text{J/m}^3]$$
## 8. Lois de Gauss & Ampère (Formes Intégrales)

### Loi de Gauss (Champ Électrique)

Le flux électrique à travers une surface fermée dépend de la charge contenue à l'intérieur[cite: 155] :

$$\epsilon_0 \oint_K \vec{E} \cdot d\vec{S} = Q_{\text{enlacée}}$$

### Loi d'Ampère (Champ Magnétique)

La circulation du champ $\vec{B}$ sur une courbe fermée $\gamma$ est liée au courant enlacé[cite: 162] :


$$\oint_{\gamma} \vec{B} \cdot d\vec{r} = \mu_0 I_{\text{enlacé}}$$

#### Formules Importantes issues d'Ampère / Gauss :

- **Champ d'un fil infini ($I$)**[cite: 163] :
	$$B(r) = \frac{\mu_0 I}{2\pi r}$$
- **Champ au centre d'un solénoïde long**[cite: 164] :
    $$B = \mu_0 \frac{N I}{l}$$

## 9. Équations de Maxwell

Les 4 équations fondamentales sous forme intégrale[cite: 168] :

  

|**Nom**|**Formule Intégrale**|**Signification Physique**|
|---|---|---|
|**Gauss (Électrique)**|$\displaystyle \epsilon_0 \oint \vec{E} \cdot d\vec{S} = Q_{\text{enlacée}}$[cite: 168]|Les charges sont les sources du champ électrique[cite: 154].|
|**Gauss (Magnétique)**|$\displaystyle \oint \vec{B} \cdot d\vec{S} = 0$[cite: 168]|Il n'existe pas de monopôles magnétiques[cite: 166].|
|**Faraday**|$\displaystyle \oint \vec{E} \cdot d\vec{r} = -\frac{d}{dt} \iint \vec{B} \cdot d\vec{S}$[cite: 168]|Un champ magnétique variable induit un champ électrique[cite: 167].|
|**Ampère-Maxwell**|$\displaystyle \oint \vec{B} \cdot d\vec{r} = \mu_0 \left( I_{\text{enlacé}} + \epsilon_0 \frac{d}{dt} \iint \vec{E} \cdot d\vec{S} \right)$[cite: 168]|Les courants électriques et les champs électriques variables induisent un champ magnétique[cite: 167].|

## Formulaire de Secours (Mémo Rapide pour l'Examen)

- $Q = C U$[cite: 93]
    
- $C_{\text{plan}} = \epsilon_0 \frac{S}{d}$[cite: 94]
	
- $W_E = \frac{1}{2} C U^2$[cite: 99]
    
- $U = R I$[cite: 102]
	
- $R = \rho \frac{L}{S}$[cite: 103]
    
- $P = U I = R I^2$[cite: 107]
    
- $\vec{F}_L = q(\vec{v} \times \vec{B})$[cite: 111]
    
- $\vec{F}_{\text{Laplace}} = I(\vec{L} \times \vec{B})$[cite: 116]
    
- $U_0 = -\frac{d\Phi}{dt}$[cite: 141]
    
- $\Phi = L I$[cite: 147]
    
- $W_M = \frac{1}{2} L I^2$[cite: 150]
	
- $w = \frac{1}{2}\epsilon_0 E^2 + \frac{1}{2\mu_0} B^2$[cite: 151]