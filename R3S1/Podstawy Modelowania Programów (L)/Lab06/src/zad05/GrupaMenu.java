package zad05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa reprezentująca kompozyt (Composite) w wzorcu Kompozyt.
 * Reprezentuje grupę elementów menu, która może zawierać inne elementy
 * (zarówno pojedyncze opcje jak i inne grupy).
 * 
 * GrupaMenu zarządza kolekcją dzieci i deleguje operacje do nich,
 * umożliwiając tworzenie hierarchicznych struktur menu.
 */
public class GrupaMenu extends ElementMenu {
    
    /** Lista elementów potomnych (dzieci) tej grupy */
    private List<ElementMenu> dzieci;
    
    /** Scanner do odczytu danych wejściowych od użytkownika */
    private Scanner scanner;
    
    /**
     * Konstruktor tworzący grupę menu.
     * 
     * @param nazwa Nazwa grupy wyświetlana w menu
     * @param indeks Indeks używany do wyboru grupy (np. "1", "2")
     * @param scanner Scanner do odczytu danych wejściowych
     */
    public GrupaMenu(String nazwa, String indeks, Scanner scanner) {
        super(nazwa, indeks);
        this.dzieci = new ArrayList<>();
        this.scanner = scanner;
    }
    
    /**
     * Dodaje element potomny do grupy.
     * 
     * @param element Element menu do dodania (może być OpcjaMenu lub GrupaMenu)
     */
    public void dodaj(ElementMenu element) {
        dzieci.add(element);
    }
    
    /**
     * Usuwa element potomny z grupy.
     * 
     * @param element Element menu do usunięcia
     */
    public void usun(ElementMenu element) {
        dzieci.remove(element);
    }
    
    /**
     * Pobiera element potomny na podstawie indeksu.
     * 
     * @param indeks Indeks szukanego elementu
     * @return Element o podanym indeksie lub null jeśli nie znaleziono
     */
    public ElementMenu pobierzOpcje(String indeks) {
        for (ElementMenu element : dzieci) {
            if (element.getIndeks().equalsIgnoreCase(indeks)) {
                return element;
            }
        }
        return null;
    }
    
    /**
     * Wyświetla grupę menu z odpowiednim wcięciem.
     * Rekurencyjnie wyświetla wszystkie elementy potomne.
     * 
     * @param poziom Poziom zagnieżdżenia dla wcięcia
     */
    @Override
    public void wyswietl(int poziom) {
        String wciecie = generujWciecie(poziom);
        // Dla indeksów literowych używamy nawiasu, dla numerycznych kropki
        String separator = Character.isLetter(indeks.charAt(0)) ? ")" : ".";
        System.out.println(wciecie + indeks + separator + " " + nazwa);
        
        // Wyświetl wszystkie dzieci z większym wcięciem
        for (ElementMenu dziecko : dzieci) {
            dziecko.wyswietl(poziom + 1);
        }
    }
    
    /**
     * Wykonuje akcję grupy - wyświetla podmenu i obsługuje nawigację.
     * Pozwala użytkownikowi wybierać opcje z podmenu lub wrócić do menu nadrzędnego.
     */
    @Override
    public void wykonaj() {
        boolean kontynuuj = true;
        
        while (kontynuuj) {
            // Wyświetl nagłówek podmenu
            System.out.println();
            System.out.println("=== " + nazwa.toUpperCase() + " ===");
            
            // Wyświetl wszystkie opcje w podmenu
            for (ElementMenu dziecko : dzieci) {
                // Dla indeksów literowych używamy nawiasu, dla numerycznych kropki
                String separator = Character.isLetter(dziecko.getIndeks().charAt(0)) ? ")" : ".";
                System.out.println(dziecko.getIndeks() + separator + " " + dziecko.getNazwa());
            }
            System.out.println("0. Powrót");
            System.out.print("> ");
            
            // Odczytaj wybór użytkownika
            String wybor = scanner.nextLine().trim();
            
            if (wybor.equals("0")) {
                // Powrót do menu nadrzędnego
                kontynuuj = false;
            } else {
                // Znajdź i wykonaj wybraną opcję
                ElementMenu wybranyElement = pobierzOpcje(wybor);
                
                if (wybranyElement != null) {
                    wybranyElement.wykonaj();
                } else {
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                }
            }
        }
    }
    
    /**
     * Zwraca listę wszystkich elementów potomnych.
     * 
     * @return Lista dzieci tej grupy
     */
    public List<ElementMenu> getDzieci() {
        return dzieci;
    }
}