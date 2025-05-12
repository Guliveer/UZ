// Oliwer Pawelski
// 12.05.2025

import java.util.concurrent.BlockingQueue;

class Wycinanie implements Runnable {
    private static final String color = "\u001B[34m";
    private static final String colorReset = "\u001B[0m";
    private static int counter = 0;
    private final BlockingQueue<Blotnik> tasma;

    public Wycinanie(BlockingQueue<Blotnik> tasma) {
        this.tasma = tasma;
    }

    @Override
    public void run() {
        try {
            while (true) { // Pętla nieskończona
                Blotnik blotnik = new Blotnik(counter++);
                System.out.println(color + "Wycinanie:\t" + blotnik + colorReset);
                Thread.sleep(1200); // Symulacja czasu wycinania
                System.out.println(color + "Wycinanie:\t" + blotnik + " [Gotowe]" + colorReset);
                tasma.put(blotnik);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
