// Korzystając z materiałów z wykładów, uruchom program liczący silnię (slajd 26-27). Czy silnia jest
// liczona prawidłowo? Co się stanie, jeżeli usuniemy synchronizację z metody licz(), czy silnia jest
// nadal prawidłowo liczona?

package lab03_p.zad03;

import java.util.Scanner;

public class Silnia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj liczbę: ");

        // pobieramy od użytkownika liczbę
        int liczba = sc.nextInt();
        sc.close();
        if (liczba < 16) {
            Silnia silnia = new Silnia();
            for (int i = 0; i < liczba; i++) {
                Watek watek = new Watek();
                watek.silnia = silnia;
                watek.liczba = liczba;
                watek.start();
            }
        } else {
            System.out.println("Licze max do !15");
        }
    }

    // obliczenie silni odbywa się za pomocą rekurencji
    public synchronized int licz(int wartosc) {
        try {
            if (wartosc <= 1)
                return 1;
            else
                return wartosc * licz(wartosc - 1);
        } finally {
            System.out.println(Thread.currentThread().getName() + " ==[liczbe]==> " + wartosc);
        }
    }

    private static class Watek extends Thread {
        private int liczba;
        private Silnia silnia;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "[" + liczba + "] != " + silnia.licz(liczba));
        }
    }
}

// [Q&A]

//? [Q] Dlaczego w tym zadaniu synchronizacja nie jest wymagana?
//* [A] W tym zadaniu synchronizacja nie jest wymagana, ponieważ każdy wątek ma swoją własną instancję
//* klasy Silnia, a więc nie ma konfliktu dostępu do zasobów, w przeciwieństwie do zadania 2.
