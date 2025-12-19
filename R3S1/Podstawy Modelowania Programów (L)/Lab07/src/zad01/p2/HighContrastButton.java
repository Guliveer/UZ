package zad01.p2;

/**
 * Implementacja przycisku w trybie wysokiego kontrastu dla niepełnosprawnych
 */
public class HighContrastButton implements Button {
    @Override
    public void draw() {
        System.out.println("... na którym rysuję przycisk wysokiego kontrastu");
    }
}