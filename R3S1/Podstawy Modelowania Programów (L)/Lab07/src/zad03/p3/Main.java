package zad03.p3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Główna klasa demonstrująca wzorzec Adapter dla systemu logowania.
 * Kod kliencki (Application) korzysta z interfejsu LoginService,
 * dzięki czemu można łatwo dodać nowe metody logowania bez zmiany kodu klienckiego.
 * Wykorzystuje Map zamiast switch-case dla lepszej rozszerzalności.
 */
public class Main {
    // Mapa adapterów logowania - łatwo rozszerzalna o nowe metody
    private static final Map<String, Supplier<LoginService>> LOGIN_ADAPTERS = new HashMap<>();
    
    static {
        LOGIN_ADAPTERS.put("P", SimpleLoginAdapter::new);
        LOGIN_ADAPTERS.put("C", ChallengeResponseLoginAdapter::new);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== System logowania z wzorcem Adapter ===");
        System.out.println("Wybierz metodę logowania:");
        
        // Dynamiczne wyświetlanie dostępnych opcji
        LOGIN_ADAPTERS.forEach((key, supplier) -> {
            LoginService service = supplier.get();
            System.out.println(key + " - " + service.getLoginMethodName());
        });
        
        System.out.print("Twój wybór: ");
        
        String choice = scanner.nextLine().trim().toUpperCase();
        System.out.println();
        
        Supplier<LoginService> adapterSupplier = LOGIN_ADAPTERS.get(choice);
        
        if (adapterSupplier == null) {
            System.out.println("Nieprawidłowy wybór");
            return;
        }
        
        // Kod kliencki - nie zmienia się niezależnie od wybranej metody logowania
        LoginService loginService = adapterSupplier.get();
        Application app = new Application(loginService);
        app.run();
    }
}