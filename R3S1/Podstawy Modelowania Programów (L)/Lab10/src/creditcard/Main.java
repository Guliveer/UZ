package creditcard;

import java.util.Scanner;

/**
 * Demo application for the Credit Card Validator.
 *
 * Provides an interactive console interface for testing credit card validation
 * using the Luhn algorithm.
 *
 * @author Lab10 TDD Implementation
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        CreditCardValidator validator = new CreditCardValidator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CREDIT CARD VALIDATOR ===");
        System.out.println("Using Luhn Algorithm");
        System.out.println();
        System.out.println("Test numbers you can try:");
        System.out.println("  Valid Visa 16:    4532015112830368");
        System.out.println("  Valid Visa 13:    4532015112830");
        System.out.println("  Valid Mastercard: 5425233430109903");
        System.out.println("  Valid Amex:       374245455400126");
        System.out.println("  Valid Discover:   6011000991300009");
        System.out.println("  Invalid (Luhn):   4532015112830366");
        System.out.println();

        boolean continueRunning = true;

        while (continueRunning) {
            System.out.print("Enter credit card number (or 'quit' to exit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                continueRunning = false;
                continue;
            }

            try {
                boolean isValid = validator.isValid(input);

                if (isValid) {
                    System.out.println("✓ Valid credit card number");
                } else {
                    System.out.println("✗ Invalid credit card number");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("Thank you for using Credit Card Validator!");
        scanner.close();
    }
}
