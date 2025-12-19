package zad05;

/**
 * Abstrakcja we wzorcu Most (Bridge Pattern).
 * Reprezentuje typ płatności i zawiera referencję do implementacji (procesora płatności).
 */
public abstract class Payment {
    
    protected PaymentProcessor processor;
    protected double amount;
    
    /**
     * Konstruktor abstrakcji płatności.
     * @param processor procesor płatności (implementacja)
     * @param amount kwota płatności
     */
    public Payment(PaymentProcessor processor, double amount) {
        this.processor = processor;
        this.amount = amount;
    }
    
    /**
     * Wykonuje płatność.
     * @return true jeśli płatność się powiodła, false w przeciwnym razie
     */
    public abstract boolean execute();
    
    /**
     * Sprawdza, czy płatność może być wykonana przez dany procesor.
     * @return true jeśli płatność jest możliwa, false w przeciwnym razie
     */
    public abstract boolean canExecute();
    
    /**
     * Zwraca nazwę typu płatności.
     * @return nazwa typu płatności
     */
    public abstract String getPaymentTypeName();
    
    /**
     * Zwraca kwotę płatności.
     * @return kwota płatności
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Zwraca procesor płatności.
     * @return procesor płatności
     */
    public PaymentProcessor getProcessor() {
        return processor;
    }
}