// Oliwer Pawelski, 24INF-SP/A

// Wyznacz równanie kwadratowe z podanych przez użytkownika wartości parametrów a, b i c.

package lab02_p;

public class Zad02 {
    public static void main(String[] args) {
        double a, b, c;
        // ask for a, b, c
        System.out.print("Podaj a: ");
        a = new java.util.Scanner(System.in).nextDouble();
        System.out.print("Podaj b: ");
        b = new java.util.Scanner(System.in).nextDouble();
        System.out.print("Podaj c: ");
        c = new java.util.Scanner(System.in).nextDouble();

        System.out.println("\ny = " + a + "x^2 + " + b + "x + " + c);


        double delta = Math.pow(b, 2) - 4 * a * c; // calculate delta
        System.out.println("Delta: " + delta);
        if (delta < 0) {
            System.out.println("Brak rozwiązań");
        }
        if (delta == 0) {
            System.out.println("x = " + (-b / (2 * a)));
        }
        if (delta > 0) {
            double x1 = (-b - Math.sqrt(delta)) / (2 * a);
            double x2 = (-b + Math.sqrt(delta)) / (2 * a);
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
    }
}
