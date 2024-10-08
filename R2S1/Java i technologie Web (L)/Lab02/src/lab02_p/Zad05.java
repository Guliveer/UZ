// Napisz program realizujący funkcje kalkulatora, z uwzględnieniem, ograniczenia dzielenia przez 0.

package lab02_p;

public class Zad05 {
    public static void main(String[] args) {
        double a, b;
        char operation;
        // ask for a, b, operation
        System.out.print("Podaj a: ");
        a = new java.util.Scanner(System.in).nextDouble();
        System.out.print("Podaj operację (+, -, *, /): ");
        operation = new java.util.Scanner(System.in).next().charAt(0);
        System.out.print("Podaj b: ");
        b = new java.util.Scanner(System.in).nextDouble();

        // calculate
        switch (operation) {
            case '+':
                System.out.println("a + b = " + (a + b));
                break;
            case '-':
                System.out.println("a - b = " + (a - b));
                break;
            case '*':
                System.out.println("a * b = " + (a * b));
                break;
            case '/':
                if (b == 0) {
                    System.out.println("Nie można dzielić przez 0");
                    return;
                }
                System.out.println("a / b = " + (a / b));
                break;
            default:
                System.out.println("Nieznana operacja");
        }

    }
}
