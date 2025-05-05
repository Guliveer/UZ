// Fabryka z powyższego zadania postanowiła zwiększyć swoje moce produkcyjne. W tym celu
// zatrudniła wielu cukierników oraz wielu pakowaczy — nie modyfikując przy tym taśmy produkcyjnej.
// Zmodyfikuj program z zad. 1 aby uwzględniał zwiększone moce produkcyjne (wiele wątków
// Cukiernika i Pakowacza).

package zad02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SweetFactory {
    private static final int BELT_CAPACITY = 5; // Maksymalna pojemność taśmy produkcyjnej
    private static final int COOKIES_TODO = 20; // Liczba ciastek do upieczenia i zapakowania
    private static final int NUM_BAKERS = 3; // Liczba cukierników
    private static final int NUM_PACKERS = 2; // Liczba pakowaczy

    public static void main(String[] args) {
        BlockingQueue<Integer> productionBelt = new ArrayBlockingQueue<>(BELT_CAPACITY);
        AtomicInteger cookieCounter = new AtomicInteger(1); // Wspólny licznik ciastek

        // Tworzenie i uruchamianie wątków cukierników
        for (int i = 1; i <= NUM_BAKERS; i++) {
            new Thread(new Baker(productionBelt, cookieCounter, COOKIES_TODO), "Cukiernik-" + i).start();
        }

        // Tworzenie i uruchamianie wątków pakowaczy
        for (int i = 1; i <= NUM_PACKERS; i++) {
            new Thread(new Packer(productionBelt, COOKIES_TODO), "Pakowacz-" + i).start();
        }
    }
}
