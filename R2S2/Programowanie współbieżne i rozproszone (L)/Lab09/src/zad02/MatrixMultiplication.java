// 2. Zmodyfikuj zadanie 1 w taki sposób, aby mnożenie w wariancie wielowątkowym wykonywane było
// dla liczby wątków zgodnej z liczbą rdzeni procesora komputera (odczytaj tą liczbę za pomocą
// metody ARuntime.getRuntime().availableProcessors()). Wyświetl na ekranie odczytaną liczbę
// procesorów. Zadanie uruchom w trybie automatycznym w trzech wariantach, dla rozmiarów
// macierzy:
//
// - 500 x 500 (macierze wynikowe C1, D1),
// - 1000 x 1000 (macierze wynikowe C2, D2),
// - 1500 x 1500 (macierze wynikowe C3, D3).
// Dla każdego przypadku wyświetlaj informacje o:
//
// - wariancie obliczeń - rozmiarze macierzy,
// - czasie obliczeń,
// - zgodności obliczeń,
// - przyspieszeniu obliczeń.
//
// Dla każdego wariantu obliczeń (i każdej wynikowej macierzy C1, C2, C3, D1, D2, D3) oblicz i wyświetl
// sumę kontrolną MD5 zawartości macierzy (wykorzystaj klasę MessageDigest). Ponadto wszystkie dane
// wyświetlane na konsoli należy jednocześnie zapisywać do pliku tekstowego wyniki.txt. Po każdym
// kolejnym uruchomieniu programu, dane powinny zostać dopisane na koniec pliku wyniki.txt — nie należy
// usuwać zawartości tego pliku. Plik wyniki.txt powinien zapisywać się wewnątrz głównego folderu z
// projektem zadania. Oddając prace, plik wyniki.txt powinien zawierać dane przynajmniej dla jednego
// wykonania programu w trzech powyższych wariantach.

package zad02;

import utils.FillMatrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MatrixMultiplication {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        int[] sizes = {500, 1000, 1500};
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of processors: " + processors + "\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wyniki.txt", true))) {
            writer.write("Number of processors: " + processors + "\n\n");

            for (int size : sizes) {
                int[][] A = new int[size][size];
                int[][] B = new int[size][size];
                int[][] C = new int[size][size];
                int[][] D = new int[size][size];

                FillMatrix.fillMatrixA(A);
                FillMatrix.fillMatrixB(B);

                // Single-threaded multiplication
                long startSingleThread = System.nanoTime();
                multiplySingleThread(A, B, C, size);
                long endSingleThread = System.nanoTime();
                double timeSingleThread = (endSingleThread - startSingleThread) / 1e9;

                // Multi-threaded multiplication
                long startMultiThread = System.nanoTime();
                multiplyMultiThread(A, B, D, size, processors);
                long endMultiThread = System.nanoTime();
                double timeMultiThread = (endMultiThread - startMultiThread) / 1e9;

                // Compare results
                boolean areEqual = Arrays.deepEquals(C, D);

                // Calculate MD5 checksums
                String md5C = calculateMD5(C);
                String md5D = calculateMD5(D);

                // Log results
                String result = String.format(
                        "Matrix size: %dx%d%n" +
                        "Single-threaded time: %.2f s%n" +
                        "Multi-threaded time: %.2f s%n" +
                        "Speedup: %.2fx%n" +
                        "Results match: %b%n" +
                        "MD5 checksum (C): %s%n" +
                        "MD5 checksum (D): %s%n%n",
                        size, size, timeSingleThread, timeMultiThread, timeSingleThread / timeMultiThread, areEqual, md5C, md5D
                );

                System.out.print(result);
                writer.write(result);
            }

            writer.write("---\n");
        }
    }

    public static void multiplySingleThread(int[][] A, int[][] B, int[][] C, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    public static void multiplyMultiThread(int[][] A, int[][] B, int[][] D, int size, int threadsCount) {
        Thread[] threads = new Thread[threadsCount];
        int chunkSize = size / threadsCount;

        for (int t = 0; t < threadsCount; t++) {
            final int startRow = t * chunkSize;
            final int endRow = (t == threadsCount - 1) ? size : startRow + chunkSize;

            threads[t] = new Thread(() -> {
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
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

    public static String calculateMD5(int[][] matrix) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (int[] row : matrix) {
            for (int value : row) {
                md.update((byte) (value & 0xFF));
                md.update((byte) ((value >> 8) & 0xFF));
                md.update((byte) ((value >> 16) & 0xFF));
                md.update((byte) ((value >> 24) & 0xFF));
            }
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}