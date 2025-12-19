package zad03.p1;

/**
 * Główna klasa demonstrująca proste logowanie login/hasło
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Proste logowanie login/hasło ===");
        System.out.println("(Poprawne dane: login=admin, hasło=haslo123)");
        System.out.println();
        
        SimpleLogin simpleLogin = new SimpleLogin();
        simpleLogin.interactiveLogin();
    }
}