package zad04;

/**
 * Konkretny produkt - Złoty papier do pakowania.
 * Implementuje interfejs Produkt jako komponent bazowy we wzorcu Dekorator.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class PapierPakowy implements Produkt {
    
    /** Cena papieru pakowego w PLN */
    private static final double CENA = 10.0;
    
    /** Opis produktu */
    private static final String OPIS = "Złoty papier do pakowania";
    
    /**
     * Zwraca cenę papieru pakowego.
     * 
     * @return cena 10 PLN
     */
    @Override
    public double getCena() {
        return CENA;
    }
    
    /**
     * Zwraca podstawowy opis produktu.
     * 
     * @return "Złoty papier do pakowania"
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