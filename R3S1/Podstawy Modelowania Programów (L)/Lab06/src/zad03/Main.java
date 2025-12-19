package zad03;

/**
 * Główna klasa programu - pełni rolę "Composition Root" we wzorcu Dependency Injection.
 * 
 * Composition Root to miejsce, gdzie:
 * - Tworzone są wszystkie zależności (obiekty)
 * - Konfigurowane są powiązania między komponentami
 * - Wstrzykiwane są zależności do obiektów, które ich potrzebują
 * 
 * W tej implementacji Main:
 * 1. Analizuje argumenty wiersza poleceń
 * 2. Tworzy odpowiednią implementację TrybPracy
 * 3. Wstrzykuje ją do klasy Aplikacja
 * 4. Uruchamia aplikację
 * 
 * Dzięki temu podejściu:
 * - Klasa Aplikacja nie wie, jaki konkretny tryb będzie używany
 * - Łatwo można dodać nowe tryby (np. REST API) bez modyfikacji Aplikacji
 * - Kod jest łatwy do testowania (można wstrzyknąć mock'i)
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            // Analiza argumentów i utworzenie odpowiedniego trybu pracy
            TrybPracy trybPracy = utworzTrybPracy(args);
            
            if (trybPracy == null) {
                // Wyświetlono pomoc lub wystąpił błąd - kończymy
                return;
            }
            
            // === COMPOSITION ROOT ===
            // Tutaj tworzymy zależności i wstrzykujemy je do aplikacji
            Aplikacja aplikacja = new Aplikacja(trybPracy);
            
            // Uruchomienie aplikacji
            aplikacja.uruchom();
            
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Tworzy odpowiednią implementację TrybPracy na podstawie argumentów wiersza poleceń.
     * 
     * Ta metoda jest częścią Composition Root - decyduje, która implementacja
     * interfejsu TrybPracy zostanie utworzona i wstrzyknięta do aplikacji.
     * 
     * @param args argumenty wiersza poleceń
     * @return implementacja TrybPracy lub null jeśli wyświetlono pomoc
     */
    private static TrybPracy utworzTrybPracy(String[] args) {
        // Brak argumentów - tryb konsolowy
        if (args.length == 0) {
            System.out.println("Uruchamianie w trybie konsolowym...");
            System.out.println();
            return new TrybKonsolowy();
        }
        
        // Jeden argument
        if (args.length == 1) {
            String argument = args[0];
            
            // Pomoc
            if (argument.equals("--help") || argument.equals("-h")) {
                wyswietlPomoc();
                return null;
            }
            
            // Tryb REST API (demonstracyjny)
            if (argument.equals("--rest-demo")) {
                System.out.println("Uruchamianie w trybie REST API (demo)...");
                System.out.println();
                return new TrybRestApi("https://api.example.com/polecenia");
            }
            
            // Tryb skryptowy - argument to nazwa pliku
            System.out.println("Uruchamianie w trybie skryptowym...");
            System.out.println();
            
            TrybSkryptowy trybSkryptowy = new TrybSkryptowy(argument);
            
            // Sprawdzenie czy plik jest dostępny
            if (!trybSkryptowy.czyPlikDostepny()) {
                System.err.println("Błąd: Plik '" + argument + "' nie istnieje lub nie można go odczytać.");
                System.err.println("Sprawdź czy plik istnieje w bieżącym katalogu i czy masz uprawnienia do jego odczytu.");
                System.err.println();
                System.err.println("Przykład użycia:");
                System.err.println("  java zad03.Main test_polecenia.txt");
                System.exit(1);
            }
            
            return trybSkryptowy;
        }
        
        // Dwa argumenty - tryb REST API z własnym URL
        if (args.length == 2 && args[0].equals("--rest")) {
            String endpointUrl = args[1];
            System.out.println("Uruchamianie w trybie REST API...");
            System.out.println();
            return new TrybRestApi(endpointUrl);
        }
        
        // Za dużo argumentów lub nieznane argumenty
        System.err.println("Błąd: Nieprawidłowe argumenty.");
        System.err.println("Użyj --help aby zobaczyć dostępne opcje.");
        System.exit(1);
        return null;
    }
    
    /**
     * Wyświetla pomoc dotyczącą użytkowania programu.
     */
    private static void wyswietlPomoc() {
        System.out.println("=".repeat(70));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - DEPENDENCY INJECTION");
        System.out.println("=".repeat(70));
        System.out.println();
        System.out.println("UŻYTKOWANIE:");
        System.out.println("  java zad03.Main                       - tryb konsolowy");
        System.out.println("  java zad03.Main <plik>                - tryb skryptowy");
        System.out.println("  java zad03.Main --rest-demo           - tryb REST API (demo)");
        System.out.println("  java zad03.Main --rest <url>          - tryb REST API z własnym URL");
        System.out.println("  java zad03.Main --help                - wyświetlenie tej pomocy");
        System.out.println();
        System.out.println("WZORZEC DEPENDENCY INJECTION:");
        System.out.println();
        System.out.println("  Ten program wykorzystuje wzorzec Dependency Injection (DI):");
        System.out.println("  - Main.java pełni rolę 'Composition Root'");
        System.out.println("  - Klasa Aplikacja otrzymuje TrybPracy przez konstruktor");
        System.out.println("  - Aplikacja nie wie, jaki konkretny tryb będzie używany");
        System.out.println();
        System.out.println("  Zalety DI:");
        System.out.println("  - Luźne powiązanie między komponentami");
        System.out.println("  - Łatwe testowanie (można wstrzyknąć mock'i)");
        System.out.println("  - Łatwe rozszerzanie o nowe tryby pracy");
        System.out.println();
        System.out.println("TRYBY PRACY:");
        System.out.println();
        System.out.println("1. TRYB KONSOLOWY (brak argumentów)");
        System.out.println("   - Polecenia wprowadzane interaktywnie przez użytkownika");
        System.out.println("   - Przykład: java zad03.Main");
        System.out.println();
        System.out.println("2. TRYB SKRYPTOWY (jeden argument - nazwa pliku)");
        System.out.println("   - Polecenia wczytywane z podanego pliku");
        System.out.println("   - Przykład: java zad03.Main test_polecenia.txt");
        System.out.println();
        System.out.println("3. TRYB REST API (--rest-demo lub --rest <url>)");
        System.out.println("   - Stub przygotowany na przyszłe rozszerzenie");
        System.out.println("   - Pokazuje gotowość architektury na nowe źródła danych");
        System.out.println("   - Przykład: java zad03.Main --rest-demo");
        System.out.println("   - Przykład: java zad03.Main --rest https://api.example.com/polecenia");
        System.out.println();
        System.out.println("DOSTĘPNE POLECENIA:");
        System.out.println("  pomoc                 - wyświetla nazwiska implementatorów");
        System.out.println("  grupa                 - wyświetla grupę dziekańską");
        System.out.println("  czesc <Nazwisko>      - wyświetla powitanie z nazwiskiem");
        System.out.println("  exit/quit/wyjscie     - kończy program");
        System.out.println();
    }
}