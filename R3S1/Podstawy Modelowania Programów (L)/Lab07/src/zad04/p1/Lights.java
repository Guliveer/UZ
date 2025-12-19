package zad04.p1;

/**
 * Klasa reprezentująca oświetlenie w systemie kina domowego
 */
public class Lights {
    private int brightness = 100;
    
    public void on() {
        this.brightness = 100;
        System.out.println("Światło włączone (jasność: 100%)");
    }
    
    public void dim(int level) {
        this.brightness = level;
        System.out.println("Światło przyciemnione do: " + level + "%");
    }
    
    public int getBrightness() {
        return brightness;
    }
}