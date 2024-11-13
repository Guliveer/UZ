// Oliwer Pawelski, 24INF-SP/A

// Napisz program realizujący funkcje kalkulatora, z uwzględnieniem, ograniczenia dzielenia przez 0.

package lab02_p;

import java.util.Scanner;

public class Zad05 {
    public static void main(String[] args) {
        double a, b, result = 0;
        char operation;

        // ask for a, b, operation
        System.out.print("Podaj a:\n");
        try {
            a = new Scanner(System.in).nextDouble();
        } catch (Exception z) {
            throw new ArithmeticException("Podana wartość nie jest liczbą");
        }

        System.out.print("Podaj operację (+, -, *, /):\n" + a + " ");
        operation = new Scanner(System.in).next().charAt(0);
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
            throw new ArithmeticException("Nieznana operacja");
        }

        System.out.print("Podaj b:\n" + a + " " + operation + " ");
        try {
            b = new Scanner(System.in).nextDouble();
        } catch (Exception e) {
            throw new ArithmeticException("Podana wartość nie jest liczbą");
        }

        // calculate
        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Dzielenie przez 0");
                }
                result = a / b;
                break;
            default:
                System.out.println("Nieznana operacja");
                return;
        }
        System.out.println("\n" + a + " " + operation + " " + b + " = " + result);
    }
}
