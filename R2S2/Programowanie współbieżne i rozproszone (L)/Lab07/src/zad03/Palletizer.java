package zad03;

import java.util.concurrent.BlockingQueue;

class Palletizer implements Runnable {
    private final BlockingQueue<String> inputBelt;
    private final BlockingQueue<String> warehouse;
    private final String color = "\u001B[36m";
    private final String color_r = "\u001B[0m";

    public Palletizer(BlockingQueue<String> inputBelt, BlockingQueue<String> warehouse) {
        this.inputBelt = inputBelt;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000); // Simulate palletizing time
                String product1 = inputBelt.take();
                String product2 = inputBelt.take();
                String product3 = inputBelt.take();
                String product4 = inputBelt.take();
                String pallet = "[" + product1 + ", " + product2 + ", " + product3 + ", " + product4 + "]";
                warehouse.put(pallet);
                System.out.println(color + "[" + Thread.currentThread().getName() + "]\tZłożono palete: " + pallet + color_r);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}