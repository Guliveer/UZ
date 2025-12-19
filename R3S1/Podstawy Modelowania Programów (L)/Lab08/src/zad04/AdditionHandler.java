package zad04;

/**
 * Handler obsługujący operację dodawania: +a;b
 * Wykonuje operację a + b i zwraca wynik.
 */
public class AdditionHandler extends Handler {
    
    private static final char OPERATION_SYMBOL = '+';
    
    @Override
    public Long handle(CalculatorRequest request) {
        String expression = request.getExpression();
        
        if (expression.isEmpty() || expression.charAt(0) != OPERATION_SYMBOL) {
            return passToNext(request);
        }
        
        try {
            // Usuwamy znak operacji i parsujemy argumenty
            String args = expression.substring(1);
            String[] parts = args.split(";");
            
            if (parts.length != 2) {
                return passToNext(request);
            }
            
            int a = Integer.parseInt(parts[0].trim());
            int b = Integer.parseInt(parts[1].trim());
            
            long result = a + b;
            return request.applyMultiplier(result);
            
        } catch (NumberFormatException e) {
            return passToNext(request);
        }
    }
}