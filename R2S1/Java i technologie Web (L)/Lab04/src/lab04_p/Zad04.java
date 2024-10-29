// Oliwer Pawelski, 24INF-SP/A

// Dla macierzy wynikowej z zadania numer 1 zaimplementuj i wykonaj mechanizmy:
// - sortowania bąbelkowego
// - sortowania szybkiego (quicksort)
// Przedstaw 2 pierwsze przebiegi mechanizmów sortowania.

package lab04_p;

public class Zad04 {
    // Bubble Sort implementation with optional 'cycles' argument
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Zamiana
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Jeśli nie było zamian, tablica jest już posortowana
            if (!swapped) {
                System.out.println("Tablica posortowana: " + java.util.Arrays.toString(arr));
                return;
            }

            // Wyświetlenie postępu (tylko 2 pierwsze przebiegi)
            if (i < 2) {
                System.out.println("Po przebiegu " + (i + 1) + ": " + java.util.Arrays.toString(arr));
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high, int[] stepCount) {
        if (low < high) {
            int pi = partition(arr, low, high, stepCount);
            quickSort(arr, low, pi - 1, stepCount);  // Lewa część
            quickSort(arr, pi + 1, high, stepCount); // Prawa część
        }
    }

    private static int partition(int[] arr, int low, int high, int[] stepCount) {
        int pivot = arr[high]; // Wybieramy ostatni element jako pivot
        int i = (low - 1); // Indeks elementu mniejszego od pivota

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Zamiana
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Zamiana pivota na właściwe miejsce
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Wyświetlamy tablicę po przebiegu (tylko 2 pierwsze)
        stepCount[0]++;
        if (stepCount[0] <= 2) {
            System.out.println("Przebieg " + stepCount[0] + ": " + java.util.Arrays.toString(arr));
        }

        return i + 1;
    }

    private static void printArray(int[] tab) {
        for (int i : tab) {
            System.out.print(i + " ");
        }
        System.out.println(); // Nowy wiersz po każdym wydruku
    }

    public static void main(String[] args) {
        int[] arrBubble = {5, 3, 8, 4, 2};
        int[] arrQuick = arrBubble.clone(); // Klonowanie tablicy dla quicksort

        System.out.println("Tablica oryginalna:");
        printArray(arrBubble);
        System.out.println();

        System.out.println("Sortowanie bąbelkowe:");
        bubbleSort(arrBubble);
        System.out.println("Posortowana tablica: " + java.util.Arrays.toString(arrBubble));

        System.out.println("\nSortowanie szybkie:");
        int[] stepCount = {0}; // Licznik przebiegów
        quickSort(arrQuick, 0, arrQuick.length - 1, stepCount);
        System.out.println("Posortowana tablica: " + java.util.Arrays.toString(arrQuick));
    }
}
