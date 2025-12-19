package zad04;

/**
 * Abstrakcyjna klasa bazowa dla wszystkich dekoratorów produktów.
 * Implementuje interfejs Produkt i przechowuje referencję do dekorowanego obiektu.
 * 
 * Wzorzec Dekorator pozwala dynamicznie dodawać nowe funkcjonalności
 * do obiektów bez modyfikacji ich kodu źródłowego.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public abstract class DekoratorProduktu implements Produkt {
    
    /** Referencja do dekorowanego produktu */
    protected Produkt produkt;
    
    /**
     * Konstruktor dekoratora.
     * 
     * @param produkt produkt do udekorowania
     */
    public DekoratorProduktu(Produkt produkt) {
        this.produkt = produkt;
    }
    
    /**
     * Zwraca cenę produktu.
     * Domyślnie deleguje do dekorowanego produktu.
     * Klasy pochodne mogą nadpisać tę metodę, aby dodać dodatkowe koszty.
     * 
     * @return cena produktu
     */
    @Override
    public double getCena() {
        return produkt.getCena();
    }
    
    /**
     * Zwraca podstawowy opis produktu (bez dodatków).
     * Deleguje do dekorowanego produktu.
     * 
     * @return podstawowy opis produktu
     */
    @Override
    public String getOpis() {
        return produkt.getOpis();
    }
    
    /**
     * Zwraca opis produktu z dodatkami.
     * Klasy pochodne powinny nadpisać tę metodę, aby dodać informacje o dodatkach.
     * 
     * @return opis produktu z dodatkami
     */
    @Override
    public String getOpisZDodatkami() {
        return produkt.getOpisZDodatkami();
    }
}