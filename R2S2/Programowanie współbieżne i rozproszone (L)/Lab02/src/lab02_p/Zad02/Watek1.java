package lab02_p.Zad02;

public class Watek1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Liczba " + i + ", Watek1");
            try {
                Thread.sleep(50); // Niewielkie opóźnienie
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
