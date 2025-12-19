package zad04;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca koszyk zakupowy.
 * Przechowuje listę produktów i automatycznie dolicza koszt transportu.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class Koszyk {
    
    /** Koszt transportu za każdy produkt w PLN */
    private static final double KOSZT_TRANSPORTU_ZA_SZTUKE = 10.0;
    
    /** Lista produktów w koszyku */
    private List<Produkt> produkty;
    
    /**
     * Konstruktor koszyka.
     * Tworzy pustą listę produktów.
     */
    public Koszyk() {
        this.produkty = new ArrayList<>();
    }
    
    /**
     * Dodaje produkt do koszyka.
     * 
     * @param produkt produkt do dodania
     */
    public void dodajProdukt(Produkt produkt) {
        produkty.add(produkt);
    }
    
    /**
     * Zwraca ostatni dodany produkt z koszyka.
     * Używane do dodawania bonusów (dekoratorów) do produktu.
     * 
     * @return ostatni produkt lub null jeśli koszyk jest pusty
     */
    public Produkt getOstatniProdukt() {
        if (produkty.isEmpty()) {
            return null;
        }
        return produkty.get(produkty.size() - 1);
    }
    
    /**
     * Zastępuje ostatni produkt w koszyku nowym produktem.
     * Używane do aktualizacji produktu po dodaniu dekoratora.
     * 
     * @param produkt nowy produkt (udekorowany)
     */
    public void zastapOstatniProdukt(Produkt produkt) {
        if (!produkty.isEmpty()) {
            produkty.set(produkty.size() - 1, produkt);
        }
    }
    
    /**
     * Zwraca liczbę produktów w koszyku.
     * 
     * @return liczba produktów
     */
    public int getLiczbaProduktow() {
        return produkty.size();
    }
    
    /**
     * Sprawdza czy koszyk jest pusty.
     * 
     * @return true jeśli koszyk jest pusty
     */
    public boolean isEmpty() {
        return produkty.isEmpty();
    }
    
    /**
     * Oblicza sumę cen wszystkich produktów (bez transportu).
     * 
     * @return suma cen produktów
     */
    public double getSumaProduktow() {
        double suma = 0;
        for (Produkt produkt : produkty) {
            suma += produkt.getCena();
        }
        return suma;
    }
    
    /**
     * Oblicza całkowity koszt transportu.
     * Transport kosztuje 10 PLN za każdy produkt.
     * 
     * @return całkowity koszt transportu
     */
    public double getSumaTransportu() {
        return produkty.size() * KOSZT_TRANSPORTU_ZA_SZTUKE;
    }
    
    /**
     * Oblicza całkowitą kwotę do zapłaty (produkty + transport).
     * 
     * @return całkowita kwota
     */
    public double getSumaCalkowita() {
        return getSumaProduktow() + getSumaTransportu();
    }
    
    /**
     * Wyświetla zawartość koszyka.
     */
    public void wyswietlKoszyk() {
        System.out.println("\n=== KOSZYK ===");
        
        if (produkty.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        
        int numer = 1;
        for (Produkt produkt : produkty) {
            System.out.printf("%d. %s - %.0f PLN%n", 
                numer, 
                produkt.getOpisZDodatkami(), 
                produkt.getCena());
            System.out.printf("   Transport: %.0f PLN%n", KOSZT_TRANSPORTU_ZA_SZTUKE);
            numer++;
        }
        
        System.out.println("---");
        System.out.printf("Suma produktów: %.0f PLN%n", getSumaProduktow());
        System.out.printf("Suma transportu: %.0f PLN%n", getSumaTransportu());
        System.out.printf("RAZEM: %.0f PLN%n", getSumaCalkowita());
    }
    
    /**
     * Wyświetla podsumowanie zakupów.
     */
    public void wyswietlPodsumowanie() {
        System.out.println("\n========================================");
        System.out.println("         PODSUMOWANIE ZAKUPÓW          ");
        System.out.println("========================================");
        
        if (produkty.isEmpty()) {
            System.out.println("Nie dokonano żadnych zakupów.");
            return;
        }
        
        System.out.println("\nZakupione produkty:");
        int numer = 1;
        for (Produkt produkt : produkty) {
            System.out.printf("%d. %s%n", numer, produkt.getOpisZDodatkami());
            System.out.printf("   Cena: %.0f PLN + transport: %.0f PLN%n", 
                produkt.getCena(), KOSZT_TRANSPORTU_ZA_SZTUKE);
            numer++;
        }
        
        System.out.println("\n----------------------------------------");
        System.out.printf("Suma produktów:    %10.0f PLN%n", getSumaProduktow());
        System.out.printf("Suma transportu:   %10.0f PLN%n", getSumaTransportu());
        System.out.println("----------------------------------------");
        System.out.printf("DO ZAPŁATY:        %10.0f PLN%n", getSumaCalkowita());
        System.out.println("========================================");
        System.out.println("\nDziękujemy za zakupy!");
    }
}