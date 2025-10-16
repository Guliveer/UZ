package zad03;

/**
 * Simple class representing a rectangle.
 */
public class Rectangle {
    
    private final String name;
    private final double sideA;
    private final double sideB;
    
    /**
     * Creates a new rectangle with given parameters.
     *
     * @param name rectangle name (single uppercase letter)
     * @param sideA length of the first side
     * @param sideB length of the second side
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Rectangle(String name, double sideA, double sideB) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.trim().length() != 1 || !Character.isUpperCase(name.trim().charAt(0))) {
            throw new IllegalArgumentException("Name must be a single uppercase letter");
        }
        if (sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Sides must be positive");
        }
        
        this.name = name.trim().toUpperCase();
        this.sideA = sideA;
        this.sideB = sideB;
    }
    
    /**
     * Returns the rectangle name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the length of the first side.
     */
    public double getSideA() {
        return sideA;
    }
    
    /**
     * Returns the length of the second side.
     */
    public double getSideB() {
        return sideB;
    }
    
    /**
     * Returns the area of the rectangle.
     */
    public double getArea() {
        return sideA * sideB;
    }
    
    /**
     * Returns the perimeter of the rectangle.
     */
    public double getPerimeter() {
        return 2 * (sideA + sideB);
    }
    
    /**
     * Checks if the rectangle is a square.
     */
    public boolean isSquare() {
        return Math.abs(sideA - sideB) < 0.0001; // Tolerance for double comparison
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Rectangle rectangle = (Rectangle) obj;
        return name.equals(rectangle.name) &&
               Math.abs(sideA - rectangle.sideA) < 0.0001 &&
               Math.abs(sideB - rectangle.sideB) < 0.0001;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("Rectangle %s: %.2f × %.2f, Area: %.2f, Perimeter: %.2f",
                           name, sideA, sideB, getArea(), getPerimeter());
    }
}