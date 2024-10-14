// Napisać program, który po wczytaniu wartości liczby całkowitej, oblicza stosunek
// średniej arytmetycznej cyfr nieparzystych do średniej arytmetycznej cyfr parzystych
// oraz sprawdza, czy podana liczba jest liczbą pierwszą.

package lab03_p;

import java.util.Scanner;

public class Zad01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        int number = scanner.nextInt();

        int oddSum = 0;
        int oddCount = 0;
        int evenSum = 0;
        int evenCount = 0;
        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit % 2 == 0) {
                evenSum += digit;
                evenCount++;
            } else {
                oddSum += digit;
                oddCount++;
            }
            temp /= 10;
        }
        double oddAvg = (double) oddSum / oddCount;
        double evenAvg = (double) evenSum / evenCount;
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
