package zad03.p3;

import java.util.Scanner;

/**
 * Klasa implementująca proste logowanie przy pomocy loginu i hasła (Adaptee)
 */
public class SimpleLogin {
    // Dane logowania zapisane "na sztywno"
    private static final String VALID_LOGIN = "admin";
    private static final String VALID_PASSWORD = "haslo123";
    
    /**
     * Próba logowania użytkownika
     * @param login wprowadzony login
     * @param password wprowadzone hasło
     * @return true jeśli logowanie udane, false w przeciwnym razie
     */
    public boolean login(String login, String password) {
        return VALID_LOGIN.equals(login) && VALID_PASSWORD.equals(password);
    }
    
    /**
     * Interaktywne logowanie z konsoli
     * @return true jeśli logowanie udane
     */
    public boolean interactiveLogin() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        
        if (login(login, password)) {
            System.out.println("Witamy w aplikacji");
            return true;
        } else {
            System.out.println("Nieprawidłowy login lub hasło");
            return false;
        }
    }
}