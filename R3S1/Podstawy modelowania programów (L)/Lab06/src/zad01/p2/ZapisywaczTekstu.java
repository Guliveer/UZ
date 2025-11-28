package zad01.p2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za zapisywanie tekstu wprowadzonego przez użytkownika do pliku tekstowego.
 * Implementuje wzorzec strategii dla różnych sposobów zapisywania danych.
 */
public class ZapisywaczTekstu {
    private String nazwaPliku;
    
    /**
     * Konstruktor klasy ZapisywaczTekstu
     * @param nazwaPliku nazwa pliku, do którego będzie zapisywany tekst
     */
    public ZapisywaczTekstu(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }
    
    /**
     * Metoda pobierająca tekst od użytkownika i zapisująca go do pliku
     * @throws IOException w przypadku problemów z zapisem do pliku
     */
    public void zapiszTekstZKlawiatury() throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== ZAPISYWANIE TEKSTU DO PLIKU ===");
        System.out.print("Wprowadź tekst do zapisania: ");
        String tekst = scanner.nextLine();
        
        // Zapisanie tekstu do pliku
        try (FileWriter writer = new FileWriter(nazwaPliku, true)) { // true = dopisywanie do pliku
            writer.write("Tekst wprowadzony przez użytkownika: " + tekst + "\n");
            writer.write("Data zapisu: " + java.time.LocalDateTime.now() + "\n");
            writer.write("----------------------------------------\n");
        }
        
        System.out.println("Tekst został pomyślnie zapisany do pliku: " + nazwaPliku);
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