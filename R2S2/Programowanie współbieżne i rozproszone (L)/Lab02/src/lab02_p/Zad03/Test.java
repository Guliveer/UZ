package lab02_p.Zad03;

public class Test {
    public static void main(String[] args) {
        Thread w1 = new Thread(new Watek1()); // Tworzenie wątku na podstawie Runnable
        Thread w2 = new Thread(new Watek2());

        w1.start();
        w2.start();

        // Czekamy, aż oba wątki zakończą działanie
        try {
            w1.join();
            w2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Koniec programu");
    }
}
