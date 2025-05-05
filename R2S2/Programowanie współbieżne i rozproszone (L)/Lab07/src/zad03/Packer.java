package zad03;

import java.util.concurrent.BlockingQueue;

class Packer implements Runnable {
    private final BlockingQueue<Integer> inputBelt;
    private final BlockingQueue<String> outputBelt;
    private final String color = "\u001B[34m";
    private final String color_r = "\u001B[0m";

    public Packer(BlockingQueue<Integer> inputBelt, BlockingQueue<String> outputBelt) {
        this.inputBelt = inputBelt;
        this.outputBelt = outputBelt;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(800); // Simulate packing time
                int cookie1 = inputBelt.take();
                int cookie2 = inputBelt.take();
                String wrappedProduct = "Zawinięte ciastka " + cookie1 + " & " + cookie2;
                outputBelt.put(wrappedProduct);
                System.out.println(color + "[" + Thread.currentThread().getName() + "]\tZapakowano: " + wrappedProduct + color_r);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}