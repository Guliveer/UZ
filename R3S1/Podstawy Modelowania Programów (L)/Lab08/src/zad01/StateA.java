package zad01;

/**
 * Stan A - stan początkowy.
 * Przejścia (wymaga LICZBY):
 * - do B gdy liczba > 5
 * - do C gdy liczba = 5
 * - do E gdy liczba < 5
 */
public class StateA extends AbstractState {
    
    public StateA() {
        // Przejście do B gdy liczba > 5
        addTransition(new Transition(
            ctx -> ctx.getLiczba() != null && ctx.getLiczba() > 5,
            StateB::new,
            "liczba > 5"
        ));
        
        // Przejście do C gdy liczba = 5
        addTransition(new Transition(
            ctx -> ctx.getLiczba() != null && ctx.getLiczba() == 5,
            StateC::new,
            "liczba = 5"
        ));
        
        // Przejście do E gdy liczba < 5
        addTransition(new Transition(
            ctx -> ctx.getLiczba() != null && ctx.getLiczba() < 5,
            StateE::new,
            "liczba < 5"
        ));
    }
    
    @Override
    public String getStateName() {
        return "A";
    }
}