// 3)
// Zmodyfikuj zadanie 2b w taki sposób, aby każdy z wątków (Dane1, Dane2, Obliczenia) wykonywał
// w pętli 3x swój kod, tzn. Dane1 w pętli 3x odczytuje liczbę a, Dane2 w pętli 3x odczytuje liczbę b,
// Obliczenia w pętli 3x pobiera odpowiednie wartości a i b oraz dokonuje sumowania tych wartości.
// Synchronizuj pracę wątków z wykorzystaniem semaforów (nie używać synchronized oraz metody
// join). Sprawdź poprawność wyników obliczeń. Przykładowe działanie programu:
//
// Dane1, podaj a: 2
// Dane2, podaj b: 3
// obliczenia, suma = 5
//
// Dane1, podaj a: 4
// Dane2, podaj b: 5
// obliczenia, suma = 9
//
// Dane1, podaj a: 6
// Dane2, podaj b: 8
// obliczenia, suma = 14


package zad03;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static final Semaphore semaphoreDane1 = new Semaphore(1); // Controls Dane1
    private static final Semaphore semaphoreDane2 = new Semaphore(0); // Controls Dane2
    private static final Semaphore semaphoreObliczenia = new Semaphore(0); // Controls Obliczenia
    private static int a;
    private static int b;

    public static void main(String[] args) {
        Thread dane1 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 3; i++) {
                try {
                    semaphoreDane1.acquire(); // Wait for permission to execute
                    System.out.print("Dane1, podaj a: ");
                    a = scanner.nextInt();
                    semaphoreDane2.release(); // Allow Dane2 to execute
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread dane2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 3; i++) {
                try {
                    semaphoreDane2.acquire(); // Wait for permission to execute
                    System.out.print("Dane2, podaj b: ");
                    b = scanner.nextInt();
                    semaphoreObliczenia.release(); // Allow Obliczenia to execute
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread obliczenia = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    semaphoreObliczenia.acquire(); // Wait for permission to execute
                    System.out.println("Obliczenia, suma = " + (a + b) + "\n");
                    semaphoreDane1.release(); // Allow Dane1 to execute again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        dane1.start();
        dane2.start();
        obliczenia.start();
    }
}
