package zad01;

/**
 * Simple class representing a common fraction.
 */
public class Fraction {
    
    private final int numerator;
    private final int denominator;
    
    /**
     * Creates a new fraction with given values.
     *
     * @param numerator fraction numerator
     * @param denominator fraction denominator
     * @throws IllegalArgumentException if denominator equals zero
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        
        // Sign normalization and reduction
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    
    /**
     * Calculates the greatest common divisor.
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * Returns the fraction numerator.
     */
    public int getNumerator() {
        return numerator;
    }
    
    /**
     * Returns the fraction denominator.
     */
    public int getDenominator() {
        return denominator;
    }
    
    /**
     * Returns the reciprocal of this fraction.
     *
     * @return new fraction that is the reciprocal of this fraction
     * @throws IllegalArgumentException if numerator equals zero
     */
    public Fraction getReciprocal() {
        if (numerator == 0) {
            throw new IllegalArgumentException("Cannot calculate reciprocal for fraction with zero numerator");
        }
        return new Fraction(denominator, numerator);
    }
    
    /**
     * Returns the decimal value of this fraction.
     */
    public double doubleValue() {
        return (double) numerator / denominator;
    }
    
    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        if (numerator == 0) {
            return "0";
        }
        return numerator + "/" + denominator;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }
    
    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
