// Napisz program, który umożliwi dwóm wątkom wzajemną wymianę danych — tekstu. Wykorzystaj do
// tego celu przekaźnik (Exchanger). Pierwszy wątek — Sprzedawca zawiera przykładowy tekst: „Olej
// rzepakowy 1 litr", drugi wątek Klient zawiera przykładowy tekst: „10 złotych”. Po dokonaniu wymiany
// Klient otrzymuje tekst: „Olej rzepakowy 1 litr”, zaś Sprzedawca tekst: „10 złotych”. Wyświetlaj dane
// przechowywane w wątkach przed i po wymianie.

package zad01;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread sprzedawca = new Thread(() -> {
            try {
                String tekstSprzedawcy = "Olej rzepakowy 1 litr";
                System.out.println("Sprzedawca przed wymianą: " + tekstSprzedawcy);
                tekstSprzedawcy = exchanger.exchange(tekstSprzedawcy);
                System.out.println("Sprzedawca po wymianie: " + tekstSprzedawcy);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Sprzedawca został przerwany.");
            }
        });

        Thread klient = new Thread(() -> {
            try {
                String tekstKlienta = "10 złotych";
                System.out.println("Klient przed wymianą: " + tekstKlienta);
                tekstKlienta = exchanger.exchange(tekstKlienta);
                System.out.println("Klient po wymianie: " + tekstKlienta);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Klient został przerwany.");
            }
        });

        sprzedawca.start();
        klient.start();
    }
}
