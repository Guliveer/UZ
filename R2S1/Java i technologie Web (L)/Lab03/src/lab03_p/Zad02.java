// Oliwer Pawelski, 24INF-SP/A

// Zrealizuj kod, który dla zadanej liczby całkowitej wyświetla jej wszystkie dzielniki.

package lab03_p;

import java.util.Scanner;

public class Zad02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = scanner.nextInt();

        System.out.println("Divisors of " + number + ":");
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.println(i);
            }
        }
    }
}
