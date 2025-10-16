package zad03;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Class for managing a collection of rectangles.
 * Implements composition pattern - Main class will use this to manage rectangles.
 */
public class RectangleCollection {
    
    private final Map<String, Rectangle> rectangles;
    
    /**
     * Creates a new empty rectangle collection.
     */
    public RectangleCollection() {
        this.rectangles = new HashMap<>();
    }
    
    /**
     * Adds a rectangle to the collection.
     * 
     * @param rectangle the rectangle to add
     * @return true if added successfully, false if name already exists
     */
    public boolean addRectangle(Rectangle rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException("Rectangle cannot be null");
        }
        
        String name = rectangle.getName();
        if (rectangles.containsKey(name)) {
            return false; // Rectangle with this name already exists
        }
        
        rectangles.put(name, rectangle);
        return true;
    }
    
    /**
     * Removes a rectangle from the collection by name.
     * 
     * @param name the name of the rectangle to remove
     * @return the removed rectangle, or null if not found
     */
    public Rectangle removeRectangle(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return rectangles.remove(name.trim().toUpperCase());
    }
    
    /**
     * Gets a rectangle by name.
     * 
     * @param name the name of the rectangle
     * @return the rectangle, or null if not found
     */
    public Rectangle getRectangle(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return rectangles.get(name.trim().toUpperCase());
    }
    
    /**
     * Checks if a rectangle with the given name exists.
     * 
     * @param name the name to check
     * @return true if exists, false otherwise
     */
    public boolean containsRectangle(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return rectangles.containsKey(name.trim().toUpperCase());
    }
    
    /**
     * Gets all rectangles in the collection.
     * 
     * @return collection of all rectangles
     */
    public Collection<Rectangle> getAllRectangles() {
        return rectangles.values();
    }
    
    /**
     * Gets all rectangle names.
     * 
     * @return collection of all rectangle names
     */
    public Collection<String> getAllNames() {
        return rectangles.keySet();
    }
    
    /**
     * Gets the number of rectangles in the collection.
     * 
     * @return the size of the collection
     */
    public int size() {
        return rectangles.size();
    }
    
    /**
     * Checks if the collection is empty.
     * 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return rectangles.isEmpty();
    }
    
    /**
     * Calculates the total area of all rectangles.
     * 
     * @return the total area
     */
    public double getTotalArea() {
        return rectangles.values().stream()
                .mapToDouble(Rectangle::getArea)
                .sum();
    }
    
    /**
     * Calculates the average area of all rectangles.
     * 
     * @return the average area, or 0 if collection is empty
     */
    public double getAverageArea() {
        if (isEmpty()) {
            return 0.0;
        }
        return getTotalArea() / size();
    }
    
    /**
     * Gets the rectangle with the largest area.
     * 
     * @return the rectangle with largest area, or null if collection is empty
     */
    public Rectangle getLargestRectangle() {
        return rectangles.values().stream()
                .max((r1, r2) -> Double.compare(r1.getArea(), r2.getArea()))
                .orElse(null);
    }
    
    /**
     * Gets the rectangle with the smallest area.
     * 
     * @return the rectangle with smallest area, or null if collection is empty
     */
    public Rectangle getSmallestRectangle() {
        return rectangles.values().stream()
                .min((r1, r2) -> Double.compare(r1.getArea(), r2.getArea()))
                .orElse(null);
    }
    
    /**
     * Counts how many rectangles are actually squares.
     * 
     * @return the number of squares
     */
    public long countSquares() {
        return rectangles.values().stream()
                .filter(Rectangle::isSquare)
                .count();
    }
    
    /**
     * Clears all rectangles from the collection.
     */
    public void clear() {
        rectangles.clear();
    }
    
    @Override
    public String toString() {
        return String.format("RectangleCollection[size=%d, totalArea=%.2f]", 
                           size(), getTotalArea());
    }
}