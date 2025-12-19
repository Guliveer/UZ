package zad05;

import java.util.Scanner;

/**
 * Klasa implementująca operację dzielenia dla kalkulatora.
 * Implementuje interfejs Runnable, aby mogła być użyta jako akcja w OpcjaMenu.
 * 
 * Pobiera dwie liczby od użytkownika i wyświetla wynik ich ilorazu.
 * Obsługuje przypadek dzielenia przez zero.
 */
public class OperacjaDzielenie implements Runnable {
    
    /** Scanner do odczytu danych wejściowych od użytkownika */
    private Scanner scanner;
    
    /**
     * Konstruktor tworzący operację dzielenia.
     * 
     * @param scanner Scanner do odczytu liczb od użytkownika
     */
    public OperacjaDzielenie(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Wykonuje operację dzielenia.
     * Pobiera dwie liczby od użytkownika i wyświetla wynik.
     * Sprawdza czy dzielnik nie jest zerem.
     */
    @Override
    public void run() {
        System.out.println();
        System.out.println("=== DZIELENIE ===");
        
        try {
            // Pobierz pierwszą liczbę (dzielna)
            System.out.print("Podaj pierwszą liczbę: ");
            double liczba1 = Double.parseDouble(scanner.nextLine().trim());
            
            // Pobierz drugą liczbę (dzielnik)
            System.out.print("Podaj drugą liczbę: ");
            double liczba2 = Double.parseDouble(scanner.nextLine().trim());
            
            // Sprawdź czy nie dzielimy przez zero
            if (liczba2 == 0) {
                System.out.println("Błąd: Nie można dzielić przez zero!");
                return;
            }
            
            // Oblicz i wyświetl wynik
            double wynik = liczba1 / liczba2;
            
            // Formatowanie wyniku
            String wynikStr = formatujLiczbe(wynik);
            String liczba1Str = formatujLiczbe(liczba1);
            String liczba2Str = formatujLiczbe(liczba2);
            
            System.out.println("Wynik: " + liczba1Str + " / " + liczba2Str + " = " + wynikStr);
            
        } catch (NumberFormatException e) {
            System.out.println("Błąd: Wprowadzono nieprawidłową liczbę!");
        }
    }
    
    /**
     * Formatuje liczbę do wyświetlenia.
     * Jeśli liczba jest całkowita, usuwa część dziesiętną.
     * 
     * @param liczba Liczba do sformatowania
     * @return Sformatowana liczba jako String
     */
    private String formatujLiczbe(double liczba) {
        if (liczba == (long) liczba) {
            return String.valueOf((long) liczba);
        } else {
            return String.valueOf(liczba);
        }
    }
}