// 2b)
// Następnie zsynchronizuj pracę wątków z wykorzystaniem semaforów
// (nie używać synchronized oraz metody join)
// Sprawdź ponownie wyniki obliczeń.

package zad02.b;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static final Semaphore semaphoreDane = new Semaphore(1); // Controls Dane1 and Dane2
    private static final Semaphore semaphoreObliczenia = new Semaphore(0); // Controls Obliczenia
    private static int a;
    private static int b;

    public static void main(String[] args) {
        Thread dane1 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            try {
                semaphoreDane.acquire(); // Wait for permission to execute
                System.out.print("Podaj liczbę a: ");
                a = scanner.nextInt();
                System.out.println("Wątek Dane1 zakończył pracę.");
                semaphoreDane.release(); // Allow Dane2 to execute
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread dane2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            try {
                semaphoreDane.acquire(); // Wait for Dane1 to finish
                System.out.print("Podaj liczbę b: ");
                b = scanner.nextInt();
                System.out.println("Wątek Dane2 zakończył pracę.");
                semaphoreObliczenia.release(); // Allow Obliczenia to execute
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread obliczenia = new Thread(() -> {
            try {
                semaphoreObliczenia.acquire(); // Wait for Dane2 to finish
                System.out.println("Suma: " + (a + b));
                System.out.println("Wątek Obliczenia zakończył pracę.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        dane1.start();
        dane2.start();
        obliczenia.start();
    }
}