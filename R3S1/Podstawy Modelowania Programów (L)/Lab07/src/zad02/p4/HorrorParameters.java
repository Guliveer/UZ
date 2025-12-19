package zad02.p4;

/**
 * Klasa reprezentująca argumenty funkcji horror z wzorcem Fluent Builder.
 * Rzuca wyjątek gdy brakuje wymaganego parametru podczas budowania.
 */
public class HorrorParameters {
    /** Licznik do operacji dzielenia */
    private final int dividend;
    
    /** Mianownik do operacji dzielenia */
    private final int divisor;
    
    /** Wartość do obliczenia pierwiastka kwadratowego */
    private final double valueForSquareRoot;
    
    /** Etykieta wyświetlana przed wynikiem dzielenia */
    private final String labelBeforeDivision;
    
    /** Etykieta wyświetlana przed pierwiastkiem */
    private final String labelBeforeSquareRoot;
    
    // Prywatny konstruktor - obiekt tworzony tylko przez Builder
    private HorrorParameters(Builder builder) {
        this.dividend = builder.dividend;
        this.divisor = builder.divisor;
        this.valueForSquareRoot = builder.valueForSquareRoot;
        this.labelBeforeDivision = builder.labelBeforeDivision;
        this.labelBeforeSquareRoot = builder.labelBeforeSquareRoot;
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
    
    /**
     * Fluent Builder dla klasy HorrorParameters
     */
    public static class Builder {
        private Integer dividend;
        private Integer divisor;
        private Double valueForSquareRoot;
        private String labelBeforeDivision;
        private String labelBeforeSquareRoot;
        
        public Builder() {
            // Domyślny konstruktor
        }
        
        /**
         * Ustawia dzielną (licznik)
         */
        public Builder withDividend(int dividend) {
            this.dividend = dividend;
            return this;
        }
        
        /**
         * Ustawia dzielnik (mianownik)
         */
        public Builder withDivisor(int divisor) {
            this.divisor = divisor;
            return this;
        }
        
        /**
         * Ustawia wartość do obliczenia pierwiastka kwadratowego
         */
        public Builder withValueForSquareRoot(double valueForSquareRoot) {
            this.valueForSquareRoot = valueForSquareRoot;
            return this;
        }
        
        /**
         * Ustawia etykietę wyświetlaną przed wynikiem dzielenia
         */
        public Builder withLabelBeforeDivision(String labelBeforeDivision) {
            this.labelBeforeDivision = labelBeforeDivision;
            return this;
        }
        
        /**
         * Ustawia etykietę wyświetlaną przed pierwiastkiem
         */
        public Builder withLabelBeforeSquareRoot(String labelBeforeSquareRoot) {
            this.labelBeforeSquareRoot = labelBeforeSquareRoot;
            return this;
        }
        
        /**
         * Buduje obiekt HorrorParameters.
         * Rzuca MissingParameterException jeśli brakuje któregokolwiek parametru.
         */
        public HorrorParameters build() {
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
            
            return new HorrorParameters(this);
        }
    }
}