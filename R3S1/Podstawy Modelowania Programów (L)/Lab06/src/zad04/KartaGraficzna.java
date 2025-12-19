package zad04;

/**
 * Konkretny produkt - Karta graficzna Nvidia Riva 128.
 * Implementuje interfejs Produkt jako komponent bazowy we wzorcu Dekorator.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class KartaGraficzna implements Produkt {
    
    /** Cena karty graficznej w PLN */
    private static final double CENA = 1500.0;
    
    /** Opis produktu */
    private static final String OPIS = "Karta graficzna Nvidia Riva 128";
    
    /**
     * Zwraca cenę karty graficznej.
     * 
     * @return cena 1500 PLN
     */
    @Override
    public double getCena() {
        return CENA;
    }
    
    /**
     * Zwraca podstawowy opis produktu.
     * 
     * @return "Karta graficzna Nvidia Riva 128"
     */
    @Override
    public String getOpis() {
        return OPIS;
    }
    
    /**
     * Zwraca opis produktu z dodatkami.
     * Dla produktu bazowego jest taki sam jak podstawowy opis.
     * 
     * @return opis produktu
     */
    @Override
    public String getOpisZDodatkami() {
        return OPIS;
    }
}