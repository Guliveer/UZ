package zad02.p1;

import java.util.Scanner;

/**
 * Główna klasa programu konsolowego obsługującego polecenia użytkownika
 * 
 * Program obsługuje następujące polecenia:
 * - "pomoc" - wypisuje nazwiska implementatorów
 * - "grupa" - wypisuje grupę dziekańską
 * - "czesc Nazwisko" - wypisuje powitanie z nazwiskiem
 * - "exit"/"quit"/"wyjscie"/"koniec" - kończy program
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KomendyProcessor processor = new KomendyProcessor();
        
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
        
        scanner.close();
    }
    
    /**
     * Wyświetla powitanie i instrukcje użytkowania programu
     */
    private static void wyswietlPowitanie() {
        System.out.println("=".repeat(60));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ KONSOLOWYCH");
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
