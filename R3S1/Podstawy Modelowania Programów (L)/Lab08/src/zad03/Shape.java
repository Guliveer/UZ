package zad03;

/**
 * Interfejs reprezentujący figurę geometryczną.
 * Wykorzystuje wzorzec Wizytator (Visitor Pattern) do umożliwienia
 * wykonywania różnych operacji na figurach bez modyfikacji ich klas.
 */
public interface Shape {
    
    /**
     * Akceptuje wizytatora i pozwala mu wykonać operację na tej figurze.
     * 
     * @param visitor wizytator wykonujący operację
     */
    void accept(ShapeVisitor visitor);
    
    /**
     * Zwraca nazwę figury.
     * 
     * @return nazwa figury
     */
    String getName();
}