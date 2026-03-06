package creditcard;

/**
 * Validates credit card numbers using the Luhn algorithm.
 *
 * The Luhn algorithm (mod 10 algorithm) is a checksum formula used to validate
 * credit card numbers and other identification numbers.
 *
 * @author Lab10 TDD Implementation
 * @version 1.0
 */
public class CreditCardValidator {

    // Constants
    private static final int MIN_CARD_LENGTH = 13;
    private static final int MAX_CARD_LENGTH = 19;

    /**
     * Validates a credit card number using the Luhn algorithm.
     *
     * @param number the credit card number as a String
     * @return true if the card number is valid, false otherwise
     * @throws IllegalArgumentException if the input is null
     */
    public boolean isValid(String number) {
        // Check for null input
        if (number == null) {
            throw new IllegalArgumentException("Card number cannot be null");
        }

        // Remove whitespace
        String cleanedNumber = removeWhitespace(number);

        // Check if empty after cleaning
        if (cleanedNumber.isEmpty()) {
            return false;
        }

        // Check if numeric
        if (!isNumeric(cleanedNumber)) {
            return false;
        }

        // Check length
        if (!isValidLength(cleanedNumber.length())) {
            return false;
        }

        // Apply Luhn algorithm
        return passesLuhnCheck(cleanedNumber);
    }

    /**
     * Removes all whitespace characters from the input string.
     *
     * @param input the string to process
     * @return string with all whitespace removed
     */
    private String removeWhitespace(String input) {
        return input.replaceAll("\\s+", "");
    }

    /**
     * Checks if the string contains only digit characters.
     *
     * @param input the string to check
     * @return true if string contains only digits, false otherwise
     */
    private boolean isNumeric(String input) {
        if (input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the length of the card number.
     *
     * @param length the length to validate
     * @return true if length is within valid range, false otherwise
     */
    private boolean isValidLength(int length) {
        return length >= MIN_CARD_LENGTH && length <= MAX_CARD_LENGTH;
    }

    /**
     * Applies the Luhn algorithm to validate the card number.
     *
     * Algorithm steps:
     * 1. Starting from the rightmost digit, moving left, double every second digit
     * 2. If doubling results in a number > 9, subtract 9
     * 3. Sum all digits
     * 4. Valid if sum % 10 == 0
     *
     * @param cardNumber the card number string (digits only)
     * @return true if Luhn checksum is valid, false otherwise
     */
    private boolean passesLuhnCheck(String cardNumber) {
        int sum = 0;
        boolean isSecondDigit = false;

        // Traverse from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (isSecondDigit) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }

            sum += digit;
            isSecondDigit = !isSecondDigit;
        }

        return (sum % 10 == 0);
    }
}
