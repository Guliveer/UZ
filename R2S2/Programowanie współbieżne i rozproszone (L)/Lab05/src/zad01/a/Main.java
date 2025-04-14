// 1a)
// Napisz program składający się z dwóch wątków: Dane i Obliczenia.
// Wątek Dane pobiera z klawiatury dwie liczby a i b po czym kończy swoją pracę.
// Wątek Obliczenia pobiera odczytane z klawiatury wartości i wyświetla sumę
// tych wartości, po czym kończy swoją pracę. Uruchom wątki bez używania
// synchronizacji, zaobserwuj jego działanie.

package zad01.a;

import java.util.Scanner;

public class Main {
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
        });

        Thread obliczenia = new Thread(() -> {
            System.out.println("Suma: " + (a + b));
            System.out.println("Wątek Obliczenia zakończył pracę.");
        });

        dane.start();
        obliczenia.start();
    }
}