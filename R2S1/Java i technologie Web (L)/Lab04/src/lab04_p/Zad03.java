// Oliwer Pawelski, 24INF-SP/A

// Dla tablicy 40 elementowej komórkom o indeksach:
// 0-10 przypisz wartość od 1-5 losowo,
// 11-20 przypisz wartość od 6-10 losowo,
// 21-30 przypisz wartość od 11-55 losowo,
// 31-39 przypisz wartość od 60-75 losowo

package lab04_p;

import java.util.Scanner;

public class Zad03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[40];
        for (int i = 0; i < tab.length; i++) {
            if (i <= 10) {
                tab[i] = (int) (Math.random() * 5) + 1;
            } else if (i <= 20) {
                tab[i] = (int) (Math.random() * 5) + 6;
            } else if (i <= 30) {
                tab[i] = (int) (Math.random() * 45) + 11;
            } else {
                tab[i] = (int) (Math.random() * 15) + 60;
            }
        }
        System.out.println("Tablica:");
        for (int i : tab) {
            System.out.print(i + " ");
        }
        scanner.close();
    }
}
