package zad01.p2;

/**
 * Implementacja przycisku w trybie ciemnym
 */
public class DarkButton implements Button {
    @Override
    public void draw() {
        System.out.println("... na którym rysuję ciemny przycisk");
    }
}