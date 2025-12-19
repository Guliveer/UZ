package zad04;

/**
 * Konkretny produkt - Pamięć RAM 1 MB.
 * Implementuje interfejs Produkt jako komponent bazowy we wzorcu Dekorator.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class PamiecRAM implements Produkt {
    
    /** Cena pamięci RAM w PLN */
    private static final double CENA = 1000.0;
    
    /** Opis produktu */
    private static final String OPIS = "Pamięć RAM 1 MB";
    
    /**
     * Zwraca cenę pamięci RAM.
     * 
     * @return cena 1000 PLN
     */
    @Override
    public double getCena() {
        return CENA;
    }
    
    /**
     * Zwraca podstawowy opis produktu.
     * 
     * @return "Pamięć RAM 1 MB"
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