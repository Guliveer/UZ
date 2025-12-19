package zad01.p2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Główna klasa aplikacji demonstrującej wzorzec Fabryka abstrakcyjna
 * z trybami Light, Dark i High Contrast dla komponentów GUI.
 * Wykorzystuje Map zamiast switch-case dla lepszej rozszerzalności.
 */
public class Main {
    // Mapa fabryk - łatwo rozszerzalna o nowe tryby
    private static final Map<String, GUIFactory> FACTORIES = new HashMap<>();
    
    static {
        FACTORIES.put("J", new LightGUIFactory());
        FACTORIES.put("C", new DarkGUIFactory());
        FACTORIES.put("K", new HighContrastGUIFactory());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Wybierz tryb: ");
            FACTORIES.forEach((key, value) ->
                    System.out.println(key + " - " + value.getName())
            );
            System.out.println("Q - wyjście");
            String choice = scanner.nextLine().trim().toUpperCase();
            
            if ("Q".equals(choice)) {
                System.out.println("Koniec programu.");
                scanner.close();
                return;
            }
            
            GUIFactory factory = FACTORIES.get(choice);
            
            if (factory == null) {
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                continue;
            }
            
            // Tworzenie i rysowanie komponentów GUI
            Window window = factory.createWindow();
            Button button = factory.createButton();
            TextField textField = factory.createTextField();
            
            window.draw();
            button.draw();
            textField.draw();
            
            System.out.println();
        }
    }
}