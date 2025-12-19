package zad01;

/**
 * Stan D.
 * Przejścia (BEZWARUNKOWE):
 * - do C przy dowolnej wartości
 */
public class StateD extends AbstractState {
    
    public StateD() {
        // Przejście bezwarunkowe do C (dowolna wartość)
        addTransition(new Transition(
            ctx -> true,  // zawsze prawda - bezwarunkowe przejście
            StateC::new,
            "bezwarunkowe (dowolna wartość)"
        ));
    }
    
    @Override
    public String getStateName() {
        return "D";
    }
}