// Napisz metodę, która sprawdzi, czy samochód posiada jeszcze gwarancję (wiek
// pojazdu do 2 lat licząc od daty rejestracji) i zwróci informację w formie komunikatu
// tekstowego.

package lab05_p;

public class Zad02 {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();

        // Set car2 values
        car2.setBrand("Audi");
        car2.setModel("A4");
        car2.setEngineCapacity(2.0);
        car2.setBodyType("Sedan");
        car2.setEngineType("Diesel");
        car2.setProductionYear(2019);
        car2.setFirstRegistrationDate("2020-01-01");
        car2.setPrice(100000.0);

        // Output values
        System.out.println("Car 1:");
        car1.printAllData();

        System.out.println("\nCar 2:");
        car2.printAllData();

        String warranty = car2.getWarranty() ? "Valid" : "Expired";
        System.out.println("\nCar 2 warranty: " + warranty);
    }
}
