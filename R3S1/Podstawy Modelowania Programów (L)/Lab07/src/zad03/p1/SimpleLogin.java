package zad03.p1;

import java.util.Scanner;

/**
 * Klasa implementująca proste logowanie przy pomocy loginu i hasła
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
     */
    public void interactiveLogin() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        
        if (login(login, password)) {
            System.out.println("Witamy w aplikacji");
        } else {
            System.out.println("Nieprawidłowy login lub hasło");
        }
    }
}