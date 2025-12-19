package zad04;

/**
 * Interfejs Produkt - komponent bazowy we wzorcu Dekorator.
 * Definiuje wspólne operacje dla wszystkich produktów i dekoratorów.
 * 
 * @author Laboratorium 6 - Wzorce projektowe
 */
public interface Produkt {
    
    /**
     * Zwraca cenę produktu (wraz z ewentualnymi dodatkami).
     * 
     * @return cena produktu w PLN
     */
    double getCena();
    
    /**
     * Zwraca podstawowy opis produktu (bez dodatków).
     * 
     * @return nazwa produktu
     */
    String getOpis();
    
    /**
     * Zwraca pełny opis produktu wraz z wszystkimi dodatkami.
     * 
     * @return opis produktu z dodatkami
     */
    String getOpisZDodatkami();
}