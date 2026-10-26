# 🦀 Le Guide Ultime de Rust (Théorie & Pratique)

Ce document fusionne et détaille l'intégralité des concepts abordés en programmation Rust, allant des bases de la syntaxe jusqu'aux concepts avancés de gestion mémoire et de concurrence. Il est conçu pour servir de manuel de référence exhaustif.

---

## 1. Outils, Cargo & Écosystème

L'écosystème Rust est centralisé autour de son gestionnaire de paquets et système de build : **Cargo**.

### Commandes Cargo Essentielles
* `cargo new <nom>` : Crée un projet binaire (avec un `main.rs`).
* `cargo new --lib <nom>` : Crée une bibliothèque (avec un `lib.rs`).
* `cargo build` : Compile le projet en mode Debug.
* `cargo build --release` : Compile avec les optimisations maximales (plus long, mais binaire très rapide).
* `cargo run` : Compile et exécute le binaire immédiatement.
* `cargo check` : Vérifie la syntaxe, le typage et le Borrow Checker sans générer de binaire (très rapide, à utiliser en boucle pendant le développement).
* `cargo test` : Lance tous les tests du projet.
* `cargo clippy` : Le linter de Rust. Il analyse le code et propose des améliorations pour le rendre plus idiomatique (plus "Rust-like").

---

## 2. Variables, Types & Mutabilité

Par défaut, Rust privilégie la sécurité et l'immuabilité.

### Variables et Constantes
Toute variable déclarée avec `let` est immuable. Il faut utiliser `mut` pour autoriser la modification.

```rust
let x = 5;       // Immuable
// x = 6;        // ERREUR COMPILATEUR

let mut y = 10;  // Mutable
y += 5;          // OK

// Shadowing (masquage) : permet de redéclarer une variable pour changer son type
let spaces = "   ";
let spaces = spaces.len(); // Devient un entier (3), très utile pour convertir des données.

// Constantes : évaluées à la compilation, type explicite obligatoire.
const MAX_SPEED: u32 = 300_000;
```

### Types Primitifs & Conversions
* **Entiers** : `i8`, `i16`, `i32`, `i64`, `isize` (signés) / `u8`, `u16`, `u32`, `u64`, `usize` (non-signés).
* **Flottants** : `f32`, `f64`.
* **Booléens & Caractères** : `bool` (`true`/`false`), `char` (4 octets, supporte l'Unicode complet).
* **Tuples** : Groupement hétérogène de taille fixe. Ex: `let t: (i32, f64, char) = (42, 3.14, 'a');`

**Conversions :**
```rust
// Cast explicite avec `as` (attention aux pertes de précision)
let a: u32 = 300;
let b: u8 = a as u8; // Résultat : 44 (troncature car 300 > 255)

// Conversion sécurisée
use std::convert::TryInto;
let c: Result<u8, _> = a.try_into(); // Renvoie une Erreur proprement
```

---

## 3. Le Cœur de Rust : Ownership, Emprunts & Lifetimes

C'est ici que Rust se différencie des autres langages (C++, Java, Python). Il n'y a pas de *Garbage Collector*, la mémoire est gérée à la compilation grâce à 3 règles strictes.

### 3.1 Move vs Copy
Les types simples de taille connue à la compilation (entiers, booléens) implémentent le trait `Copy`. Ils sont copiés.
Les types alloués sur le tas (Heap) comme `String` ou `Vec` ont une sémantique de déplacement (`Move`).

```rust
let a = 42;
let b = a; // Copie (valide car i32 est Copy)
println!("{} et {}", a, b); // OK

let s1 = String::from("hello");
let s2 = s1; // Move ! La propriété de la mémoire passe à s2.
// println!("{s1}"); // ERREUR : s1 n'est plus valide, il a été "vidé".
```

### 3.2 Borrowing (Les Emprunts)
Au lieu de transférer la propriété, on peut "emprunter" l'accès à une donnée via des références (`&`).
**Règle d'or du Borrow Checker :** À un instant T, vous pouvez avoir SOIT plusieurs références immuables (`&T`), SOIT exactement une seule référence mutable (`&mut T`). Jamais les deux en même temps.

```rust
let mut s = String::from("hello");

let r1 = &s; // Emprunt immuable
let r2 = &s; // Un autre, c'est permis !
// let r3 = &mut s; // ERREUR : impossible car r1 et r2 lisent la donnée.

println!("{} et {}", r1, r2); // r1 et r2 sont utilisés pour la dernière fois ici.

// Maintenant on peut créer une référence mutable :
let m = &mut s; 
m.push_str(", world");
```

### 3.3 Lifetimes (Durées de vie)
Parfois, le compilateur ne sait pas combien de temps une référence va vivre, notamment quand une fonction retourne une référence basée sur plusieurs paramètres entrants. On utilise alors les annotations de lifetime (`'a`).

```rust
// Le compilateur exige un lifetime pour savoir si la référence retournée
// dépend de `x` ou de `y`.
fn plus_longue<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() { x } else { y }
}
// Explication : La référence retournée vivra au moins aussi longtemps 
// que la plus courte des durées de vie entre `x` et `y`.
```

---

## 4. Contrôle de Flux (Expressions)

En Rust, presque tout est une expression (qui retourne une valeur).

### `if` et `match`
```rust
let age = 18;
// if assigné directement
let status = if age >= 18 { "Majeur" } else { "Mineur" };

// match est ultra puissant et DOIT être exhaustif
let code = 200;
match code {
    200 => println!("OK"),
    400..=499 => println!("Erreur client"),
    _ => println!("Autre code"), // _ capture tout le reste
}
```

### Boucles (`loop`, `while`, `for`)
```rust
// loop : boucle infinie. Peut retourner une valeur avec break !
let mut i = 0;
let resultat = loop {
    i += 1;
    if i == 10 { break i * 2; }
};

// for sur un itérateur (la méthode la plus performante et sûre en Rust)
let items = [10, 20, 30];
for val in items.iter() {
    println!("{}", val);
}
```

---

## 5. Gestion des Erreurs & Enums Avancés

Rust n'a pas d'exceptions (`try/catch`). Les erreurs font partie de la signature de la fonction.

### `Option<T>` pour l'absence de valeur
```rust
fn diviser(a: f64, b: f64) -> Option<f64> {
    if b == 0.0 { None } else { Some(a / b) }
}

if let Some(resultat) = diviser(10.0, 2.0) {
    println!("Succès : {}", resultat);
}
```

### `Result<T, E>` pour la gestion d'erreurs
Plutôt que d'utiliser des chaînes de caractères, on modélise souvent les erreurs avec des `enum`. L'opérateur `?` permet de propager l'erreur automatiquement.

```rust
#[derive(Debug)]
pub enum MathError {
    DivisionParZero,
    NombreNegatif,
}

fn racine_carree(x: f64) -> Result<f64, MathError> {
    if x < 0.0 { return Err(MathError::NombreNegatif); }
    Ok(x.sqrt())
}

fn calcul_complexe(x: f64) -> Result<f64, MathError> {
    // L'opérateur ? retourne l'erreur immédiatement si racine_carree échoue
    let racine = racine_carree(x)?; 
    Ok(racine * 2.0)
}
```

---

## 6. Programmation Orientée Objet (Structs & Traits)

### Encapsulation (Structs & Impl)
Les données sont dans `struct`, les méthodes dans `impl`.
```rust
pub struct Compte {
    solde: u32, // Privé par défaut
}

impl Compte {
    pub fn new() -> Self { Self { solde: 0 } }
    pub fn deposer(&mut self, montant: u32) { self.solde += montant; }
    pub fn solde(&self) -> u32 { self.solde }
}
```

### Abstraction & Polymorphisme (Traits & Génériques)
Les traits définissent des comportements partagés (comme les interfaces en Java/C#).

```rust
use std::fmt::Display;

pub trait Animal {
    fn crier(&self) -> String;
}

struct Chien;
impl Animal for Chien {
    fn crier(&self) -> String { "Ouaf !".into() }
}

// Fonction générique avec un "Trait Bound" : 
// T doit implémenter Animal ET Display
fn faire_du_bruit<T: Animal + Display>(animal: &T) {
    println!("L'animal dit : {}", animal.crier());
}
```

---

## 7. Programmation Fonctionnelle : Closures & Itérateurs

### Closures
Des fonctions anonymes qui capturent leur environnement.
```rust
let multiplicateur = 3;
let multiplier = |x| x * multiplicateur; 
println!("{}", multiplier(5)); // 15
```

### Itérateurs (Zero-Cost Abstractions)
Les itérateurs sont paresseux. Ils enchaînent les opérations très efficacement sans allouer de mémoire inutile.
```rust
let nombres = vec![1, 2, 3, 4, 5];

// Map / Filter / Collect
let doubles_pairs: Vec<i32> = nombres
    .into_iter()
    .filter(|x| x % 2 == 0) // Garde 2, 4
    .map(|x| x * 2)         // Devient 4, 8
    .collect();             // Rassemble dans un nouveau Vec
```

---

## 8. Smart Pointers & Mutabilité Intérieure

Pour contourner les limites strictes du Borrow Checker, Rust propose des types intelligents.

* **`Box<T>`** : Alloue la donnée sur le Tas (Heap). Utile pour les types récursifs ou les Trait Objects (polymorphisme dynamique).
* **`Rc<T>`** (Reference Counted) : Permet à plusieurs variables de posséder la même donnée en lecture seule (mono-thread).
* **`RefCell<T>`** : Permet la **mutabilité intérieure**. Vérifie les règles d'emprunt à *l'exécution* plutôt qu'à la *compilation*.

**Exemple de RefCell :**
```rust
use std::cell::RefCell;

struct Data {
    valeur: RefCell<i32>,
}

let d = Data { valeur: RefCell::new(0) };
// d est immuable, mais on peut modifier son contenu intérieur via borrow_mut()
*d.valeur.borrow_mut() += 1; 
```

---

## 9. Concurrence : Threads, Arc & Mutex

Rust garantit l'absence de *Data Races* (accès concurrents aux données) à la compilation !

* **`Arc<T>`** : Version Thread-Safe de `Rc` (Atomic Reference Counted).
* **`Mutex<T>`** : Assure l'exclusion mutuelle, permettant de modifier une donnée partagée.

**Le combo classique `Arc<Mutex<T>>` :**
```rust
use std::sync::{Arc, Mutex};
use std::thread;

// On crée un compteur thread-safe
let compteur = Arc::new(Mutex::new(0));
let mut handles = vec![];

for _ in 0..5 {
    // On clone le Arc pour partager la propriété entre les threads
    let compteur_clone = Arc::clone(&compteur);
    
    let handle = thread::spawn(move || {
        // On verrouille le Mutex pour accéder à la donnée
        let mut num = compteur_clone.lock().unwrap();
        *num += 1;
    });
    handles.push(handle);
}

// On attend que tous les threads se terminent
for handle in handles {
    handle.join().unwrap();
}

println!("Résultat final: {}", *compteur.lock().unwrap()); // Affiche 5
```

---

## 10. Tests et Macros Utiles

### Les Tests
Rust intègre les tests nativement.
```rust
#[cfg(test)]
mod tests {
    use super::*; // Importe le code du fichier actuel

    #[test]
    fn test_addition() {
        assert_eq!(2 + 2, 4);
    }

    #[test]
    #[should_panic(expected = "Alerte")]
    fn test_qui_doit_crasher() {
        panic!("Alerte générale");
    }
}
```

### Macros de Débogage
* `println!("{:?}", var);` : Affiche une structure (si elle dérive le trait `Debug`).
* `dbg!(var);` : Affiche la variable, le fichier, la ligne, et **retourne** la variable. Très utile au milieu d'une chaîne d'appels !
```rust
#[derive(Debug)]
struct Point { x: i32, y: i32 }

let p = Point { x: 10, y: 20 };
// dbg! prend la propriété de p, l'affiche, puis la rend.
let p2 = dbg!(p); 
```