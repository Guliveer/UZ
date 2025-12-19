package zad02.p2;

/**
 * Klasa reprezentująca argumenty funkcji horror z sensownymi nazwami pól
 */
public class HorrorParameters {
    /** Licznik do operacji dzielenia */
    private int dividend;
    
    /** Mianownik do operacji dzielenia */
    private int divisor;
    
    /** Wartość do obliczenia pierwiastka kwadratowego */
    private double valueForSquareRoot;
    
    /** Etykieta wyświetlana przed wynikiem dzielenia */
    private String labelBeforeDivision;
    
    /** Etykieta wyświetlana przed pierwiastkiem */
    private String labelBeforeSquareRoot;
    
    public HorrorParameters(int dividend, int divisor, double valueForSquareRoot, 
                           String labelBeforeDivision, String labelBeforeSquareRoot) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.valueForSquareRoot = valueForSquareRoot;
        this.labelBeforeDivision = labelBeforeDivision;
        this.labelBeforeSquareRoot = labelBeforeSquareRoot;
    }
    
    public int getDividend() {
        return dividend;
    }
    
    public int getDivisor() {
        return divisor;
    }
    
    public double getValueForSquareRoot() {
        return valueForSquareRoot;
    }
    
    public String getLabelBeforeDivision() {
        return labelBeforeDivision;
    }
    
    public String getLabelBeforeSquareRoot() {
        return labelBeforeSquareRoot;
    }
}