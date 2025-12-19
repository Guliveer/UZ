package zad05;

/**
 * Rozszerzona abstrakcja we wzorcu Most (Bridge Pattern).
 * Reprezentuje płatność jednorazową.
 * Płatność jednorazowa może być wykonana przez każdy procesor płatności.
 */
public class OneTimePayment extends Payment {
    
    private static final String PAYMENT_TYPE_NAME = "Płatność jednorazowa";
    
    /**
     * Konstruktor płatności jednorazowej.
     * @param processor procesor płatności (implementacja)
     * @param amount kwota płatności
     */
    public OneTimePayment(PaymentProcessor processor, double amount) {
        super(processor, amount);
    }
    
    @Override
    public boolean execute() {
        System.out.println("\n=== " + PAYMENT_TYPE_NAME.toUpperCase() + " ===");
        System.out.println("Kwota: " + String.format("%.2f", amount) + " PLN");
        System.out.println("Procesor: " + processor.getName());
        System.out.println();
        
        return processor.processPayment(amount);
    }
    
    @Override
    public boolean canExecute() {
        // Płatność jednorazowa może być wykonana przez każdy procesor
        return true;
    }
    
    @Override
    public String getPaymentTypeName() {
        return PAYMENT_TYPE_NAME;
    }
}