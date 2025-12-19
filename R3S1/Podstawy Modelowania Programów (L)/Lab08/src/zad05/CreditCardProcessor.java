package zad05;

/**
 * Implementacja procesora płatności dla karty kredytowej.
 * Karta kredytowa OBSŁUGUJE płatności subskrypcyjne.
 */
public class CreditCardProcessor implements PaymentProcessor {
    
    private static final String NAME = "Karta kredytowa";
    
    @Override
    public boolean supportsSubscription() {
        return true;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("[" + NAME + "] Autoryzacja karty kredytowej...");
        System.out.println("[" + NAME + "] Obciążanie karty kwotą: " 
                + String.format("%.2f", amount) + " PLN");
        System.out.println("[" + NAME + "] Transakcja zatwierdzona!");
        return true;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}