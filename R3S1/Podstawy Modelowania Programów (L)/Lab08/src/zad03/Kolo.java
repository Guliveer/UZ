package zad03;

/**
 * Klasa reprezentująca koło o promieniu r.
 * Implementuje interfejs Shape i wspiera wzorzec Wizytator.
 */
public class Kolo implements Shape {
    
    private final double r;
    
    /**
     * Tworzy nowe koło o podanym promieniu.
     * 
     * @param r promień koła
     */
    public Kolo(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Promień koła musi być dodatni");
        }
        this.r = r;
    }
    
    /**
     * Zwraca promień koła.
     * 
     * @return promień r
     */
    public double getR() {
        return r;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String getName() {
        return "Koło";
    }
    
    @Override
    public String toString() {
        return String.format("Koło(r=%.2f)", r);
    }
}