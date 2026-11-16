// Page 12, 13 : Une classe publique par fichier
// Page 10 : La classe "Père" (Parent class)
class Vehicle {
    
    // Page 11, 13 : Visibilité des attributs
    protected String maker;  // Accessible par les classes filles
    private String model;    // Uniquement accessible dans Vehicle (Encapsulation)
    public int year;         // Accessible partout

    // Page 5, 12 : Constructeur de la classe mère
    public Vehicle(String maker, String model, int year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }

    public void printInfo() {
        System.out.println("Marque: " + maker + ", Modèle: " + model);
    }
}

// Page 4, 5, 10 : L'héritage avec le mot-clé 'extends'
// Car est une "classe fille" (Child class) de Vehicle
class Car extends Vehicle {
    
    private int numberOfDoors;

    public Car(String maker, String model, int year, int doors) {
        // Page 5, 6, 9 : Appel au constructeur de la classe mère avec 'super'
        super(maker, model, year); 
        this.numberOfDoors = doors;
    }

    // Page 9 : Utilisation de 'super' pour appeler une méthode parente
    public void displayCarDetails() {
        super.printInfo(); 
        System.out.println("Portes : " + numberOfDoors);
    }
}

// Classe de test
public class Main {
    public static void main(String[] args) {
        // Création d'un objet de la classe fille
        Car myCar = new Car("Toyota", "Yaris", 2022, 5);

        myCar.displayCarDetails();
    }
}
