// Oliwer Pawelski, 24INF-SP/A
//
// Napisz aplikację, która utworzy 15 wątków wyświetlających informacje: id wątku,
// tekst „wątek:...1-15” oraz dzisiejszą datę. Po wykonaniu zadania działanie każdego
// z wątków zostaje uśpione na czas 300 milisekund.

package lab10_p;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Zad01 {

    public static void main(String[] args) {
        // Tworzenie 15 wątków
        for (int i = 1; i <= 15; i++) {
            final int threadId = i;
            Thread thread = new Thread(() -> {
                // Wyświetlanie informacji o wątku
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                System.out.println("wątek: " + threadId + "\tdata: " + currentDate);

                // Uśpienie wątku na 300 ms
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.err.println("Wątek " + threadId + " został przerwany.");
                }
            });

            // Uruchamianie wątku
            thread.start();
        }
    }
}
