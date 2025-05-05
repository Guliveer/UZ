package zad03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Baker implements Runnable {
    private final BlockingQueue<Integer> belt;
    private final AtomicInteger cookieCounter;
    private final int cookiesToDo;
    private final String color = "\u001B[32m";
    private final String color_r = "\u001B[0m";

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
                    break;
                }
                Thread.sleep(500); // Simulate baking time
                int cookie = cookieCounter.getAndIncrement();
                if (cookie <= cookiesToDo) {
                    belt.put(cookie);
                    System.out.println(color + "[" + Thread.currentThread().getName() + "]\tUpieczono ciastko nr " + cookie + color_r);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}