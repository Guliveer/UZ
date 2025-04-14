package zad04;

import java.util.concurrent.Semaphore;

public class Pierwiastki implements Runnable {
    private final Semaphore semaphorePierwiastki;

    public Pierwiastki(Semaphore semaphorePierwiastki) {
        this.semaphorePierwiastki = semaphorePierwiastki;
    }

    @Override
    public void run() {
        try {
            semaphorePierwiastki.acquire(); // Wait for Delta to finish
            if (Delta.delta < 0) {
                System.out.println("Brak pierwiastków rzeczywistych.");
            } else if (Delta.delta == 0) {
                double x = -Dane.b / (2 * Dane.a);
                System.out.println("Jeden pierwiastek: x = " + x);
            } else {
                double x1 = (-Dane.b - Math.sqrt(Delta.delta)) / (2 * Dane.a);
                double x2 = (-Dane.b + Math.sqrt(Delta.delta)) / (2 * Dane.a);
                System.out.println("Dwa pierwiastki: x1 = " + x1 + ", x2 = " + x2);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}