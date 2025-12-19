package zad01;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Klasa reprezentująca przejście między stanami.
 * Zawiera warunek (Predicate) oraz stan docelowy.
 */
public class Transition {
    
    private final Predicate<Context> condition;
    private final Supplier<State> targetStateSupplier;
    private final String conditionDescription;
    
    /**
     * Konstruktor tworzący przejście.
     * @param condition warunek przejścia
     * @param targetStateSupplier dostawca stanu docelowego
     * @param conditionDescription opis warunku (do wyświetlania)
     */
    public Transition(Predicate<Context> condition, Supplier<State> targetStateSupplier, String conditionDescription) {
        this.condition = condition;
        this.targetStateSupplier = targetStateSupplier;
        this.conditionDescription = conditionDescription;
    }
    
    /**
     * Sprawdza czy warunek przejścia jest spełniony.
     * @param context kontekst z danymi
     * @return true jeśli warunek jest spełniony
     */
    public boolean canTransition(Context context) {
        return condition.test(context);
    }
    
    /**
     * Zwraca nową instancję stanu docelowego.
     * @return stan docelowy
     */
    public State getTargetState() {
        return targetStateSupplier.get();
    }
    
    /**
     * Zwraca opis warunku przejścia.
     * @return opis warunku
     */
    public String getConditionDescription() {
        return conditionDescription;
    }
}