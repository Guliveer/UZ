package zad04.p1;

/**
 * Klasa reprezentująca wzmacniacz w systemie kina domowego
 */
public class Amplifier {
    private int volume = 0;
    
    public void on() {
        System.out.println("Wzmacniacz włączony");
    }
    
    public void off() {
        System.out.println("Wzmacniacz wyłączony");
    }
    
    public void setVolume(int level) {
        this.volume = level;
        System.out.println("Głośność wzmacniacza ustawiona na: " + level);
    }
    
    public int getVolume() {
        return volume;
    }
}