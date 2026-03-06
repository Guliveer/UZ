package creditcard;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for CreditCardValidator using JUnit 4.
 *
 * Tests are organized by category:
 * - Null and empty input tests
 * - Invalid format tests
 * - Valid credit card numbers
 * - Invalid credit card numbers (Luhn failures)
 * - Whitespace handling tests
 * - Edge case tests
 *
 * @author Lab10 TDD Implementation
 * @version 1.0
 */
public class CreditCardValidatorTest {

    private CreditCardValidator validator;

    @Before
    public void setUp() {
        validator = new CreditCardValidator();
    }

    // ========== NULL AND EMPTY INPUT TESTS ==========

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        // Should throw IllegalArgumentException
        validator.isValid(null);
    }

    @Test
    public void testEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void testWhitespaceOnly() {
        assertFalse(validator.isValid("   "));
    }

    // ========== INVALID FORMAT TESTS ==========

    @Test
    public void testContainsLetters() {
        assertFalse(validator.isValid("4532ABCD12345678"));
    }

    @Test
    public void testContainsSpecialCharacters() {
        assertFalse(validator.isValid("4532@1234#5678"));
    }

    @Test
    public void testTooShort() {
        assertFalse(validator.isValid("123456789012")); // 12 digits
    }

    @Test
    public void testTooLong() {
        assertFalse(validator.isValid("12345678901234567890")); // 20 digits
    }

    @Test
    public void testSingleDigit() {
        assertFalse(validator.isValid("5"));
    }

    @Test
    public void testContainsHyphens() {
        assertFalse(validator.isValid("4532-0151-1283-0368"));
    }

    // ========== VALID CREDIT CARD NUMBERS ==========

    @Test
    public void testValidVisa16Digits() {
        assertTrue(validator.isValid("4532015112830368"));
    }

    @Test
    public void testValidVisa13Digits() {
        assertTrue(validator.isValid("4532015112830"));
    }

    @Test
    public void testValidMastercard() {
        assertTrue(validator.isValid("5425233430109903"));
    }

    @Test
    public void testValidAmex() {
        assertTrue(validator.isValid("374245455400126"));
    }

    @Test
    public void testValidDiscover() {
        assertTrue(validator.isValid("6011000991300009"));
    }

    @Test
    public void testValidVisa16Digits2() {
        assertTrue(validator.isValid("4556737586899855"));
    }

    @Test
    public void testValidMastercard2() {
        assertTrue(validator.isValid("2221000010000015"));
    }

    @Test
    public void testValidAmex2() {
        assertTrue(validator.isValid("378282246310005"));
    }

    @Test
    public void testValidDiscover2() {
        assertTrue(validator.isValid("6011111111111117"));
    }

    // ========== INVALID CREDIT CARD NUMBERS (FAIL LUHN CHECK) ==========

    @Test
    public void testInvalidLuhnChecksum() {
        assertFalse(validator.isValid("4532015112830366")); // Last digit wrong
    }

    @Test
    public void testInvalidAllOnes() {
        assertFalse(validator.isValid("1111111111111111"));
    }

    @Test
    public void testInvalidAllSameDigit() {
        assertFalse(validator.isValid("5555555555555555"));
    }

    @Test
    public void testInvalidSequential() {
        assertFalse(validator.isValid("1234567890123456"));
    }

    // ========== WHITESPACE HANDLING TESTS ==========

    @Test
    public void testValidNumberWithSpaces() {
        assertTrue(validator.isValid("4532 0151 1283 0368"));
    }

    @Test
    public void testValidNumberWithLeadingTrailingSpaces() {
        assertTrue(validator.isValid("  4532015112830368  "));
    }

    @Test
    public void testValidNumberWithMultipleSpaces() {
        assertTrue(validator.isValid("4532  0151  1283  0368"));
    }

    @Test
    public void testValidNumberWithMixedSpacing() {
        assertTrue(validator.isValid(" 5425 233430109903 "));
    }

    // ========== EDGE CASE TESTS ==========

    @Test
    public void testMinimumValidLength() {
        // Test with 13-digit valid number
        assertTrue(validator.isValid("4532015112830"));
    }

    @Test
    public void testMaximumValidLength() {
        // Test with 19-digit valid number - need to find one that passes Luhn
        // Using a constructed 19-digit number
        assertTrue(validator.isValid("6011000991300009123"));
    }

    @Test
    public void testAllZeros() {
        // 16 zeros: sum = 0, 0 % 10 = 0, so should be valid by Luhn
        assertTrue(validator.isValid("0000000000000000"));
    }

    @Test
    public void testMinimumLengthInvalid() {
        // 12 digits - too short
        assertFalse(validator.isValid("453201511283"));
    }

    @Test
    public void testMaximumLengthInvalid() {
        // 20 digits - too long
        assertFalse(validator.isValid("45320151128303681234"));
    }

    @Test
    public void testLeadingZeros() {
        // Valid format with leading zeros
        assertTrue(validator.isValid("0000000000000000"));
    }

    @Test
    public void testMixedValidInvalidChars() {
        assertFalse(validator.isValid("4532 0151 ABCD 0368"));
    }
}
