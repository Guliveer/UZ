package lab02_p.Zad03;

public class Watek1 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Watek1: " + i);
            try {
                Thread.sleep(50); // Niewielkie opóźnienie
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
