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
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final String ID = "[Client] ";
    // Kolory ANSI
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Odbieranie komunikatu od serwera
            String serverMessage = in.readLine();
            System.out.println(ID + CYAN + "Otrzymano od serwera: " + serverMessage + RESET);

            if ("Komunikat dla klienta".equals(serverMessage)) {
                // Wysłanie odpowiedzi do serwera
                out.println("Nawiązano komunikację i wysłano odpowiedź");
                System.out.println(ID + YELLOW + "Wysłano odpowiedź do serwera." + RESET);
            }

            // Działanie przez 120 sekund
            TimeUnit.SECONDS.sleep(120);
            System.out.println(ID + YELLOW + "Oczekiwanie przez 120 sekund." + RESET);

            // Wysłanie końcowego komunikatu i zamknięcie
            out.println("Kończę pracę");
            System.out.println(ID + YELLOW + "Kończę pracę i zamykam połączenie." + RESET);
        } catch (IOException | InterruptedException e) {
            System.out.print(ID);
            System.err.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
