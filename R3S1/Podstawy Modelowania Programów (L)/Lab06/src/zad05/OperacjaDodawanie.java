package zad05;

import java.util.Scanner;

/**
 * Klasa implementująca operację dodawania dla kalkulatora.
 * Implementuje interfejs Runnable, aby mogła być użyta jako akcja w OpcjaMenu.
 * 
 * Pobiera dwie liczby od użytkownika i wyświetla wynik ich sumy.
 */
public class OperacjaDodawanie implements Runnable {
    
    /** Scanner do odczytu danych wejściowych od użytkownika */
    private Scanner scanner;
    
    /**
     * Konstruktor tworzący operację dodawania.
     * 
     * @param scanner Scanner do odczytu liczb od użytkownika
     */
    public OperacjaDodawanie(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Wykonuje operację dodawania.
     * Pobiera dwie liczby od użytkownika i wyświetla wynik.
     */
    @Override
    public void run() {
        System.out.println();
        System.out.println("=== DODAWANIE ===");
        
        try {
            // Pobierz pierwszą liczbę
            System.out.print("Podaj pierwszą liczbę: ");
            double liczba1 = Double.parseDouble(scanner.nextLine().trim());
            
            // Pobierz drugą liczbę
            System.out.print("Podaj drugą liczbę: ");
            double liczba2 = Double.parseDouble(scanner.nextLine().trim());
            
            // Oblicz i wyświetl wynik
            double wynik = liczba1 + liczba2;
            
            // Formatowanie wyniku - jeśli wynik jest liczbą całkowitą, wyświetl bez części dziesiętnej
            String wynikStr = formatujLiczbe(wynik);
            String liczba1Str = formatujLiczbe(liczba1);
            String liczba2Str = formatujLiczbe(liczba2);
            
            System.out.println("Wynik: " + liczba1Str + " + " + liczba2Str + " = " + wynikStr);
            
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