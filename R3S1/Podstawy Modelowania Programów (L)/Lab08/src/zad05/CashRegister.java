package zad05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * System kasowy obsługujący proces sprzedaży produktów.
 * Wykorzystuje wzorzec Most (Bridge Pattern) do obsługi różnych typów płatności
 * przez różne kanały płatności.
 */
public class CashRegister {
    
    private final List<PaymentProcessor> availableProcessors;
    private final Scanner scanner;
    
    /**
     * Konstruktor systemu kasowego.
     */
    public CashRegister() {
        this.availableProcessors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeProcessors();
    }
    
    /**
     * Inicjalizuje dostępne procesory płatności.
     */
    private void initializeProcessors() {
        availableProcessors.add(new BankTransferProcessor());
        availableProcessors.add(new CreditCardProcessor());
        availableProcessors.add(new StripeProcessor());
        availableProcessors.add(new PayPalProcessor());
    }
    
    /**
     * Rozpoczyna proces sprzedaży produktu.
     */
    public void startSale() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       SYSTEM KASOWY - BRIDGE PATTERN   ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        
        // Krok 1: Podanie ceny
        double price = getPrice();
        if (price <= 0) {
            System.out.println("Anulowano transakcję.");
            return;
        }
        
        // Krok 2-4: Wybór procesora i typu płatności (z walidacją)
        boolean paymentSuccessful = false;
        while (!paymentSuccessful) {
            PaymentProcessor processor = selectProcessor();
            if (processor == null) {
                System.out.println("Anulowano transakcję.");
                return;
            }
            
            Payment payment = selectPaymentType(processor, price);
            if (payment == null) {
                System.out.println("Anulowano transakcję.");
                return;
            }
            
            // Sprawdzenie czy płatność jest możliwa
            if (!payment.canExecute()) {
                System.out.println("\n[UWAGA] Wybrany procesor (" + processor.getName() 
                        + ") nie obsługuje " + payment.getPaymentTypeName().toLowerCase() + ".");
                System.out.println("Proszę wybrać inny procesor lub typ płatności.\n");
                continue;
            }
            
            // Wykonanie płatności
            paymentSuccessful = payment.execute();
            
            if (paymentSuccessful) {
                System.out.println("\n════════════════════════════════════════");
                System.out.println("✓ TRANSAKCJA ZAKOŃCZONA POMYŚLNIE!");
                System.out.println("════════════════════════════════════════\n");
            } else {
                System.out.println("\n[BŁĄD] Płatność nie powiodła się. Spróbuj ponownie.\n");
            }
        }
    }
    
    /**
     * Pobiera cenę od sprzedawcy.
     * @return cena produktu lub -1 jeśli anulowano
     */
    private double getPrice() {
        System.out.print("Podaj łączną cenę zakupu (PLN): ");
        try {
            String input = scanner.nextLine().trim().replace(",", ".");
            double price = Double.parseDouble(input);
            if (price <= 0) {
                System.out.println("Cena musi być większa od zera.");
                return -1;
            }
            return price;
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format ceny.");
            return -1;
        }
    }
    
    /**
     * Pozwala wybrać procesor płatności.
     * @return wybrany procesor lub null jeśli anulowano
     */
    private PaymentProcessor selectProcessor() {
        System.out.println("\nDostępne procesory płatności:");
        System.out.println("────────────────────────────────────────");
        
        for (int i = 0; i < availableProcessors.size(); i++) {
            PaymentProcessor processor = availableProcessors.get(i);
            String subscriptionInfo = processor.supportsSubscription() 
                    ? " [obsługuje subskrypcje]" 
                    : " [tylko płatności jednorazowe]";
            System.out.println((i + 1) + ". " + processor.getName() + subscriptionInfo);
        }
        System.out.println("0. Anuluj");
        System.out.println("────────────────────────────────────────");
        
        System.out.print("Wybierz procesor płatności: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) {
                return null;
            }
            if (choice < 1 || choice > availableProcessors.size()) {
                System.out.println("Nieprawidłowy wybór.");
                return selectProcessor();
            }
            return availableProcessors.get(choice - 1);
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format.");
            return selectProcessor();
        }
    }
    
    /**
     * Pozwala wybrać typ płatności.
     * @param processor wybrany procesor płatności
     * @param price cena produktu
     * @return obiekt płatności lub null jeśli anulowano
     */
    private Payment selectPaymentType(PaymentProcessor processor, double price) {
        System.out.println("\nTypy płatności:");
        System.out.println("────────────────────────────────────────");
        System.out.println("1. Płatność jednorazowa");
        System.out.println("2. Płatność subskrypcyjna (cykliczna)");
        System.out.println("0. Anuluj");
        System.out.println("────────────────────────────────────────");
        
        System.out.print("Wybierz typ płatności: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 0:
                    return null;
                case 1:
                    return new OneTimePayment(processor, price);
                case 2:
                    return createSubscriptionPayment(processor, price);
                default:
                    System.out.println("Nieprawidłowy wybór.");
                    return selectPaymentType(processor, price);
            }
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format.");
            return selectPaymentType(processor, price);
        }
    }
    
    /**
     * Tworzy płatność subskrypcyjną z podaniem liczby miesięcy.
     * @param processor wybrany procesor płatności
     * @param monthlyPrice miesięczna cena
     * @return obiekt płatności subskrypcyjnej
     */
    private Payment createSubscriptionPayment(PaymentProcessor processor, double monthlyPrice) {
        System.out.print("Podaj liczbę miesięcy subskrypcji: ");
        try {
            int months = Integer.parseInt(scanner.nextLine().trim());
            if (months <= 0) {
                System.out.println("Liczba miesięcy musi być większa od zera.");
                return createSubscriptionPayment(processor, monthlyPrice);
            }
            return new SubscriptionPayment(processor, monthlyPrice, months);
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format.");
            return createSubscriptionPayment(processor, monthlyPrice);
        }
    }
    
    /**
     * Zamyka zasoby systemu kasowego.
     */
    public void close() {
        scanner.close();
    }
}