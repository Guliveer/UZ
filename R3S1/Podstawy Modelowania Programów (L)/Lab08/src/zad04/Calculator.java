package zad04;

/**
 * Główna klasa kalkulatora ONP (Odwrotna Notacja Polska) dla dzieci.
 * Buduje łańcuch odpowiedzialności i przetwarza wyrażenia.
 */
public class Calculator {
    
    private final Handler handlerChain;
    
    /**
     * Tworzy nowy kalkulator i buduje łańcuch handlerów.
     */
    public Calculator() {
        this.handlerChain = buildChain();
    }
    
    /**
     * Buduje łańcuch odpowiedzialności.
     * Kolejność handlerów:
     * 1. DoubleModifierHandler - obsługuje modyfikator @
     * 2. AdditionHandler - obsługuje operację +
     * 3. FactorialHandler - obsługuje operację !
     * 4. MultiplyAddHandler - obsługuje operację *
     * 
     * @return pierwszy handler w łańcuchu
     */
    private Handler buildChain() {
        Handler doubleModifier = new DoubleModifierHandler();
        Handler addition = new AdditionHandler();
        Handler factorial = new FactorialHandler();
        Handler multiplyAdd = new MultiplyAddHandler();
        
        // Budujemy łańcuch
        doubleModifier.setNext(addition);
        addition.setNext(factorial);
        factorial.setNext(multiplyAdd);
        
        return doubleModifier;
    }
    
    /**
     * Oblicza wartość wyrażenia.
     * @param expression wyrażenie do obliczenia (np. "+3;5", "!5", "*2;3;4", "@+3;5")
     * @return wynik obliczenia lub null jeśli wyrażenie jest nieprawidłowe
     */
    public Long calculate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            System.err.println("Błąd: Puste wyrażenie");
            return null;
        }
        
        CalculatorRequest request = new CalculatorRequest(expression.trim());
        Long result = handlerChain.handle(request);
        
        if (result == null) {
            System.err.println("Błąd: Nierozpoznane wyrażenie: " + expression);
        }
        
        return result;
    }
    
    /**
     * Oblicza i wyświetla wynik wyrażenia.
     * @param expression wyrażenie do obliczenia
     */
    public void calculateAndPrint(String expression) {
        System.out.print(expression + " → wynik: ");
        Long result = calculate(expression);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("błąd");
        }
    }
}