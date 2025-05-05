package zad02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Baker implements Runnable {
    private final BlockingQueue<Integer> belt;
    private final AtomicInteger cookieCounter;
    private final int cookiesToDo;

    public Baker(BlockingQueue<Integer> belt, AtomicInteger cookieCounter, int cookiesToDo) {
        this.belt = belt;
        this.cookieCounter = cookieCounter;
        this.cookiesToDo = cookiesToDo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int currentCookie = cookieCounter.get();
                if (currentCookie > cookiesToDo) {
                    break; // Przerwanie, gdy osiągnięto limit ciastek
                }
                Thread.sleep(500); // Symulacja czasu pieczenia ciastka
                int cookie = cookieCounter.getAndIncrement(); // Pobranie i zwiększenie licznika
                if (cookie <= cookiesToDo) {
                    belt.put(cookie);
                    System.out.println("[" + Thread.currentThread().getName() + "]\tUpieczono ciastko nr " + cookie);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
