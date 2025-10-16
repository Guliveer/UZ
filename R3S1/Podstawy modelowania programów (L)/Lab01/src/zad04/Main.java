package zad04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main application class for managing geometric shapes.
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Shape> shapes = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println("=== GEOMETRIC SHAPES MANAGER ===");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readChoice();
            
            switch (choice) {
                case 1 -> addRectangle();
                case 2 -> addSquare();
                case 3 -> displayAllShapes();
                case 4 -> removeShape();
                case 5 -> calculateTotalArea();
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
        System.out.println("Thank you for using the Shapes Manager!");
    }
    
    private static void displayMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add rectangle");
        System.out.println("2. Add square");
        System.out.println("3. Display all shapes");
        System.out.println("4. Remove shape");
        System.out.println("5. Calculate total area");
        System.out.println("6. Exit program");
        System.out.printf("Current number of shapes: %d\n", shapes.size());
        System.out.print("Choose option (1-6): ");
    }
    
    private static int readChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return choice;
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
            return -1;
        }
    }
    
    private static void addRectangle() {
        System.out.println("\n--- ADDING RECTANGLE ---");
        
        try {
            System.out.print("Enter rectangle name (single uppercase letter A-Z): ");
            String name = scanner.nextLine().trim().toUpperCase();
            
            if (shapes.containsKey(name)) {
                System.out.println("Shape with name '" + name + "' already exists!");
                return;
            }
            
            System.out.print("Enter length of first side: ");
            double sideA = scanner.nextDouble();
            
            System.out.print("Enter length of second side: ");
            double sideB = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            Rectangle rectangle = new Rectangle(name, sideA, sideB);
            shapes.put(name, rectangle);
            
            System.out.println("✓ Rectangle added successfully!");
            System.out.println("Details: " + rectangle);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear input
        }
    }
    
    private static void addSquare() {
        System.out.println("\n--- ADDING SQUARE ---");
        
        try {
            System.out.print("Enter square name (single uppercase letter A-Z): ");
            String name = scanner.nextLine().trim().toUpperCase();
            
            if (shapes.containsKey(name)) {
                System.out.println("Shape with name '" + name + "' already exists!");
                return;
            }
            
            System.out.print("Enter side length: ");
            double side = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            Square square = new Square(name, side);
            shapes.put(name, square);
            
            System.out.println("✓ Square added successfully!");
            System.out.println("Details: " + square);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear input
        }
    }
    
    private static void displayAllShapes() {
        System.out.println("\n--- ALL SHAPES ---");
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes in collection.");
            return;
        }
        
        System.out.printf("%-6s %-12s %-15s %-15s %-15s %-10s\n",
                         "Name", "Type", "Dimensions", "Area", "Perimeter", "Regular");
        System.out.println("-".repeat(75));
        
        for (Shape shape : shapes.values()) {
            String dimensions = getDimensionsString(shape);
            System.out.printf("%-6s %-12s %-15s %-15.2f %-15.2f %-10s\n",
                            shape.getName(),
                            shape.getType(),
                            dimensions,
                            shape.getArea(),
                            shape.getPerimeter(),
                            shape.isRegular() ? "YES" : "NO");
        }
        
        System.out.println("-".repeat(75));
        System.out.printf("Total area: %.2f\n", calculateTotalAreaValue());
    }
    
    private static String getDimensionsString(Shape shape) {
        if (shape instanceof Rectangle rect) {
            return String.format("%.1f×%.1f", rect.getSideA(), rect.getSideB());
        } else if (shape instanceof Square square) {
            return String.format("%.1f×%.1f", square.getSide(), square.getSide());
        }
        return "N/A";
    }
    
    private static void removeShape() {
        System.out.println("\n--- REMOVING SHAPE ---");
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes to remove.");
            return;
        }
        
        System.out.println("Available shapes:");
        for (String name : shapes.keySet()) {
            System.out.println("• " + name + " - " + shapes.get(name));
        }
        
        System.out.print("Enter name of shape to remove: ");
        String name = scanner.nextLine().trim().toUpperCase();
        
        Shape removed = shapes.remove(name);
        if (removed != null) {
            System.out.println("✓ Shape removed successfully!");
            System.out.println("Removed shape: " + removed);
        } else {
            System.out.println("✗ Shape with name '" + name + "' not found.");
        }
    }
    
    private static void calculateTotalArea() {
        System.out.println("\n--- TOTAL AREA ---");
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes in collection.");
            return;
        }
        
        double totalArea = calculateTotalAreaValue();
        System.out.printf("Total area of all shapes: %.2f\n", totalArea);
        System.out.printf("Number of shapes: %d\n", shapes.size());
        System.out.printf("Average area: %.2f\n", totalArea / shapes.size());
        
        // Additional statistics
        long rectangleCount = shapes.values().stream()
                .filter(shape -> shape instanceof Rectangle && !(shape instanceof Square))
                .count();
        long squareCount = shapes.values().stream()
                .filter(shape -> shape instanceof Square)
                .count();
        
        System.out.printf("Rectangles: %d, Squares: %d\n", rectangleCount, squareCount);
    }
    
    private static double calculateTotalAreaValue() {
        return shapes.values().stream()
                .mapToDouble(Shape::getArea)
                .sum();
    }
}