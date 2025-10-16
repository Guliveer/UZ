package zad03;

import java.util.Scanner;

/**
 * Main application class for managing rectangles using composition pattern.
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final RectangleCollection rectangleCollection = new RectangleCollection();
    
    public static void main(String[] args) {
        System.out.println("=== RECTANGLE MANAGER ===");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readChoice();
            
            switch (choice) {
                case 1 -> addRectangle();
                case 2 -> displayAllRectangles();
                case 3 -> removeRectangle();
                case 4 -> calculateTotalArea();
                case 5 -> displayStatistics();
                case 6 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
        System.out.println("Thank you for using the Rectangle Manager!");
    }
    
    private static void displayMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add rectangle");
        System.out.println("2. Display all rectangles");
        System.out.println("3. Remove rectangle");
        System.out.println("4. Calculate total area");
        System.out.println("5. Display statistics");
        System.out.println("6. Exit program");
        System.out.printf("Current number of rectangles: %d\n", rectangleCollection.size());
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
            
            if (rectangleCollection.containsRectangle(name)) {
                System.out.println("Rectangle with name '" + name + "' already exists!");
                return;
            }
            
            System.out.print("Enter length of first side: ");
            double sideA = scanner.nextDouble();
            
            System.out.print("Enter length of second side: ");
            double sideB = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            Rectangle rectangle = new Rectangle(name, sideA, sideB);
            boolean added = rectangleCollection.addRectangle(rectangle);
            
            if (added) {
                System.out.println("✓ Rectangle added successfully!");
                System.out.println("Details: " + rectangle);
            } else {
                System.out.println("✗ Failed to add rectangle!");
            }
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear input
        }
    }
    
    private static void displayAllRectangles() {
        System.out.println("\n--- ALL RECTANGLES ---");
        
        if (rectangleCollection.isEmpty()) {
            System.out.println("No rectangles in collection.");
            return;
        }
        
        System.out.printf("%-6s %-12s %-12s %-15s %-15s %-10s\n",
                        "Name", "Side A", "Side B", "Area", "Perimeter", "Type");
        System.out.println("-".repeat(75));
        
        for (Rectangle rectangle : rectangleCollection.getAllRectangles()) {
            System.out.printf("%-6s %-12.2f %-12.2f %-15.2f %-15.2f %-10s\n",
                            rectangle.getName(),
                            rectangle.getSideA(),
                            rectangle.getSideB(),
                            rectangle.getArea(),
                            rectangle.getPerimeter(),
                            rectangle.isSquare() ? "Square" : "Rectangle");
        }
        
        System.out.println("-".repeat(75));
        System.out.printf("Total area: %.2f\n", rectangleCollection.getTotalArea());
    }
    
    private static void removeRectangle() {
        System.out.println("\n--- REMOVING RECTANGLE ---");
        
        if (rectangleCollection.isEmpty()) {
            System.out.println("No rectangles to remove.");
            return;
        }
        
        System.out.println("Available rectangles:");
        for (String name : rectangleCollection.getAllNames()) {
            System.out.println("• " + name + " - " + rectangleCollection.getRectangle(name));
        }
        
        System.out.print("Enter name of rectangle to remove: ");
        String name = scanner.nextLine().trim().toUpperCase();
        
        Rectangle removed = rectangleCollection.removeRectangle(name);
        if (removed != null) {
            System.out.println("✓ Rectangle removed successfully!");
            System.out.println("Removed rectangle: " + removed);
        } else {
            System.out.println("✗ Rectangle with name '" + name + "' not found.");
        }
    }
    
    private static void calculateTotalArea() {
        System.out.println("\n--- TOTAL AREA ---");
        
        if (rectangleCollection.isEmpty()) {
            System.out.println("No rectangles in collection.");
            return;
        }
        
        double totalArea = rectangleCollection.getTotalArea();
        System.out.printf("Total area of all rectangles: %.2f\n", totalArea);
        System.out.printf("Number of rectangles: %d\n", rectangleCollection.size());
        System.out.printf("Average area: %.2f\n", rectangleCollection.getAverageArea());
    }
    
    private static void displayStatistics() {
        System.out.println("\n--- STATISTICS ---");
        
        if (rectangleCollection.isEmpty()) {
            System.out.println("No rectangles in collection.");
            return;
        }
        
        System.out.println("Collection: " + rectangleCollection);
        System.out.printf("Total rectangles: %d\n", rectangleCollection.size());
        System.out.printf("Squares: %d\n", rectangleCollection.countSquares());
        System.out.printf("Regular rectangles: %d\n", rectangleCollection.size() - rectangleCollection.countSquares());
        System.out.printf("Total area: %.2f\n", rectangleCollection.getTotalArea());
        System.out.printf("Average area: %.2f\n", rectangleCollection.getAverageArea());
        
        Rectangle largest = rectangleCollection.getLargestRectangle();
        Rectangle smallest = rectangleCollection.getSmallestRectangle();
        
        if (largest != null) {
            System.out.println("Largest rectangle: " + largest);
        }
        if (smallest != null) {
            System.out.println("Smallest rectangle: " + smallest);
        }
    }
}
