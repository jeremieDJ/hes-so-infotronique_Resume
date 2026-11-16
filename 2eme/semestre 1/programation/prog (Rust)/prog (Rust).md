# Guide Récapitulatif et Aide-Mémoire Rust (AProg - HES-SO)

Ce document récapitule l'ensemble du cours **Advanced Programming (AProg)**. Il est conçu comme un **aide-mémoire complet** pour vous accompagner pendant vos exercices et laboratoires de programmation en Rust.

---

## Table des Matières
1. [Configuration, Outils & Git Workflow](#1-configuration-outils--git-workflow)
2. [Bases du Langage & Syntaxe Impérative](#2-bases-du-langage--syntaxe-impérative)
3. [Types Primitifs, Littéraux et Conversions](#3-types-primitifs-littéraux-et-conversions)
4. [Gestion de la Mémoire : Stack, Heap, Directives de Propriété (Ownership)](#4-gestion-de-la-mémoire--stack-heap-directives-de-propriété-ownership)
5. [Contrôle de Flux & Pattern Matching](#5-contrôle-de-flux--pattern-matching)
6. [Types Composés et Collections](#6-types-composés-et-collections)
7. [Gestion des Erreurs : Option et Result](#7-gestion-des-erreurs--option-et-result)
8. [Programmation Orientée Objet (OOP) & Design en Rust](#8-programmation-orientée-objet-oop--design-en-rust)
9. [Traits Standard & Patrons de Conception (Design Patterns)](#9-traits-standard--patrons-de-conception-design-patterns)
10. [Concurrence et Async/Await](#10-concurrence-et-asyncawait)
11. [Pointeurs Intelligents (Smart Pointers)](#11-pointeurs-intelligents-smart-pointers)
12. [Tests, Débogage, Documentation & Profilage](#12-tests-débogage-documentation--profilage)

---

## 1. Configuration, Outils & Git Workflow

### Convention de Commits Git (Conventional Commits)
Format : `<type>(<scope>): <description>`

| Type | Utilisation |
| :--- | :--- |
| `feat` | Nouvelle fonctionnalité / comportement |
| `fix` | Correction de bug |
| `docs` | Modifications de la documentation uniquement |
| `style` | Formatage / cosmétique (aucun changement logique) |
| `refactor` | Amélioration du code sans modifier le comportement |
| `test` | Ajout ou modification de tests |
| `chore` | Tâches de build, CI, configuration |
| `perf` | Amélioration des performances |

**Workflow de rendu :**
```bash
git add .
git commit -m "feat(ex03): implement temperature converter"
git tag exercises-03
git push && git push --tags
```

### Commandes de la Chaîne d'Outils (Cargo & Rustup)
* `rustup update` : Mettre à jour la chaîne d'outils Rust.
* `cargo new my_app` : Créer un nouveau projet binaire.
* `cargo new --lib my_lib` : Créer un projet sous forme de bibliothèque.
* `cargo build` / `cargo build --release` : Compiler en mode Debug ou Release (optimisé).
* `cargo run` : Compiler et exécuter le binaire.
* `cargo check` : Vérifier la typographie et la syntaxe rapidement sans générer de binaire.
* `cargo test` : Exécuter l'ensemble des tests (unitaires, d'intégration, doc-tests).
* `cargo fmt` : Formater le code selon les normes canoniques.
* `cargo clippy` : Exécuter le linter statique avancé.
* `cargo doc --open` : Générer et ouvrir la documentation HTML du projet.

---

## 2. Bases du Langage & Syntaxe Impérative

### Conventions de Nommage
* **Variables, Fonctions, Modules** : `snake_case` (ex: `sensor_value`, `read_sensor()`)
* **Constantes, Statics** : `SCREAMING_SNAKE_CASE` (ex: `MAX_RETRIES`)
* **Types, Structs, Enums, Traits** : `UpperCamelCase` (ex: `TemperatureSensor`, `SensorState`)

### Constantes vs Variables
* `const` : Évaluée à la compilation, inlinée (zéro coût à l'exécution), portée globale possible, type obligatoire.
* `let` : Liaison à l'exécution, stockée sur la Stack, immuable par défaut.
* `let mut` : Rend la liaison réassignable.

```rust
const MAX_SCORE: u32 = 100_000; // Type obligatoire
const SECS_PER_DAY: u64 = 60 * 60 * 24; // Constante calculée

fn main() {
    let x = 5; // Immuable par défaut
    let mut count = 0_u32; // Mutable
    count += 1;
    // Shadowing : Réutilisation du nom + possibilité de changer de type
    let spaces = "   ";
    let spaces = spaces.len(); // spaces devient un usize (valeur 3)
}
```

### Ignorer des valeurs (`_` vs `_nom`)
* `let _ = func();` : La valeur retournée est immédiatement détruite (dropped).
* `let _guard = func();` : La valeur reste vivante jusqu'à la fin de la portée, mais le warning d'inutilisation est masqué.

---

## 3. Types Primitifs, Littéraux et Conversions

### Littéraux Numériques
* Décimaux : `98_222`
* Hexadécimaux (`0x`) : `0xFF`
* Octaux (`0o`) : `0o77`
* Binaires (`0b`) : `0b1111_0000`
* Octet ASCII : `b'A'` (Type `u8`, valeur `65`)

### Types Scalaires Standard
* **Entiers Signés** : `i8`, `i16`, `i32` (par défaut), `i64`, `i128`, `isize` (dépend de l'architecture).
* **Entiers Non-Signés** : `u8`, `u16`, `u32`, `u64`, `u128`, `usize` (indices de collections).
* **Flottants** : `f32`, `f64` (par défaut).
* **Booléens** : `bool` (`true`, `false`).
* **Caractère** : `char` (Scalar Unicode, 4 octets, ex: `'A'`, `'é'`, `'🦀'`).
* **Type Unité** : `()` (Tuple vide, zéro octet, indique l'absence de valeur de retour).

### Gestion du Débordement d'Entiers (Overflow)
En mode `debug`, un overflow provoque un panic. En mode `release`, il effectue un saut circulaire (*wrap*).
Méthodes explicites :
```rust
let x: u8 = 255;
x.wrapping_add(1);   // Succès : retourne 0
x.saturating_add(1); // Clampe la valeur à la limite : retourne 255
x.checked_add(1);    // Retourne un Option : None
x.overflowing_add(1);// Retourne un tuple : (0, true)
```

### Conversion de Types
* **Cast explicite avec `as`** : Rapide, mais potentiellement destructeur (tronquage lors du passage d'un grand type vers un plus petit, ex: `300_i32 as u8` donne `44`). À utiliser en toute sécurité uniquement pour les élargissements (`u8 as u32`).
* **Traits `From` / `Into`** : Conversion infaillible (ex: `String::from("hello")` ou `"hello".into()`).
* **Traits `TryFrom` / `TryInto`** : Conversion faillible retournant un `Result` (ex: `u8::try_from(300_i32)` renvoie une `Err`).

---

## 4. Gestion de la Mémoire : Stack, Heap, Directives de Propriété (Ownership)

### Memory Allocation
* **Stack (Pile)** : Rapide, taille fixe déterminée à la compilation. Stocke les variables locales des fonctions. Nettoyage automatique en fin de portée (RAII).
* **Heap (Tas)** : Taille dynamique, allocation et libération explicites (gérées automatiquement par Rust sans Garbage Collector).

```text
Stack                    Heap
+-----------------+      +-------------------+
| ptr -----------+-----> | "Hello World"     |
| len: 11         |      +-------------------+
| cap: 11         |
+-----------------+
```

### Les 3 Règles d'Or de l'Ownership
1. Chaque valeur en Rust possède un propriétaire (*owner*).
2. Il ne peut y avoir qu'un seul propriétaire à la fois.
3. Quand le propriétaire sort de la portée (*scope*), la valeur est automatiquement libérée (`drop`).

### Move vs Copy
* **Types `Copy`** : Types simples stockés uniquement sur la Stack (`i32`, `f64`, `bool`, `char`, tuples de types `Copy`). L'affectation duplique la valeur.
* **Types `Move`** : Types gérant des ressources Heap (`String`, `Vec<T>`, structs personnalisés par défaut). L'affectation transfère la propriété ; l'ancienne variable devient invalide.

```rust
// Types Copy
let a = 42;
let b = a; // Duplication : 'a' et 'b' sont utilisables

// Types Move
let s1 = String::from("hello");
let s2 = s1; // Transfert de propriété (Move)
// println!("{s1}"); // ERREUR DE COMPILATION : s1 n'est plus valide
```

### Règles de l'Emprunt (Borrowing & References)
1. Vous pouvez avoir soit **un nombre illimité de références immuables (`&T`)**, soit **exactement une référence mutable (`&mut T`)** à un instant donné.
2. Les références doivent toujours être valides (absence de pointeurs pendants / *dangling pointers*).

```rust
let mut s = String::from("hello");
let r1 = &s; // OK
let r2 = &s; // OK
println!("{r1} et {r2}");

let m = &mut s; // OK : r1 et r2 ne sont plus utilisés après ce point
m.push_str(", world!");
```

### Durées de Vie (Lifetimes `'a`)
Les durées de vie sont des annotations à la compilation garantissant qu'une référence ne survit pas à sa donnée source.
```rust
// 'a indique que le résultat est valide aussi longtemps que in1 ET in2 restent valides
fn longest<'a>(in1: &'a str, in2: &'a str) -> &'a str {
    if in1.len() > in2.len() { in1 } else { in2 }
}
```

---

## 5. Contrôle de Flux & Pattern Matching

### `if` est une Expression
```rust
let x = -7;
let abs_x = if x < 0 { -x } else { x }; // Les deux branches doivent renvoyer le même type
```
*Exemple récursif (Factorielle) :*
```rust
fn factorial(n: u64) -> u64 {
    if n == 0 { 1 } else { n * factorial(n - 1) }
}
```

### Pattern Matching (`match`)
Le bloc `match` doit être **exhaustif**.
```rust
let day = 3;
let name = match day {
    1 => "Lundi",
    2 => "Mardi",
    3 => "Mercredi",
    4 | 5 => "Jeudi ou Vendredi",
    6..=7 => "Weekend",
    _ => "Invalide", // Joker obligatoire si toutes les valeurs ne sont pas couvertes
};
```
*Exemple récursif (Fibonacci) :*
```rust
fn fib_rec(n: u32) -> u64 {
    match n {
        0 => 0,
        1 => 1,
        _ => fib_rec(n - 1) + fib_rec(n - 2),
    }
}
```

### Destruction Conditionnelle (`if let` & `let else`)
```rust
let maybe_num: Option<i32> = Some(42);

// if let : Exécute le code uniquement si le pattern correspond
if let Some(n) = maybe_num {
    println!("Valeur : {n}");
}

// let else : Extrait la valeur ou diverge (return/break/panic) dans la branche else
fn process(val: Option<i32>) -> i32 {
    let Some(n) = val else {
        return 0; // Obligation de diverger
    };
    n * 2
}
```

### Boucles (`loop`, `while`, `for`)
```rust
// loop avec valeur de retour
let mut counter = 0;
let result = loop {
    counter += 1;
    if counter == 10 {
        break counter * 2; // Retourne 20
    }
};

// Iterateurs et Ranges dans for
for i in 0..5 { /* [0, 4] */ }
for i in 1..=5 { /* [1, 5] */ }

let items = ["a", "b", "c"];
for (idx, val) in items.iter().enumerate() {
    println!("{idx}: {val}");
}
```

---

## 6. Types Composés et Collections

### Chaines de Caractères (`&str` vs `String`)
* `&str` : Tranche de chaîne (*String slice*), vue immuable empruntée (souvent stockée dans le binaire ou sur la Heap).
* `String` : Texte encodé en UTF-8, modifiable, possédée et stockée sur la Heap.

```rust
let mut s: String = String::from("Hello");
s.push_str(" World");
let slice: &str = &s[0..5]; // Contient "Hello"
```

### Tableau Fixe (`[T; N]`) et Tranche (`&[T]`)
Taille fixe connue à la compilation, données contiguës en mémoire.
```rust
let arr: [i32; 4] = [10, 20, 30, 40];
let slice: &[i32] = &arr[1..3]; // Tranche contenant [20, 30]
```
*Exemple de recherche du max dans une tranche :*
```rust
fn max(arr: &[i32]) -> Option<i32> {
    if arr.is_empty() { None } else { arr.iter().copied().max() }
}
```

### Vecteur (`Vec<T>`)
Liste dynamique stockée sur la Heap.
```rust
let mut vec = vec![1, 2, 3];
vec.push(4);
let item: Option<&i32> = vec.get(10); // Accès sécurisé évitant les panics
```

### Tables de Hachage (`HashMap<K, V>` & `BTreeMap<K, V>`)
* `HashMap` : Accès rapide en $O(1)$, ordre non garanti.
* `BTreeMap` : Éléments triés selon les clés.

```rust
use std::collections::HashMap;

let mut map = HashMap::new();
map.insert("T1", 23.7);

// Pattern idiomatique : insertion ou mise à jour
let counter = map.entry("T1").or_insert(0.0);
*counter += 1.0;
```

---

## 7. Gestion des Erreurs : Option et Result

Rust n'utilise pas de pointeurs nuls ni d'exceptions, mais deux enums clés :

### Enum `Option<T>`
Représente la présence (`Some(T)`) ou l'absence (`None`) d'une valeur.
```rust
enum Option<T> {
    Some(T),
    None,
}
```

### Enum `Result<T, E>`
Représente le succès (`Ok(T)`) ou l'échec (`Err(E)`).
```rust
enum Result<T, E> {
    Ok(T),
    Err(E),
}
```

### Boîte à Outils pour Extraire ou Propager
```rust
// 1. L'opérateur '?' : Propage l'erreur/None à la fonction appelante
fn parse_number(s: &str) -> Result<u32, std::num::ParseIntError> {
    let n = s.trim().parse::<u32>()?; // Retourne immédiatement si Err
    Ok(n)
}

// 2. Traitement sécurisé avec valeurs par défaut
let val = opt.unwrap_or(0);                 // Valeur par défaut basique
let val = opt.unwrap_or_else(|| compute());  // Valeur par défaut calculée paresseusement
let val = opt.unwrap_or_default();           // Valeur par défaut du trait Default

// 3. Extraction forcée (À ÉVITER en code de production)
let val = opt.unwrap();         // Crash/Panic si None/Err
let val = opt.expect("Message");// Crash avec message d'erreur explicite
```
*Exemple de recherche linéaire avec retour `Option<usize>` :*
```rust
fn linear_search(arr: &[i32], target: i32) -> Option<usize> {
    for i in 0..arr.len() {
        if arr[i] == target { return Some(i); }
    }
    None
}
```

---

## 8. Programmation Orientée Objet (OOP) & Design en Rust

En Rust, l'OOP s'articule autour de la séparation des données (`struct`) et du comportement (`impl`), combinés aux `traits`.

### Struct et Impl
```rust
pub struct Rectangle {
    width: f64,
    height: f64,
}

impl Rectangle {
    // Constructeur associé (pas de self)
    pub fn new(width: f64, height: f64) -> Self {
        Self { width, height }
    }

    // Méthode de lecture (&self)
    pub fn area(&self) -> f64 {
        self.width * self.height
    }

    // Méthode de modification (&mut self)
    pub fn scale(&mut self, factor: f64) {
        self.width *= factor;
        self.height *= factor;
    }

    // Méthode consommant l'objet (self)
    pub fn destroy(self) { /* L'objet est libéré ici */ }
}
```

### Les 4 Piliers OOP en Rust
| Pilier Classic | Équivalent Rust |
| --- | --- |
| **Encapsulation** | Modules (`mod`) et visibilité `pub` (privé par défaut). |
| **Abstraction** | Traits (contrats d'interfaces sans données). |
| **Héritage** | *Pas d'héritage de structures*. Remplacé par la **Composition** et les **Traits**. |
| **Polymorphisme** | **Génériques** (Dispatch Statique) ou **Trait Objects** (Dispatch Dynamique). |

### Encapsulation avec Module & Visibilité
```rust
mod bank {
    pub struct Account {
        owner: String,   // Public
        balance: u64,    // Privé : inaccessible en dehors du module bank
    }

    impl Account {
        pub fn new(owner: &str) -> Self {
            Self { owner: owner.into(), balance: 0 }
        }
        pub fn balance(&self) -> u64 { self.balance } // Getter
    }
}
```

### Abstraction avec Traits
```rust
pub trait Drawable {
    fn draw(&self) -> String; // Méthode abstraite
    fn show(&self) {          // Méthode par défaut
        println!("{}", self.draw());
    }
}
```

### Composition au lieu de l'Héritage
```rust
struct Engine { power_kw: u32 }
struct Car {
    engine: Engine, // Car HAS-A Engine (Contient un moteur)
    seats: u8,
}
```

### Polymorphisme : Static vs Dynamic Dispatch
```rust
// 1. Static Dispatch (Génériques & Trait Bounds)
// Génère une version de la fonction par type à la compilation (Monomorphisation, ultra rapide)
fn render_static<T: Drawable>(item: &T) {
    println!("{}", item.draw());
}

// 2. Dynamic Dispatch (Trait Objects & vtable)
// Permet de mélanger différents types dans une même collection via des pointeurs gras (data_ptr, vtable_ptr)
fn render_dynamic(items: &[Box<dyn Drawable>]) {
    for item in items {
        println!("{}", item.draw());
    }
}
```

---

## 9. Traits Standard & Patrons de Conception (Design Patterns)

### Traits Derivables `#[derive(...)]`
* `Debug` (`{:?}`) : Formate le type pour le débogage.
* `Clone` : Permet la duplication explicite via `.clone()`.
* `Copy` : Duplication implicite sur la Stack.
* `PartialEq`, `Eq` : Permet les comparaisons d'égalité (`==`, `!=`).
* `PartialOrd`, `Ord` : Permet les comparaisons d'ordre (`<`, `>`, `.sort()`).
* `Default` : Fournit un constructeur sans arguments (`Type::default()`).
* `Hash` : Permet d'utiliser le type comme clé dans une `HashMap`.

### Exemples de Traits Idiomatiques
```rust
use std::fmt;
use std::ops::Add;

struct Point { x: f64, y: f64 }

// Trait Display ({})
impl fmt::Display for Point {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "({}, {})", self.x, self.y)
    }
}

// Surcharge d'opérateur '+'
impl Add for Point {
    type Output = Point;
    fn add(self, rhs: Point) -> Point {
        Point { x: self.x + rhs.x, y: self.y + rhs.y }
    }
}
```

### Design Patterns en Rust
1. **Newtype Pattern** : Envelopper un type dans une structure tuple à un champ pour garantir la sécurité des types à coût nul (ex: `struct Meters(f64); struct Seconds(f64);`).
2. **Builder Pattern** : Permet la construction étape par étape de structures complexes sans constructeurs télescopiques.
3. **RAII / Drop** : Libération des ressources (fichiers, verrous) automatiquement exécutée à la fin de la portée dans la méthode `drop(&mut self)`.
4. **Mémoïsation (Dynamic Programming)** : Utilisation d'un vecteur de type `Option` jouant le rôle de « carnet de notes » pour stocker les résultats intermédiaires d'une fonction récursive (ex: Fibonacci) et éviter les recalculs exponentiels :
```rust
fn fib_memo(n: u32) -> u64 {
    let mut memo = vec![None; (n + 1) as usize];
    fib_memo_dyn(n, &mut memo)
}

fn fib_memo_dyn(n: u32, memo: &mut Vec<Option<u64>>) -> u64 {
    if let Some(val) = memo[n as usize] {
        return val;
    }
    let res = if n <= 1 {
        n as u64
    } else {
        fib_memo_dyn(n - 1, memo) + fib_memo_dyn(n - 2, memo)
    };
    memo[n as usize] = Some(res);
    res
}
```
5. **Singleton** : Utilisation de `static INSTANCE: OnceLock<T> = OnceLock::new();`.

---

## 10. Concurrence et Async/Await

### Concurrence avec Threads
```rust
use std::thread;
use std::time::Duration;

let handle = thread::spawn(|| {
    thread::sleep(Duration::from_millis(50));
    42 // Valeur retournée par le thread
});

let result = handle.join().unwrap(); // Attend la fin du thread
```

### Passages de Messages par Canaux (mpsc)
Multi-Producer, Single-Consumer : Plusieurs producteurs envoient des données à un seul récepteur.
```rust
use std::sync::mpsc;
use std::thread;

let (tx, rx) = mpsc::channel();
let tx1 = tx.clone();
thread::spawn(move || {
    tx1.send("message du thread 1").unwrap();
});

let msg = rx.recv().unwrap(); // Bloque jusqu'à réception
```

### Modèle Async / Await
Utilisé pour les opérations dépendantes des E/S (I/O Bound) à grande échelle.
* Les fonctions `async fn` renvoient des `Future` qui sont exécutées de manière paresseuse (*lazy*).
* `.await` suspend la tâche courante sans bloquer le thread sous-jacent.

```rust
async fn fetch_data(id: u32) -> String {
    // Simulation d'une attente non-bloquante
    tokio::time::sleep(std::time::Duration::from_millis(100)).await;
    format!("données {id}")
}
```

---

## 11. Pointeurs Intelligents (Smart Pointers)

| Type | Thread | Propriété | Usage principal |
| :--- | :--- | :--- | :--- |
| **`Box<T>`** | Monothread / Multithread | Unique | Allocation sur la Heap, structures récursives, Trait Objects (`Box<dyn Trait>`). |
| **`Rc<T>`** | Monothread | Partagée | Compteur de références (*Reference Counting*). Plusieurs lecteurs de la même donnée. |
| **`Arc<T>`** | Multithread | Partagée | Atomic Reference Counting (sécurisé entre threads). |
| **`Cell<T>`** | Monothread | Intérieure | Mutabilité intérieure pour les types `Copy` via `get()` / `set()`. |
| **`RefCell<T>`** | Monothread | Intérieure | Mutabilité intérieure avec vérification des règles d'emprunt à l'exécution (`borrow()`, `borrow_mut()`). |
| **`Mutex<T>`** | Multithread | Intérieure | Verrou à exclusion mutuelle. Donne un accès exclusif en écriture à un seul thread. |
| **`RwLock<T>`** | Multithread | Intérieure | Verrou lecture/écriture. Autorise plusieurs lecteurs simultanés OU un écrivain. |

### Combinaisons Fréquentes
* **Monothread Partagé + Mutable** : `Rc<RefCell<T>>`
* **Multithread Partagé + Mutable** : `Arc<Mutex<T>>`

```rust
use std::sync::{Arc, Mutex};
use std::thread;

let counter = Arc::new(Mutex::new(0));
let mut handles = vec![];

for _ in 0..5 {
    let counter_clone = Arc::clone(&counter);
    let handle = thread::spawn(move || {
        let mut guard = counter_clone.lock().unwrap();
        *guard += 1; // Déverrouillage automatique en fin de portée (RAII)
    });
    handles.push(handle);
}
```

---

## 12. Tests, Débogage, Documentation & Profilage

### Structure d'un Test Unitaire
```rust
pub fn add(a: i32, b: i32) -> i32 {
    a + b
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_add() {
        assert_eq!(add(2, 3), 5);
        assert_ne!(add(2, 3), 6);
    }

    #[test]
    #[should_panic(expected = "overflow")]
    fn test_panic() {
        panic!("overflow");
    }
}
```

### Outils de Débogage & Macro
* `println!("val = {val:?}");` : Affichage Debug.
* `dbg!(expr);` : Imprime le fichier, le numéro de ligne, l'expression et sa valeur, puis renvoie la valeur.

### Documentation (`rustdoc`)
* `///` : Documentation de l'item suivant (fonctions, structs). Les exemples de code inclus sont exécutés lors du `cargo test`.
* `//!` : Documentation du module englobant ou du crate (`lib.rs`).

```rust
/// Calcule le carré d'un nombre.
///
/// # Examples
/// ```
/// let res = my_crate::square(4);
/// assert_eq!(res, 16);
/// ```
pub fn square(x: i32) -> i32 {
    x * x
}
```

### Profilage & Mesure de Performance
* **Hyperfine** : Outil de benchmark en ligne de commande (permet de mesurer la répartition temps Utilisateur / Système).
* **Flamegraph** : Représentation visuelle des points chauds de consommation processeur (`cargo flamegraph`).

---
*Ce document regroupe toutes les notions de vos supports de cours pour vos séances de travaux pratiques et vos révisions.*