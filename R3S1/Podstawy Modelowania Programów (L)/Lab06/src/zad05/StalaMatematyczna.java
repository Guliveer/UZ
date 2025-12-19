package zad05;

/**
 * Klasa implementująca wyświetlanie stałych matematycznych dla kalkulatora.
 * Implementuje interfejs Runnable, aby mogła być użyta jako akcja w OpcjaMenu.
 * 
 * Wyświetla wartość wybranej stałej matematycznej (Pi, e, √2).
 */
public class StalaMatematyczna implements Runnable {
    
    /** Nazwa stałej matematycznej */
    private String nazwa;
    
    /** Wartość stałej matematycznej */
    private double wartosc;
    
    /**
     * Konstruktor tworzący stałą matematyczną.
     * 
     * @param nazwa Nazwa stałej (np. "Pi", "e", "√2")
     * @param wartosc Wartość numeryczna stałej
     */
    public StalaMatematyczna(String nazwa, double wartosc) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }
    
    /**
     * Wykonuje akcję - wyświetla wartość stałej matematycznej.
     */
    @Override
    public void run() {
        System.out.println();
        System.out.println(nazwa + " = " + wartosc);
    }
    
    /**
     * Zwraca nazwę stałej.
     * 
     * @return Nazwa stałej matematycznej
     */
    public String getNazwa() {
        return nazwa;
    }
    
    /**
     * Zwraca wartość stałej.
     * 
     * @return Wartość numeryczna stałej
     */
    public double getWartosc() {
        return wartosc;
    }
    
    // ==================== FABRYKA STAŁYCH MATEMATYCZNYCH ====================
    
    /**
     * Tworzy stałą matematyczną Pi (π ≈ 3.14159...).
     * 
     * @return Stała matematyczna Pi
     */
    public static StalaMatematyczna pi() {
        return new StalaMatematyczna("Pi", Math.PI);
    }
    
    /**
     * Tworzy stałą matematyczną e (liczba Eulera ≈ 2.71828...).
     * 
     * @return Stała matematyczna e
     */
    public static StalaMatematyczna e() {
        return new StalaMatematyczna("e", Math.E);
    }
    
    /**
     * Tworzy stałą matematyczną √2 (pierwiastek z 2 ≈ 1.41421...).
     * 
     * @return Stała matematyczna √2
     */
    public static StalaMatematyczna pierwiastekZ2() {
        return new StalaMatematyczna("√2 (pierwiastek z 2)", Math.sqrt(2));
    }
}