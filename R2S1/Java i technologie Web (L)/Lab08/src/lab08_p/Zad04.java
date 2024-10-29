// Oliwer Pawelski, 24INF-SP/A

// Stwórz wyrażenie regularne obsługujące adres e-mail.

package lab08_p;

public class Zad04 {
    public static void main(String[] args) {
        System.out.print("Podaj adres e-mail: ");
        String email = System.console().readLine();
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) { // uproszczony regex
            System.out.println("Adres e-mail jest poprawny.");
        } else {
            System.out.println("Adres e-mail jest niepoprawny.");
        }
    }
}
