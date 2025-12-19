package zad04;

/**
 * Dekorator - Smycz do pendrive (gratis).
 * Dodaje smycz do pendrive do produktu bez dodatkowych kosztów.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class SmyczPendrive extends DekoratorProduktu {
    
    /** Nazwa dodatku */
    private static final String NAZWA_DODATKU = "Smycz do pendrive";
    
    /**
     * Konstruktor dekoratora smyczy.
     * 
     * @param produkt produkt do udekorowania
     */
    public SmyczPendrive(Produkt produkt) {
        super(produkt);
    }
    
    /**
     * Zwraca cenę produktu.
     * Smycz jest gratisem, więc nie dodaje żadnych kosztów.
     * 
     * @return cena produktu bez zmian
     */
    @Override
    public double getCena() {
        return produkt.getCena();
    }
    
    /**
     * Zwraca opis produktu z dodatkiem smyczy.
     * 
     * @return opis produktu z informacją o smyczy
     */
    @Override
    public String getOpisZDodatkami() {
        return produkt.getOpisZDodatkami() + " + " + NAZWA_DODATKU;
    }
}