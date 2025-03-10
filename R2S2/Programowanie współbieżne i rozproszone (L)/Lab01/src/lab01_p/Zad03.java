/*
Napisać program, który:
    a) utworzy tablice N liczb całkowitych i wypelni je wartościami losowymi (N - stała),
    b) wypisze na ekranie zawartość tablicy,
    c) wyznaczy najwiekszy oraz najmniejszy element tablicy,
    d) wyznaczy 3 najwieksze elementy tablicy (nie sortować tablicy),
    e) wyznaczy srednia arytmetyczna elementów tablicy,
    f) wyswietli na ekranie zwartość tablicy w odwrotnej kolejnosci (od ostatniego elementu do pierwszego).
    g) odwróci kolejnosc elementów w tablicy (element ostatni staje sie pierwszym, itd.)

Wszystkie wyznaczone elementy nalezy wyswietlié na ekranie monitora.
*/

import java.util.Arrays;
import java.util.Random;

public class Zad03 {
    public static void main(String[] args) {
        int N = 10;
        int[] array = new int[N];
        Random random = new Random();

        // a)
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(1000); // Random values
        }

        // b)
        System.out.println("Array: " + Arrays.toString(array));

        // c)
        int max = array[0];
        int min = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        // d)
        int[] top3 = findTop3(array);
        System.out.println("Top 3 largest elements: " + Arrays.toString(top3));

        // e)
        double average = Arrays.stream(array).average().orElse(0);
        System.out.println("Average: " + average);

        // f)
        System.out.print("Array in reverse order: ");
        for (int i = N - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // g)
        reverseArray(array);
        System.out.println("Reversed array: " + Arrays.toString(array));
    }

    private static int[] findTop3(int[] array) {
        int[] top3 = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int value : array) {
            if (value > top3[0]) {
                top3[2] = top3[1];
                top3[1] = top3[0];
                top3[0] = value;
            } else if (value > top3[1]) {
                top3[2] = top3[1];
                top3[1] = value;
            } else if (value > top3[2]) {
                top3[2] = value;
            }
        }
        return top3;
    }

    private static void reverseArray(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
