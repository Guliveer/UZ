package lab04_p.zad02.result;

public class TestThreadPriority {
    public static void main(String[] args) {
        HeavyComputation task = new HeavyComputation();

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        Thread thread3 = new Thread(task, "Thread 3");

        thread1.setPriority(Thread.MIN_PRIORITY); // Set minimum priority
        thread2.setPriority(Thread.NORM_PRIORITY); // Set normal priority
        thread3.setPriority(Thread.MAX_PRIORITY); // Set maximum priority

        thread1.start();
        thread2.start();
        thread3.start();

        // Loading indication
        String[] loading = {"|", "/", "-", "\\"};
        int i = 0;
        while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
            System.out.print("Program is running... " + loading[i++ % loading.length] + "\r");
            try {
                Thread.sleep(225); // Delay in changing loading text
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted");
            }
        }
    }
}