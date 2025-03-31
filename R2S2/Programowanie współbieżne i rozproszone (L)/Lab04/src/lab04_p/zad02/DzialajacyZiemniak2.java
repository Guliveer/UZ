package lab04_p.zad02;

public class DzialajacyZiemniak2 implements Runnable {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Thread.yield(); // allow other threads to execute
        }
    }
}
