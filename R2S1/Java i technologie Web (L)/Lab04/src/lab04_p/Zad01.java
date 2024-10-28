// Oliwer Pawelski, 24INF-SP/A

// Napisz program, w którym zostanie utworzona 30-elementowa tablica liczb typu int.
// Za pomocą pętli zapisz w kolejnych komórkach liczby losowo wygenerowane z
// przedziału od 99-150. Wykonać kopię do nowej tablicy.

package lab04_p;

import java.util.Scanner;

public class Zad01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[30];
        int[] tabCopy = new int[30];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (int) (Math.random() * 51) + 99;
            tabCopy[i] = tab[i];
        }
        System.out.println("Tablica oryginalna:");
        for (int j : tab) {
            System.out.print(j + " ");
        }
        System.out.println("\nTablica kopia:");
        for (int j : tabCopy) {
            System.out.print(j + " ");
        }
        scanner.close();
    }
}
