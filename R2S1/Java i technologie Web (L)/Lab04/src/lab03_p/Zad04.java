// Dla macierzy wynikowej z zadania numer 1 zaimplementuj i wykonaj mechanizmy:
// - sortowania bąbelkowego
// - sortowania szybkiego (quicksort)
// Przedstaw 2 pierwsze przebiegi mechanizmów sortowania.

package lab03_p;

import java.util.Scanner;

public class Zad04 {
    // Bubble Sort implementation with optional 'cycles' argument
    public static int[] bubbleSort(int[] array, int... cycles) {
        int n = array.length;
        int[] sortedArray = array.clone(); // Clone the original array to avoid modifying it
        int maxCycles = cycles.length > 0 ? cycles[0] : n - 1;

        for (int i = 0; i < maxCycles; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // If no elements were swapped, the array is already sorted
            }
        }
        return sortedArray;
    }

    // Quick Sort implementation with optional 'cycles' argument
    public static int[] quickSort(int[] array, int... cycles) {
        int[] sortedArray = array.clone(); // Clone the original array to avoid modifying it
        int maxCycles = cycles.length > 0 ? cycles[0] : Integer.MAX_VALUE; // Unlimited cycles if not provided
        quickSortHelper(sortedArray, 0, sortedArray.length - 1, new int[]{0}, maxCycles);
        return sortedArray;
    }

    // Helper function for Quick Sort
    private static void quickSortHelper(int[] array, int low, int high, int[] currentCycle, int maxCycles) {
        if (low < high && currentCycle[0] < maxCycles) {
            int pi = partition(array, low, high);
            currentCycle[0]++;

            quickSortHelper(array, low, pi - 1, currentCycle, maxCycles);
            quickSortHelper(array, pi + 1, high, currentCycle, maxCycles);
        }
    }

    // Partition function for Quick Sort
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    private static void printArray(int[] tab) {
        for (int i : tab) {
            System.out.print(i + " ");
        }
        System.out.println(); // Nowy wiersz po każdym wydruku
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[30];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (int) (Math.random() * 51) + 99; // Tablica bazowa
        }

        System.out.println("Tablica oryginalna:");
        printArray(tab);

        // Tworzenie osobnych kopii do sortowania
        System.out.println("\n\nSortowanie bąbelkowe (2 cykle):");
        int[] tabBubbleShort = bubbleSort(tab, 2);
        printArray(tabBubbleShort);

        System.out.println("\nSortowanie bąbelkowe (pełne):");
        int[] tabBubble = bubbleSort(tab);
        printArray(tabBubble);

        System.out.println("\n\nSortowanie szybkie (2 cykle):");
        int[] tabQuickShort = quickSort(tab, 2);
        printArray(tabQuickShort);

        System.out.println("\nSortowanie szybkie (pełne):");
        int[] tabQuick = quickSort(tab);
        printArray(tabQuick);

        scanner.close();
    }
}
