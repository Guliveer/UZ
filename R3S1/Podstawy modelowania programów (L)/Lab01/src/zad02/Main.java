package zad02;

import java.util.Scanner;

/**
 * Main application class for managing mixed fractions.
 */
public class Main {
    
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== MIXED FRACTION CALCULATOR ===");
            System.out.println("Enter mixed fraction data:");
            System.out.println("Format: [whole part] [numerator]/[denominator]");

            System.out.print("Enter whole part (0 if none): ");
            int wholePart = scanner.nextInt();

            System.out.print("Enter numerator of fractional part: ");
            int numerator = scanner.nextInt();

            System.out.print("Enter denominator of fractional part: ");
            int denominator = scanner.nextInt();

            Fraction fraction = new Fraction(wholePart, numerator, denominator);

            System.out.println("\nFraction information:");
            System.out.println("Fraction: " + fraction);
            System.out.println("Decimal value: " + String.format("%.6f", fraction.doubleValue()));
            System.out.println("Is zero: " + (fraction.isZero() ? "YES" : "NO"));
            System.out.println("Is integer: " + (fraction.isInteger() ? "YES" : "NO"));

            // Conversions
            System.out.println("\nConversions:");
            System.out.println("Mixed form: " + fraction.toMixed());
            System.out.println("Improper form: " + fraction.toImproper());

            // Calculate reciprocal
            try {
                Fraction reciprocal = fraction.getReciprocal();
                System.out.println("\nFraction reciprocal:");
                System.out.println("Reciprocal: " + reciprocal);
                System.out.println("Decimal value: " + String.format("%.6f", reciprocal.doubleValue()));
            } catch (IllegalArgumentException e) {
                System.out.println("\nCannot calculate reciprocal: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
