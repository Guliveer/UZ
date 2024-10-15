// Napisz program realizujący funkcje kalkulatora, z uwzględnieniem, ograniczenia dzielenia przez 0.

package lab02_p;

import java.util.Scanner;

public class Zad05 {
    public static void main(String[] args) {
        double a, b, result = 0;
        char operation;

        // ask for a, b, operation
        System.out.print("Podaj a: ");
        a = new Scanner(System.in).nextDouble();
        System.out.print("Podaj operację (+, -, *, /): ");
        operation = new Scanner(System.in).next().charAt(0);
        System.out.print("Podaj b: ");
        b = new Scanner(System.in).nextDouble();

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
                    System.out.println("Nie można dzielić przez 0");
                    return;
                }
                result = a / b;
                break;
            default:
                System.out.println("Nieznana operacja");
                return;
        }
        System.out.println(a + " " + operation + " " + b + " = " + result);
    }
}
