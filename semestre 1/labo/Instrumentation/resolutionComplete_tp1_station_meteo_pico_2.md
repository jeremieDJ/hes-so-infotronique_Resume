# Résolution Complète : TP Station Météo (Raspberry Pi Pico 2)

## 1. Exercice 1 : Clignotement de la LED embarquée

### Code Complété (`etape1_clignote.py`)

```python
import time
from machine import Pin

# Configuration de la LED embarquée sur le Raspberry Pi Pico 2
led = Pin("LED", Pin.OUT)

compteur = 0

# Boucle d'exécution infinie du microcontrôleur
while True:
  led.on()
  compteur += 1
  print("Allumage n°:", compteur)
  time.sleep(0.5)

  led.off()
  time.sleep(0.5)
```

### Réponses aux Questions :
1. **Pourquoi la boucle `while True:` est-elle indispensable ?**
   Un microcontrôleur n'a pas de système d'exploitation pour gérer la fin d'un programme. S'il n'y a pas de boucle infinie, le processeur exécute les instructions une fois puis s'arrête ou entre dans un état indéterminé (reset). La boucle `while True:` maintient le microcontrôleur en fonctionnement continu.
2. **Que se passe-t-il lors de l'interruption avec `Ctrl+C` ?**
   `Ctrl+C` envoie un signal d'interruption au REPL de MicroPython, ce qui stoppe la boucle `while True:` et redonne le contrôle à l'utilisateur via le terminal interactif.

---

## 2. Exercice 2 : Le Bus I2C et Détection des Périphériques

### Balayage I2C (REPL)
```python
from machine import I2C, Pin

i2c = I2C(0, scl=Pin(1), sda=Pin(0), freq=400000)
adresses = [hex(a) for a in i2c.scan()]
print("Adresses détectées :", adresses)
```

### Résultats et Adresses :
* `0x3c` : Écran OLED SSD1306 ($128 \times 64$).
* `0x76` ou `0x77` : Capteur Météo BME280 / BMP280.

---

## 3. Exercice 3 : Lecture du Capteur BME280

### Code du Module (`etape2_bme280_fonction.py`)

```python
import time
from bme280 import BME280
from machine import I2C, Pin


def lire_meteo(capteur):
  """Lit les données du capteur et retourne (temperature, pression)."""
  temp, press, hum = capteur.lire()
  return temp, press


def formater_meteo(temperature, pression):
  """Formate les valeurs physiques sous forme de chaîne textuelle."""
  texte_temp = "T={:.1f} C".format(temperature)
  texte_press = "P={:.2f} hPa".format(pression)
  return texte_temp, texte_press


def main():
  i2c = I2C(0, scl=Pin(1), sda=Pin(0), freq=400000)
  capteur = BME280(i2c, adresse=0x76)

  while True:
    t, p = lire_meteo(capteur)
    str_t, str_p = formater_meteo(t, p)
    print(str_t + " | " + str_p)
    time.sleep(2)


if __name__ == "__main__":
  main()
```

### Réponses aux Questions :
1. **Unités de sortie :** La température est exprimée en **Degrés Celsius ($^\circ\text{C}$)** et la pression en **Hectopascals ($\text{hPa}$)**.
2. **Signification des formats :**
   * `{:.1f}` : Nombre flottant arrondi à **1 décimale**.
   * `{:.3f}` : Nombre flottant avec **3 décimales**.
   * `{:6.1f}` : Nombre flottant occupant au total **6 caractères de largeur**, avec **1 décimale**.
   * `{}` : Affichage brut du nombre sans formatage prédéfini.
3. **Pression mesurée vs MétéoSuisse :**
   La pression mesurée par le BME280 est la **pression absolue (pression station)** à l'altitude où vous vous trouvez. MétéoSuisse annonce la **pression réduite au niveau de la mer ($QNH$)**. 
   *Loi de la statique des fluides :* $\Delta p \approx -\rho g \Delta z$. La pression diminue d'environ $1\text{ hPa}$ tous les $8.5\text{ mètres}$ d'altitude.

---

## 4. Exercice 4 : Affichage sur Écran OLED SSD1306

### Code du Module (`etape3_oled_fonction.py`)

```python
import time
from machine import I2C, Pin
from ssd1306 import SSD1306_I2C


def initialiser_oled(i2c):
  oled = SSD1306_I2C(128, 64, i2c, adresse=0x3C)
  oled.fill(0)
  oled.show()
  return oled


def afficher_message(oled, message):
  oled.fill(0)
  oled.text(message, 0, 0)
  oled.show()


def afficher_mesures(oled, temperature, pression):
  oled.fill(0)
  # Titre
  oled.text("Station meteo", 0, 0)
  oled.hline(0, 10, 128, 1)

  # Données
  oled.text("T: {:.1f} C".format(temperature), 0, 25)
  oled.text("P: {:.2f} hPa".format(pression), 0, 45)

  oled.show()
```

### Réponses aux Questions :
1. **Effet de l'absence de `fill(0)` :** Les nouveaux caractères s'écrivent par-dessus les anciens sans les effacer, créant une superposition illisible. Le rôle de `fill(0)` est de réinitialiser le tampon mémoire (framebuffer) à zéro (écran noir).
2. **Capacité d'affichage :** La police intégrée fait $8 \times 8$ pixels.
   * Nombre de caractères par ligne : $\frac{128}{8} = 16\text{ caractères}$.
   * Nombre de lignes : $\frac{64}{8} = 8\text{ lignes}$.
   * Un texte trop long est tronqué au bord droit de l'écran.
3. **Repère d'affichage :** L'origine $(0,0)$ est au **coin supérieur gauche**. L'axe $X$ pointe vers la droite, et l'axe $Y$ pointe vers le **bas**.

---

## 5. Exercice 5 : Approfondissement - Filtrage par Moyennage

### Formules Mathématiques du Filtrage
Pour un échantillon de $N$ mesures indépendantes entachées d'un bruit blanc d'écart-type $\sigma$ :

$$\bar{x} = \frac{1}{N} \sum_{i=1}^{N} x_i \quad \implies \quad \sigma_{\bar{x}} = \frac{\sigma}{\sqrt{N}}$$

### Réponses aux Questions :
1. **Facteur de réduction par 10 :** Pour diviser l'écart-type du bruit par 10 ($\frac{\sigma}{\sqrt{N}} = \frac{\sigma}{10}$), il faut :

   $$\sqrt{N} = 10 \quad \implies \quad N = 100\text{ échantillons}$$

   Si chaque mesure dure $10\text{ ms}$, la mesure filtrée prend $100 \times 10\text{ ms} = 1000\text{ ms} = 1\text{ seconde}$.
   *Le prix à payer est une réduction importante du temps de réponse (bande passante plus faible).*

2. **Influence de `DELAI_MS = 0` :**
   Si les lectures sont effectuées plus rapidement que la période de conversion du capteur ($\sim 7\text{ ms}$), le code relit plusieurs fois la même conversion numérique non rafraîchie. Les valeurs ne sont plus indépendantes, le bruit est corrélé et l'amélioration en $\sqrt{N}$ s'effondre.

3. **Erreur systématique et moyennage :**
   La moyenne **n'élimine pas** l'erreur systématique (le biais d'offset). La moyenne réduit uniquement l'incertitude aléatoire.

### Implémentation de la fonction de moyennage (`meteo_station.py`)

```python
import time


def mesurer_pression_moyenne(capteur, nb_echantillons, delai_ms):
  somme_pression = 0.0
  temp_derniere = 0.0

  for _ in range(nb_echantillons):
    temp_derniere, press = capteur.lire()
    somme_pression += press
    time.sleep_ms(delai_ms)

  pression_moyenne = somme_pression / nb_echantillons
  return temp_derniere, pression_moyenne
```

---

## 6. Synthèse de la Chaîne de Mesure

```
[ Pression / Temp. ] ---> [ Capteur BME280 ] ---> [ Conversion I2C ] ---> [ Pico 2 (Filtrage) ] ---> [ Écran OLED ]
```

| Maillon | Grandeur / Signal | Sources potentielles d'erreur / bruit |
| :--- | :--- | :--- |
| **Mesurande** | Température ($^\circ\text{C}$), Pression ($\text{Pa}$) | Fluctuations d'air, auto-échauffement local. |
| **Capteur** | Éléments piezorésistif / capacitif | Bruit thermique du capteur, non-linéarité, hystérèse. |
| **Mise en forme / ADC** | Numérisation interne 20-bit | Bruit de quantification, erreur de gain de l'ADC interne. |
| **Transfert I2C** | Trame numérique | Parasites induits sur les lignes SDA/SCL (erreurs de trame). |
| **Calcul Pico 2** | Traitement MicroPython | Erreurs d'arrondi sur flottants 32-bit, temporisation inadéquate. |
| **Affichage OLED** | Pixels ($128 \times 64$) | Erreur de formatage / tronquage de texte. |