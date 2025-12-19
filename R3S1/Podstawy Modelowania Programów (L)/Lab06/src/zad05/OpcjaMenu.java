package zad05;

/**
 * Klasa reprezentująca liść (Leaf) w wzorcu Kompozyt.
 * Reprezentuje pojedynczą opcję menu, która wykonuje konkretną akcję.
 * 
 * OpcjaMenu nie może zawierać innych elementów - jest końcowym elementem
 * hierarchii menu, który wykonuje przypisaną mu operację.
 */
public class OpcjaMenu extends ElementMenu {
    
    /** Akcja do wykonania po wybraniu tej opcji */
    private Runnable akcja;
    
    /**
     * Konstruktor tworzący opcję menu z przypisaną akcją.
     * 
     * @param nazwa Nazwa opcji wyświetlana w menu
     * @param indeks Indeks używany do wyboru opcji (np. "a", "b")
     * @param akcja Akcja (Runnable) wykonywana po wybraniu opcji
     */
    public OpcjaMenu(String nazwa, String indeks, Runnable akcja) {
        super(nazwa, indeks);
        this.akcja = akcja;
    }
    
    /**
     * Wyświetla opcję menu z odpowiednim wcięciem i formatowaniem.
     * Format: "indeks) nazwa" lub "indeks. nazwa" w zależności od typu indeksu.
     * 
     * @param poziom Poziom zagnieżdżenia dla wcięcia
     */
    @Override
    public void wyswietl(int poziom) {
        String wciecie = generujWciecie(poziom);
        // Dla indeksów literowych używamy nawiasu, dla numerycznych kropki
        String separator = Character.isLetter(indeks.charAt(0)) ? ")" : ".";
        System.out.println(wciecie + indeks + separator + " " + nazwa);
    }
    
    /**
     * Wykonuje akcję przypisaną do tej opcji menu.
     * Jeśli akcja nie została przypisana, wyświetla komunikat o błędzie.
     */
    @Override
    public void wykonaj() {
        if (akcja != null) {
            akcja.run();
        } else {
            System.out.println("Brak przypisanej akcji dla opcji: " + nazwa);
        }
    }
}