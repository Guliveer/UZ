package zad01.p2;

/**
 * Implementacja przycisku w trybie jasnym
 */
public class LightButton implements Button {
    @Override
    public void draw() {
        System.out.println("... na którym rysuję jasny przycisk");
    }
}