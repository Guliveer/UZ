// 1b)
// Następnie zsynchronizuj pracę wątków z wykorzystaniem semaforów
// (nie używać synchronized oraz metody join)
// Sprawdź ponownie wyniki obliczeń.

package zad01.b;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static final Semaphore semaphore = new Semaphore(0);
    private static int a;
    private static int b;

    public static void main(String[] args) {
        Thread dane = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj liczbę a: ");
            a = scanner.nextInt();
            System.out.print("Podaj liczbę b: ");
            b = scanner.nextInt();
            System.out.println("Wątek Dane zakończył pracę.");
            semaphore.release(); // Zwolnienie semafora
        });

        Thread obliczenia = new Thread(() -> {
            try {
                semaphore.acquire(); // Oczekiwanie na semafor
                System.out.println("Suma: " + (a + b));
                System.out.println("Wątek Obliczenia zakończył pracę.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        dane.start();
        obliczenia.start();
    }
}
