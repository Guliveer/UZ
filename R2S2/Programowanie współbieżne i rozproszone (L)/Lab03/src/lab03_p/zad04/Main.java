// Napisać program składający się z pięciu wątków. W każdym z wątków w pętli 10x wyświetlać nazwę
// wątku i dowolny tekst, a następnie używając metody sleep wstrzymywać pracę wątków na losowo
// wybrany czas. Następnie:
// a) uruchomić wielokrotnie wątki i zaobserwować ich działanie,
// b) wymusić uruchomienie wątków w kolejności: 5, 4, 3, 2, 1 (wątek 4 startuje i wyświetla tekst
//    dopiero po całkowitym zakończeniu pracy wątku 5 — itd), wykorzystaj metodę join().

package lab03_p.zad04;

public class Main {
    public static void main(String[] args) {
        CustomThread[] threads = {
                new CustomThread("Thread-1", "M1"),
                new CustomThread("Thread-2", "M2"),
                new CustomThread("Thread-3", "M3"),
                new CustomThread("Thread-4", "M4"),
                new CustomThread("Thread-5", "M5")
        };

        for (int i = threads.length - 1; i >= 0; i--) {
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads have finished execution.");
    }
}