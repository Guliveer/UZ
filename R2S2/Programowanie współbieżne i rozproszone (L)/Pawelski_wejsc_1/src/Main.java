// Pawelski Oliwer
// 28.04.2025

import java.util.Random;
import java.util.concurrent.Semaphore;

class BokA extends Thread {
    private final Semaphore semA;
    private final Semaphore semPole;
    private int wartosc;

    public BokA(Semaphore semA, Semaphore semPole) {
        this.semA = semA;
        this.semPole = semPole;
    }

    public int getBokA() {
        return wartosc;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            try {
                semA.acquire();
                wartosc = random.nextInt(10) + 1;
                System.out.println("[BokA] Wylosowano bok A -> " + wartosc + "\t[losowanie nr " + i + "]");
                semPole.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class BokB extends Thread {
    private final Semaphore semB;
    private final Semaphore semPole;
    private int wartosc;

    public BokB(Semaphore semB, Semaphore semPole) {
        this.semB = semB;
        this.semPole = semPole;
    }

    public int getBokB() {
        return wartosc;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            try {
                semB.acquire();
                wartosc = random.nextInt(10) + 1;
                System.out.println("[BokB] Wylosowano bok B -> " + wartosc + "\t[losowanie nr " + i + "]");
                semPole.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Pole extends Thread {
    private final Semaphore semA;
    private final Semaphore semB;
    private final Semaphore semPole;
    private final BokA bokA;
    private final BokB bokB;

    public Pole(Semaphore semA, Semaphore semB, Semaphore semPole, BokA bokA, BokB bokB) {
        this.semA = semA;
        this.semB = semB;
        this.semPole = semPole;
        this.bokA = bokA;
        this.bokB = bokB;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                semPole.acquire(2);
                int a = bokA.getBokA();
                int b = bokB.getBokB();
                System.out.println("[Pole] Pobrano dane: BokA = " + a + ", BokB = " + b);
                System.out.println("[Pole] Obliczone pole: " + (a * b) + "\t[pole nr " + i + "]");
                System.out.println();
                semA.release();
                semB.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Semaphore semA = new Semaphore(1);
        Semaphore semB = new Semaphore(1);
        Semaphore semPole = new Semaphore(0);

        BokA bokA = new BokA(semA, semPole);
        BokB bokB = new BokB(semB, semPole);
        Pole pole = new Pole(semA, semB, semPole, bokA, bokB);

        bokA.start();
        bokB.start();
        pole.start();
    }
}
