package zad03;

/**
 * Interfejs wizytatora dla figur geometrycznych.
 * Wzorzec Wizytator (Visitor Pattern) pozwala na dodawanie nowych operacji
 * do hierarchii klas bez modyfikacji tych klas.
 */
public interface ShapeVisitor {
    
    /**
     * Odwiedza prostokąt i wykonuje na nim operację.
     * 
     * @param prostokat prostokąt do odwiedzenia
     */
    void visit(Prostokat prostokat);
    
    /**
     * Odwiedza kwadrat i wykonuje na nim operację.
     * 
     * @param kwadrat kwadrat do odwiedzenia
     */
    void visit(Kwadrat kwadrat);
    
    /**
     * Odwiedza koło i wykonuje na nim operację.
     * 
     * @param kolo koło do odwiedzenia
     */
    void visit(Kolo kolo);
}