package zad03;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowująca kolekcję figur geometrycznych.
 * Umożliwia dodawanie figur i wykonywanie operacji na wszystkich figurach
 * za pomocą wzorca Wizytator.
 */
public class ShapeCollection {
    
    private final List<Shape> shapes;
    
    /**
     * Tworzy nową pustą kolekcję figur.
     */
    public ShapeCollection() {
        this.shapes = new ArrayList<>();
    }
    
    /**
     * Dodaje figurę do kolekcji.
     * 
     * @param shape figura do dodania
     */
    public void addShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Figura nie może być null");
        }
        shapes.add(shape);
    }
    
    /**
     * Usuwa figurę z kolekcji.
     * 
     * @param shape figura do usunięcia
     * @return true jeśli figura została usunięta, false w przeciwnym razie
     */
    public boolean removeShape(Shape shape) {
        return shapes.remove(shape);
    }
    
    /**
     * Zwraca liczbę figur w kolekcji.
     * 
     * @return liczba figur
     */
    public int size() {
        return shapes.size();
    }
    
    /**
     * Sprawdza czy kolekcja jest pusta.
     * 
     * @return true jeśli kolekcja jest pusta
     */
    public boolean isEmpty() {
        return shapes.isEmpty();
    }
    
    /**
     * Wykonuje operację wizytatora na wszystkich figurach w kolekcji.
     * 
     * @param visitor wizytator do wykonania operacji
     */
    public void accept(ShapeVisitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
    
    /**
     * Zwraca listę wszystkich figur w kolekcji.
     * 
     * @return niemodyfikowalna lista figur
     */
    public List<Shape> getShapes() {
        return List.copyOf(shapes);
    }
    
    /**
     * Czyści kolekcję figur.
     */
    public void clear() {
        shapes.clear();
    }
}