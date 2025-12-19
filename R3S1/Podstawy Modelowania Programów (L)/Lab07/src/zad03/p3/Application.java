package zad03.p3;

/**
 * Klasa kliencka korzystająca z interfejsu LoginService.
 * Dzięki wzorcowi Adapter można łatwo dodać nowe metody logowania
 * bez zmiany kodu klienckiego.
 */
public class Application {
    private final LoginService loginService;
    
    public Application(LoginService loginService) {
        this.loginService = loginService;
    }
    
    /**
     * Uruchamia proces logowania
     */
    public void run() {
        System.out.println("=== " + loginService.getLoginMethodName() + " ===");
        boolean success = loginService.authenticate();
        
        if (success) {
            System.out.println("\nLogowanie zakończone sukcesem!");
            System.out.println("Możesz teraz korzystać z aplikacji.");
        } else {
            System.out.println("\nLogowanie nieudane.");
            System.out.println("Spróbuj ponownie później.");
        }
    }
}