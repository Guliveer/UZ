// Oliwer Pawelski, 24INF-SP/A

// Z użyciem Socket, ServerSocket napisz aplikację typu klient-serwer.
// Server:
//  - po uruchomieniu oczekuje na połączenie
//  - po połączeniu serwer wysyła do klienta komunikat "Wysłano komunikat do
//     klienta" i oczekuje na odpowiedź.
//  - w przypadku gdy klient odpowie "Nawiązano komunikację i wysłano
//     odpowiedź"
//  - serwer po 5 sekundach wyśle komunikat „Połączenie zatwierdzone” w
//     przypadku otrzymania złej odpowiedzi serwer się wyłącza.
// Klient:
//  - sprawdza przychodzące komunikaty
//  - jeśli otrzyma "Wysłano komunikat do klienta" to odpowiada "Nawiązano
//     komunikację i wysłano odpowiedź"
//  - po 120 sekundach działania wyśle komunikat "Kończę pracę" i się wyłączy

package lab11_p.zad03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final String ID = "[Server] ";
    // Kolory ANSI
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println(ID + GREEN + "Serwer uruchomiony. Oczekiwanie na połączenie..." + RESET);
            Socket socket = serverSocket.accept();
            System.out.println(ID + GREEN + "Połączono z klientem." + RESET);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                // Wysłanie komunikatu do klienta
                out.println("Komunikat dla klienta");
                System.out.println(ID + GREEN + "Komunikat wysłany do klienta. Oczekiwanie na odpowiedź..." + RESET);

                // Odbiór odpowiedzi od klienta
                String response = in.readLine();
                System.out.println(ID + "Odpowiedź klienta: " + response);

                if ("Nawiązano komunikację i wysłano odpowiedź".equals(response)) {
                    TimeUnit.SECONDS.sleep(5);
                    out.println("Połączenie zatwierdzone");
                    System.out.println(ID + GREEN + "Połączenie zatwierdzone." + RESET);
                } else {
                    System.out.println(ID + RED + "Zła odpowiedź. Serwer kończy pracę." + RESET);
                    socket.close();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.print(ID);
            System.err.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
