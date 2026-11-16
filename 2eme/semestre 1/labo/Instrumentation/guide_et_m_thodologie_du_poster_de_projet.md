# Guide Méthodologique : Structure du Projet Capteurs & Rendu Poster

Ce guide récapitule les 6 étapes requises pour concevoir le projet de capteurs et structurer le poster d'évaluation.

---

## 1. Structure du Poster (6 Sections Indispensables)

```
+-----------------------------------------------------------------------------------+
| TITRE DU PROJET ET NOMS DES MEMBRES DE L'ÉQUIPE                                   |
+-----------------------------------+-----------------------------------------------+
| 1. CONTEXTE & BESOIN              | 4. CHAÎNE DE MESURE                           |
| - Grandeur physique mesurée       | - Schéma bloc complet                         |
| - Cahier des charges (Plage,      | - Montage, filtrage, amplification            |
|   Précision, Dynamique, Extérieur)| - Numérisation & Interface (I2C/ADC/Modbus)   |
+-----------------------------------+-----------------------------------------------+
| 2. PRINCIPE PHYSIQUE              | 5. PERFORMANCES & INCERTITUDES                |
| - Effet physique utilisé          | - Sensibilité $S_0$, Linéarité                |
| - Équations théoriques            | - Bilan d'incertitude ($E_Y$)                 |
| - Schéma explicatif               | - Procédure d'étalonnage                      |
+-----------------------------------+-----------------------------------------------+
| 3. VARIANTES & CHOIX              | 6. CONCLUSION & LIMITES                       |
| - Tableau comparatif (3 techno.)  | - Conformité au cahier des charges            |
| - 4 à 5 critères d'évaluation     | - Axes d'amélioration futurs                  |
+-----------------------------------+-----------------------------------------------+
```

---

## 2. Contenu Rédactionnel Point par Point

### Section 1 : Contexte & Besoin
* Définir le mesurande $x(t)$.
* Fixer des objectifs chiffrés :
  * Plage de mesure : $[x_{\min}, x_{\max}]$ avec unité SI.
  * Précision / Incertitude maximale tolérée.
  * Bande passante minimale / Temps de réponse $t_{0.9}$.
  * Contraintes d'environnement (étanchéité IP67, température extrême, masse).

### Section 2 : Principe Physique
* Présenter l'effet (ex: Piezorésistivité, Effet Hall, Effet Seebeck, Capacité variable).
* Rédiger la formule fondatrice en LaTeX (ex: $R(T) = R_0(1+\alpha T)$ ou $V = K_H \cdot B \cdot I$).

### Section 3 : Tableau Comparatif des Variantes
Exemple de structure pour le tableau comparatif :

| Critère d'évaluation | Option A (ex: Pt100) | Option B (ex: Thermocouple K) | Option C (ex: NTC 10k) |
| :--- | :--- | :--- | :--- |
| **Plage de mesure** | $-200^\circ\text{C}$ à $+850^\circ\text{C}$ | $-200^\circ\text{C}$ à $+1260^\circ\text{C}$ | $-40^\circ\text{C}$ à $+125^\circ\text{C}$ |
| **Sensibilité** | $0.385\ \Omega/^\circ\text{C}$ (Moyenne) | $41\ \mu\text{V}/^\circ\text{C}$ (Faible) | Non linéaire (Élevée) |
| **Précision** | **Excellente ($\pm 0.15^\circ\text{C}$)** | Moyenne ($\pm 1.5^\circ\text{C}$) | Bon en plage étroite |
| **Complexité conditionnement** | Faible | Élevée (Soudure froide) | Très faible (Pont diviseur) |
| **Coût** | Moyen | Élevé | **Très bas** |

### Section 4 : Schéma Bloc de la Chaîne de Mesure

$$\text{Mesurande } x \longrightarrow \boxed{\text{Capteur}} \overset{s(t)}{\longrightarrow} \boxed{\text{Filtre/Amp.}} \overset{V_{\text{out}}}{\longrightarrow} \boxed{\text{ADC}} \overset{\text{Code}}{\longrightarrow} \boxed{\text{Microcontrôleur}} \longrightarrow \text{Affichage/Logger}$$

### Section 5 : Bilan d'Incertitude Simplifié
Appliquer le modèle général de propagation des erreurs :

$$E_{\text{total}} = \sum_{i=1}^{N} \left| \frac{\partial f}{\partial X_i} \right| \cdot E_{X_i}$$

Comparer la valeur calculée $E_{\text{total}}$ à l'exigence du cahier des charges.

---

## 3. Grille de Notation & Présentation Orale (100 Points)

1. **Contenu Technique du Poster (60 pts) :** Complétude des 6 sections.
2. **Qualité Formelle (10 pts) :** Lisibilité, unités SI, graphiques avec axes légendés.
3. **Présentation Orale Collective (10 pts) :** Respect des 6 minutes max.
4. **Présentation Orale Individuelle (15 pts) :** Maîtrise technique et réponses aux questions (2 min par étudiant).
5. **Auto-évaluation (5 pts) :** Formulaire individuel de contribution.