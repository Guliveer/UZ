package zad01;

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
            for (int i=0; i < cookiesToDo; i++) {
                Thread.sleep(800); // Symulacja czasu pakowania ciastka
                int cookie = belt.take();
                System.out.println("[Pakowacz]\tZapakowano ciastko nr " + cookie);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
