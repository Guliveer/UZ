package zad02.p4;

/**
 * Wyjątek rzucany gdy brakuje wymaganego parametru
 */
public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String parameterName) {
        super("Brak wymaganego parametru: " + parameterName);
    }
}