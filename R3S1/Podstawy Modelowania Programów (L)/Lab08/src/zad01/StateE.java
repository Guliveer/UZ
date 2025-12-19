package zad01;

/**
 * Stan E.
 * Przejścia (BEZWARUNKOWE):
 * - do D przy dowolnej wartości
 */
public class StateE extends AbstractState {
    
    public StateE() {
        // Przejście bezwarunkowe do D (dowolna wartość)
        addTransition(new Transition(
            ctx -> true,  // zawsze prawda - bezwarunkowe przejście
            StateD::new,
            "bezwarunkowe (dowolna wartość)"
        ));
    }
    
    @Override
    public String getStateName() {
        return "E";
    }
}