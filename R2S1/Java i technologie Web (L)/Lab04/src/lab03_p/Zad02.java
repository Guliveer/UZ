// Stworzyć tablicę 7 x 7 liczb całkowitych, wypełnić ją losowymi wartościami, następnie
// wyznaczyć dla każdej kolumny minimum i maksimum, najmniejszy oraz największy
// element w tablicy, średnią arytmetyczną elementów tablicy, ilość elementów
// mniejszych oraz większych od średniej. Do nowej tablicy skopiować w odwrotnej
// kolejności zawartość tablicy tj. od ostatniego do pierwszego.

package lab03_p;

import java.util.Scanner;

public class Zad02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] tab = new int[7][7];
        int[][] tabCopy = new int[7][7];
        int min, max, sum = 0, count = 0;
        double avg;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = (int) (Math.random() * 101);
                sum += tab[i][j];
                count++;
            }
        }

        min = max = tab[0][0];
        for (int[] ints : tab) {
            for (int anInt : ints) {
                if (anInt < min) {
                    min = anInt;
                }
                if (anInt > max) {
                    max = anInt;
                }
            }
        }

        avg = (double) sum / count;
        System.out.println("Tablica:");
        for (int[] i : tab) {
            System.out.print("\t");
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println("Min:\t" + min);
        System.out.println("Max:\t" + max);
        System.out.println("Avg:\t" + avg);

        for (int[] ints : tab) {
            for (int anInt : ints) {
                if (anInt < avg) {
                    count++;
                }
            }
        }

        System.out.println("X < Avg:\t" + count);
        count = 0;
        for (int[] ints : tab) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] > avg) {
                    count++;
                }
            }
        }

        System.out.println("X > Avg:\t" + count);

        for (int i = tab.length - 1; i >= 0; i--) {
            for (int j = tab[i].length - 1; j >= 0; j--) {
                tabCopy[tab.length - 1 - i][tab[i].length - 1 - j] = tab[i][j];
            }
        }
        System.out.println("Tablica kopia (odwrotna):");
        for (int[] i : tabCopy) {
            System.out.print("\t");
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}
