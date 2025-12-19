package zad05;

import java.util.Scanner;

/**
 * Aplikacja konsolowa demonstrująca wzorzec Most (Bridge Pattern)
 * w kontekście systemu kasowego obsługującego różne typy płatności
 * przez różne kanały płatności.
 * 
 * Wzorzec Most (Bridge Pattern):
 * - Abstrakcja: Payment (typ płatności)
 *   - OneTimePayment (płatność jednorazowa)
 *   - SubscriptionPayment (płatność subskrypcyjna)
 * 
 * - Implementacja: PaymentProcessor (kanał płatności)
 *   - BankTransferProcessor (przelew bankowy - bez subskrypcji)
 *   - CreditCardProcessor (karta kredytowa - z subskrypcją)
 *   - StripeProcessor (Stripe - z subskrypcją)
 *   - PayPalProcessor (PayPal - z subskrypcją)
 * 
 * Zalety wzorca Most:
 * 1. Rozdzielenie abstrakcji od implementacji
 * 2. Możliwość niezależnego rozszerzania obu hierarchii
 * 3. Ukrycie szczegółów implementacji przed klientem
 * 4. Łatwe dodawanie nowych typów płatności i procesorów
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CashRegister cashRegister = new CashRegister();
        
        boolean running = true;
        while (running) {
            cashRegister.startSale();
            
            System.out.print("Czy chcesz przeprowadzić kolejną transakcję? (t/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            running = answer.equals("t") || answer.equals("tak");
            System.out.println();
        }
        
        System.out.println("Dziękujemy za korzystanie z systemu kasowego!");
        System.out.println("Do widzenia!");
        
        cashRegister.close();
        scanner.close();
    }
}