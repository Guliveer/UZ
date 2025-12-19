package zad04.p2;

/**
 * Główna klasa demonstrująca wzorzec Fasada dla systemu kina domowego
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstracja wzorca Fasada - System kina domowego ===\n");
        
        // Tworzenie komponentów
        Amplifier amplifier = new Amplifier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();
        ProjectorScreen screen = new ProjectorScreen();
        
        // Tworzenie fasady
        HomeTheater homeTheater = new HomeTheater(
            amplifier, dvdPlayer, projector, lights, screen
        );
        
        // Użycie fasady - proste API zamiast wielu wywołań
        homeTheater.watchMovie("Matrix");
        
        // Symulacja oglądania filmu
        System.out.println("\n... oglądanie filmu ...\n");
        
        // Zakończenie seansu
        homeTheater.endMovie();
    }
}