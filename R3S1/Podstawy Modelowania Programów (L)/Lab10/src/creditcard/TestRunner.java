package creditcard;

/**
 * Simple test runner to verify CreditCardValidator implementation.
 * This runs all test cases without requiring JUnit.
 *
 * @author Lab10 TDD Implementation
 * @version 1.0
 */
public class TestRunner {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        CreditCardValidator validator = new CreditCardValidator();

        System.out.println("=== CREDIT CARD VALIDATOR TEST SUITE ===\n");

        // NULL AND EMPTY INPUT TESTS
        System.out.println("--- NULL AND EMPTY INPUT TESTS ---");
        testNullInput(validator);
        testEmptyString(validator);
        testWhitespaceOnly(validator);

        // INVALID FORMAT TESTS
        System.out.println("\n--- INVALID FORMAT TESTS ---");
        testContainsLetters(validator);
        testContainsSpecialCharacters(validator);
        testTooShort(validator);
        testTooLong(validator);
        testSingleDigit(validator);
        testContainsHyphens(validator);

        // VALID CREDIT CARD NUMBERS
        System.out.println("\n--- VALID CREDIT CARD NUMBERS ---");
        testValidVisa16Digits(validator);
        testValidVisa13Digits(validator);
        testValidMastercard(validator);
        testValidAmex(validator);
        testValidDiscover(validator);
        testValidVisa16Digits2(validator);
        testValidMastercard2(validator);
        testValidAmex2(validator);
        testValidDiscover2(validator);

        // INVALID CREDIT CARD NUMBERS (FAIL LUHN CHECK)
        System.out.println("\n--- INVALID LUHN CHECKSUM TESTS ---");
        testInvalidLuhnChecksum(validator);
        testInvalidAllOnes(validator);
        testInvalidAllSameDigit(validator);
        testInvalidSequential(validator);

        // WHITESPACE HANDLING TESTS
        System.out.println("\n--- WHITESPACE HANDLING TESTS ---");
        testValidNumberWithSpaces(validator);
        testValidNumberWithLeadingTrailingSpaces(validator);
        testValidNumberWithMultipleSpaces(validator);
        testValidNumberWithMixedSpacing(validator);

        // EDGE CASE TESTS
        System.out.println("\n--- EDGE CASE TESTS ---");
        testMinimumValidLength(validator);
        testMaximumValidLength(validator);
        testAllZeros(validator);
        testMinimumLengthInvalid(validator);
        testMaximumLengthInvalid(validator);
        testMixedValidInvalidChars(validator);

        // SUMMARY
        System.out.println("\n=== TEST SUMMARY ===");
        System.out.println("Total Tests:  " + totalTests);
        System.out.println("Passed:       " + passedTests + " ✓");
        System.out.println("Failed:       " + failedTests + " ✗");
        System.out.println("Success Rate: " + (totalTests > 0 ? (passedTests * 100 / totalTests) : 0) + "%");

        if (failedTests == 0) {
            System.out.println("\n🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("\n⚠️  SOME TESTS FAILED ⚠️");
        }
    }

    // Helper methods
    private static void assertTrue(String testName, boolean condition) {
        totalTests++;
        if (condition) {
            System.out.println("✓ " + testName);
            passedTests++;
        } else {
            System.out.println("✗ " + testName + " (expected true, got false)");
            failedTests++;
        }
    }

    private static void assertFalse(String testName, boolean condition) {
        totalTests++;
        if (!condition) {
            System.out.println("✓ " + testName);
            passedTests++;
        } else {
            System.out.println("✗ " + testName + " (expected false, got true)");
            failedTests++;
        }
    }

    private static void assertThrows(String testName, Runnable action) {
        totalTests++;
        try {
            action.run();
            System.out.println("✗ " + testName + " (expected exception, but none thrown)");
            failedTests++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ " + testName);
            passedTests++;
        }
    }

    // Test methods
    private static void testNullInput(CreditCardValidator validator) {
        assertThrows("testNullInput", () -> validator.isValid(null));
    }

    private static void testEmptyString(CreditCardValidator validator) {
        assertFalse("testEmptyString", validator.isValid(""));
    }

    private static void testWhitespaceOnly(CreditCardValidator validator) {
        assertFalse("testWhitespaceOnly", validator.isValid("   "));
    }

    private static void testContainsLetters(CreditCardValidator validator) {
        assertFalse("testContainsLetters", validator.isValid("4532ABCD12345678"));
    }

    private static void testContainsSpecialCharacters(CreditCardValidator validator) {
        assertFalse("testContainsSpecialCharacters", validator.isValid("4532@1234#5678"));
    }

    private static void testTooShort(CreditCardValidator validator) {
        assertFalse("testTooShort", validator.isValid("123456789012"));
    }

    private static void testTooLong(CreditCardValidator validator) {
        assertFalse("testTooLong", validator.isValid("12345678901234567890"));
    }

    private static void testSingleDigit(CreditCardValidator validator) {
        assertFalse("testSingleDigit", validator.isValid("5"));
    }

    private static void testContainsHyphens(CreditCardValidator validator) {
        assertFalse("testContainsHyphens", validator.isValid("4532-0151-1283-0368"));
    }

    private static void testValidVisa16Digits(CreditCardValidator validator) {
        assertTrue("testValidVisa16Digits", validator.isValid("4532015112830368"));
    }

    private static void testValidVisa13Digits(CreditCardValidator validator) {
        assertTrue("testValidVisa13Digits", validator.isValid("4532015112830"));
    }

    private static void testValidMastercard(CreditCardValidator validator) {
        assertTrue("testValidMastercard", validator.isValid("5425233430109903"));
    }

    private static void testValidAmex(CreditCardValidator validator) {
        assertTrue("testValidAmex", validator.isValid("374245455400126"));
    }

    private static void testValidDiscover(CreditCardValidator validator) {
        assertTrue("testValidDiscover", validator.isValid("6011000991300009"));
    }

    private static void testValidVisa16Digits2(CreditCardValidator validator) {
        assertTrue("testValidVisa16Digits2", validator.isValid("4556737586899855"));
    }

    private static void testValidMastercard2(CreditCardValidator validator) {
        assertTrue("testValidMastercard2", validator.isValid("2221000010000015"));
    }

    private static void testValidAmex2(CreditCardValidator validator) {
        assertTrue("testValidAmex2", validator.isValid("378282246310005"));
    }

    private static void testValidDiscover2(CreditCardValidator validator) {
        assertTrue("testValidDiscover2", validator.isValid("6011111111111117"));
    }

    private static void testInvalidLuhnChecksum(CreditCardValidator validator) {
        assertFalse("testInvalidLuhnChecksum", validator.isValid("4532015112830366"));
    }

    private static void testInvalidAllOnes(CreditCardValidator validator) {
        assertFalse("testInvalidAllOnes", validator.isValid("1111111111111111"));
    }

    private static void testInvalidAllSameDigit(CreditCardValidator validator) {
        assertFalse("testInvalidAllSameDigit", validator.isValid("5555555555555555"));
    }

    private static void testInvalidSequential(CreditCardValidator validator) {
        assertFalse("testInvalidSequential", validator.isValid("1234567890123456"));
    }

    private static void testValidNumberWithSpaces(CreditCardValidator validator) {
        assertTrue("testValidNumberWithSpaces", validator.isValid("4532 0151 1283 0368"));
    }

    private static void testValidNumberWithLeadingTrailingSpaces(CreditCardValidator validator) {
        assertTrue("testValidNumberWithLeadingTrailingSpaces", validator.isValid("  4532015112830368  "));
    }

    private static void testValidNumberWithMultipleSpaces(CreditCardValidator validator) {
        assertTrue("testValidNumberWithMultipleSpaces", validator.isValid("4532  0151  1283  0368"));
    }

    private static void testValidNumberWithMixedSpacing(CreditCardValidator validator) {
        assertTrue("testValidNumberWithMixedSpacing", validator.isValid(" 5425 233430109903 "));
    }

    private static void testMinimumValidLength(CreditCardValidator validator) {
        assertTrue("testMinimumValidLength", validator.isValid("4532015112830"));
    }

    private static void testMaximumValidLength(CreditCardValidator validator) {
        assertTrue("testMaximumValidLength", validator.isValid("6011000991300009123"));
    }

    private static void testAllZeros(CreditCardValidator validator) {
        assertTrue("testAllZeros", validator.isValid("0000000000000000"));
    }

    private static void testMinimumLengthInvalid(CreditCardValidator validator) {
        assertFalse("testMinimumLengthInvalid", validator.isValid("453201511283"));
    }

    private static void testMaximumLengthInvalid(CreditCardValidator validator) {
        assertFalse("testMaximumLengthInvalid", validator.isValid("45320151128303681234"));
    }

    private static void testMixedValidInvalidChars(CreditCardValidator validator) {
        assertFalse("testMixedValidInvalidChars", validator.isValid("4532 0151 ABCD 0368"));
    }
}
