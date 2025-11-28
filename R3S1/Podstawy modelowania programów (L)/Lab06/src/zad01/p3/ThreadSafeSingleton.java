package zad01.p3;

/**
 * Implementacja wzorca Singleton bezpiecznego dla programów współbieżnych
 * Używa wzorca "Initialization-on-demand holder idiom"
 * 
 * Ta implementacja jest thread-safe ponieważ:
 * 1. Klasa SingletonHolder jest ładowana dopiero przy pierwszym wywołaniu getInstance()
 * 2. JVM gwarantuje, że inicjalizacja statycznych pól jest thread-safe
 * 3. Nie wymaga synchronizacji, więc jest wydajna
 * 4. Lazy loading - instancja jest tworzona dopiero gdy jest potrzebna
 */
public class ThreadSafeSingleton {
    
    // Prywatny konstruktor zapobiega tworzeniu instancji z zewnątrz
    private ThreadSafeSingleton() {
        // Symulacja kosztownej inicjalizacji
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Singleton został utworzony w wątku: " + Thread.currentThread().getName());
    }
    
    /**
     * Klasa wewnętrzna przechowująca instancję Singleton
     * Jest ładowana dopiero przy pierwszym wywołaniu getInstance()
     * JVM gwarantuje thread-safety podczas ładowania klas
     */
    private static class SingletonHolder {
        private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
    }
    
    /**
     * Publiczna metoda dostępu do instancji Singleton
     * Thread-safe dzięki mechanizmowi ładowania klas JVM
     * 
     * @return jedyna instancja ThreadSafeSingleton
     */
    public static ThreadSafeSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    // Przykładowe metody biznesowe
    
    private int licznik = 0;
    
    /**
     * Przykładowa metoda biznesowa - zwiększa licznik
     * Synchronizowana dla bezpieczeństwa wątków
     */
    public synchronized void zwiększLicznik() {
        licznik++;
        System.out.println("Licznik zwiększony do: " + licznik + " w wątku: " + Thread.currentThread().getName());
    }
    
    /**
     * Pobiera aktualną wartość licznika
     * @return aktualna wartość licznika
     */
    public synchronized int getLicznik() {
        return licznik;
    }
    
    /**
     * Przykładowa metoda biznesowa - wykonuje jakąś operację
     */
    public void wykonajOperację(String operacja) {
        System.out.println("Wykonuję operację: " + operacja + " w wątku: " + Thread.currentThread().getName());
    }
    
    /**
     * Zwraca informacje o instancji
     */
    public String getInfo() {
        return "ThreadSafeSingleton - instancja: " + this.hashCode() + ", licznik: " + licznik;
    }
}