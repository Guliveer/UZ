package zad01;

import java.util.List;

/**
 * Interfejs State definiujący kontrakt dla wszystkich stanów w maszynie stanów.
 * Wzorzec projektowy Stan (State Pattern).
 */
public interface State {
    
    /**
     * Zwraca nazwę aktualnego stanu.
     * @return nazwa stanu
     */
    String getStateName();
    
    /**
     * Sprawdza czy stan jest stanem końcowym.
     * @return true jeśli stan jest końcowy, false w przeciwnym razie
     */
    boolean isFinalState();
    
    /**
     * Zwraca listę możliwych przejść z tego stanu.
     * @return lista przejść
     */
    List<Transition> getTransitions();
}