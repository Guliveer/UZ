package zad05;

/**
 * Implementacja procesora płatności dla przelewu bankowego.
 * Przelew bankowy NIE obsługuje płatności subskrypcyjnych.
 */
public class BankTransferProcessor implements PaymentProcessor {
    
    private static final String NAME = "Przelew bankowy";
    
    @Override
    public boolean supportsSubscription() {
        return false;
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("[" + NAME + "] Przetwarzanie przelewu bankowego na kwotę: " 
                + String.format("%.2f", amount) + " PLN");
        System.out.println("[" + NAME + "] Oczekiwanie na potwierdzenie przelewu...");
        System.out.println("[" + NAME + "] Płatność zakończona sukcesem!");
        return true;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}