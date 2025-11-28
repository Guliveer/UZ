package zad02.p2;

/**
 * Interfejs definiujący wspólne API dla różnych trybów pracy programu
 */
public interface TrybPracy {
    
    /**
     * Uruchamia tryb pracy
     * @param processor procesor poleceń do obsługi komend
     */
    void uruchom(KomendyProcessor processor);
}