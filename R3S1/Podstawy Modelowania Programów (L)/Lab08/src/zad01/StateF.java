package zad01;

/**
 * Stan F.
 * Przejścia (BEZWARUNKOWE):
 * - do A przy dowolnej wartości (zamyka cykl)
 */
public class StateF extends AbstractState {
    
    public StateF() {
        // Przejście bezwarunkowe do A (dowolna wartość) - zamyka cykl
        addTransition(new Transition(
            ctx -> true,  // zawsze prawda - bezwarunkowe przejście
            StateA::new,
            "bezwarunkowe (dowolna wartość) → powrót do A"
        ));
    }
    
    @Override
    public String getStateName() {
        return "F";
    }
}