package zad05;

/**
 * Rozszerzona abstrakcja we wzorcu Most (Bridge Pattern).
 * Reprezentuje płatność subskrypcyjną (wielokrotną).
 * Płatność subskrypcyjna może być wykonana tylko przez procesory obsługujące subskrypcje.
 */
public class SubscriptionPayment extends Payment {
    
    private static final String PAYMENT_TYPE_NAME = "Płatność subskrypcyjna";
    private final int months;
    
    /**
     * Konstruktor płatności subskrypcyjnej.
     * @param processor procesor płatności (implementacja)
     * @param monthlyAmount miesięczna kwota płatności
     * @param months liczba miesięcy subskrypcji
     */
    public SubscriptionPayment(PaymentProcessor processor, double monthlyAmount, int months) {
        super(processor, monthlyAmount);
        this.months = months;
    }
    
    @Override
    public boolean execute() {
        if (!canExecute()) {
            System.out.println("\n[BŁĄD] Procesor " + processor.getName() 
                    + " nie obsługuje płatności subskrypcyjnych!");
            return false;
        }
        
        System.out.println("\n=== " + PAYMENT_TYPE_NAME.toUpperCase() + " ===");
        System.out.println("Kwota miesięczna: " + String.format("%.2f", amount) + " PLN");
        System.out.println("Liczba miesięcy: " + months);
        System.out.println("Łączna kwota: " + String.format("%.2f", getTotalAmount()) + " PLN");
        System.out.println("Procesor: " + processor.getName());
        System.out.println();
        
        System.out.println("Konfigurowanie płatności cyklicznej...");
        return processor.processPayment(amount);
    }
    
    @Override
    public boolean canExecute() {
        // Płatność subskrypcyjna może być wykonana tylko przez procesory obsługujące subskrypcje
        return processor.supportsSubscription();
    }
    
    @Override
    public String getPaymentTypeName() {
        return PAYMENT_TYPE_NAME;
    }
    
    /**
     * Zwraca liczbę miesięcy subskrypcji.
     * @return liczba miesięcy
     */
    public int getMonths() {
        return months;
    }
    
    /**
     * Zwraca łączną kwotę subskrypcji.
     * @return łączna kwota (miesięczna kwota * liczba miesięcy)
     */
    public double getTotalAmount() {
        return amount * months;
    }
}