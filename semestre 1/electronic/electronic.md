Voici votre fiche de révision consolidée au propre au format **Markdown (`.md`)**, nettoyée et prête à être commitée directement sur votre dépôt Git (avec des schémas en blocs de code texte et du rendu mathématique standardisé).

  

Markdown

````
# Fiche de Révision : Électronique Analogue (Diodes & BJT)

Résumé synthétique pour examen basé sur le programme HES-SO Valais-Wallis (Basic Electronic Circuits / ELNB).

---

## 1. Rappels & Théorèmes de Base (Circuits RLC / DC)

### Lois Fondamentales
* **Loi d'Ohm** : $V = I \cdot R$
* **Loi des Nœuds** : $\sum I = 0$
* **Loi des Mailles** : $\sum V = 0$

### Pont Diviseur de Tension & Modèle de Thévenin
Pour un pont diviseur alimenté entre $V_1$ et $V_2$ (avec $V_2$ négatif ou relié à la masse $0\text{ V}$) :

```text
      ▲ V1
      │
     ┌┴┐ R1
     └┬┘
      ├───► V_TH, R_TH
     ┌┴┐ R2
     └┬┘
      ▼ V2
````

- **Tension de Thévenin ($V_{TH}$)** :
    
      
    
    $$V_{TH} = V_2 + (V_1 - V_2) \cdot \frac{R_2}{R_1 + R_2} = x \cdot V_1 + (1 - x) \cdot V_2 \quad \text{avec } x = \frac{R_2}{R_1 + R_2}$$
    
- **Résistance de Thévenin ($R_{TH}$)** :
    
      
    
    $$R_{TH} = R_1 \parallel R_2 = \frac{R_1 \cdot R_2}{R_1 + R_2} = x \cdot R_1$$
    

## 2. Diodes, Redressement & Stabilisation Zener

### Redresseur en Pont de Graetz

- **Tension crête en charge** :
    
      
    
    $$\hat{U}_L = \hat{U}_s - 2 V_d \quad (\text{avec } V_d \approx 0,7\text{ V})$$
    
- **Ondulation (Filtre Capacitif $C$)** :
    
      
    - Fréquence de sortie : $f_L = 2 \cdot f_{in}$
        
          
        
    - Temps de décharge : $\Delta T \approx \frac{1}{2 f_{in}}$
        
          
        
    - Variation de tension (ondulation crête-à-crête) :
        
          
        
        $$\Delta U_C = \frac{\hat{U}_L \cdot \Delta T}{R_L \cdot C} = \frac{I_L \cdot \Delta T}{C}$$
        
    - Tension moyenne : $\bar{U}_L = \hat{U}_L - \frac{\Delta U_C}{2}$
        
          
        

### Stabilisation par Diode Zener

Pour un montage avec résistance de protection $R_S$ et charge $R_L$ :

  

Plaintext

```
             RS         IS
     ▲ ──────┐ ┌──────┬───────▲
     │       │ │      │       │
    Us      [ ]      ─┴─ Z   [ ] RL  UL
     │       │        ▲       │
     ▼ ──────┴────────┴───────▼
```

- **Courants Zener extremums** :
    
      
    
    $$I_{Z,\text{max}} = \frac{U_{S,\text{max}} - V_Z}{R_S} - \frac{V_Z}{R_{L,\text{max}}}$$
    
    $$I_{Z,\text{min}} = \frac{U_{S,\text{min}} - V_Z}{R_S} - \frac{V_Z}{R_{L,\text{min}}}$$
    
- **Dimensionnement de $R_S$** :
    
      
    
    $$\frac{U_{S,\text{max}} - V_Z}{I_{Z,\text{max(lim)}} + I_{L,\text{min}}} < R_S < \frac{U_{S,\text{min}} - V_Z}{I_{Z,\text{min(lim)}} + I_{L,\text{max}}}$$
    
- **Variation de tension sur la charge (avec résistance dynamique $r_z$)** :
    
      
    
    $$\Delta U_L = r_z \cdot \Delta I_Z$$
    

## 3. Transistor Bipolaire (BJT) en Régime Statique (DC)

### Procédure de Calcul des Points de Repos ($I_C, V_{CE}$)

1. Remplacer le pont diviseur par son équivalent de Thévenin ($V_{TH}, R_{TH}$) à la base.
    
      
    
2. Poser l'équation de la maille d'entrée :
    
      
    
    $$V_{TH} - V_B = I_B \cdot R_{TH} \implies V_B = V_{TH} - R_{TH} \cdot \frac{I_C}{\beta}$$
    
    $$V_E = V_{EE} + I_E \cdot R_E \approx V_{EE} + I_C \cdot R_E$$
    
    $$V_B - V_E = V_{BE(ON)} \approx 0,7\text{ V}$$
    
3. Calculer le courant $I_C$ :
    
      
    
    $$I_C = \frac{V_{TH} - V_{EE} - V_{BE(ON)}}{R_E + \frac{R_{TH}}{\beta}}$$
    
    - _Si $I_C \le 0$_ $\implies$ **Mode Bloqué** ($I_C = 0\text{ A}$).
        
          
        
4. Tester la Saturation :
    
      
    
    $$V_C = V_{CC} - I_C \cdot R_C$$
    
    $$V_{CE} = V_C - V_E = V_{CC} - V_{EE} - I_C (R_C + R_E)$$
    
    - _Si $V_{CE} > V_{CE,\text{sat}}$ ($\approx 0,3\text{ V} - 0,4\text{ V}$)_ $\implies$ **Mode Actif Direct**.
        
          
        
    - _Si $V_{CE} \le V_{CE,\text{sat}}$_ $\implies$ **Mode Saturé** :
        
          
        
        $$I_C = \frac{V_{CC} - V_{EE} - V_{CE,\text{sat}}}{R_C + R_E}$$
        

## 4. Transistor Bipolaire en Régime AC (Petits Signaux)

### Paramètres Hybrides du Transistor

- **Tension thermique** : $V_T \approx 26\text{ mV}$ (à température ambiante).
    
      
    
- **Résistance dynamique d'émetteur ($r'_e$)** :
    
      
    
    $$r'_e = \frac{V_T}{I_{C0}}$$
    
- **Résistance d'entrée de base ($r_\pi$)** :
    
      
    
    $$r_\pi = \beta \cdot r'_e = \frac{\beta \cdot V_T}{I_{C0}}$$
    

## 5. Comparatif des Montages Amplificateurs BJT

### A. Émetteur Commun (CE) — _Gain en tension élevé, Inverseur_

- Condensateur de découplage $C_E$ connecté sur l'émetteur.
    
      
    
- **Résistance d'entrée ($R_i$)** :
    
      
    
    $$R_i = R_{B1} \parallel R_{B2} \parallel r_\pi = R_{TH} \parallel r_\pi$$
    
- **Résistance de sortie ($R_o$)** :
    
      
    
    $$R_o = R_C$$
    
- **Gain en tension à vide ($A_{VE}$)** :
    
      
    
    $$A_{VE} = -\frac{R_C}{r'_e} \quad (\text{sans } C_E : A_{VE} \approx -\frac{R_C}{R_E})$$
    
- **Gain total en charge ($A_v = \frac{v_L}{v_S}$)** :
    
      
    
    $$A_v = \frac{R_i}{R_S + R_i} \cdot \left( -\frac{R_C \parallel R_L}{r'_e} \right)$$
    

### B. Collecteur Commun (CC / Suiveur) — _Adaptateur d'impédance_

- Collecteur directement relié au rail $V_{CC}$ en AC.
    
      
    
- **Résistance d'entrée ($r_{in}$)** :
    
      
    
    $$r_{in} = R_{TH} \parallel \left[ \beta (r'_e + R_E \parallel R_L) \right] \approx R_{TH} \parallel \beta(R_E \parallel R_L)$$
    
- **Résistance de sortie ($r_{out}$)** :
    
      
    
    $$r_{out} = R_E \parallel \left( r'_e + \frac{R_S \parallel R_{TH}}{\beta} \right) \approx R_E \parallel \frac{R_S}{\beta} \parallel \frac{R_{TH}}{\beta}$$
    
- **Gain en tension de l'étage ($A_{v0}$)** :
    
      
    
    $$A_{v0} = \frac{R_E \parallel R_L}{r'_e + R_E \parallel R_L} \approx 1$$
    
- **Gain total ($A_v = \frac{v_L}{v_S}$)** :
    
      
    
    $$A_v = \frac{r_{in}}{R_S + r_{in}} \cdot A_{v0} \approx \frac{r_{in}}{R_S + r_{in}}$$
    

## 6. Amplificateurs Multi-Étages (Cascadés)

Pour un système d'amplificateurs en chaîne (étages 1 à $k$) :

  

Plaintext

```
┌─────────┐      ┌──────────┐            ┌──────────┐      ┌─────────┐
│ Source  ├─────►│ Étage 1  ├───── ... ─►│ Étage k  ├─────►│ Charge  │
│ (vS,RS) │      │ (Ri1,Ro1)│            │ (Rik,Rok)│      │ (RL,vL) │
└─────────┘      └──────────┘            └──────────┘      └─────────┘
```

- **Atténuation d'entrée** : $\frac{R_{i1}}{R_{i1} + R_S}$
    
      
    
- **Adaptation entre étages $m$ et $m+1$** : $\frac{R_{i(m+1)}}{R_{om} + R_{i(m+1)}}$
    
      
    
- **Atténuation de sortie** : $\frac{R_L}{R_{ok} + R_L}$
    
      
    
- **Gain Total ($A_v = \frac{v_L}{v_S}$)** :
    
      
    
    $$A_v = \left( \frac{R_{i1}}{R_{i1} + R_S} \right) \cdot A_{VE1} \cdot \left( \frac{R_{i2}}{R_{o1} + R_{i2}} \right) \cdot A_{VE2} \cdots \left( \frac{R_L}{R_{ok} + R_L} \right)$$
    

## 7. Formulaire Rapide des Pièges d'Examen

1. **Unités** : Reste homogène. $[V] = [k\Omega] \cdot [mA]$ et $[\Omega] = \frac{[mV]}{[mA]}$.
    
      
    
2. **Alimentation double ($V_{CC}$ et $V_{EE}$)** : Attention, $V_{EE}$ est négatif, donc $V_{CC} - V_{EE} = V_{CC} + |V_{EE}|$.
    
      
    
3. **Calcul de $r'_e$** : Nécessite la valeur exacte de $I_C$ au point de repos (DC).
    
      
    
4. **Découplage $C_E$** :
    
      
    - **Avec $C_E$** : $R_E$ disparaît en AC $\implies$ gain élevé ($A_{VE} = -\frac{R_C}{r'_e}$).
        
          
        
    - **Sans $C_E$** : $R_E$ reste présente en AC $\implies$ gain stabilisé mais plus faible ($A_{VE} \approx -\frac{R_C}{R_E}$).
        

## 8. Tableaux de Synthèse des Formules BJT

### Régime Statique (Polarisation DC)

|**Grandeur / Modèle**|**Formule**|**Remarques / Procédure**|
|---|---|---|
|**Tension de Thévenin ($V_{TH}$)**|$V_{TH} = V_{EE} + (V_{CC} - V_{EE}) \cdot \frac{R_{B2}}{R_{B1} + R_{B2}}$|$V_{EE} = 0\text{ V}$ si alimentation simple ($V_{CC}$ seul)|
|**Résistance de Thévenin ($R_{TH}$)**|$R_{TH} = R_{B1} \parallel R_{B2} = \frac{R_{B1} \cdot R_{B2}}{R_{B1} + R_{B2}}$|Résistance équivalente vue par la base|
|**Courant de collecteur ($I_C$)**|$I_C = \frac{V_{TH} - V_{EE} - V_{BE}}{\frac{R_{TH}}{\beta} + R_E \frac{\beta + 1}{\beta}}$|Avec $V_{BE} \approx 0,7\text{ V}$ (NPN) et $I_E = \frac{\beta + 1}{\beta} I_C$|
|**Courant de base ($I_B$)**|$I_B = \frac{I_C}{\beta}$|Négligeable si $\beta \gg 1$|
|**Tension Collecteur-Émetteur ($V_{CE}$)**|$V_{CE} = (V_{CC} - V_{EE}) - I_C \cdot R_C - I_E \cdot R_E$|Vérifier $V_{CE} > V_{CE,\text{sat}}$ ($\approx 0,3\text{ V}$) pour le mode actif|

### Paramètres Alternatifs (Petits Signaux AC)

|**Grandeur**|**Formule**|**Remarques**|
|---|---|---|
|**Tension thermique ($V_T$)**|$V_T = \frac{k \cdot T}{q} \approx 26\text{ mV}$|À température ambiante ($25^\circ\text{C}$)|
|**Résistance dynamique émetteur ($r'_e$)**|$r'_e = \frac{V_T}{I_C}$|Dépend uniquement du courant de repos $I_C$|
|**Résistance d'entrée base ($r_\pi$)**|$r_\pi = \beta \cdot r'_e = \frac{\beta \cdot V_T}{I_C}$|Modèle Hybrid-$\pi$|

### Montages Amplificateurs (Petits Signaux AC)

|**Grandeur / Paramètre**|**Émetteur Commun (CE) (avec CE​)**|**Collecteur Commun (CC) (Suiveur)**|
|---|---|---|
|**Résistance équivalente émetteur ($R_e$)**|$0\ \Omega$ (court-circuité par $C_E$)|$R_E \parallel R_L$|
|**Résistance d'entrée ($R_i$ / $R_{in}$)**|$R_{TH} \parallel r_\pi$|$R_{TH} \parallel \left[ \beta \cdot (r'_e + R_E \parallel R_L) \right]$|
|**Résistance de sortie ($R_o$)**|$R_C$|$r'_e \parallel R_E \parallel \frac{R_S \parallel R_{TH}}{\beta}$ _(très faible)_|
|**Gain à vide ($A_{VE}$ / $A_{v0}$)**|$-\frac{R_C}{r'_e}$|$\frac{R_E \parallel R_L}{r'_e + R_E \parallel R_L} \approx 1$|
|**Gain en charge ($A_{v0} = \frac{v_L}{v_b}$)**|$-\frac{R_C \parallel R_L}{r'_e}$|$\frac{R_E \parallel R_L}{r'_e + R_E \parallel R_L} \approx 1$|
|**Gain total ($A_v = \frac{v_L}{v_S}$)**|$\frac{R_i}{R_S + R_i} \cdot A_{v0}$|$\frac{R_{in}}{R_S + R_{in}} \cdot A_{v0}$|