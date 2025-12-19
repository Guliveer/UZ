package zad03;

/**
 * Aplikacja konsolowa demonstrująca działanie wzorca Wizytator (Visitor Pattern)
 * w systemie inżynierskim do obliczeń dwuwymiarowych.
 * 
 * System przechowuje dowolną liczbę figur geometrycznych i umożliwia:
 * - Obliczanie i wypisanie pola powierzchni każdej figury
 * - Obliczanie i wypisanie obwodu każdej figury
 * 
 * Wzorzec Wizytator pozwala na łatwe dodawanie nowych operacji
 * bez modyfikacji klas figur.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Wzorzec Wizytator (Visitor Pattern) ===");
        System.out.println();
        
        // Tworzenie kolekcji figur
        ShapeCollection collection = new ShapeCollection();
        
        // Dodawanie różnych figur do kolekcji
        collection.addShape(new Prostokat(5.0, 3.0));
        collection.addShape(new Prostokat(10.0, 4.5));
        collection.addShape(new Kwadrat(4.0));
        collection.addShape(new Kwadrat(7.5));
        collection.addShape(new Kolo(3.0));
        collection.addShape(new Kolo(5.5));
        
        System.out.println("Dodano " + collection.size() + " figur do kolekcji.");
        System.out.println();
        
        // Tworzenie wizytatorów
        ShapeVisitor areaVisitor = new AreaVisitor();
        ShapeVisitor perimeterVisitor = new PerimeterVisitor();
        
        // Obliczanie pól powierzchni wszystkich figur
        System.out.println("--- OBLICZANIE PÓL POWIERZCHNI ---");
        collection.accept(areaVisitor);
        System.out.println();
        
        // Obliczanie obwodów wszystkich figur
        System.out.println("--- OBLICZANIE OBWODÓW ---");
        collection.accept(perimeterVisitor);
        System.out.println();
        
        // Demonstracja elastyczności wzorca - dodanie nowej figury
        System.out.println("--- DODANIE NOWEJ FIGURY ---");
        Shape prostokat = new Prostokat(8.0, 6.0);
        collection.addShape(prostokat);
        System.out.println("Dodano: " + prostokat);
        System.out.println();
        
        // Ponowne obliczenia dla wszystkich figur
        System.out.println("--- POLA POWIERZCHNI (po dodaniu nowej figury) ---");
        collection.accept(areaVisitor);
        System.out.println();
        
        System.out.println("--- OBWODY (po dodaniu nowej figury) ---");
        collection.accept(perimeterVisitor);
        System.out.println();
        
        // Demonstracja pojedynczej figury
        System.out.println("--- OBLICZENIA DLA POJEDYNCZEJ FIGURY ---");
        Shape kolo = new Kolo(10.0);
        System.out.println("Figura: " + kolo);
        System.out.print("Pole: ");
        kolo.accept(areaVisitor);
        System.out.print("Obwód: ");
        kolo.accept(perimeterVisitor);
        
        System.out.println();
        System.out.println("=== Koniec demonstracji ===");
    }
}