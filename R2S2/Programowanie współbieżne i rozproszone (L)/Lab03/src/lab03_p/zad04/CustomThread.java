package lab03_p.zad04;

class CustomThread extends Thread {
    private final String message;

    public CustomThread(String name, String message) {
        super(name);
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread: " + getName() + ", Message: " + message);
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
