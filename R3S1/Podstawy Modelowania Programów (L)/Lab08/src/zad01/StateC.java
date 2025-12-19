package zad01;

/**
 * Stan C.
 * Przejścia (wymaga LICZBY):
 * - do E gdy liczba = 4
 * - do F gdy liczba != 4
 */
public class StateC extends AbstractState {
    
    public StateC() {
        // Przejście do E gdy liczba = 4
        addTransition(new Transition(
            ctx -> ctx.getLiczba() != null && ctx.getLiczba() == 4,
            StateE::new,
            "liczba = 4"
        ));
        
        // Przejście do F gdy liczba != 4
        addTransition(new Transition(
            ctx -> ctx.getLiczba() != null && ctx.getLiczba() != 4,
            StateF::new,
            "liczba != 4"
        ));
    }
    
    @Override
    public String getStateName() {
        return "C";
    }
}