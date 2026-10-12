Markdown

````
# Résumé des concepts clés du cours

---

## 1. Bases & Syntaxe Rust

### Modèle mémoire : Stack vs Heap
- **Stack (Pile) :**
  - Accès très rapide, taille fixe connue à la compilation.
  - Libération automatique lorsque la variable sort du scope.
- **Heap (Tas) :**
  - Données à taille dynamique ou durée de vie flexible.
  - Allocation et désallocation explicites (gérées par le système d'ownership de Rust sans Garbage Collector).

### Variables, Mutabilité & Shadowing
- **Immutabilité par défaut :** En Rust, `let x = 5;` rend `x` immuable.
- **Mutabilité :** On utilise `let mut x = 5;` pour autoriser les modifications.
- **Shadowing (Masquage) :** On peut redéclarer une variable avec le même nom (`let x = x + 1;` ou `let x = x.to_string();`), ce qui permet d'en changer éventuellement le type tout en réutilisant l'identifiant.

### Constantes vs Variables
- Déclarées avec le mot-clé `const` (`const MAX_POINTS: u32 = 100;`).
- Le type **doit** être explicitement annoté.
- Évaluées à la compilation et inlinées à chaque utilisation.

---

## 2. Types Primitifs & Composés

### Types scalaires
- **Entiers :** Signés (`i8`, `i16`, `i32`, `i64`, `i128`, `isize`) et non signés (`u8`, `u16`, `u32`, `u64`, `u128`, `usize`).
- **Flottants :** `f32` et `f64`.
- **Booléens :** `bool` (`true`, `false`).
- **Caractères :** `char` (scalaires Unicode sur 4 octets, ex: `'a'`, `'é'`, `'🦀'`).

### Formattage de chaînes
- `println!`, `print!`, `format!`
- **Placeholders :**
  - Affichage standard : `{}` (requiert le trait `Display`)
  - Formattage de débogage : `{:?}` (requiert le trait `Debug`)
  - Spécificateurs de précision et alignement : `{:>8}`, `{:08.2}`, `{:#x}` (hexadécimal).

### Tuples
- Groupes fixes de valeurs de types potentiellement différents : `let pt: (i32, f64) = (10, 3.14);`
- Accès par indice : `pt.0`, `pt.1` ou par déstructuration : `let (x, y) = pt;`.

### Collections de la bibliothèque standard (`std::collections`)
- **Tableaux fixes (`[T; N]`) :** Taille fixe allouée sur la stack (ex: `[i32; 5]`).
- **Vecteurs (`Vec<T>`) :** Liste dynamique allouée sur le heap (ex: `vec![1, 2, 3]`).
- **Maps :**
  - `HashMap<K, V>` : Clé-valeur à accès rapide ($O(1)$ moyen), ordre non garanti.
  - `BTreeMap<K, V>` : Clé-valeur ordonnée par les clés.
- **Sets :** `HashSet<T>` et `BTreeSet<T>` pour garantir l'unicité des éléments.
- **Files d'attente :** `VecDeque<T>` pour les structures FIFO/LIFO efficaces aux deux extrémités.

---

## 3. Structure de Contrôle & Fonctions

### Structure d'une fonction
```rust
fn nom_fonction(param1: Type1, param2: Type2) -> TypeRetour {
    // La dernière expression sans point-virgule est la valeur de retour
    param1 + param2
}
````

### Structures de contrôle

- **`if / else` :** `if` est une expression et peut retourner une valeur (`let x = if cond { 1 } else { 2 };`).
    
      
    
- **`match` :** Pattern matching exhaustif.
    
      
    
- **`if let` & `let else` :**
    
      
    - `if let Some(v) = option { ... }` : Traitement concis d'un seul cas.
        
          
        
    - `let Ok(val) = resultat else { return; };` : Destructuration précoce ou sortie de bloc.
        
          
        
- **Boucles :**
    
      
    - `loop` (peut retourner une valeur via `break val;`)
        
          
        
    - `while` et `while let`
        
          
        
    - `for item in collection` (avec adaptateurs comme `.enumerate()`, `.zip()`, `.rev()`, `.step_by()`).
        
          
        

## 4. Ownership, Borrowing & Lifetimes

### Les 3 règles d'Ownership (Propriété)

1. Chaque valeur dans Rust a un propriétaire (_owner_).
    
      
    
2. Il ne peut y avoir qu'un seul propriétaire à la fois.
    
      
    
3. Quand le propriétaire sort du scope (`drop`), la mémoire de la valeur est libérée.
    
      
    

### Move vs Copy

- **Move :** Transfert de propriété (par défaut pour les types alloués dynamiquement comme `String` ou `Vec`). L'ancienne variable devient inutilisable.
    
      
    
- **Copy :** Duplication bit à bit implicite pour les types scalaires de taille fixe (`i32`, `f64`, `bool`, `char`, tuples de types `Copy`).
    
      
    

### Règles du Borrowing (Emprunt)

- On peut avoir **soit** un nombre illimité de références immuables (`&T`), **soit** une seule référence mutable (`&mut T`) à un instant donné.
    
      
    
- Les références doivent toujours être valides (absence de pointeurs pendants / _dangling pointers_).
    
      
    

### Lifetimes (`'a`)

- Les lifetimes sont des annotations (`'a`) aidant le compilateur à vérifier qu'une référence ne vit pas plus longtemps que la donnée qu'elle pointe.
    
      
    
- Souvent inférées par le compilateur (_lifetime elision_), mais requises explicitement dans les signatures de fonctions retournant des références issues de plusieurs entrées.
    
      
    

## 5. Types Définis par l'Utilisateur & Programmation Orientée Objet (POO)

### POO en Rust vs Langages Classiques (C++/Java)

Rust n'a pas de classes ni d'héritage de structures. Il sépare les données (`struct`, `enum`) des comportements (`impl`, `trait`).

  

|**Concept POO Classique**|**Équivalent Rust**|
|---|---|
|Classe|`struct` / `enum` + bloc `impl`|
|Objet / Instance|Instance d'une `struct` ou `enum`|
|Encapsulation|Modules (`mod`) et visibilité `pub`|
|Interface / Abstract Class|`trait`|
|Héritage|Remplacé par la composition et les `traits`|

### Structs & Enums

- **`struct` :**
    
      
    
    Rust
    
    ```
    struct Sensor {
        id: u32,
        value: f64,
    }
    ```
    
- **`enum` avec données :**
    
      
    
    Rust
    
    ```
    enum Status {
        Active(u32),
        Inactive,
        Error(String),
    }
    ```
    

### Méthodes & Récepteur `self`

Dans un bloc `impl StructName` :

  

- `&self` : Emprunt immuable (lecture seule).
    
      
    
- `&mut self` : Emprunt mutable (modification).
    
      
    
- `self` : Prise de possession (consommation de l'objet).
    
      
    
- Sans récepteur `self` : Fonction associée (ex: constructeur `new()`).
    
      
    

### Gestion d'erreurs & Valeurs Optionnelles

- **`Option<T>` :** `Some(T)` ou `None` (remplace l'absence de `null`).
    
      
    
- **`Result<T, E>` :** `Ok(T)` ou `Err(E)` pour les opérations pouvant échouer.
    
      
    
- **Extraction :**
    
      
    - `unwrap()` / `expect("msg")` : Extrait la valeur ou fait paniquer le programme.
        
          
        
    - Opérateur `?` : Propage l'erreur/absence à la fonction appelante.
        
          
        
    - `unwrap_or(default)` / `unwrap_or_else(closure)` : Gestion sécurisée avec valeur de repli.
        
          
        

## 6. Abstraction & Polymorphisme (Traits)

### Traits (Contrats d'interface)

Un `trait` définit un ensemble de méthodes qu'un type doit implémenter.

  

Rust

```
trait Drawable {
    fn draw(&self) -> String; // Méthode abstraite
    fn show(&self) {          // Méthode par défaut
        println!("{}", self.draw());
    }
}
```

### Dispatch Statique vs Dynamique

|**Critère**|**Dispatch Statique (Generics / impl Trait)**|**Dispatch Dynamique (Trait Objects)**|
|---|---|---|
|**Syntaxe**|`fn show<T: Drawable>(item: &T)` ou `fn show(item: &impl Drawable)`|`fn show(item: &dyn Drawable)` ou `Box<dyn Drawable>`|
|**Résolution**|À la compilation (_Monomorphization_)|À l'exécution via une _vtable_|
|**Performance**|Maximale (inlinable, zéro surcoût)|Légère indirection (pointeur vtable)|
|**Collections**|Homogènes (un seul type concret par collection)|Hétérogènes (`Vec<Box<dyn Drawable>>`)|

## 7. Traits de la Bibliothèque Standard & Design Patterns

### Traits dérivables usuels (`#[derive(...)]`)

- `Debug` / `Display` : Formattage textuel.
    
      
    
- `Clone` / `Copy` : Duplication des valeurs.
    
      
    
- `Default` : Fournit une valeur par défaut (`Type::default()`).
    
      
    
- `PartialEq`, `Eq`, `PartialOrd`, `Ord`, `Hash` : Égalité, comparaison, tri et hachage.
    
      
    

### Design Patterns Idiomatiques en Rust

1. **Newtype Pattern :** Envelopper un type existant (`struct Meters(f64);`) pour garantir la sécurité au niveau des types sans surcoût à l'exécution.
    
      
    
2. **RAII / Drop :** Libération automatique des ressources (fichiers, sockets) à la sortie du scope via le trait `Drop`.
    
      
    
3. **Builder Pattern :** Construction étape par étape d'objets complexes.
    
      
    
4. **Strategy Pattern :** Interchangeabilité des algorithmes via les traits.
    
      
    

## 8. Concurrence & Smart Pointers

### Processus vs Threads

- **Processus :** Instance isolée d'un programme avec son propre espace mémoire.
    
      
    
- **Thread :** Fil d'exécution au sein d'un processus, partageant la mémoire du processus.
    
      
    

### Les garanties de Rust (_Fearless Concurrency_)

Rust empêche les _Data Races_ (accès concurrents non synchronisés dont au moins un est une écriture) à la compilation grâce aux règles d'ownership et aux traits marqueurs :

  

- **`Send` :** Indique qu'un type peut être transféré d'un thread à un autre.
    
      
    
- **`Sync` :** Indique qu'un type peut être partagé entre plusieurs threads via des références (`&T`).
    
      
    

### Panorama des Smart Pointers

|**Smart Pointer**|**Cas d'usage principal**|**Thread-Safe ?**|
|---|---|---|
|**`Box<T>`**|Allocation sur le heap, propriété unique|Oui (si `T: Send`)|
|**`Rc<T>`**|Propriété partagée (comptage de références)|**Non** (mono-thread)|
|**`Arc<T>`**|Propriété partagée atomique|**Oui** (multi-thread)|
|**`Cell<T>`**|Mutabilité intérieure pour types `Copy`|**Non**|
|**`RefCell<T>`**|Mutabilité intérieure avec vérification des emprunts à l'exécution|**Non**|
|**`Mutex<T>` / `RwLock<T>`**|Mutabilité intérieure synchronisée pour accès exclusif / lecteur-rédacteur|**Oui**|

### Communication entre Threads

- **Message Passing :** Préférer la transmission de messages via des canaux (`mpsc::channel`) plutôt que le partage de mémoire explicite (« _Do not communicate by sharing memory; instead, share memory by communicating_ »).
    
      
    

## 9. Débogage, Tests & Qualité de Code

### Structuration des tests

- **Tests unitaires :** Placés dans le même fichier que le code source sous le module `#[cfg(test)] mod tests`.
    
      
    
- **Tests d'intégration :** Fichiers situés dans le dossier `tests/` testant l'API publique de la crate.
    
      
    
- **Doc-tests :** Exemples de code dans les commentaires de documentation (`///`) exécutés par `cargo test`.
    
      
    

### Outils de l'écosystème Cargo

- `cargo check` : Vérification rapide des types sans générer le binaire.
    
      
    
- `cargo fmt` : Formattage automatique du code selon les conventions Rust.
    
      
    
- `cargo clippy` : Linter avancé identifiant les constructions non-idiomatiques ou à risque.
    
      
    
- `cargo doc --open` : Génération automatique de la documentation HTML interactive.