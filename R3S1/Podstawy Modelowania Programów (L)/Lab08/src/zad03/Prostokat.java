package zad03;

/**
 * Klasa reprezentująca prostokąt o bokach x i y.
 * Implementuje interfejs Shape i wspiera wzorzec Wizytator.
 */
public class Prostokat implements Shape {
    
    private final double x;
    private final double y;
    
    /**
     * Tworzy nowy prostokąt o podanych wymiarach.
     * 
     * @param x długość pierwszego boku
     * @param y długość drugiego boku
     */
    public Prostokat(double x, double y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Boki prostokąta muszą być dodatnie");
        }
        this.x = x;
        this.y = y;
    }
    
    /**
     * Zwraca długość pierwszego boku.
     * 
     * @return długość boku x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Zwraca długość drugiego boku.
     * 
     * @return długość boku y
     */
    public double getY() {
        return y;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String getName() {
        return "Prostokąt";
    }
    
    @Override
    public String toString() {
        return String.format("Prostokąt(x=%.2f, y=%.2f)", x, y);
    }
}