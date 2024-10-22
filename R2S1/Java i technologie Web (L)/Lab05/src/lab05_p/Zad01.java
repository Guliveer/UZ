// Stwórz klasę o nazwie Samochod reprezentującą dane pojazdu zawierającą
// następujące pola:
// - marka,
// - model,
// - pojemność silnika,
// - typ nadwozia,
// - typ silnika,
// - rok produkcji,
// - data 1 rejestracji,
// - cena.
// Klasa powinna zawierać osobne metody służące do ustawiania i zwracania wartości
// poszczególnych pól obiektów.

package lab05_p;

import java.util.Scanner;

public class Zad01 {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();

        // Set car2 values
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car2 data:");
        System.out.print("Brand: ");
        car2.setBrand(scanner.nextLine());
        System.out.print("Model: ");
        car2.setModel(scanner.nextLine());
        System.out.print("Engine capacity: ");
        car2.setEngineCapacity(Double.parseDouble(scanner.nextLine()));
        System.out.print("Body type: ");
        car2.setBodyType(scanner.nextLine());
        System.out.print("Engine type: ");
        car2.setEngineType(scanner.nextLine());
        System.out.print("Production year: ");
        car2.setProductionYear(Integer.parseInt(scanner.nextLine()));
        System.out.print("First registration date: ");
        car2.setFirstRegistrationDate(scanner.nextLine());
        System.out.print("Price: ");
        car2.setPrice(Double.parseDouble(scanner.nextLine()));


        // Output values
        System.out.println("Car 1:");
        car1.printAllData();

        System.out.println("\nCar 2:");
        car2.printAllData();
    }
}
