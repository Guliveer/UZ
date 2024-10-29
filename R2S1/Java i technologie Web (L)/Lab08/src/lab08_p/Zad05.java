// Oliwer Pawelski, 24INF-SP/A

// Stosując wyrażenia lambda należy wyświetlić zawartość listy obiektów dla klasy
// Samochod znajdującej się w liście 5 (./Zad01.java). Stosując kolekcje należy wyodrębnić pole
// „pojemność silnika” i wartości posortować rosnąco.

package lab08_p;

import java.util.ArrayList;
import java.util.List;

public class Zad05 {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>(); // Klasa Car zawarta jest w pliku ./Zad01.java

        // Dodajemy przykładowe obiekty do listy
        Car car1 = new Car();
        car1.setBrand("Toyota");
        car1.setModel("Corolla");
        car1.setEngineCapacity(1.6);
        car1.setProductionYear(2018);
        car1.setFirstRegistrationDate("2019-05-12");

        Car car2 = new Car();
        car2.setBrand("Honda");
        car2.setModel("Civic");
        car2.setEngineCapacity(2.0);
        car2.setProductionYear(2020);
        car2.setFirstRegistrationDate("2020-08-10");

        Car car3 = new Car();
        car3.setBrand("Ford");
        car3.setModel("Fiesta");
        car3.setEngineCapacity(1.2);
        car3.setProductionYear(2017);
        car3.setFirstRegistrationDate("2018-03-15");

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        // Wyświetlamy wszystkie obiekty z listy za pomocą wyrażenia lambda, oddzielając je pustą linią
        System.out.println("Lista samochodów:");
        carList.forEach(Car::printAllData);

        // Wyodrębniamy pojemność silnika, sortujemy i wyświetlamy
        System.out.println("\nPosortowane pojemności silnika (rosnąco):");
        carList.stream()
                .map(Car::getEngineCapacity)            // Wyodrębnianie pojemności silnika
                .sorted()                               // Sortowanie rosnąco
                .forEach(System.out::println);          // Wyświetlanie pojemności
    }
}

