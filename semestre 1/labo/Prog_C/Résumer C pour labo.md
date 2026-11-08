# Résumé des Laboratoires d'Infotronique (STM32)

Ce document résume le fonctionnement et le code des différents laboratoires sur le microcontrôleur STM32, de manière accessible même pour les débutants en C.

## 1. Architecture Générale (Le fichier `main.c`)

### Concepts

Le fichier `main.c` est le point de départ de votre programme. Il centralise toute l'initialisation matérielle avant de lancer la boucle de traitement infinie.

### Explication du code

* La fonction commence par `HAL_Init()` pour réinitialiser les composants internes.

* `SystemPower_Config()` et `SystemClock_Config()` règlent l'alimentation et la vitesse du processeur (les horloges, ici avec un oscillateur interne MSI).

* Les routines `MX_GPIO_Init` et `MX_USART1_UART_Init` préparent les broches physiques et le port série pour la communication.

* **La boucle principale (`while (1)`)** : Le cœur du programme tourne ici en boucle infinie. L'exécution reste dans ce bloc tant qu'aucune interruption (un événement externe) ne force le processeur à faire autre chose.

## 2. Laboratoire 1 : Introduction et Environnement (GPIO)

### Concepts

* **Outils** : Utilisation de Visual Studio Code avec l'extension STM32CubeIDE.

* **Configuration** : STM32CubeMX configure graphiquement les broches en entrée (boutons) ou en sortie (LEDs).

* **Communication** : Redirection des envois de texte vers le PC via le port série.

### Exemples de Code Pratiques

**Activer l'affichage sur la console (printf) et corriger le bug matériel :**
Pour utiliser `printf`, il faut rediriger la sortie vers le port série (UART). Il faut aussi appliquer la correction (errata) matérielle propre au STM32U5G9 pour que les boutons fonctionnent.

```c
/* Dans main.c, section USER CODE BEGIN 0 */
#include <stdio.h>

// Redirection de printf vers l'UART1
int _io_putchar(int ch) {
    HAL_UART_Transmit(&huart1, (uint8_t *)&ch, 1, HAL_MAX_DELAY);
    return ch;
}
/* USER CODE END 0 */

/* Dans la fonction main(), section USER CODE BEGIN 2 */
// Désactivation du tampon mémoire pour un affichage immédiat dans la console
setvbuf(stdin, NULL, _IONBF, 0);
setvbuf(stdout, NULL, _IONBF, 0);

#ifdef STM32U5G9xx
// Correction obligatoire pour activer le "pull-up" (résistance de tirage) des boutons
SET_BIT(PWR->UCPDR, PWR_UCPDR_UCPD_DBDIS);
HAL_Delay(1);
#endif 
/* USER CODE END 2 */
```

**Allumer une LED quand un bouton est pressé :**
Les boutons utilisent un "pull-up", ils sont donc à l'état bas (`RESET` / 0V) lorsqu'on appuie dessus.

```c
/* Dans la boucle while (1) */
// Si le bouton S1 est pressé (état RESET)
if (HAL_GPIO_ReadPin(S1_GPIO_Port, S1_Pin) == GPIO_PIN_RESET) {
    // Allumer la LED LD1 (état SET)
    HAL_GPIO_WritePin(LD1_GPIO_Port, LD1_Pin, GPIO_PIN_SET);
} else {
    // Eteindre la LED LD1
    HAL_GPIO_WritePin(LD1_GPIO_Port, LD1_Pin, GPIO_PIN_RESET);
}
```

## 3. Laboratoire 2 : Les Interruptions (EXTI) et Anti-rebond

### Concepts

Au lieu de vérifier constamment si un bouton est pressé, on configure une alarme matérielle (Interruption / EXTI). Le processeur s'arrête uniquement quand le bouton est cliqué.

* **Fichier central** : `stm32u5xx_it.c` gère toutes ces interruptions matérielles.

* **La fonction de rappel unique (Callback)** : La bibliothèque HAL centralise la gestion logicielle. **Peu importe la broche configurée en interruption (S1, S2, etc.), le système finit par appeler une seule et même fonction** (`HAL_GPIO_EXTI_Rising_Callback`). C'est au développeur de tester l'argument `GPIO_Pin` à l'intérieur de cette fonction (avec des `if`) pour savoir quelle broche a déclenché l'événement.

* **Le problème du rebond mécanique** : Un bouton physique "rebondit" et crée plusieurs faux contacts. On le corrige en désactivant temporairement l'interruption (logiciel d'anti-rebond).

* **Économie d'énergie** : On peut endormir le processeur quand il attend.

### Exemples de Code Pratiques

**Identifier le bouton pressé dans le Callback unique :**

```c
/* Fonction appelée automatiquement par le processeur lors d'un appui (front montant/descendant) */
/* USER CODE BEGIN 4 */
void HAL_GPIO_EXTI_Rising_Callback(uint16_t GPIO_Pin)
{
    // Le système nous donne la broche dans la variable GPIO_Pin. À nous de la tester !
    
    // Test : Est-ce que l'interruption vient du bouton S1 ?
    if (GPIO_Pin == S1_Pin) 
    {
        // Code spécifique pour le bouton S1 (ex: allumer LD1)
        HAL_GPIO_TogglePin(LD1_GPIO_Port, LD1_Pin);
    }
    // Test : Est-ce que l'interruption vient du bouton S2 ?
    else if (GPIO_Pin == S2_Pin) 
    {
        // Code spécifique pour le bouton S2 (ex: allumer LD2)
        HAL_GPIO_TogglePin(LD2_GPIO_Port, LD2_Pin);
    }
}
/* USER CODE END 4 */
```

**Gestion du rebond mécanique :**

```c
/* Variables globales (à placer avant le main) */
uint32_t temps_interruption = 0;
uint8_t bouton_en_attente = 0;

/* Suite de la fonction Callback précédente */
void HAL_GPIO_EXTI_Rising_Callback(uint16_t GPIO_Pin) {
    if (GPIO_Pin == S1_Pin) {
        HAL_GPIO_TogglePin(LD1_GPIO_Port, LD1_Pin);
        
        // Désactiver l'interruption matérielle pour ignorer les rebonds
        HAL_NVIC_DisableIRQ(EXTI15_IRQn);
        
        // Sauvegarder le temps exact de l'appui et lever un drapeau (flag)
        temps_interruption = HAL_GetTick();
        bouton_en_attente = 1;
    }
}

/* Dans la boucle while (1) (Gestion du retour à la normale) */
// Si 100 millisecondes se sont écoulées depuis l'appui
if (bouton_en_attente == 1 && (HAL_GetTick() - temps_interruption > 100)) {
    // Nettoyer les faux-rebonds stockés en mémoire
    __HAL_GPIO_EXTI_CLEAR_IT(S1_Pin);
    
    // Réactiver le bouton
    HAL_NVIC_EnableIRQ(EXTI15_IRQn);
    bouton_en_attente = 0;
}
```

**Mettre le processeur en veille (Sleep Mode) :**

```c
/* Dans la boucle while (1), lorsqu'il n'y a pas d'action en cours */
// Suspendre le compteur de temps de base (SysTick) pour économiser la batterie
HAL_SuspendTick();

// Plonger le processeur en veille (il se réveillera à la prochaine interruption EXTI)
HAL_PWR_EnterSLEEPMode(PWR_MAINREGULATOR_ON, PWR_SLEEPENTRY_WFI);

// Une fois réveillé (après un appui bouton), on relance le chronomètre
HAL_ResumeTick();
```

## 4. Laboratoire 3 : Les Timers et le PWM

### Concepts

* **Les Timers** : Ce sont des chronomètres internes autonomes. Ils peuvent déclencher des actions à intervalles réguliers sans bloquer le programme (contrairement à `HAL_Delay`).

* **Le PWM (Modulation de largeur d'impulsion)** : C'est un signal rapide allumé/éteint dont on modifie la largeur d'allumage pour contrôler la puissance d'un appareil (ex: vitesse d'un ventilateur de PC).

* **Le retour d'information (Sense)** : Un timer peut aussi agir en mode "compteur" pour compter les impulsions générées par la rotation du ventilateur et ainsi mesurer sa vitesse.

### Exemples de Code Pratiques

**Clignotement strict avec interruption de Timer :**

```c
/* Dans main(), section USER CODE BEGIN 2 */
// Armer et démarrer le Timer 1 en mode interruption
HAL_TIM_Base_Start_IT(&htim1);
/* USER CODE END 2 */

/* Fonction appelée automatiquement à chaque fois que le Timer 1 déborde (ex: chaque seconde) */
/* USER CODE BEGIN 4 */
void HAL_TIM_PeriodElapsedCallback(TIM_HandleTypeDef *htim) {
    // Vérifier de quel timer il s'agit
    if (htim->Instance == TIM1) {
        // Inverser l'état de la LED Verte
        HAL_GPIO_TogglePin(GREEN_LED_GPIO_Port, GREEN_LED_Pin);
    }
}
/* USER CODE END 4 */
```

**Contrôle du ventilateur via un signal PWM :**

```c
/* Dans main(), section USER CODE BEGIN 2 */
// Démarrer le signal PWM sur le Canal 1 du Timer 2
HAL_TIM_PWM_Start(&htim2, TIM_CHANNEL_1);
/* USER CODE END 2 */

/* Dans le code (ex: lors d'un appui bouton ou dans le while) */
// Modifier la puissance via le registre de comparaison CCR1
// (Si la période totale (ARR) est configurée à 1000, 500 représente 50% de puissance)
TIM2->CCR1 = 500; 
```

**Mesurer une fréquence externe avec un Timer :**

```c
/* Variables globales pour le stockage */
uint32_t frequence = 0;
uint32_t temps_mesure = 0;

/* Dans la boucle while(1) */
// Effectuer une lecture toutes les secondes (1000 ms)
if (HAL_GetTick() - temps_mesure >= 1000) {
    // Le Timer 2 a compté silencieusement en arrière-plan. On lit sa valeur.
    frequence = __HAL_TIM_GET_COUNTER(&htim2);
    
    // On réinitialise le compteur à 0 pour la prochaine mesure
    __HAL_TIM_SET_COUNTER(&htim2, 0);
    
    // Affichage du résultat sur le terminal du PC
    printf("Frequence mesuree : %lu Hz\n", frequence);
    
    // Mise à jour du chronomètre de référence
    temps_mesure = HAL_GetTick();
}
```