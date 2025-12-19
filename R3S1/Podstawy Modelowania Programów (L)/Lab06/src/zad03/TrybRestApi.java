package zad03;

/**
 * Stub klasy implementującej tryb REST API.
 * 
 * Ta klasa jest przygotowana na przyszłe rozszerzenie programu o możliwość
 * pozyskiwania poleceń z REST API. Obecnie zawiera jedynie szkielet implementacji,
 * który pokazuje gotowość architektury na takie rozszerzenie.
 * 
 * Dzięki wzorcowi Dependency Injection, dodanie pełnej implementacji REST API
 * wymaga jedynie:
 * 1. Uzupełnienia tej klasy o właściwą logikę komunikacji z API
 * 2. Utworzenia odpowiedniej instancji w klasie Main (Composition Root)
 * 
 * Nie wymaga to żadnych zmian w klasach Aplikacja ani KomendyProcessor.
 */
public class TrybRestApi implements TrybPracy {
    
    private String endpointUrl;
    private int timeout;
    
    /**
     * Konstruktor tworzący tryb REST API z podanym URL endpointu.
     * 
     * @param endpointUrl URL endpointu REST API
     */
    public TrybRestApi(String endpointUrl) {
        this(endpointUrl, 30000); // Domyślny timeout 30 sekund
    }
    
    /**
     * Konstruktor tworzący tryb REST API z podanym URL endpointu i timeoutem.
     * 
     * @param endpointUrl URL endpointu REST API
     * @param timeout timeout połączenia w milisekundach
     */
    public TrybRestApi(String endpointUrl, int timeout) {
        this.endpointUrl = endpointUrl;
        this.timeout = timeout;
    }
    
    /**
     * Uruchamia tryb REST API.
     * 
     * UWAGA: To jest stub - pełna implementacja wymaga dodania:
     * - Klienta HTTP (np. HttpURLConnection, Apache HttpClient, OkHttp)
     * - Parsera JSON (np. Jackson, Gson)
     * - Obsługi błędów sieciowych
     * - Mechanizmu retry
     * 
     * @param processor procesor poleceń do obsługi komend
     */
    @Override
    public void uruchom(KomendyProcessor processor) {
        System.out.println("=".repeat(60));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - TRYB REST API (DI)");
        System.out.println("=".repeat(60));
        System.out.println("Endpoint: " + endpointUrl);
        System.out.println("Timeout: " + timeout + " ms");
        System.out.println();
        
        // TODO: Implementacja pobierania poleceń z REST API
        // Przykładowa struktura:
        // 1. Nawiąż połączenie z endpointem
        // 2. Pobierz listę poleceń (np. jako JSON array)
        // 3. Dla każdego polecenia:
        //    - Przetwórz przez processor.przetworzPolecenie()
        //    - Wyślij wynik z powrotem do API (opcjonalnie)
        
        System.out.println("UWAGA: Tryb REST API nie jest jeszcze zaimplementowany.");
        System.out.println("To jest stub pokazujący gotowość architektury na rozszerzenie.");
        System.out.println();
        System.out.println("Przykładowa implementacja mogłaby:");
        System.out.println("  1. Połączyć się z: " + endpointUrl);
        System.out.println("  2. Pobrać polecenia w formacie JSON");
        System.out.println("  3. Przetworzyć każde polecenie przez KomendyProcessor");
        System.out.println("  4. Zwrócić wyniki do API");
        System.out.println();
        
        // Demonstracja, że procesor działa poprawnie
        System.out.println("Demonstracja działania procesora poleceń:");
        String[] przykladowePolecenia = {"pomoc", "grupa", "czesc TestRestApi"};
        for (String polecenie : przykladowePolecenia) {
            System.out.println("  Polecenie: " + polecenie);
            System.out.println("  Wynik: " + processor.przetworzPolecenie(polecenie));
            System.out.println();
        }
    }
    
    /**
     * Sprawdza czy endpoint REST API jest dostępny.
     * 
     * UWAGA: To jest stub - pełna implementacja wymaga rzeczywistego
     * sprawdzenia połączenia z API.
     * 
     * @return true jeśli endpoint jest dostępny, false w przeciwnym razie
     */
    public boolean czyEndpointDostepny() {
        // TODO: Implementacja sprawdzenia dostępności endpointu
        // Np. wysłanie żądania HEAD lub GET do endpointu
        System.out.println("UWAGA: Sprawdzanie dostępności endpointu nie jest zaimplementowane.");
        return false;
    }
    
    /**
     * Zwraca URL endpointu REST API.
     * 
     * @return URL endpointu
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }
    
    /**
     * Zwraca timeout połączenia.
     * 
     * @return timeout w milisekundach
     */
    public int getTimeout() {
        return timeout;
    }
    
    @Override
    public String getNazwaTrybu() {
        return "Tryb REST API (endpoint: " + endpointUrl + ")";
    }
}