package zad02;

import java.util.concurrent.BlockingQueue;

class Packer implements Runnable {
    private final BlockingQueue<Integer> belt;
    private final int cookiesToDo;

    public Packer(BlockingQueue<Integer> belt, int cookiesToDo) {
        this.belt = belt;
        this.cookiesToDo = cookiesToDo;
    }

    @Override
    public void run() {
        try {
            int packedCookies = 0;
            while (packedCookies < cookiesToDo) {
                Thread.sleep(800); // Simulate packing time
                Integer cookie = belt.take(); // Take a cookie from the belt
                packedCookies++;
                System.out.println("[" + Thread.currentThread().getName() + "]\tZapakowano ciastko nr " + cookie);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
