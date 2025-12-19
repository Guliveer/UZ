package zad05;

import java.util.Scanner;

/**
 * Główna klasa programu kalkulatora wykorzystującego wzorzec Kompozyt.
 * 
 * Program demonstruje użycie wzorca Kompozyt do budowy hierarchicznego menu:
 * - ElementMenu (Component) - abstrakcyjna klasa bazowa
 * - OpcjaMenu (Leaf) - pojedyncza opcja wykonująca akcję
 * - GrupaMenu (Composite) - grupa zawierająca inne elementy
 * 
 * Struktura menu:
 * 1. Operacje
 *    a) Dodawanie
 *    b) Odejmowanie
 *    c) Mnożenie
 *    d) Dzielenie
 * 2. Stałe
 *    a) Pi
 *    b) e
 *    c) pierwiastek z 2
 * 0. Wyjście
 */
public class Main {
    
    /** Scanner współdzielony przez wszystkie komponenty */
    private static Scanner scanner;
    
    /**
     * Punkt wejścia programu.
     * Buduje strukturę menu i uruchamia główną pętlę programu.
     * 
     * @param args Argumenty linii poleceń (nieużywane)
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        // Zbuduj strukturę menu używając wzorca Kompozyt
        GrupaMenu menuGlowne = zbudujMenu();
        
        // Uruchom główną pętlę programu
        uruchomKalkulator(menuGlowne);
        
        // Zamknij scanner
        scanner.close();
    }
    
    /**
     * Buduje hierarchiczną strukturę menu kalkulatora.
     * Wykorzystuje wzorzec Kompozyt do tworzenia drzewa menu.
     * 
     * @return Główne menu kalkulatora (GrupaMenu)
     */
    private static GrupaMenu zbudujMenu() {
        // ==================== PODMENU OPERACJE ====================
        GrupaMenu operacje = new GrupaMenu("Operacje", "1", scanner);
        
        // Dodaj opcje operacji matematycznych
        operacje.dodaj(new OpcjaMenu("Dodawanie", "a", new OperacjaDodawanie(scanner)));
        operacje.dodaj(new OpcjaMenu("Odejmowanie", "b", new OperacjaOdejmowanie(scanner)));
        operacje.dodaj(new OpcjaMenu("Mnożenie", "c", new OperacjaMnozenie(scanner)));
        operacje.dodaj(new OpcjaMenu("Dzielenie", "d", new OperacjaDzielenie(scanner)));
        
        // ==================== PODMENU STAŁE ====================
        GrupaMenu stale = new GrupaMenu("Stałe", "2", scanner);
        
        // Dodaj opcje stałych matematycznych
        stale.dodaj(new OpcjaMenu("Pi", "a", StalaMatematyczna.pi()));
        stale.dodaj(new OpcjaMenu("e", "b", StalaMatematyczna.e()));
        stale.dodaj(new OpcjaMenu("pierwiastek z 2", "c", StalaMatematyczna.pierwiastekZ2()));
        
        // ==================== MENU GŁÓWNE ====================
        GrupaMenu menuGlowne = new GrupaMenu("Kalkulator", "0", scanner);
        menuGlowne.dodaj(operacje);
        menuGlowne.dodaj(stale);
        
        return menuGlowne;
    }
    
    /**
     * Uruchamia główną pętlę kalkulatora.
     * Wyświetla menu główne i obsługuje wybory użytkownika.
     * 
     * @param menuGlowne Główne menu kalkulatora
     */
    private static void uruchomKalkulator(GrupaMenu menuGlowne) {
        boolean dzialaj = true;
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     KALKULATOR - Wzorzec Kompozyt      ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        while (dzialaj) {
            // Wyświetl menu główne
            System.out.println();
            System.out.println("=== KALKULATOR ===");
            
            // Wyświetl opcje menu głównego
            for (ElementMenu element : menuGlowne.getDzieci()) {
                String separator = Character.isLetter(element.getIndeks().charAt(0)) ? ")" : ".";
                System.out.println(element.getIndeks() + separator + " " + element.getNazwa());
            }
            System.out.println("0. Wyjście");
            System.out.print("> ");
            
            // Odczytaj wybór użytkownika
            String wybor = scanner.nextLine().trim();
            
            if (wybor.equals("0")) {
                // Zakończ program
                dzialaj = false;
                System.out.println();
                System.out.println("Do widzenia!");
            } else {
                // Znajdź i wykonaj wybraną opcję
                ElementMenu wybranyElement = menuGlowne.pobierzOpcje(wybor);
                
                if (wybranyElement != null) {
                    wybranyElement.wykonaj();
                } else {
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                }
            }
        }
    }
}