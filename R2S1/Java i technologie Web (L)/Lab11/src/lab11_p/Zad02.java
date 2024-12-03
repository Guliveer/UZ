// Oliwer Pawelski,  24INF-SP/A
//
// Napisz aplikację generującą i obsługującą własny wyjątek.

package lab11_p;

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class Zad02 {
    // Metoda, która może rzucić własnym wyjątkiem
    public static void validateAge(int age) throws MyException {
        if (age < 18) {
            throw new MyException("Wiek jest zbyt mały, aby kontynuować.");
        }
        System.out.println("Wiek jest wystarczający.");
    }

    public static void main(String[] args) {
        try {
            // Próba sprawdzenia wieku
            System.out.println("Sprawdzanie wieku dla: 16");
            validateAge(16);
        } catch (MyException e) {
            // Obsługa własnego wyjątku
            System.err.println("Wystąpił wyjątek: " + e.getMessage());
        }

        try {
            // Próba sprawdzenia wieku
            System.out.println("Sprawdzanie wieku dla: 20");
            validateAge(20);
        } catch (MyException e) {
            System.err.println("Wystąpił wyjątek: " + e.getMessage());
        }
    }
}
