package zad05;

/**
 * Implementacja procesora płatności dla PayPal.
 * PayPal OBSŁUGUJE płatności subskrypcyjne.
 */
public class PayPalProcessor implements PaymentProcessor {
    
    private static final String NAME = "PayPal";
    
    @Override
    public boolean supportsSubscription() {
        return true;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("[" + NAME + "] Przekierowanie do PayPal...");
        System.out.println("[" + NAME + "] Autoryzacja płatności na kwotę: " 
                + String.format("%.2f", amount) + " PLN");
        System.out.println("[" + NAME + "] Płatność PayPal zakończona sukcesem!");
        return true;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}