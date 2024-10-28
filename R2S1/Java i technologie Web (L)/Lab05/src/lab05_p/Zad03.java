// Oliwer Pawelski, 24INF-SP/A

// Dodaj klasę AutoKomis, w której stworzone będą nowe obiekty klasy Samochod,
// przechowujące dane o pojazdach (10 sztuk).

package lab05_p;

class CarDealership {
    // Data
    private final Car[] cars;

    // Constructor
    public CarDealership() {
        this.cars = new Car[10];
        for (int i = 0; i < 10; i++) {
            this.cars[i] = new Car();
        }
    }

    // Methods
    public void printAllData() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Car " + (i + 1) + ":");
            this.cars[i].printAllData();
        }
    }
}

public class Zad03 {
    public static void main(String[] args) {
    }
}
