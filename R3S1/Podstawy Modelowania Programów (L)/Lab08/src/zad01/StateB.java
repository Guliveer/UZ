package zad01;

/**
 * Stan B.
 * Przejścia (wymaga TEKSTU):
 * - do C gdy tekst = "Ala"
 * - do D gdy tekst != "Ala"
 */
public class StateB extends AbstractState {
    
    public StateB() {
        // Przejście do C gdy tekst = "Ala"
        addTransition(new Transition(
            ctx -> ctx.getTekst() != null && ctx.getTekst().equals("Ala"),
            StateC::new,
            "tekst = \"Ala\""
        ));
        
        // Przejście do D gdy tekst != "Ala" (dowolny inny tekst)
        addTransition(new Transition(
            ctx -> ctx.getTekst() != null && !ctx.getTekst().equals("Ala"),
            StateD::new,
            "tekst != \"Ala\""
        ));
    }
    
    @Override
    public String getStateName() {
        return "B";
    }
}