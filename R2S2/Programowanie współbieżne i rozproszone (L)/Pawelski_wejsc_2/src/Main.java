// Oliwer Pawelski
// 12.05.2025

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Blotnik> tasma = new ArrayBlockingQueue<>(12);
        BlockingQueue<Blotnik> stolKontroli = new ArrayBlockingQueue<>(8);
        BlockingQueue<Blotnik> magazyn = new LinkedBlockingQueue<>();

        Thread wycinanie1 = new Thread(new Wycinanie(tasma));
        Thread wycinanie2 = new Thread(new Wycinanie(tasma));
        Thread prasowanie = new Thread(new Prasowanie(tasma, stolKontroli));
        Thread malowanie1 = new Thread(new Malowanie(stolKontroli, magazyn));
        Thread malowanie2 = new Thread(new Malowanie(stolKontroli, magazyn));

        wycinanie1.start();
        wycinanie2.start();
        prasowanie.start();
        malowanie1.start();
        malowanie2.start();
    }
}