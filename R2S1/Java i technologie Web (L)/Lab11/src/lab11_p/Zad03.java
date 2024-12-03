package lab11_p.zad03;

public class Zad03 {
    private static final String ID = "[Main] ";
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            try {
                Server.main(null);
            } catch (Exception e) {
                System.err.println(ID + "Błąd w wątku serwera: " + e.getMessage());
            }
        });

        Thread clientThread = new Thread(() -> {
            try {
                Client.main(null);
            } catch (Exception e) {
                System.err.println(ID + "Błąd w wątku klienta: " + e.getMessage());
            }
        });

        serverThread.start();

        // Opóźnienie dla klienta, aby upewnić się, że serwer już działa
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(ID + "Wątek główny został przerwany: " + e.getMessage());
        }

        clientThread.start();

        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            System.err.println(ID + "Błąd podczas oczekiwania na zakończenie wątków: " + e.getMessage());
        }

        System.out.println(ID + "Program zakończył działanie.");
    }
}
