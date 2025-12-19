package zad04;

import java.util.Scanner;

/**
 * Program główny - Sklep komputerowy.
 * Demonstracja wzorca Dekorator na przykładzie sklepu z produktami komputerowymi.
 * 
 * Produkty:
 * - Pamięć RAM 1 MB (1000 PLN)
 * - Karta graficzna Nvidia Riva 128 (1500 PLN)
 * - Złoty papier do pakowania (10 PLN)
 * 
 * Bonusy (dekoratory):
 * - Maskotka sklepu (gratis)
 * - Smycz do pendrive (gratis)
 * - Dodatkowy transport (+13 PLN)
 * 
 * Transport: 10 PLN za każdy produkt
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class Main {
    
    /** Skaner do odczytu danych z konsoli */
    private static Scanner scanner = new Scanner(System.in);
    
    /** Koszyk zakupowy */
    private static Koszyk koszyk = new Koszyk();
    
    /**
     * Punkt wejścia programu.
     * 
     * @param args argumenty wiersza poleceń (nieużywane)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       SKLEP KOMPUTEROWY RETRO          ");
        System.out.println("    Wzorzec Dekorator - Lab 6           ");
        System.out.println("========================================");
        
        boolean kontynuuj = true;
        
        while (kontynuuj) {
            wyswietlMenu();
            String wybor = scanner.nextLine().trim();
            
            switch (wybor) {
                case "1":
                    dodajPamiecRAM();
                    break;
                case "2":
                    dodajKarteGraficzna();
                    break;
                case "3":
                    dodajPapierPakowy();
                    break;
                case "4":
                    dodajBonus();
                    break;
                case "5":
                    koszyk.wyswietlKoszyk();
                    break;
                case "6":
                    koszyk.wyswietlPodsumowanie();
                    kontynuuj = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Wyświetla menu główne sklepu.
     */
    private static void wyswietlMenu() {
        System.out.println("\n=== SKLEP KOMPUTEROWY ===");
        System.out.println("1. Dodaj Pamięć RAM 1 MB (1000 PLN)");
        System.out.println("2. Dodaj Kartę graficzną Nvidia Riva 128 (1500 PLN)");
        System.out.println("3. Dodaj Złoty papier do pakowania (10 PLN)");
        System.out.println("4. Dodaj bonus do ostatniego produktu");
        System.out.println("5. Pokaż koszyk");
        System.out.println("6. Podsumowanie i zakończ");
        System.out.print("> ");
    }
    
    /**
     * Dodaje pamięć RAM do koszyka.
     */
    private static void dodajPamiecRAM() {
        Produkt produkt = new PamiecRAM();
        koszyk.dodajProdukt(produkt);
        System.out.println("Dodano: " + produkt.getOpis());
    }
    
    /**
     * Dodaje kartę graficzną do koszyka.
     */
    private static void dodajKarteGraficzna() {
        Produkt produkt = new KartaGraficzna();
        koszyk.dodajProdukt(produkt);
        System.out.println("Dodano: " + produkt.getOpis());
    }
    
    /**
     * Dodaje papier pakowy do koszyka.
     */
    private static void dodajPapierPakowy() {
        Produkt produkt = new PapierPakowy();
        koszyk.dodajProdukt(produkt);
        System.out.println("Dodano: " + produkt.getOpis());
    }
    
    /**
     * Dodaje bonus (dekorator) do ostatniego produktu w koszyku.
     */
    private static void dodajBonus() {
        if (koszyk.isEmpty()) {
            System.out.println("Koszyk jest pusty! Najpierw dodaj produkt.");
            return;
        }
        
        System.out.println("\nWybierz bonus:");
        System.out.println("  a) Maskotka sklepu (gratis)");
        System.out.println("  b) Smycz do pendrive (gratis)");
        System.out.println("  c) Dodatkowy transport (+13 PLN)");
        System.out.println("  x) Anuluj");
        System.out.print("> ");
        
        String wybor = scanner.nextLine().trim().toLowerCase();
        Produkt ostatniProdukt = koszyk.getOstatniProdukt();
        Produkt udekorowanyProdukt = null;
        String nazwaBonusu = "";
        
        switch (wybor) {
            case "a":
                udekorowanyProdukt = new MaskotkaSklep(ostatniProdukt);
                nazwaBonusu = "Maskotka sklepu";
                break;
            case "b":
                udekorowanyProdukt = new SmyczPendrive(ostatniProdukt);
                nazwaBonusu = "Smycz do pendrive";
                break;
            case "c":
                udekorowanyProdukt = new DodatkowyTransport(ostatniProdukt);
                nazwaBonusu = "Dodatkowy transport";
                break;
            case "x":
                System.out.println("Anulowano dodawanie bonusu.");
                return;
            default:
                System.out.println("Nieprawidłowy wybór.");
                return;
        }
        
        koszyk.zastapOstatniProdukt(udekorowanyProdukt);
        System.out.println("Dodano bonus: " + nazwaBonusu);
    }
}