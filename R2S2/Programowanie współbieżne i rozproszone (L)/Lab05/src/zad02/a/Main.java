// 2a)
// Napisz program składający się z trzech wątków: Dane1, Dane2 i Obliczenia.
// Wątek Dane1 pobiera z klawiatury liczbę a, po czym kończy swoją pracę.
// Wątek Dane2 pobiera z klawiatury liczbę b, po czym kończy swoją pracę.
// Wątek Obliczenia pobiera odczytane z klawiatury wartości (a i b)
// i wyświetla sumę tych wartości, po czym kończy swoją pracę.
// Uruchom wątki bez używania synchronizacji, zaobserwuj jego działanie.

package zad02.a;

import java.util.Scanner;

public class Main {
    private static int a;
    private static int b;

    public static void main(String[] args) {
        Thread dane1 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj liczbę a: ");
            a = scanner.nextInt();
            System.out.println("Wątek Dane1 zakończył pracę.");
        });

        Thread dane2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj liczbę b: ");
            b = scanner.nextInt();
            System.out.println("Wątek Dane2 zakończył pracę.");
        });

        Thread obliczenia = new Thread(() -> {
            System.out.println("Suma: " + (a + b));
            System.out.println("Wątek Obliczenia zakończył pracę.");
        });

        dane1.start();
        dane2.start();
        obliczenia.start();
    }
}