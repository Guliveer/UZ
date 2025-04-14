package zad04;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Dane implements Runnable {
    public static double a, b, c;
    private final Semaphore semaphoreDane;
    private final Semaphore semaphoreDelta;

    public Dane(Semaphore semaphoreDane, Semaphore semaphoreDelta) {
        this.semaphoreDane = semaphoreDane;
        this.semaphoreDelta = semaphoreDelta;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            semaphoreDane.acquire(); // Wait for permission to execute
            System.out.print("Podaj współczynnik a: ");
            a = scanner.nextDouble();
            System.out.print("Podaj współczynnik b: ");
            b = scanner.nextDouble();
            System.out.print("Podaj współczynnik c: ");
            c = scanner.nextDouble();
            semaphoreDelta.release(); // Allow Delta to execute
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}