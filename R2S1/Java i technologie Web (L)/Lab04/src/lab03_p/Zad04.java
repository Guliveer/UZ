// Dla macierzy wynikowej z zadania numer 1 zaimplementuj i wykonaj mechanizmy:
// - sortowania bąbelkowego
// - sortowania szybkiego (quicksort)
// Przedstaw 2 pierwsze przebiegi mechanizmów sortowania.

package lab03_p;

import java.util.Scanner;

public class Zad04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[30];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (int) (Math.random() * 51) + 99;
        }

        System.out.println("Tablica oryginalna:");
        for (int j : tab) {
            System.out.print(j + " ");
        }

        System.out.println("\n\nSortowanie bąbelkowe:");
        bubbleSortPreview(tab);
        bubbleSort(tab);
        for (int j : tab) {
            System.out.print(j + " ");
        }

        System.out.println("\n\nSortowanie szybkie:");
        quickSortPreview(tab, 0, tab.length - 1);
        quickSort(tab, 0, tab.length - 1);
        for (int j : tab) {
            System.out.print(j + " ");
        }

        scanner.close();
    }

    public static void bubbleSort(int[] tab) {
        int n = tab.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    int temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                }
            }
        }
    }

    // Sortowanie bąbelkowe - wypisywanie tylko dwóch pierwszych przebiegów
    public static void bubbleSortPreview(int[] tab) {
        int n = tab.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    int temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.print("Przebieg " + (i + 1) + ": ");
            for (int k : tab) {
                System.out.print(k + " ");
            }
            System.out.println();
            if (i == 1) break;  // Wypisujemy tylko dwa pierwsze przebiegi
            if (!swapped) break;  // Jeśli brak zamian, sortowanie zakończone
        }
    }

    public static void quickSort(int[] tab, int low, int high) {
        if (low < high) {
            int pi = partition(tab, low, high);
            quickSort(tab, low, pi - 1);
            quickSort(tab, pi + 1, high);
        }
    }

    // Sortowanie szybkie - wypisywanie tylko dwóch pierwszych przebiegów
    public static void quickSortPreview(int[] tab, int low, int high) {
        quickSortPreviewHelper(tab, low, high, 0);
    }

    public static void quickSortPreviewHelper(int[] tab, int low, int high, int depth) {
        if (low < high) {
            // Tworzymy kopię tablicy do wypisania stanu
            int[] currentTab = tab.clone();
            int pi = partition(currentTab, low, high);
            depth++;
            System.out.print("Przebieg " + depth + ": ");
            for (int k : currentTab) {
                System.out.print(k + " ");
            }
            System.out.println();
            if (depth == 2) return;  // Wypisujemy tylko dwa pierwsze przebiegi

            quickSortPreviewHelper(tab, low, pi - 1, depth);
            quickSortPreviewHelper(tab, pi + 1, high, depth);
        }
    }

    // Funkcja partycjonowania dla sortowania szybkiego
    public static int partition(int[] tab, int low, int high) {
        int pivot = tab[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (tab[j] < pivot) {
                i++;
                int temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
            }
        }
        int temp = tab[i + 1];
        tab[i + 1] = tab[high];
        tab[high] = temp;
        return i + 1;
    }
}
