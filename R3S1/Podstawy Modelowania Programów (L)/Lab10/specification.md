# Lab10: Credit Card Number Validator - Technical Specification

## 1. Overview

This document provides a comprehensive technical specification for implementing a credit card number validator using Test-Driven Development (TDD) methodology. The validator will use the Luhn algorithm (also known as the "modulus 10" or "mod 10" algorithm) to verify the validity of credit card numbers.

## 2. Algorithm Selection: Luhn Algorithm

### 2.1 Background

The Luhn algorithm is an industry-standard checksum formula used to validate credit card numbers, IMEI numbers, and other identification numbers. It was created by IBM scientist Hans Peter Luhn and is described in U.S. Patent No. 2,950,048.

### 2.2 Algorithm Steps

The Luhn algorithm validates a number by performing the following steps:

1. **Starting from the rightmost digit** (excluding the check digit position), moving left, double every second digit
2. If doubling a digit results in a number greater than 9, subtract 9 from the result (or equivalently, sum the digits: 16 → 1+6 = 7)
3. Sum all the digits (both doubled and non-doubled)
4. If the total modulo 10 equals 0, the number is valid; otherwise, it is invalid

### 2.3 Example Calculation

For card number: `4532015112830366`

```
Original:  4  5  3  2  0  1  5  1  1  2  8  3  0  3  6  6
Position: 16 15 14 13 12 11 10  9  8  7  6  5  4  3  2  1
Double:    -  x  -  x  -  x  -  x  -  x  -  x  -  x  -  x

Step 1 - Double every second digit from right:
           4 10  3  4  0  2  5  2  1  4  8  6  0  6  6  6

Step 2 - Subtract 9 from numbers > 9:
           4  1  3  4  0  2  5  2  1  4  8  6  0  6  6  6

Step 3 - Sum all digits:
           4+1+3+4+0+2+5+2+1+4+8+6+0+6+6+6 = 58

Step 4 - Check if sum % 10 == 0:
           58 % 10 = 8 (NOT 0, so this number is INVALID)
```

Valid example: `4532015112830368`

- Sum = 60
- 60 % 10 = 0 ✓ (VALID)

## 3. Method Signature

### 3.1 Java Method Specification

```java
/**
 * Validates a credit card number using the Luhn algorithm.
 *
 * @param number the credit card number as a String
 * @return true if the card number is valid according to Luhn algorithm, false otherwise
 * @throws IllegalArgumentException if the input is null
 */
public boolean isValid(String number)
```

**Note:** The requirement specifies `bool IsValid(string number)`, but in Java:

- The return type is `boolean` (not `bool`)
- Method names follow camelCase convention (not PascalCase)
- Therefore, the method signature is: `public boolean isValid(String number)`

## 4. Validation Rules

### 4.1 Input Format Requirements

1. **Null Check**: Input must not be null (throw `IllegalArgumentException`)
2. **Empty String**: Empty strings are invalid (return `false`)
3. **Whitespace Handling**: Leading/trailing whitespace should be trimmed; internal spaces should be removed
4. **Digit-Only Validation**: After removing spaces, the string must contain only digits (0-9)
5. **Length Constraints**:
   - Minimum length: 13 digits (Visa can be 13 digits)
   - Maximum length: 19 digits (some cards can be up to 19 digits)
   - Most common: 15-16 digits

### 4.2 Algorithm Implementation Rules

1. Process digits from right to left
2. Double every second digit starting from the second-to-last digit
3. If doubled digit > 9, subtract 9 (equivalent to summing the two digits)
4. Sum all processed digits
5. Valid if sum % 10 == 0

### 4.3 Edge Cases to Handle

| Case                   | Input Example            | Expected Result               | Reason                               |
| ---------------------- | ------------------------ | ----------------------------- | ------------------------------------ |
| Null input             | `null`                   | `IllegalArgumentException`    | Cannot process null                  |
| Empty string           | `""`                     | `false`                       | No digits to validate                |
| Whitespace only        | `"   "`                  | `false`                       | No digits after trimming             |
| Single digit           | `"0"`                    | `false`                       | Too short                            |
| Too short              | `"123456789012"`         | `false`                       | Less than 13 digits                  |
| Too long               | `"12345678901234567890"` | `false`                       | More than 19 digits                  |
| Contains letters       | `"4532-ABCD-1234"`       | `false`                       | Non-numeric characters               |
| Contains special chars | `"4532@1234#5678"`       | `false`                       | Invalid characters                   |
| Spaces in number       | `"4532 0151 1283 0368"`  | Process after removing spaces | Common format                        |
| Hyphens in number      | `"4532-0151-1283-0368"`  | `false`                       | Hyphens not allowed (or remove them) |
| Leading zeros          | `"0000000000000000"`     | Validate normally             | Valid input format                   |
| All same digit         | `"1111111111111111"`     | Validate with Luhn            | May be invalid by Luhn               |

## 5. Test Cases for TDD

### 5.1 Test Structure

Following TDD principles, tests should be written BEFORE implementation. Each test should follow the Arrange-Act-Assert pattern.

### 5.2 Comprehensive Test Cases

#### 5.2.1 Null and Empty Input Tests

```java
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
```

#### 5.2.2 Invalid Format Tests

```java
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
```

#### 5.2.3 Valid Credit Card Numbers

```java
@Test
public void testValidVisa16Digits() {
    assertTrue(validator.isValid("4532015112830368"));
}

@Test
public void testValidVisa13Digits() {
    assertTrue(validator.isValid("4532015112830")); // If valid by Luhn
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
public void testValidWithLeadingZeros() {
    // If this passes Luhn algorithm
    assertTrue(validator.isValid("0000000000000000")); // Depends on Luhn result
}
```

#### 5.2.4 Invalid Credit Card Numbers (Fail Luhn Check)

```java
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
```

#### 5.2.5 Whitespace Handling Tests

```java
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
```

#### 5.2.6 Edge Case Tests

```java
@Test
public void testMinimumValidLength() {
    // Test with 13-digit valid number
    assertTrue(validator.isValid("4532015112830")); // If valid by Luhn
}

@Test
public void testMaximumValidLength() {
    // Test with 19-digit valid number
    assertTrue(validator.isValid("6011000991300009123")); // If valid by Luhn
}

@Test
public void testAllZeros() {
    // Depends on Luhn algorithm result
    boolean result = validator.isValid("0000000000000000");
    // Document expected behavior
}
```

### 5.3 Test Execution Order (TDD Approach)

1. **Red Phase**: Write a failing test
2. **Green Phase**: Write minimal code to make the test pass
3. **Refactor Phase**: Improve code while keeping tests green

**Recommended Test Implementation Order:**

1. Start with null/empty tests (simplest)
2. Add format validation tests (non-digits, length)
3. Implement basic Luhn algorithm with known valid numbers
4. Add edge cases and whitespace handling
5. Test with real credit card test numbers
6. Add comprehensive invalid number tests

## 6. Project Structure

### 6.1 Directory Structure

Following the pattern of existing labs (Lab06, Lab07, Lab08, Lab09):

```
Lab10/
├── .gitignore
├── Lab10.iml
├── specification.md (this document)
├── src/
│   └── creditcard/
│       ├── CreditCardValidator.java
│       ├── CreditCardValidatorTest.java
│       └── Main.java
└── README.md
```

### 6.2 File Descriptions

- **`.gitignore`**: Standard Java ignore patterns (compiled classes, IDE files)
- **`Lab10.iml`**: IntelliJ IDEA module file
- **`specification.md`**: This technical specification document
- **`CreditCardValidator.java`**: Main validator class with `isValid()` method
- **`CreditCardValidatorTest.java`**: JUnit test class with all test cases
- **`Main.java`**: Demo application showing validator usage
- **`README.md`**: Lab instructions and usage guide

## 7. Class Design

### 7.1 CreditCardValidator Class

```java
package creditcard;

/**
 * Validates credit card numbers using the Luhn algorithm.
 *
 * The Luhn algorithm (mod 10 algorithm) is a checksum formula used to validate
 * credit card numbers and other identification numbers.
 *
 * @author [Student Name]
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
        // Implementation to be written following TDD
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes all whitespace characters from the input string.
     *
     * @param input the string to process
     * @return string with all whitespace removed
     */
    private String removeWhitespace(String input) {
        // Helper method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Checks if the string contains only digit characters.
     *
     * @param input the string to check
     * @return true if string contains only digits, false otherwise
     */
    private boolean isNumeric(String input) {
        // Helper method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Validates the length of the card number.
     *
     * @param length the length to validate
     * @return true if length is within valid range, false otherwise
     */
    private boolean isValidLength(int length) {
        // Helper method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Applies the Luhn algorithm to validate the card number.
     *
     * @param cardNumber the card number string (digits only)
     * @return true if Luhn checksum is valid, false otherwise
     */
    private boolean passesLuhnCheck(String cardNumber) {
        // Core algorithm implementation
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```

### 7.2 CreditCardValidatorTest Class

```java
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
 * @author [Student Name]
 * @version 1.0
 */
public class CreditCardValidatorTest {

    private CreditCardValidator validator;

    @Before
    public void setUp() {
        validator = new CreditCardValidator();
    }

    // Test methods to be implemented following the test cases in Section 5.2
}
```

### 7.3 Main Class (Demo Application)

```java
package creditcard;

import java.util.Scanner;

/**
 * Demo application for the Credit Card Validator.
 *
 * Provides an interactive console interface for testing credit card validation.
 *
 * @author [Student Name]
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        CreditCardValidator validator = new CreditCardValidator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CREDIT CARD VALIDATOR ===");
        System.out.println("Using Luhn Algorithm");
        System.out.println();

        boolean continueRunning = true;

        while (continueRunning) {
            System.out.print("Enter credit card number (or 'quit' to exit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
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
```

## 8. Implementation Guidelines

### 8.1 TDD Workflow

1. **Write Test First**: Start with the simplest test case
2. **Run Test**: Verify it fails (Red phase)
3. **Write Minimal Code**: Make the test pass with simplest implementation
4. **Run Test**: Verify it passes (Green phase)
5. **Refactor**: Improve code quality without breaking tests
6. **Repeat**: Move to next test case

### 8.2 Recommended Implementation Order

1. **Phase 1**: Null and empty validation

   - Handle null input (throw exception)
   - Handle empty string (return false)

2. **Phase 2**: Format validation

   - Implement whitespace removal
   - Validate numeric characters only
   - Validate length constraints

3. **Phase 3**: Luhn algorithm core

   - Implement basic Luhn checksum calculation
   - Test with known valid numbers

4. **Phase 4**: Edge cases

   - Handle all zeros
   - Handle minimum/maximum lengths
   - Test with various card types

5. **Phase 5**: Refinement
   - Optimize performance
   - Improve code readability
   - Add comprehensive documentation

### 8.3 Code Quality Standards

- **Documentation**: All public methods must have Javadoc comments
- **Naming**: Use descriptive variable and method names
- **Single Responsibility**: Each method should have one clear purpose
- **DRY Principle**: Don't repeat yourself - extract common logic
- **Error Handling**: Provide clear error messages
- **Test Coverage**: Aim for 100% code coverage

## 9. Testing Framework Setup

### 9.1 JUnit 4 Dependency

For IntelliJ IDEA projects, JUnit 4 is typically included. If needed, add to project:

**Lab10.iml** should include:

```xml
<orderEntry type="module-library">
  <library name="JUnit4">
    <CLASSES>
      <root url="jar://$APPLICATION_HOME_DIR$/lib/junit-4.12.jar!/" />
      <root url="jar://$APPLICATION_HOME_DIR$/lib/hamcrest-core-1.3.jar!/" />
    </CLASSES>
  </library>
</orderEntry>
```

### 9.2 Running Tests

**Command Line:**

```bash
# Compile
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar src/creditcard/*.java

# Run tests
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore creditcard.CreditCardValidatorTest
```

**IntelliJ IDEA:**

- Right-click on test class → Run 'CreditCardValidatorTest'
- Or use keyboard shortcut: Ctrl+Shift+F10 (Windows/Linux) or Ctrl+Shift+R (Mac)

## 10. Known Valid Test Credit Card Numbers

For testing purposes, here are valid credit card numbers that pass the Luhn algorithm:

| Card Type        | Number           | Length |
| ---------------- | ---------------- | ------ |
| Visa             | 4532015112830368 | 16     |
| Visa             | 4556737586899855 | 16     |
| Visa             | 4532015112830    | 13     |
| Mastercard       | 5425233430109903 | 16     |
| Mastercard       | 2221000010000015 | 16     |
| American Express | 374245455400126  | 15     |
| American Express | 378282246310005  | 15     |
| Discover         | 6011000991300009 | 16     |
| Discover         | 6011111111111117 | 16     |

**Note**: These are test numbers only and are not associated with real accounts.

## 11. Acceptance Criteria

The implementation is considered complete when:

1. ✓ All test cases pass (100% pass rate)
2. ✓ Code coverage is at least 90%
3. ✓ All public methods have Javadoc documentation
4. ✓ Code follows Java naming conventions
5. ✓ No compiler warnings
6. ✓ Main demo application works correctly
7. ✓ Edge cases are handled appropriately
8. ✓ Luhn algorithm is correctly implemented
9. ✓ TDD approach was followed (tests written first)
10. ✓ Code is clean, readable, and maintainable

## 12. Deliverables

1. **Source Code**:

   - `CreditCardValidator.java` - Fully implemented validator
   - `CreditCardValidatorTest.java` - Complete test suite
   - `Main.java` - Working demo application

2. **Documentation**:

   - This specification document
   - README.md with usage instructions
   - Javadoc comments in all classes

3. **Project Files**:
   - Lab10.iml (IntelliJ module file)
   - .gitignore (version control exclusions)

## 13. References

1. **Luhn Algorithm**:

   - Wikipedia: https://en.wikipedia.org/wiki/Luhn_algorithm
   - U.S. Patent 2,950,048

2. **Credit Card Number Formats**:

   - ISO/IEC 7812 - Identification cards standard

3. **Test-Driven Development**:

   - Kent Beck - "Test Driven Development: By Example"
   - Martin Fowler - Refactoring principles

4. **Java Testing**:
   - JUnit 4 Documentation: https://junit.org/junit4/

## 14. Appendix: Luhn Algorithm Pseudocode

```
function isValidLuhn(cardNumber):
    // Remove all whitespace
    cardNumber = removeWhitespace(cardNumber)

    // Validate format
    if not isNumeric(cardNumber):
        return false

    if length(cardNumber) < 13 or length(cardNumber) > 19:
        return false

    // Apply Luhn algorithm
    sum = 0
    isSecondDigit = false

    // Traverse from right to left
    for i from length(cardNumber) - 1 down to 0:
        digit = parseInt(cardNumber[i])

        if isSecondDigit:
            digit = digit * 2
            if digit > 9:
                digit = digit - 9

        sum = sum + digit
        isSecondDigit = not isSecondDigit

    return (sum % 10 == 0)
```

## 15. Revision History

| Version | Date       | Author         | Changes                       |
| ------- | ---------- | -------------- | ----------------------------- |
| 1.0     | 2026-01-16 | Architect Mode | Initial specification created |

---

**End of Technical Specification**
