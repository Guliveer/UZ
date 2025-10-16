package zad01;

import java.util.Scanner;

/**
 * Main application class for managing common fractions.
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== FRACTION CALCULATOR ===");
        
        try {
            System.out.println("Enter fraction data:");
            
            System.out.print("Enter numerator: ");
            int numerator = scanner.nextInt();
            
            System.out.print("Enter denominator: ");
            int denominator = scanner.nextInt();
            
            Fraction fraction = new Fraction(numerator, denominator);
            
            System.out.println("\nFraction information:");
            System.out.println("Fraction: " + fraction);
            System.out.println("Decimal value: " + String.format("%.6f", fraction.doubleValue()));
            
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
        } finally {
            scanner.close();
        }
    }
}
