// Oliwer Pawelski, 24INF-SP/A
//
// Napisz aplikację, która obsłuży wyjątek w przypadku wystąpienia operacji
// niedozwolonej – np. dzielenia liczby przez zero (ArithmeticException). Po wykryciu
// wystąpienia wyjątku należy wyświetlić komunikat „Nie dziel przez zero”, następnie
// pomimo wystąpienia błędu zwrócić komunikat i zakończyć działanie programu.

package lab11_p;

import java.util.Scanner;

public class Zad01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- Dzielenie --");

        System.out.print("Podaj a: ");
        int a = scanner.nextInt();

        System.out.print("Podaj b: ");
        int b = scanner.nextInt();

        System.out.printf("a / b = %d / %d = ", a, b);
        try {
            System.out.println(divide(a, b));
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage() + ": Nie dziel przez zero");
        }
    }

    static double divide(int a, int b) throws ArithmeticException {
        if (b == 0) throw new ArithmeticException("[Div0]");
        return (double) a / b;
    }
}
