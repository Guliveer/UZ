class TestDebug {
    public static void main(String[] args) {
        // Test the problematic numbers
        String[] testNumbers = {
            "4532015112830368",  // Should be valid
            "4532015112830366",  // Should be invalid
            "4532 0151 1283 0368", // Should be valid (with spaces)
            "6011000991300009123" // 19 digits - should be valid
        };
        
        for (String num : testNumbers) {
            System.out.println("\nTesting: " + num);
            String cleaned = num.replaceAll("\\s+", "");
            System.out.println("Cleaned: " + cleaned + " (length: " + cleaned.length() + ")");
            
            int sum = 0;
            boolean isSecond = false;
            
            for (int i = cleaned.length() - 1; i >= 0; i--) {
                int digit = Character.getNumericValue(cleaned.charAt(i));
                System.out.print("Pos " + i + ": " + digit);
                
                if (isSecond) {
                    digit = digit * 2;
                    System.out.print(" *2=" + digit);
                    if (digit > 9) {
                        digit = digit - 9;
                        System.out.print(" -9=" + digit);
                    }
                }
                
                sum += digit;
                System.out.println(" (sum=" + sum + ")");
                isSecond = !isSecond;
            }
            
            System.out.println("Final sum: " + sum);
            System.out.println("Valid: " + (sum % 10 == 0));
        }
    }
}
