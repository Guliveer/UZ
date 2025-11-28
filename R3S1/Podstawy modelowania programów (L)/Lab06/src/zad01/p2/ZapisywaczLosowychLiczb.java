package zad01.p2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Klasa odpowiedzialna za generowanie i zapisywanie losowych liczb do pliku tekstowego.
 * Implementuje wzorzec strategii dla różnych sposobów zapisywania danych.
 */
public class ZapisywaczLosowychLiczb {
    private String nazwaPliku;
    private Random generator;
    
    /**
     * Konstruktor klasy ZapisywaczLosowychLiczb
     * @param nazwaPliku nazwa pliku, do którego będą zapisywane losowe liczby
     */
    public ZapisywaczLosowychLiczb(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
        this.generator = new Random();
    }
    
    /**
     * Metoda generująca losowe liczby i zapisująca je do pliku
     * @throws IOException w przypadku problemów z zapisem do pliku
     */
    public void zapiszLosoweLiczby() throws IOException {
        System.out.println("=== ZAPISYWANIE LOSOWYCH LICZB DO PLIKU ===");
        
        // Generowanie 10 losowych liczb z zakresu 1-100
        int iloscLiczb = 10;
        int minWartosc = 1;
        int maxWartosc = 100;
        
        try (FileWriter writer = new FileWriter(nazwaPliku, true)) { // true = dopisywanie do pliku
            writer.write("Losowe liczby wygenerowane przez program:\n");
            
            for (int i = 0; i < iloscLiczb; i++) {
                int losowa = generator.nextInt(maxWartosc - minWartosc + 1) + minWartosc;
                writer.write("Liczba " + (i + 1) + ": " + losowa + "\n");
            }
            
            writer.write("Data generowania: " + java.time.LocalDateTime.now() + "\n");
            writer.write("Zakres: " + minWartosc + " - " + maxWartosc + "\n");
            writer.write("----------------------------------------\n");
        }
        
        System.out.println("Wygenerowano i zapisano " + iloscLiczb + " losowych liczb do pliku: " + nazwaPliku);
    }
    
    /**
     * Metoda generująca określoną ilość losowych liczb z podanego zakresu
     * @param ilosc ilość liczb do wygenerowania
     * @param min minimalna wartość
     * @param max maksymalna wartość
     * @throws IOException w przypadku problemów z zapisem do pliku
     */
    public void zapiszLosoweLiczby(int ilosc, int min, int max) throws IOException {
        System.out.println("=== ZAPISYWANIE LOSOWYCH LICZB DO PLIKU (ZAAWANSOWANE) ===");
        
        try (FileWriter writer = new FileWriter(nazwaPliku, true)) {
            writer.write("Losowe liczby wygenerowane przez program (parametry niestandardowe):\n");
            
            for (int i = 0; i < ilosc; i++) {
                int losowa = generator.nextInt(max - min + 1) + min;
                writer.write("Liczba " + (i + 1) + ": " + losowa + "\n");
            }
            
            writer.write("Data generowania: " + java.time.LocalDateTime.now() + "\n");
            writer.write("Zakres: " + min + " - " + max + "\n");
            writer.write("Ilość: " + ilosc + "\n");
            writer.write("----------------------------------------\n");
        }
        
        System.out.println("Wygenerowano i zapisano " + ilosc + " losowych liczb do pliku: " + nazwaPliku);
    }
    
    /**
     * Getter dla nazwy pliku
     * @return nazwa pliku
     */
    public String getNazwaPliku() {
        return nazwaPliku;
    }
    
    /**
     * Setter dla nazwy pliku
     * @param nazwaPliku nowa nazwa pliku
     */
    public void setNazwaPliku(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }
}