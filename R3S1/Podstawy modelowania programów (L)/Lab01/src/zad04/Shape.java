package zad04;

/**
 * Interface representing a geometric shape.
 */
public interface Shape {
    
    /**
     * Returns the shape name.
     */
    String getName();
    
    /**
     * Returns the shape type.
     */
    String getType();
    
    /**
     * Calculates the area of the shape.
     */
    double getArea();
    
    /**
     * Calculates the perimeter of the shape.
     */
    double getPerimeter();
    
    /**
     * Checks if the shape is regular (all sides equal).
     */
    boolean isRegular();
}