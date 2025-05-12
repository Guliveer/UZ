// Oliwer Pawelski
// 12.05.2025

import java.util.concurrent.BlockingQueue;

class Malowanie implements Runnable {
    private static final String color = "\u001B[32m";
    private static final String colorReset = "\u001B[0m";
    private final BlockingQueue<Blotnik> stolKontroli;
    private final BlockingQueue<Blotnik> magazyn;

    public Malowanie(BlockingQueue<Blotnik> stolKontroli, BlockingQueue<Blotnik> magazyn) {
        this.stolKontroli = stolKontroli;
        this.magazyn = magazyn;
    }

    @Override
    public void run() {
        try {
            while (true) { // Pętla nieskończona
                Blotnik blotnik = stolKontroli.take();
                System.out.println(color + "Malowanie:\t" + blotnik + colorReset);
                Thread.sleep(2000); // Symulacja czasu malowania
                System.out.println(color + "Malowanie:\t" + blotnik + " [Gotowe]" + colorReset);
                magazyn.put(blotnik);
                System.out.println("Magazyn:\t" + blotnik + " [Przeniesiono]");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}