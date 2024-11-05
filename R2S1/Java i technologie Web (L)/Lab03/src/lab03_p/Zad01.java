// Oliwer Pawelski, 24INF-SP/A

// Napisać program, który po wczytaniu wartości liczby całkowitej, oblicza stosunek
// średniej arytmetycznej cyfr nieparzystych do średniej arytmetycznej cyfr parzystych
// oraz sprawdza, czy podana liczba jest liczbą pierwszą.

package lab03_p;

import java.util.ArrayList;
import java.util.Scanner;

public class Zad01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();

        String numberStr = Integer.toString(number);
        for (char digitChar : numberStr.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            if (digit % 2 == 0) {
                even.add(digit);
            } else {
                odd.add(digit);
            }
        }

        double oddAvg = odd.stream().mapToInt(Integer::intValue).average().orElse(0);
        double evenAvg = even.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.print("Odd digits: ");
        for (int i = odd.size() - 1; i >= 0; i--) {
            System.out.print(odd.get(i) + " ");
        }

        System.out.print("\nEven digits: ");
        for (int i = even.size() - 1; i >= 0; i--) {
            System.out.print(even.get(i) + " ");
        }

        System.out.print("\n");
        System.out.println("Odd average: " + oddAvg);
        System.out.println("Even average: " + evenAvg);
        System.out.println("Ratio: " + oddAvg / evenAvg);
        if (isPrime(number)) {
            System.out.println("Number is prime");
        } else {
            System.out.println("Number is not prime");
        }
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
