package zad04;

/**
 * Dekorator - Maskotka sklepu (gratis).
 * Dodaje maskotkę sklepu do produktu bez dodatkowych kosztów.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public class MaskotkaSklep extends DekoratorProduktu {
    
    /** Nazwa dodatku */
    private static final String NAZWA_DODATKU = "Maskotka sklepu";
    
    /**
     * Konstruktor dekoratora maskotki.
     * 
     * @param produkt produkt do udekorowania
     */
    public MaskotkaSklep(Produkt produkt) {
        super(produkt);
    }
    
    /**
     * Zwraca cenę produktu.
     * Maskotka jest gratisem, więc nie dodaje żadnych kosztów.
     * 
     * @return cena produktu bez zmian
     */
    @Override
    public double getCena() {
        return produkt.getCena();
    }
    
    /**
     * Zwraca opis produktu z dodatkiem maskotki.
     * 
     * @return opis produktu z informacją o maskotce
     */
    @Override
    public String getOpisZDodatkami() {
        return produkt.getOpisZDodatkami() + " + " + NAZWA_DODATKU;
    }
}