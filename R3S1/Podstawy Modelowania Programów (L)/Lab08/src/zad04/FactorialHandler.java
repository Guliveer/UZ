package zad04;

/**
 * Handler obsługujący operację silni: !a
 * Wykonuje operację a! (silnia) i zwraca wynik.
 */
public class FactorialHandler extends Handler {
    
    private static final char OPERATION_SYMBOL = '!';
    
    @Override
    public Long handle(CalculatorRequest request) {
        String expression = request.getExpression();
        
        if (expression.isEmpty() || expression.charAt(0) != OPERATION_SYMBOL) {
            return passToNext(request);
        }
        
        try {
            // Usuwamy znak operacji i parsujemy argument
            String arg = expression.substring(1).trim();
            int n = Integer.parseInt(arg);
            
            if (n < 0) {
                System.err.println("Błąd: Silnia nie jest zdefiniowana dla liczb ujemnych");
                return null;
            }
            
            long result = factorial(n);
            return request.applyMultiplier(result);
            
        } catch (NumberFormatException e) {
            return passToNext(request);
        }
    }
    
    /**
     * Oblicza silnię liczby n.
     * @param n liczba nieujemna
     * @return n! (silnia z n)
     */
    private long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}