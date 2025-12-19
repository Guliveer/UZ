package zad04.p2;

/**
 * Klasa reprezentująca odtwarzacz DVD w systemie kina domowego
 */
public class DvdPlayer {
    private String currentMovie = null;
    
    public void on() {
        System.out.println("Odtwarzacz DVD włączony");
    }
    
    public void off() {
        System.out.println("Odtwarzacz DVD wyłączony");
    }
    
    public void play(String movie) {
        this.currentMovie = movie;
        System.out.println("Odtwarzanie filmu: " + movie);
    }
    
    public void stop() {
        System.out.println("Zatrzymano odtwarzanie filmu: " + currentMovie);
        this.currentMovie = null;
    }
    
    public String getCurrentMovie() {
        return currentMovie;
    }
}