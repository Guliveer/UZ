package zad05;

/**
 * Interfejs implementacji we wzorcu Most (Bridge Pattern).
 * Reprezentuje kanał płatności (procesor płatności).
 */
public interface PaymentProcessor {
    
    /**
     * Sprawdza, czy procesor obsługuje płatności wielokrotne (subskrypcje).
     * @return true jeśli procesor obsługuje subskrypcje, false w przeciwnym razie
     */
    boolean supportsSubscription();
    
    /**
     * Dokonuje płatności o podanej kwocie.
     * @param amount kwota płatności
     * @return true jeśli płatność się powiodła, false w przeciwnym razie
     */
    boolean processPayment(double amount);
    
    /**
     * Zwraca nazwę procesora płatności.
     * @return nazwa procesora
     */
    String getName();
}