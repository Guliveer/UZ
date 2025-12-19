package zad05;

/**
 * Implementacja procesora płatności dla Stripe.
 * Stripe OBSŁUGUJE płatności subskrypcyjne.
 */
public class StripeProcessor implements PaymentProcessor {
    
    private static final String NAME = "Stripe";
    
    @Override
    public boolean supportsSubscription() {
        return true;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("[" + NAME + "] Łączenie z API Stripe...");
        System.out.println("[" + NAME + "] Tworzenie intencji płatności na kwotę: " 
                + String.format("%.2f", amount) + " PLN");
        System.out.println("[" + NAME + "] Płatność potwierdzona przez Stripe!");
        return true;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}