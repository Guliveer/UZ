import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {
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
                        "MD5 checksum (C): %s%n" +
                        "MD5 checksum (D): %s%n" +
                        "Results match: %b%n" +
                        "%n",
                        size, size, timeSingleThread, timeMultiThread, timeSingleThread / timeMultiThread, md5C, md5D, areEqual
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

class FillMatrix {
    public static void main(String[] args) {
        final int SIZE = 20;
        int[][] matrixA = new int[SIZE][SIZE];
        int[][] matrixB = new int[SIZE][SIZE];

        fillMatrixA(matrixA);
        showArray(matrixA);

        fillMatrixB(matrixB);
        showArray(matrixB);
    }

    public static void fillMatrixA(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.abs((int) Math.round(Math.sin(i+j)*10));
            }
        }
    }

    public static void fillMatrixB(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.abs((int) Math.round(Math.cos(i+j)*10));
            }
        }
    }

    public static void showArray(int[][] matrix) {
        System.out.println("\nMatrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+", ");
            }
        }
    }
}
