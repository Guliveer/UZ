package zad04;

import java.util.concurrent.Semaphore;

public class Delta implements Runnable {
    public static double delta;
    private final Semaphore semaphoreDelta;
    private final Semaphore semaphorePierwiastki;

    public Delta(Semaphore semaphoreDelta, Semaphore semaphorePierwiastki) {
        this.semaphoreDelta = semaphoreDelta;
        this.semaphorePierwiastki = semaphorePierwiastki;
    }

    @Override
    public void run() {
        try {
            semaphoreDelta.acquire(); // Wait for Dane to finish
            delta = Dane.b * Dane.b - 4 * Dane.a * Dane.c;
            System.out.println("Obliczona delta: " + delta);
            semaphorePierwiastki.release(); // Allow Pierwiastki to execute
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}