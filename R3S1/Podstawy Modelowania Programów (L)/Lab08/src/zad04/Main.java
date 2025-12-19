package zad04;

import java.util.Scanner;

/**
 * Aplikacja konsolowa demonstrująca działanie kalkulatora ONP
 * z wykorzystaniem wzorca Łańcuch odpowiedzialności (Chain of Responsibility).
 * 
 * Kalkulator obsługuje następujące operacje:
 * - +a;b     → a + b
 * - !a       → a! (silnia)
 * - *a;b;c   → (a + b) * c
 * - @        → modyfikator podwajający wynik następnej operacji
 */
public class Main {
    
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        
        System.out.println("=== Kalkulator ONP dla dzieci ===");
        System.out.println("Wzorzec: Łańcuch odpowiedzialności (Chain of Responsibility)");
        System.out.println();
        System.out.println("Dostępne operacje:");
        System.out.println("  +a;b     - dodawanie (a + b)");
        System.out.println("  !a       - silnia (a!)");
        System.out.println("  *a;b;c   - mnożenie sumy ((a + b) * c)");
        System.out.println("  @        - modyfikator podwajający wynik");
        System.out.println();
        
        // Demonstracja przykładów z zadania
        System.out.println("=== Przykłady z zadania ===");
        calculator.calculateAndPrint("+3;5");    // wynik: 8
        calculator.calculateAndPrint("!5");      // wynik: 120
        calculator.calculateAndPrint("*2;3;4");  // wynik: 20 (bo (2+3)*4 = 20)
        calculator.calculateAndPrint("@+3;5");   // wynik: 16 (bo (3+5)*2 = 16)
        calculator.calculateAndPrint("@@!3");    // wynik: 24 (bo 3! * 2 * 2 = 6 * 4 = 24)
        
        System.out.println();
        System.out.println("=== Dodatkowe przykłady ===");
        calculator.calculateAndPrint("!0");      // wynik: 1 (0! = 1)
        calculator.calculateAndPrint("!1");      // wynik: 1 (1! = 1)
        calculator.calculateAndPrint("+10;20");  // wynik: 30
        calculator.calculateAndPrint("*1;2;3");  // wynik: 9 (bo (1+2)*3 = 9)
        calculator.calculateAndPrint("@*1;2;3"); // wynik: 18 (bo (1+2)*3*2 = 18)
        calculator.calculateAndPrint("@@@!2");   // wynik: 16 (bo 2! * 2 * 2 * 2 = 2 * 8 = 16)
        
        System.out.println();
        System.out.println("=== Tryb interaktywny ===");
        System.out.println("Wpisz wyrażenie lub 'exit' aby zakończyć:");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Do widzenia!");
                break;
            }
            
            if (input.isEmpty()) {
                continue;
            }
            
            calculator.calculateAndPrint(input);
        }
        
        scanner.close();
    }
}