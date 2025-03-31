// Korzystając z materiałów z wykładów, uruchom przykład z jedzeniem tortu przez myszy
// (wykład 2, slajdy 17-20). Naszym celem jest zagwarantowanie, że tylko jedna mysz może jeść tort
// w danym momencie. Uruchom program kilkakrotnie, zaobserwuj jego działanie.
// Odpowiedz na pytanie, czy w danym momencie tylko jedna mysz je tort?
// W dalszej kolejności zmodyfikuj zadanie zgodnie ze slajdem 23 i 24.
// Uruchom program kilkakrotnie, zaobserwuj jego działanie, jak te zmiany wpływają na
// działanie programu? Jak brzmi motto synchronizacji?
// (Odpowiedzi do pytań umieszczać w komentarzach w plikach źródłowych).


package lab04_p.zad01.original;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Mouse extends Thread {
    private final Cake cake;
    public Mouse(final Cake cake, final String name) {
        super(name);
        this.cake = cake;
    }

    public static void main(String[] args) {
        Cake cake = new Cake();
        Thread mouse1 = new Mouse(cake, "Mysz_1");
        Thread mouse2 = new Mouse(cake, "Mysz_2");
        System.out.println("mouse1=" + mouse1.getState());
        System.out.println("mouse2=" + mouse2.getState());
        mouse1.start();
        mouse2.start();

        while (true) {
            System.out.println("mouse1=" + mouse1.getState());
            System.out.println("mouse2=" + mouse2.getState());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mouse1.getState().ordinal()
                    + mouse2.getState().ordinal() == 10) {
                break;
            }
        }
    }

    private synchronized int eat(int percent) {
        System.out.println(Thread.currentThread().getName() + " jem " + percent + " torta.");
        cake.percent = Math.max(cake.percent- percent, 0);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " zostało " + cake.percent + " torta.");
        return cake.percent;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if(eat(new Random().nextInt(40)) <= 0){
                System.out.println("======KONIEC=======");
                break;
            }
        }
    }
}

// [Q&A]

//? 1. Czy w danym momencie tylko jedna mysz je tort?
//* Tak, w danym momencie tylko jedna mysz je tort, ponieważ metoda `eat` jest zsynchronizowana.
//* Synchronizacja zapewnia, że tylko jeden wątek może wykonywać tę metodę w danym momencie.

//? 2. Jak te zmiany wpływają na działanie programu?
//* Zmiany zgodnie ze slajdami 23 i 24 mogą obejmować dodanie bardziej zaawansowanych mechanizmów synchronizacji,
//* takich jak użycie blokad (Locks) lub semaforów. Te zmiany mogą poprawić wydajność i kontrolę nad dostępem
//* do zasobów, ale mogą również wprowadzić większą złożoność w zarządzaniu wątkami.

//? 3. Jak brzmi motto synchronizacji?
//* Motto synchronizacji brzmi:
//* "Jeśli zapisujesz wartość zmiennej, która może zostać zaraz odczytana
//* przez inny wątek lub odczytujesz wartość zmiennej, która może właśnie
//* być zapisywana przez inny wątek, to musisz zastosować synchronizację"
