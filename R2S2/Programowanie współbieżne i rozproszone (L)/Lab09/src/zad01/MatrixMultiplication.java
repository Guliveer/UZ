// 1. Napisz program obliczający iloczyn dwóch macierzy kwadratowych A i B (mnożenie macierzy).
// Rozmiar macierzy zdefiniuj jako stałą SIZE równą 1000 (rozmiar macierzy 1000 x 1000). Dla
// uproszczenia rozważamy tylko macierz kwadratową. Zadanie rozwiąż w dwóch wariantach
// (w jednym projekcie w IntelliJ):
//
// - dla jednego wątku,
// - dla czterech wątków.
//
// Wyniki obliczeń powinny zostać umieszczone w macierzy C (mnożenie jednowatkowe) i macierzy D
// (mnożenie dla czterech wątków). Napisz metodę sprawdzającą czy wyniki obliczeń dla dwóch
// wariantów są ze sobą zgodne, wyświetl wynik porównania. Dokonaj pomiaru czasu dla każdego
// wariantu obliczeń używając metody System.nanoTime(). Czas obliczeń wyświetlaj w sekundach
// z dokładnością do dwóch miejsc po przecinku. Oblicz i wyświetl informację, ile razy szybciej wykonuje
// się mnożenie w wariancie wielowątkowym.
//
// Macierze nazwij dokładnie jak w instrukcji:
// - A,B -> macierze z danymi do mnożenia,
// - C -> macierz wynikowa dla mnożenia jednowątkowego,
// - D -> macierz wynikowa dla mnożenia wielowątkowego.
//
// Zawartość macierzy A oraz B wypełnij przy użyciu metod udostępnionych przez prowadzącego [fill-matrix.java] (nie należy wypełniać macierzy losowo).
//
// Program powinien poprawnie działać przy zmianie rozmiaru macierzy SIZE na inny (np. 512, 899).

package zad01;

import utils.FillMatrix;

import java.util.Arrays;

public class MatrixMultiplication {
    private static final int SIZE = 1000;

    public static void main(String[] args) {
        int[][] A = new int[SIZE][SIZE];
        int[][] B = new int[SIZE][SIZE];
        int[][] C = new int[SIZE][SIZE];
        int[][] D = new int[SIZE][SIZE];

        FillMatrix.fillMatrixA(A);
        FillMatrix.fillMatrixB(B);

        // Jednowątkowe mnożenie macierzy
        long startSingleThread = System.nanoTime();
        multiplySingleThread(A, B, C);
        long endSingleThread = System.nanoTime();
        double timeSingleThread = (endSingleThread - startSingleThread) / 1e9;

        // Wielowątkowe mnożenie macierzy
        long startMultiThread = System.nanoTime();
        multiplyMultiThread(A, B, D);
        long endMultiThread = System.nanoTime();
        double timeMultiThread = (endMultiThread - startMultiThread) / 1e9;

        // Porównanie wyników
        boolean areEqual = Arrays.deepEquals(C, D);
        System.out.println("Czy wyniki są zgodne?\t" + areEqual);

        // Wyświetlenie czasu i przyspieszenia
        System.out.printf("Czas jednowątkowy: %.2f s%n", timeSingleThread);
        System.out.printf("Czas wielowątkowy: %.2f s%n", timeMultiThread);
        System.out.printf("Przyspieszenie: %.2fx%n", timeSingleThread / timeMultiThread);
    }

    public static void multiplySingleThread(int[][] A, int[][] B, int[][] C) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    public static void multiplyMultiThread(int[][] A, int[][] B, int[][] D) {
        Thread[] threads = new Thread[4];
        int chunkSize = SIZE / 4;

        for (int t = 0; t < 4; t++) {
            final int startRow = t * chunkSize;
            final int endRow = (t == 3) ? SIZE : startRow + chunkSize;

            threads[t] = new Thread(() -> {
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        for (int k = 0; k < SIZE; k++) {
                            D[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
