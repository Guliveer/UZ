/*
Napisać program, który wypełni dwie macierze wartośćiami losowymi a nastepnie wykonuje operacje
mnożenia tych macierzy. Po wykonanych obliczeniach wyświetl macierze źródłowe i macierz
docelową.
*/

package lab01_p;

import java.util.Random;

public class Zad05 {
    public static void main(String[] args) {
        int rows1 = 4;
        int cols1 = 3;

        int rows2 = 3;
        int cols2 = 5;

        int[][] matrix1 = new int[rows1][cols1];
        int[][] matrix2 = new int[rows2][cols2];
        int[][] resultMatrix = new int[rows1][cols2];
        Random random = new Random();

        fillMatrixWithRandomValues(matrix1, random);
        fillMatrixWithRandomValues(matrix2, random);

        multiplyMatrices(matrix1, matrix2, resultMatrix);

        System.out.println("Matrix 1:");
        printMatrix(matrix1);
        System.out.println("\nMatrix 2:");
        printMatrix(matrix2);
        System.out.println("\nResult Matrix:");
        printMatrix(resultMatrix);
    }

    private static void fillMatrixWithRandomValues(int[][] matrix, Random random) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
    }

    private static void multiplyMatrices(int[][] matrix1, int[][] matrix2, int[][] resultMatrix) {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("Number of columns in matrix1 must be equal to number of rows in matrix2");
        }
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                resultMatrix[i][j] = 0;
                for (int k = 0; k < matrix1[0].length; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}