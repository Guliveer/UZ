package zad02;

/**
 * Simple class representing a mixed fraction.
 */
public class Fraction {
    
    private final int wholePart;
    private final int numerator;
    private final int denominator;
    
    /**
     * Creates a mixed fraction with given values.
     *
     * @param wholePart whole part
     * @param numerator numerator of fractional part
     * @param denominator denominator of fractional part
     * @throws IllegalArgumentException if denominator equals zero
     */
    public Fraction(int wholePart, int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        
        // Sign normalization
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        
        // Fraction reduction
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        
        this.wholePart = wholePart;
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
    
    /**
     * Creates a simple fraction (without whole part).
     */
    public Fraction(int numerator, int denominator) {
        this(0, numerator, denominator);
    }
    
    /**
     * Creates a fraction from decimal value.
     */
    public Fraction(double decimal) {
        this(0, (int)(decimal * 1000), 1000);
    }
    
    /**
     * Creates a fraction representing just a whole number.
     */
    public Fraction(int wholeNumber) {
        this(wholeNumber, 0, 1);
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
     * Returns the whole part of the fraction.
     */
    public int getWholePart() {
        return wholePart;
    }
    
    /**
     * Returns the numerator of the fractional part.
     */
    public int getNumerator() {
        return numerator;
    }
    
    /**
     * Returns the denominator of the fractional part.
     */
    public int getDenominator() {
        return denominator;
    }
    
    /**
     * Checks if the fraction equals zero.
     */
    public boolean isZero() {
        return wholePart == 0 && numerator == 0;
    }
    
    /**
     * Checks if the fraction is an integer.
     */
    public boolean isInteger() {
        return numerator == 0;
    }
    
    /**
     * Returns the reciprocal of the fraction.
     *
     * @return reciprocal of the fraction
     * @throws IllegalArgumentException if fraction equals zero
     */
    public Fraction getReciprocal() {
        if (isZero()) {
            throw new IllegalArgumentException("Cannot calculate reciprocal for fraction equal to zero");
        }
        
        // Convert to improper form
        int improperNumerator = wholePart * denominator + numerator;
        return new Fraction(0, denominator, improperNumerator);
    }
    
    /**
     * Converts the fraction to mixed form.
     */
    public Fraction toMixed() {
        if (wholePart != 0) {
            return this; // Already mixed
        }
        
        int newWholePart = numerator / denominator;
        int newNumerator = numerator % denominator;
        
        return new Fraction(newWholePart, newNumerator, denominator);
    }
    
    /**
     * Converts the fraction to improper form.
     */
    public Fraction toImproper() {
        int improperNumerator = wholePart * denominator + numerator;
        return new Fraction(0, improperNumerator, denominator);
    }
    
    /**
     * Returns the decimal value of the fraction.
     */
    public double doubleValue() {
        return wholePart + (double) numerator / denominator;
    }
    
    @Override
    public String toString() {
        if (isZero()) {
            return "0";
        }
        
        if (isInteger()) {
            return String.valueOf(wholePart);
        }
        
        if (wholePart == 0) {
            return numerator + "/" + denominator;
        }
        
        return wholePart + " " + numerator + "/" + denominator;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Fraction fraction = (Fraction) obj;
        return wholePart == fraction.wholePart &&
               numerator == fraction.numerator &&
               denominator == fraction.denominator;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * wholePart + numerator) + denominator;
    }
    
    /**
     * Static method to create a fraction from two integers.
     */
    public static Fraction of(int numerator, int denominator) {
        return new Fraction(numerator, denominator);
    }
    
    /**
     * Static method to create a mixed fraction.
     */
    public static Fraction of(int wholePart, int numerator, int denominator) {
        return new Fraction(wholePart, numerator, denominator);
    }
    
    /**
     * Static method to parse fraction from string (e.g., "3/4" or "2 1/3").
     */
    public static Fraction parse(String fractionStr) {
        if (fractionStr == null || fractionStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Fraction string cannot be empty");
        }
        
        fractionStr = fractionStr.trim();
        
        // Handle mixed fraction format "2 1/3"
        if (fractionStr.contains(" ")) {
            String[] parts = fractionStr.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid mixed fraction format");
            }
            
            int wholePart = Integer.parseInt(parts[0]);
            String[] fractionParts = parts[1].split("/");
            if (fractionParts.length != 2) {
                throw new IllegalArgumentException("Invalid fraction format");
            }
            
            int numerator = Integer.parseInt(fractionParts[0]);
            int denominator = Integer.parseInt(fractionParts[1]);
            return new Fraction(wholePart, numerator, denominator);
        }
        
        // Handle simple fraction format "3/4"
        if (fractionStr.contains("/")) {
            String[] parts = fractionStr.split("/");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid fraction format");
            }
            
            int numerator = Integer.parseInt(parts[0]);
            int denominator = Integer.parseInt(parts[1]);
            return new Fraction(numerator, denominator);
        }
        
        // Handle whole number
        int wholeNumber = Integer.parseInt(fractionStr);
        return new Fraction(wholeNumber);
    }
}
