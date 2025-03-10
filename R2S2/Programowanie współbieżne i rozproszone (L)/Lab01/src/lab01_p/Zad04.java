/*
Zapelnić trzy tablice wartościami losowymi. Wyznaczyć wartość najwiekszą z każdej tablicy.
Następnie wyznaczyć wartość najwiekszą sposród maksimów tych trzech tablic.
*/

package lab01_p;

import java.util.Random;

public class Zad04 {
    public static void main(String[] args) {
        int N = 10;
        int[] array1 = new int[N];
        int[] array2 = new int[N];
        int[] array3 = new int[N];
        Random random = new Random();

        fillArrayWithRandomValues(array1, random);
        fillArrayWithRandomValues(array2, random);
        fillArrayWithRandomValues(array3, random);

        int max1 = findMaxValue(array1);
        int max2 = findMaxValue(array2);
        int max3 = findMaxValue(array3);

        int overallMax = Math.max(max1, Math.max(max2, max3));

        System.out.println("Max value in array1: " + max1);
        System.out.println("Max value in array2: " + max2);
        System.out.println("Max value in array3: " + max3);
        System.out.println("Overall max value: " + overallMax);
    }

    private static void fillArrayWithRandomValues(int[] array, Random values) {
        for (int i = 0; i < array.length; i++) {
            array[i] = values.nextInt(1000); // Random values
        }
    }

    private static int findMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}