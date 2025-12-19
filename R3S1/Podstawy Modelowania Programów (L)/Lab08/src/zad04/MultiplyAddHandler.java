package zad04;

/**
 * Handler obsługujący operację mnożenia sumy: *a;b;c
 * Wykonuje operację (a + b) * c i zwraca wynik.
 */
public class MultiplyAddHandler extends Handler {
    
    private static final char OPERATION_SYMBOL = '*';
    
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
            
            if (parts.length != 3) {
                return passToNext(request);
            }
            
            int a = Integer.parseInt(parts[0].trim());
            int b = Integer.parseInt(parts[1].trim());
            int c = Integer.parseInt(parts[2].trim());
            
            // Obliczamy (a + b) * c
            long result = (long) (a + b) * c;
            return request.applyMultiplier(result);
            
        } catch (NumberFormatException e) {
            return passToNext(request);
        }
    }
}