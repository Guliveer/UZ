package zad02.p3;

/**
 * Klasa reprezentująca argumenty funkcji horror z walidacją parametrów.
 * Rzuca wyjątek gdy brakuje wymaganego parametru.
 */
public class HorrorParameters {
    /** Licznik do operacji dzielenia */
    private Integer dividend;
    
    /** Mianownik do operacji dzielenia */
    private Integer divisor;
    
    /** Wartość do obliczenia pierwiastka kwadratowego */
    private Double valueForSquareRoot;
    
    /** Etykieta wyświetlana przed wynikiem dzielenia */
    private String labelBeforeDivision;
    
    /** Etykieta wyświetlana przed pierwiastkiem */
    private String labelBeforeSquareRoot;
    
    public HorrorParameters(Integer dividend, Integer divisor, Double valueForSquareRoot, 
                           String labelBeforeDivision, String labelBeforeSquareRoot) {
        // Walidacja wszystkich parametrów
        if (dividend == null) {
            throw new MissingParameterException("dividend (dzielna)");
        }
        if (divisor == null) {
            throw new MissingParameterException("divisor (dzielnik)");
        }
        if (valueForSquareRoot == null) {
            throw new MissingParameterException("valueForSquareRoot (wartość do pierwiastka)");
        }
        if (labelBeforeDivision == null) {
            throw new MissingParameterException("labelBeforeDivision (etykieta przed dzieleniem)");
        }
        if (labelBeforeSquareRoot == null) {
            throw new MissingParameterException("labelBeforeSquareRoot (etykieta przed pierwiastkiem)");
        }
        
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