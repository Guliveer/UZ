package zad03.p2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Główna klasa demonstrująca oba sposoby logowania.
 * Wykorzystuje Map zamiast switch-case dla lepszej rozszerzalności.
 */
public class Main {
    // Interfejs funkcyjny dla metod logowania
    @FunctionalInterface
    interface LoginMethod {
        boolean login();
        
        default String getDescription() {
            return "Metoda logowania";
        }
    }
    
    // Mapa metod logowania - łatwo rozszerzalna
    private static final Map<String, LoginMethod> LOGIN_METHODS = new HashMap<>();
    
    static {
        LOGIN_METHODS.put("P", new LoginMethod() {
            @Override
            public boolean login() {
                System.out.println("=== Proste logowanie ===");
                System.out.println("(Poprawne dane: login=admin, hasło=haslo123)");
                return new SimpleLogin().interactiveLogin();
            }
            
            @Override
            public String getDescription() {
                return "Proste logowanie (login/hasło)";
            }
        });
        
        LOGIN_METHODS.put("C", new LoginMethod() {
            @Override
            public boolean login() {
                System.out.println("=== Logowanie Challenge-Response ===");
                return new ChallengeResponseLogin().interactiveLogin();
            }
            
            @Override
            public String getDescription() {
                return "Challenge-Response";
            }
        });
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== System logowania ===");
        System.out.println("Wybierz rodzaj logowania:");
        
        // Dynamiczne wyświetlanie dostępnych opcji
        LOGIN_METHODS.forEach((key, method) -> 
            System.out.println(key + " - " + method.getDescription()));
        
        System.out.print("Twój wybór: ");
        
        String choice = scanner.nextLine().trim().toUpperCase();
        System.out.println();
        
        LoginMethod loginMethod = LOGIN_METHODS.get(choice);
        
        if (loginMethod == null) {
            System.out.println("Nieprawidłowy wybór");
            return;
        }
        
        loginMethod.login();
    }
}