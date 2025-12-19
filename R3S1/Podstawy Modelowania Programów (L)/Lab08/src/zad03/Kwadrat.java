package zad03;

/**
 * Klasa reprezentująca kwadrat o boku a.
 * Implementuje interfejs Shape i wspiera wzorzec Wizytator.
 */
public class Kwadrat implements Shape {
    
    private final double a;
    
    /**
     * Tworzy nowy kwadrat o podanym boku.
     * 
     * @param a długość boku kwadratu
     */
    public Kwadrat(double a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Bok kwadratu musi być dodatni");
        }
        this.a = a;
    }
    
    /**
     * Zwraca długość boku kwadratu.
     * 
     * @return długość boku a
     */
    public double getA() {
        return a;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String getName() {
        return "Kwadrat";
    }
    
    @Override
    public String toString() {
        return String.format("Kwadrat(a=%.2f)", a);
    }
}