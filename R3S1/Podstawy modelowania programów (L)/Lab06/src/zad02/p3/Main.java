package zad02.p3;

/**
 * Główna klasa programu obsługującego polecenia użytkownika z wyborem trybu przez argumenty wiersza poleceń:
 * - Brak argumentów → tryb konsolowy
 * - Jeden argument (plik) → tryb skryptowy
 * - --help → wyświetla pomoc
 */
public class Main {
    
    public static void main(String[] args) {
        KomendyProcessor processor = new KomendyProcessor();
        
        try {
            // Analiza argumentów wiersza poleceń
            if (args.length == 0) {
                // Brak argumentów - tryb konsolowy
                uruchomTrybKonsolowy(processor);
                
            } else if (args.length == 1) {
                String argument = args[0];
                
                if (argument.equals("--help") || argument.equals("-h")) {
                    // Wyświetlenie pomocy
                    wyswietlPomoc();
                    
                } else {
                    // Jeden argument - tryb skryptowy z podanym plikiem
                    uruchomTrybSkryptowy(processor, argument);
                }
                
            } else {
                // Za dużo argumentów
                System.err.println("Błąd: Za dużo argumentów.");
                System.err.println("Użyj --help aby zobaczyć dostępne opcje.");
                System.exit(1);
            }
            
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Uruchamia tryb konsolowy
     * @param processor procesor poleceń
     */
    private static void uruchomTrybKonsolowy(KomendyProcessor processor) {
        System.out.println("Uruchamianie w trybie konsolowym...");
        System.out.println();
        
        TrybKonsolowy tryb = new TrybKonsolowy();
        tryb.uruchom(processor);
    }
    
    /**
     * Uruchamia tryb skryptowy z podanym plikiem
     * @param processor procesor poleceń
     * @param nazwaPliku nazwa pliku ze skryptem
     */
    private static void uruchomTrybSkryptowy(KomendyProcessor processor, String nazwaPliku) {
        System.out.println("Uruchamianie w trybie skryptowym...");
        System.out.println();
        
        TrybSkryptowy trybSkryptowy = new TrybSkryptowy(nazwaPliku);
        
        // Sprawdzenie czy plik jest dostępny
        if (!trybSkryptowy.czyPlikDostepny()) {
            System.err.println("Błąd: Plik '" + nazwaPliku + "' nie istnieje lub nie można go odczytać.");
            System.err.println("Sprawdź czy plik istnieje w bieżącym katalogu i czy masz uprawnienia do jego odczytu.");
            System.err.println();
            System.err.println("Przykład użycia:");
            System.err.println("  java zad02.p3.Main test_polecenia.txt");
            System.exit(1);
        }
        
        trybSkryptowy.uruchom(processor);
    }
    
    /**
     * Wyświetla pomoc dotyczącą użytkowania programu
     */
    private static void wyswietlPomoc() {
        System.out.println("=".repeat(70));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - POMOC");
        System.out.println("=".repeat(70));
        System.out.println();
        System.out.println("UŻYTKOWANIE:");
        System.out.println("  java zad02.p3.Main                    - uruchomienie w trybie konsolowym");
        System.out.println("  java zad02.p3.Main <plik>             - uruchomienie w trybie skryptowym");
        System.out.println("  java zad02.p3.Main --help             - wyświetlenie tej pomocy");
        System.out.println();
        System.out.println("TRYBY PRACY:");
        System.out.println();
        System.out.println("1. TRYB KONSOLOWY (brak argumentów)");
        System.out.println("   - Polecenia wprowadzane interaktywnie przez użytkownika");
        System.out.println("   - Program działa w pętli do momentu wprowadzenia polecenia wyjścia");
        System.out.println("   - Przykład: java zad02.p3.Main");
        System.out.println();
        System.out.println("2. TRYB SKRYPTOWY (jeden argument - nazwa pliku)");
        System.out.println("   - Polecenia wczytywane z podanego pliku i wykonywane sekwencyjnie");
        System.out.println("   - Linie zaczynające się od # są traktowane jako komentarze");
        System.out.println("   - Puste linie są ignorowane");
        System.out.println("   - Przykład: java zad02.p3.Main test_polecenia.txt");
        System.out.println();
        System.out.println("DOSTĘPNE POLECENIA W OBU TRYBACH:");
        System.out.println("  pomoc                 - wyświetla nazwiska implementatorów");
        System.out.println("  grupa                 - wyświetla grupę dziekańską");
        System.out.println("  czesc <Nazwisko>      - wyświetla powitanie z nazwiskiem");
        System.out.println("  exit/quit/wyjscie     - kończy program");
        System.out.println();
        System.out.println("PRZYKŁADY POLECEŃ:");
        System.out.println("  pomoc");
        System.out.println("  grupa");
        System.out.println("  czesc Kowalski");
        System.out.println("  exit");
        System.out.println();
        System.out.println("PRZYKŁADOWY PLIK SKRYPTU (test_polecenia.txt):");
        System.out.println("  # To jest komentarz");
        System.out.println("  pomoc");
        System.out.println("  grupa");
        System.out.println("  czesc Nowak");
        System.out.println("  exit");
        System.out.println();
    }
}