package zad04;

/**
 * Klasa reprezentująca żądanie kalkulatora.
 * Przechowuje wyrażenie do obliczenia oraz mnożnik wynikający z modyfikatora @.
 */
public class CalculatorRequest {
    
    private String expression;
    private int multiplier;
    
    /**
     * Tworzy nowe żądanie kalkulatora.
     * @param expression wyrażenie do obliczenia
     */
    public CalculatorRequest(String expression) {
        this.expression = expression;
        this.multiplier = 1;
    }
    
    /**
     * Tworzy nowe żądanie kalkulatora z określonym mnożnikiem.
     * @param expression wyrażenie do obliczenia
     * @param multiplier mnożnik wyniku
     */
    public CalculatorRequest(String expression, int multiplier) {
        this.expression = expression;
        this.multiplier = multiplier;
    }
    
    /**
     * Zwraca wyrażenie do obliczenia.
     * @return wyrażenie
     */
    public String getExpression() {
        return expression;
    }
    
    /**
     * Ustawia wyrażenie do obliczenia.
     * @param expression nowe wyrażenie
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    /**
     * Zwraca aktualny mnożnik wyniku.
     * @return mnożnik
     */
    public int getMultiplier() {
        return multiplier;
    }
    
    /**
     * Ustawia mnożnik wyniku.
     * @param multiplier nowy mnożnik
     */
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
    
    /**
     * Podwaja aktualny mnożnik (używane przez modyfikator @).
     */
    public void doubleMultiplier() {
        this.multiplier *= 2;
    }
    
    /**
     * Stosuje mnożnik do podanej wartości.
     * @param value wartość do pomnożenia
     * @return wartość pomnożona przez mnożnik
     */
    public long applyMultiplier(long value) {
        return value * multiplier;
    }
    
    @Override
    public String toString() {
        return "CalculatorRequest{expression='" + expression + "', multiplier=" + multiplier + "}";
    }
}