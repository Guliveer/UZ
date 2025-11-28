package zad02.p3;

import java.util.Scanner;

/**
 * Klasa implementująca tryb konsolowy - identyczny jak w zadaniu 1
 * Polecenia są wprowadzane interaktywnie przez użytkownika
 */
public class TrybKonsolowy implements TrybPracy {
    
    private Scanner scanner;
    
    public TrybKonsolowy() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Uruchamia tryb konsolowy
     * @param processor procesor poleceń do obsługi komend
     */
    @Override
    public void uruchom(KomendyProcessor processor) {
        // Wyświetlenie powitania i instrukcji
        wyswietlPowitanie();
        
        // Główna pętla programu
        while (true) {
            System.out.print("> ");
            
            try {
                String polecenie = scanner.nextLine();
                
                // Sprawdzenie czy użytkownik chce zakończyć program
                if (processor.czyPolecenieWyjscia(polecenie)) {
                    System.out.println("Do widzenia!");
                    break;
                }
                
                // Przetworzenie polecenia i wyświetlenie wyniku
                String wynik = processor.przetworzPolecenie(polecenie);
                System.out.println(wynik);
                
            } catch (Exception e) {
                System.err.println("Wystąpił błąd podczas przetwarzania polecenia: " + e.getMessage());
                System.out.println("Spróbuj ponownie lub wpisz 'pomoc' aby zobaczyć dostępne polecenia.");
            }
            
            // Dodanie pustej linii dla lepszej czytelności
            System.out.println();
        }
    }
    
    /**
     * Wyświetla powitanie i instrukcje użytkowania programu
     */
    private void wyswietlPowitanie() {
        System.out.println("=".repeat(60));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - TRYB KONSOLOWY");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println("Dostępne polecenia:");
        System.out.println("  pomoc                 - wyświetla nazwiska implementatorów");
        System.out.println("  grupa                 - wyświetla grupę dziekańską");
        System.out.println("  czesc <Nazwisko>      - wyświetla powitanie z nazwiskiem");
        System.out.println("  exit/quit/wyjscie     - kończy program");
        System.out.println();
        System.out.println("Przykłady użycia:");
        System.out.println("  > pomoc");
        System.out.println("  > grupa");
        System.out.println("  > czesc Kowalski");
        System.out.println("  > exit");
        System.out.println();
        System.out.println("Wprowadź polecenie:");
    }
}