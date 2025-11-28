package zad02.p2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Klasa implementująca tryb skryptowy
 * Polecenia są wczytywane z pliku i wykonywane sekwencyjnie
 */
public class TrybSkryptowy implements TrybPracy {
    
    private String nazwaPliku;
    
    public TrybSkryptowy(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }
    
    /**
     * Uruchamia tryb skryptowy
     * @param processor procesor poleceń do obsługi komend
     */
    @Override
    public void uruchom(KomendyProcessor processor) {
        System.out.println("=".repeat(60));
        System.out.println("    PROGRAM OBSŁUGI POLECEŃ - TRYB SKRYPTOWY");
        System.out.println("=".repeat(60));
        System.out.println("Wczytywanie poleceń z pliku: " + nazwaPliku);
        System.out.println();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku))) {
            String polecenie;
            int numerLinii = 1;
            boolean czyZakonczone = false;
            
            while ((polecenie = reader.readLine()) != null && !czyZakonczone) {
                // Pomiń puste linie i komentarze
                if (polecenie.trim().isEmpty() || polecenie.trim().startsWith("#")) {
                    numerLinii++;
                    continue;
                }
                
                System.out.println("Linia " + numerLinii + ": " + polecenie);
                
                try {
                    // Sprawdzenie czy to polecenie wyjścia
                    if (processor.czyPolecenieWyjscia(polecenie)) {
                        System.out.println("Wynik: Zakończenie programu");
                        czyZakonczone = true;
                    } else {
                        // Przetworzenie polecenia i wyświetlenie wyniku
                        String wynik = processor.przetworzPolecenie(polecenie);
                        System.out.println("Wynik: " + wynik);
                    }
                    
                } catch (Exception e) {
                    System.err.println("Błąd podczas przetwarzania polecenia w linii " + numerLinii + ": " + e.getMessage());
                }
                
                System.out.println(); // Pusta linia dla czytelności
                numerLinii++;
            }
            
            if (!czyZakonczone) {
                System.out.println("Zakończono wykonywanie wszystkich poleceń z pliku.");
            }
            
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku '" + nazwaPliku + "': " + e.getMessage());
            System.err.println("Sprawdź czy plik istnieje i czy masz uprawnienia do jego odczytu.");
        } catch (Exception e) {
            System.err.println("Nieoczekiwany błąd podczas wykonywania skryptu: " + e.getMessage());
        }
    }
    
    /**
     * Sprawdza czy plik skryptu istnieje i jest dostępny do odczytu
     * @return true jeśli plik jest dostępny, false w przeciwnym razie
     */
    public boolean czyPlikDostepny() {
        try {
            java.io.File plik = new java.io.File(nazwaPliku);
            return plik.exists() && plik.canRead() && plik.isFile();
        } catch (Exception e) {
            return false;
        }
    }
}