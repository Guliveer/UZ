package zad03;

/**
 * Wizytator obliczający pole powierzchni figur geometrycznych.
 * Implementuje wzorzec Wizytator (Visitor Pattern).
 */
public class AreaVisitor implements ShapeVisitor {
    
    /**
     * Oblicza i wypisuje pole powierzchni prostokąta.
     * Wzór: pole = x * y
     * 
     * @param prostokat prostokąt do obliczenia pola
     */
    @Override
    public void visit(Prostokat prostokat) {
        double pole = prostokat.getX() * prostokat.getY();
        System.out.printf("%s: pole = %.2f%n", prostokat, pole);
    }
    
    /**
     * Oblicza i wypisuje pole powierzchni kwadratu.
     * Wzór: pole = a²
     * 
     * @param kwadrat kwadrat do obliczenia pola
     */
    @Override
    public void visit(Kwadrat kwadrat) {
        double pole = kwadrat.getA() * kwadrat.getA();
        System.out.printf("%s: pole = %.2f%n", kwadrat, pole);
    }
    
    /**
     * Oblicza i wypisuje pole powierzchni koła.
     * Wzór: pole = π * r²
     * 
     * @param kolo koło do obliczenia pola
     */
    @Override
    public void visit(Kolo kolo) {
        double pole = Math.PI * kolo.getR() * kolo.getR();
        System.out.printf("%s: pole = %.2f%n", kolo, pole);
    }
}