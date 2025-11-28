package zad02.p2;

import java.util.Scanner;

/**
 * Główna klasa programu obsługującego polecenia użytkownika w dwóch trybach:
 * - Tryb konsolowy (identyczny jak w zadaniu 1)
 * - Tryb skryptowy (polecenia wczytywane z pliku)
 * 
 * Wybór trybu:
 * - Pusta linia (Enter) - uruchamia tryb konsolowy
 * - "s nazwa_pliku" - uruchamia tryb skryptowy z podanego pliku
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KomendyProcessor processor = new KomendyProcessor();
        
        wyswietlMenuWyboru();
        
        try {
            System.out.print("Wybór: ");
            String wybor = scanner.nextLine().trim();
            
            TrybPracy tryb = null;
            
            // Analiza wyboru użytkownika
            if (wybor.isEmpty()) {
                // Pusta linia - tryb konsolowy
                tryb = new TrybKonsolowy();
                
            } else if (wybor.toLowerCase().startsWith("s ")) {
                // Tryb skryptowy
                String nazwaPliku = wybor.substring(2).trim();
                
                if (nazwaPliku.isEmpty()) {
                    System.err.println("Błąd: Nie podano nazwy pliku.");
                    System.err.println("Użyj formatu: 's nazwa_pliku.txt'");
                    return;
                }
                
                TrybSkryptowy trybSkryptowy = new TrybSkryptowy(nazwaPliku);
                
                // Sprawdzenie czy plik jest dostępny
                if (!trybSkryptowy.czyPlikDostepny()) {
                    System.err.println("Błąd: Plik '" + nazwaPliku + "' nie istnieje lub nie można go odczytać.");
                    System.err.println("Sprawdź czy plik istnieje w bieżącym katalogu i czy masz uprawnienia do jego odczytu.");
                    return;
                }
                
                tryb = trybSkryptowy;
                
            } else {
                // Nieprawidłowy wybór
                System.err.println("Błąd: Nieprawidłowy wybór '" + wybor + "'");
                System.err.println("Dostępne opcje:");
                System.err.println("  - Naciśnij Enter dla trybu konsolowego");
                System.err.println("  - Wpisz 's nazwa_pliku.txt' dla trybu skryptowego");
                return;
            }
            
            // Uruchomienie wybranego trybu
            if (tryb != null) {
                System.out.println();
                tryb.uruchom(processor);
            }
            
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Wyświetla menu wyboru trybu pracy
     */
    private static void wyswietlMenuWyboru() {
        System.out.println("=".repeat(60));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - WYBÓR TRYBU");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println("Dostępne tryby pracy:");
        System.out.println();
        System.out.println("1. TRYB KONSOLOWY");
        System.out.println("   - Polecenia wprowadzane interaktywnie");
        System.out.println("   - Wybór: naciśnij Enter (pusta linia)");
        System.out.println();
        System.out.println("2. TRYB SKRYPTOWY");
        System.out.println("   - Polecenia wczytywane z pliku");
        System.out.println("   - Wybór: wpisz 's nazwa_pliku.txt'");
        System.out.println("   - Przykład: s test_polecenia.txt");
        System.out.println();
        System.out.println("Dostępne polecenia w obu trybach:");
        System.out.println("  pomoc                 - wyświetla nazwiska implementatorów");
        System.out.println("  grupa                 - wyświetla grupę dziekańską");
        System.out.println("  czesc <Nazwisko>      - wyświetla powitanie z nazwiskiem");
        System.out.println("  exit/quit/wyjscie     - kończy program");
        System.out.println();
    }
}