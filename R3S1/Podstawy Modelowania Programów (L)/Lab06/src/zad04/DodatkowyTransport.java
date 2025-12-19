package zad04;

/**
 * Dekorator - Dodatkowy transport (+13 PLN).
 * Dodaje dodatkowy koszt transportu do produktu.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class DodatkowyTransport extends DekoratorProduktu {
    
    /** Koszt dodatkowego transportu w PLN */
    private static final double KOSZT_TRANSPORTU = 13.0;
    
    /** Nazwa dodatku */
    private static final String NAZWA_DODATKU = "Dodatkowy transport";
    
    /**
     * Konstruktor dekoratora dodatkowego transportu.
     * 
     * @param produkt produkt do udekorowania
     */
    public DodatkowyTransport(Produkt produkt) {
        super(produkt);
    }
    
    /**
     * Zwraca cenę produktu z dodatkowym kosztem transportu.
     * 
     * @return cena produktu + 13 PLN
     */
    @Override
    public double getCena() {
        return produkt.getCena() + KOSZT_TRANSPORTU;
    }
    
    /**
     * Zwraca opis produktu z informacją o dodatkowym transporcie.
     * 
     * @return opis produktu z informacją o dodatkowym transporcie
     */
    @Override
    public String getOpisZDodatkami() {
        return produkt.getOpisZDodatkami() + " + " + NAZWA_DODATKU;
    }
}