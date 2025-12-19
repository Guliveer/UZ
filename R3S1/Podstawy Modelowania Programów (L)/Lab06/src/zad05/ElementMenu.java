package zad05;

/**
 * Klasa abstrakcyjna reprezentująca element menu w wzorcu Kompozyt.
 * Jest to komponent (Component) w strukturze wzorca.
 * 
 * Wzorzec Kompozyt pozwala traktować pojedyncze obiekty i kompozycje obiektów
 * w jednolity sposób, co jest idealne dla hierarchicznych struktur menu.
 */
public abstract class ElementMenu {
    
    /** Nazwa elementu menu wyświetlana użytkownikowi */
    protected String nazwa;
    
    /** Indeks elementu (np. "1", "a", "b") używany do nawigacji */
    protected String indeks;
    
    /**
     * Konstruktor tworzący element menu.
     * 
     * @param nazwa Nazwa elementu wyświetlana w menu
     * @param indeks Indeks używany do wyboru elementu (np. "1", "a")
     */
    public ElementMenu(String nazwa, String indeks) {
        this.nazwa = nazwa;
        this.indeks = indeks;
    }
    
    /**
     * Wyświetla element menu z odpowiednim wcięciem.
     * Metoda abstrakcyjna - każda podklasa implementuje własny sposób wyświetlania.
     * 
     * @param poziom Poziom zagnieżdżenia (0 = główne menu, 1 = podmenu, itd.)
     */
    public abstract void wyswietl(int poziom);
    
    /**
     * Wykonuje akcję przypisaną do elementu menu.
     * Dla liści (OpcjaMenu) wykonuje konkretną operację.
     * Dla kompozytów (GrupaMenu) wyświetla podmenu.
     */
    public abstract void wykonaj();
    
    /**
     * Zwraca nazwę elementu menu.
     * 
     * @return Nazwa elementu
     */
    public String getNazwa() {
        return nazwa;
    }
    
    /**
     * Zwraca indeks elementu menu.
     * 
     * @return Indeks elementu (np. "1", "a", "b")
     */
    public String getIndeks() {
        return indeks;
    }
    
    /**
     * Generuje wcięcie dla wyświetlania hierarchii menu.
     * 
     * @param poziom Poziom zagnieżdżenia
     * @return String z odpowiednią liczbą spacji
     */
    protected String generujWciecie(int poziom) {
        StringBuilder wciecie = new StringBuilder();
        for (int i = 0; i < poziom; i++) {
            wciecie.append("   ");
        }
        return wciecie.toString();
    }
}