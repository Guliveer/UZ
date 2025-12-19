package zad04.p1;

/**
 * Główna klasa demonstrująca komponenty systemu kina domowego
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstracja komponentów systemu kina domowego ===\n");
        
        // Tworzenie komponentów
        Amplifier amplifier = new Amplifier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        ProjectorScreen screen = new ProjectorScreen();
        
        // Demonstracja wzmacniacza
        System.out.println("--- Wzmacniacz ---");
        amplifier.on();
        amplifier.setVolume(50);
        amplifier.off();
        
        System.out.println();
        
        // Demonstracja odtwarzacza DVD
        System.out.println("--- Odtwarzacz DVD ---");
        dvdPlayer.on();
        dvdPlayer.play("Matrix");
        dvdPlayer.stop();
        dvdPlayer.off();
        
        System.out.println();
        
        // Demonstracja projektora
        System.out.println("--- Projektor ---");
        projector.on();
        projector.off();
        
        System.out.println();
        
        // Demonstracja oświetlenia
        System.out.println("--- Oświetlenie ---");
        lights.on();
        lights.dim(50);
        
        System.out.println();
        
        // Demonstracja ekranu projektora
        System.out.println("--- Ekran projektora ---");
        screen.down();
        screen.up();
    }
}