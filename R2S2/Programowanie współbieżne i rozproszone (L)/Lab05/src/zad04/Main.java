// 4)
// Napisz program składający się z wątków: Dane, Delta, Pierwiastki oraz klasy Main, liczący
// pierwiastki równania kwadratowego, bez używania mechanizmów synchronizacji.
//
// Dane — wprowadzanie danych z klawiatury,
// Delta — obliczenie delty,
// Pierwiastki — obliczenie pierwiastków równania kwadratowego,
// Main — uruchomienie wątków i wyświetlenie wyników.
//
// Sprawdź wyniki obliczeń - czy są one poprawne? Zsynchronizuj pracę wątków
// z wykorzystaniem semaforów (nie używać synchronized oraz metody join)
// Sprawdź ponownie wyniki obliczeń.

package zad04;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphoreDane = new Semaphore(1); // Start with Dane
        Semaphore semaphoreDelta = new Semaphore(0); // Wait for Dane
        Semaphore semaphorePierwiastki = new Semaphore(0); // Wait for Delta

        Thread daneThread = new Thread(new Dane(semaphoreDane, semaphoreDelta));
        Thread deltaThread = new Thread(new Delta(semaphoreDelta, semaphorePierwiastki));
        Thread pierwiastkiThread = new Thread(new Pierwiastki(semaphorePierwiastki));

        daneThread.start();
        deltaThread.start();
        pierwiastkiThread.start();
    }
}
