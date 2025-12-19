package zad01;

import java.util.Scanner;

/**
 * Klasa Context zarządzająca aktualnym stanem w maszynie stanów.
 * Przechowuje dane wejściowe: jedną zmienną tekst i jedną zmienną liczba.
 *
 * UWAGA: Ta maszyna stanów jest cykliczna - nie ma stanów końcowych.
 * Użytkownik może zakończyć działanie wpisując "quit" lub "exit".
 */
public class Context {
    
    private final Scanner scanner;
    private State currentState;
    // Dane wejściowe - JEDNA zmienna tekst i JEDNA zmienna liczba
    private String tekst;
    private Integer liczba;
    
    // Flaga określająca czy ostatnio wprowadzono liczbę czy tekst
    private boolean lastInputWasNumber;
    
    // Flaga do zakończenia działania
    private boolean running;
    
    /**
     * Konstruktor tworzący kontekst z początkowym stanem A.
     * @param scanner skaner do odczytu danych wejściowych
     */
    public Context(Scanner scanner) {
        this.scanner = scanner;
        this.currentState = new StateA();
        this.tekst = null;
        this.liczba = null;
        this.lastInputWasNumber = false;
        this.running = true;
        System.out.println("=== Maszyna stanów uruchomiona ===");
        System.out.println("Stan początkowy: " + currentState.getStateName());
        System.out.println("(Wpisz 'quit' lub 'exit' aby zakończyć)");
    }
    
    /**
     * Zmienia aktualny stan na nowy.
     * @param newState nowy stan
     */
    public void setState(State newState) {
        this.currentState = newState;
    }
    
    /**
     * Uruchamia maszynę stanów - wykonuje przejścia w cyklu.
     * Maszyna działa do momentu wpisania "quit" lub "exit".
     */
    public void run() {
        while (running) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║ Aktualny stan: " + currentState.getStateName() + "                     ║");
            System.out.println("╚══════════════════════════════════════╝");
            
            if (currentState instanceof AbstractState abstractState) {
                abstractState.displayTransitions();
            }
            
            // Pobierz wartość od użytkownika
            System.out.print("Podaj wartość: ");
            String input = scanner.nextLine().trim();
            
            // Sprawdź czy użytkownik chce zakończyć
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("\n=== Zakończono działanie maszyny stanów ===");
                System.out.println("Ostatni stan: " + currentState.getStateName());
                break;
            }
            
            // Automatyczne rozpoznanie typu wartości
            parseAndSetValue(input);
            
            // Próba wykonania przejścia
            if (currentState instanceof AbstractState abstractState) {
                if (!abstractState.tryTransition(this)) {
                    System.out.println("⚠ Brak pasującego przejścia dla podanej wartości.");
                    if (lastInputWasNumber) {
                        System.out.println("  Wprowadzono liczbę: " + liczba);
                        System.out.println("  Ten stan może wymagać tekstu.");
                    } else {
                        System.out.println("  Wprowadzono tekst: \"" + tekst + "\"");
                        System.out.println("  Ten stan może wymagać liczby.");
                    }
                }
            }
        }
    }
    
    /**
     * Parsuje wartość wejściową i ustawia odpowiednią zmienną.
     * Jeśli wartość jest liczbą - ustawia zmienną liczba.
     * Jeśli nie jest liczbą - ustawia zmienną tekst.
     * @param input wartość wejściowa
     */
    private void parseAndSetValue(String input) {
        try {
            this.liczba = Integer.parseInt(input);
            this.tekst = null;
            this.lastInputWasNumber = true;
        } catch (NumberFormatException e) {
            this.tekst = input;
            this.liczba = null;
            this.lastInputWasNumber = false;
        }
    }
    
    /**
     * Zwraca skaner do odczytu danych wejściowych.
     * @return skaner
     */
    public Scanner getScanner() {
        return scanner;
    }
    
    // Gettery dla danych wejściowych
    
    /**
     * Zwraca wartość tekstową.
     * @return tekst lub null jeśli nie ustawiono
     */
    public String getTekst() {
        return tekst;
    }
    
    /**
     * Zwraca wartość liczbową.
     * @return liczba lub null jeśli nie ustawiono
     */
    public Integer getLiczba() {
        return liczba;
    }
    
    /**
     * Sprawdza czy ostatnio wprowadzono liczbę.
     * @return true jeśli ostatnio wprowadzono liczbę
     */
    public boolean wasLastInputNumber() {
        return lastInputWasNumber;
    }
    
    /**
     * Sprawdza czy ostatnio wprowadzono tekst.
     * @return true jeśli ostatnio wprowadzono tekst
     */
    public boolean wasLastInputText() {
        return !lastInputWasNumber;
    }
    
    /**
     * Zwraca aktualny stan.
     * @return aktualny stan
     */
    public State getCurrentState() {
        return currentState;
    }
}