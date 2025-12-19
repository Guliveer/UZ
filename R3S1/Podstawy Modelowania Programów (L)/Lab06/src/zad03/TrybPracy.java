package zad03;

/**
 * Interfejs definiujący wspólne API dla różnych trybów pracy programu.
 * 
 * W wzorcu Dependency Injection interfejs ten służy jako abstrakcja,
 * która pozwala na wstrzykiwanie różnych implementacji do klasy Aplikacja.
 * 
 * Interfejs jest zaprojektowany z myślą o przyszłych rozszerzeniach,
 * takich jak tryb REST API.
 */
public interface TrybPracy {
    
    /**
     * Uruchamia tryb pracy.
     * 
     * @param processor procesor poleceń do obsługi komend
     */
    void uruchom(KomendyProcessor processor);
    
    /**
     * Zwraca nazwę trybu pracy (do celów diagnostycznych i logowania).
     * 
     * @return nazwa trybu pracy
     */
    default String getNazwaTrybu() {
        return this.getClass().getSimpleName();
    }
}