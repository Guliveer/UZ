package zad03.p3;

/**
 * Wspólny interfejs dla wszystkich sposobów logowania (Target w wzorcu Adapter)
 */
public interface LoginService {
    /**
     * Wykonuje interaktywne logowanie
     * @return true jeśli logowanie udane, false w przeciwnym razie
     */
    boolean authenticate();
    
    /**
     * Zwraca nazwę metody logowania
     * @return nazwa metody logowania
     */
    String getLoginMethodName();
}