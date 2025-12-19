package zad01.p2;

/**
 * Implementacja okna w trybie wysokiego kontrastu dla niepełnosprawnych
 */
public class HighContrastWindow implements Window {
    @Override
    public void draw() {
        System.out.println("Rysuję okno w trybie wysokiego kontrastu");
    }
}