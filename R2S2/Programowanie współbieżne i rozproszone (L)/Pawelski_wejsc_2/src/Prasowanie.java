// Oliwer Pawelski
// 12.05.2025

import java.util.concurrent.BlockingQueue;

class Prasowanie implements Runnable {
    private static final String color = "\u001B[35m";
    private static final String colorReset = "\u001B[0m";
    private final BlockingQueue<Blotnik> tasma;
    private final BlockingQueue<Blotnik> stolKontroli;

    public Prasowanie(BlockingQueue<Blotnik> tasma, BlockingQueue<Blotnik> stolKontroli) {
        this.tasma = tasma;
        this.stolKontroli = stolKontroli;
    }

    @Override
    public void run() {
        try {
            while (true) { // Pętla nieskończona
                Blotnik blotnik = tasma.take();
                System.out.println(color + "Prasowanie:\t" + blotnik + colorReset);
                Thread.sleep(1000); // Symulacja czasu prasowania
                System.out.println(color + "Prasowanie:\t" + blotnik + " [Gotowe]" + colorReset);
                stolKontroli.put(blotnik);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
