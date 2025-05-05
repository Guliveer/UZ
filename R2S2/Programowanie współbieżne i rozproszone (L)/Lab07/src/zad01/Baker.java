package zad01;

import java.util.concurrent.BlockingQueue;

class Baker implements Runnable {
    private final BlockingQueue<Integer> belt;
    private final int cookiesToDo;
    private int cookieCounter = 1;

    public Baker(BlockingQueue<Integer> belt, int cookiesToDo) {
        this.belt = belt;
        this.cookiesToDo = cookiesToDo;
    }

    @Override
    public void run() {
        try {
            for (int i=0; i < cookiesToDo; i++) {
                Thread.sleep(500); // Symulacja czasu pieczenia ciastka
                belt.put(cookieCounter);
                System.out.println("[Cukiernik]\tUpieczono ciastko nr " + cookieCounter);
                cookieCounter++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
