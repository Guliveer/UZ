package zad04;

/**
 * Abstrakcyjna klasa bazowa dla handlerów w łańcuchu odpowiedzialności.
 * Każdy handler może obsłużyć żądanie lub przekazać je do następnego handlera.
 */
public abstract class Handler {
    
    protected Handler nextHandler;
    
    /**
     * Ustawia następny handler w łańcuchu.
     * @param nextHandler następny handler
     * @return następny handler (umożliwia łańcuchowe wywołania)
     */
    public Handler setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
    
    /**
     * Obsługuje żądanie kalkulatora.
     * Jeśli handler nie może obsłużyć żądania, przekazuje je do następnego.
     * @param request żądanie do obsłużenia
     * @return wynik operacji lub null jeśli żaden handler nie obsłużył żądania
     */
    public abstract Long handle(CalculatorRequest request);
    
    /**
     * Przekazuje żądanie do następnego handlera w łańcuchu.
     * @param request żądanie do przekazania
     * @return wynik z następnego handlera lub null jeśli brak następnego handlera
     */
    protected Long passToNext(CalculatorRequest request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return null;
    }
}