package zad04;

/**
 * Simple class representing a square implementing the Shape interface.
 */
public class Square implements Shape {
    
    private final String name;
    private final double side;
    
    /**
     * Creates a new square with given parameters.
     *
     * @param name square name (single uppercase letter)
     * @param side length of the square side
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Square(String name, double side) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (!name.matches("^[A-Z]'*$")) {
            throw new IllegalArgumentException("Name must be a single uppercase letter");
        }
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }
        
        this.name = name.trim().toUpperCase();
        this.side = side;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getType() {
        return "Square";
    }
    
    @Override
    public double getArea() {
        return side * side;
    }
    
    @Override
    public double getPerimeter() {
        return 4 * side;
    }
    
    @Override
    public boolean isRegular() {
        return true; // Square is always regular
    }
    
    /**
     * Returns the length of the square side.
     */
    public double getSide() {
        return side;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Square square = (Square) obj;
        return name.equals(square.name) &&
               Math.abs(side - square.side) < 0.0001;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("Square %s: %.2f × %.2f, Area: %.2f", 
                            name, side, side, getArea());
    }
}