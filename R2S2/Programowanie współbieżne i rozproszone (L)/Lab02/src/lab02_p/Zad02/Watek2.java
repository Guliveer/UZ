package lab02_p.Zad02;

public class Watek2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Liczba " + i + ", Watek2");
        }
    }
}
