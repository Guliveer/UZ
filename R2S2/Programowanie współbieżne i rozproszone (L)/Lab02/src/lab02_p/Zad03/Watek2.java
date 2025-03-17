package lab02_p.Zad03;

public class Watek2 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Watek2: " + i);
        }
    }
}
