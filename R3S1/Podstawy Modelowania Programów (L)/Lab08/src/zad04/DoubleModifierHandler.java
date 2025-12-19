package zad04;

/**
 * Handler obsługujący modyfikator @.
 * Modyfikator @ podwaja wynik następnej operacji.
 * Może być użyty wielokrotnie (@@, @@@, itd.).
 */
public class DoubleModifierHandler extends Handler {
    
    private static final char MODIFIER_SYMBOL = '@';
    
    @Override
    public Long handle(CalculatorRequest request) {
        String expression = request.getExpression();
        
        if (expression.isEmpty() || expression.charAt(0) != MODIFIER_SYMBOL) {
            return passToNext(request);
        }
        
        // Zliczamy ile razy występuje modyfikator @ na początku
        int atCount = 0;
        while (atCount < expression.length() && expression.charAt(atCount) == MODIFIER_SYMBOL) {
            atCount++;
        }
        
        // Usuwamy wszystkie znaki @ z początku wyrażenia
        String remainingExpression = expression.substring(atCount);
        
        if (remainingExpression.isEmpty()) {
            System.err.println("Błąd: Modyfikator @ wymaga operacji do wykonania");
            return null;
        }
        
        // Tworzymy nowe żądanie z pozostałym wyrażeniem
        CalculatorRequest newRequest = new CalculatorRequest(remainingExpression, request.getMultiplier());
        
        // Podwajamy mnożnik tyle razy, ile było znaków @
        for (int i = 0; i < atCount; i++) {
            newRequest.doubleMultiplier();
        }
        
        // Przekazujemy zmodyfikowane żądanie do następnego handlera
        return passToNext(newRequest);
    }
}