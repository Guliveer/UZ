package zad01;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakcyjna klasa bazowa dla stanów.
 * Zawiera wspólną logikę obsługi przejść między stanami.
 */
public abstract class AbstractState implements State {
    
    protected final List<Transition> transitions = new ArrayList<>();
    
    /**
     * Dodaje przejście do listy możliwych przejść.
     * @param transition przejście do dodania
     */
    protected void addTransition(Transition transition) {
        transitions.add(transition);
    }
    
    @Override
    public List<Transition> getTransitions() {
        return transitions;
    }
    
    @Override
    public boolean isFinalState() {
        return false;
    }
    
    /**
     * Próbuje wykonać przejście na podstawie aktualnego kontekstu.
     * Sprawdza wszystkie przejścia i wykonuje pierwsze pasujące.
     * @param context kontekst z danymi
     * @return true jeśli przejście zostało wykonane, false w przeciwnym razie
     */
    public boolean tryTransition(Context context) {
        for (Transition transition : transitions) {
            if (transition.canTransition(context)) {
                State targetState = transition.getTargetState();
                System.out.println("Przejście do stanu " + targetState.getStateName() + 
                                   " (warunek: " + transition.getConditionDescription() + ")");
                context.setState(targetState);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Wyświetla informacje o możliwych przejściach z tego stanu.
     */
    public void displayTransitions() {
        System.out.println("Możliwe przejścia ze stanu " + getStateName() + ":");
        for (int i = 0; i < transitions.size(); i++) {
            Transition t = transitions.get(i);
            System.out.println("  - " + t.getConditionDescription() + 
                               " → stan " + t.getTargetState().getStateName());
        }
    }
}