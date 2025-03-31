package lab04_p.zad02;

public class DzialajacyZiemniak1 implements Runnable {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
