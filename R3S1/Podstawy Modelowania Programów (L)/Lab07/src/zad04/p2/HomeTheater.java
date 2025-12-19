package zad04.p2;

/**
 * Klasa Fasada dla systemu kina domowego.
 * Upraszcza korzystanie z wielu komponentów poprzez dostarczenie
 * prostego interfejsu do typowych operacji.
 */
public class HomeTheater {
    private final Amplifier amplifier;
    private final DvdPlayer dvdPlayer;
    private final Projector projector;
    private final Lights lights;
    private final ProjectorScreen screen;
    
    public HomeTheater(Amplifier amplifier, DvdPlayer dvdPlayer, 
                       Projector projector, Lights lights, ProjectorScreen screen) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
    }
    
    /**
     * Uruchamia wszystkie potrzebne komponenty do oglądania filmu.
     * Opuszcza ekran, przygasza światło o 50% i rozpoczyna odtwarzanie filmu.
     * 
     * @param movie nazwa filmu do odtworzenia
     */
    public void watchMovie(String movie) {
        System.out.println("=== Przygotowanie do oglądania filmu: " + movie + " ===\n");
        
        // Włączenie urządzeń
        amplifier.on();
        amplifier.setVolume(50);
        
        dvdPlayer.on();
        
        projector.on();
        
        // Opuszczenie ekranu
        screen.down();
        
        // Przygaszenie światła o 50%
        lights.dim(50);
        
        // Rozpoczęcie odtwarzania
        dvdPlayer.play(movie);
        
        System.out.println("\n=== Miłego oglądania! ===");
    }
    
    /**
     * Zatrzymuje odtwarzanie, wyłącza wszystkie urządzenia i zapala światło.
     */
    public void endMovie() {
        System.out.println("\n=== Kończenie seansu ===\n");
        
        // Zatrzymanie odtwarzania
        dvdPlayer.stop();
        
        // Wyłączenie urządzeń
        dvdPlayer.off();
        projector.off();
        amplifier.off();
        
        // Podniesienie ekranu
        screen.up();
        
        // Zapalenie światła
        lights.on();
        
        System.out.println("\n=== Seans zakończony ===");
    }
}