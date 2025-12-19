package zad03;

/**
 * Wizytator obliczający obwód figur geometrycznych.
 * Implementuje wzorzec Wizytator (Visitor Pattern).
 */
public class PerimeterVisitor implements ShapeVisitor {
    
    /**
     * Oblicza i wypisuje obwód prostokąta.
     * Wzór: obwód = 2 * (x + y)
     * 
     * @param prostokat prostokąt do obliczenia obwodu
     */
    @Override
    public void visit(Prostokat prostokat) {
        double obwod = 2 * (prostokat.getX() + prostokat.getY());
        System.out.printf("%s: obwód = %.2f%n", prostokat, obwod);
    }
    
    /**
     * Oblicza i wypisuje obwód kwadratu.
     * Wzór: obwód = 4 * a
     * 
     * @param kwadrat kwadrat do obliczenia obwodu
     */
    @Override
    public void visit(Kwadrat kwadrat) {
        double obwod = 4 * kwadrat.getA();
        System.out.printf("%s: obwód = %.2f%n", kwadrat, obwod);
    }
    
    /**
     * Oblicza i wypisuje obwód koła.
     * Wzór: obwód = 2 * π * r
     * 
     * @param kolo koło do obliczenia obwodu
     */
    @Override
    public void visit(Kolo kolo) {
        double obwod = 2 * Math.PI * kolo.getR();
        System.out.printf("%s: obwód = %.2f%n", kolo, obwod);
    }
}