// Rozwiązać przy pomocy semaforów problem pięciu ucztujących filozofów. Problem polega na
// zsynchronizowaniu działań pięciu filozofów, którzy siedzą przy okrągłym stole i myślą. Myślenie jest ich głównym zajęciem, jednak co pewien czas filozof głodnieje i musi się posilić. Przed każdym
// filozofem stoi talerz, a pomiędzy kolejnymi dwoma talerzami leży jeden widelec, w sumie na stole
// znajduje się 5 talerzy i 5 widelców. Na środku stołu stoi talerz ze spaghetti. Filozof aby rozpocząć
// jedzenie musi mieć do dyspozycji dwa widelce. Podnosząc leżące przy jego talerzu widelce
// uniemożliwia jedzenie swoim sąsiadom. Algorytm rozwiązania problemu powinien gwarantować, że
// każdy filozof będzie mógł w skończonym czasie najeść się do syta. Tak więc zbiór własności
// poprawności jest następujący:
//
//
// - filozof je tylko wtedy gdy ma dwa widelce,
// - dwóch filozofów nie może jednocześnie trzymać tego samego widelca,
// - nie występuje blokada,
// - żaden filozof nie może być zagłodzony, Widelec 2
// - żaden filozof nie może być wyróżniony.
//
// Bieżący stan wątków reprezentować poprzez
// wyświetlanie stosownych komunikatów.
//
//
// Wskazówki do zadania:
//
// - zasobem chronionym przez semafor są widelce,
// - jeden semafor chroni jeden widelec,
// - filozof po podniesieniu jednego widelca, próbuje podnieść drugi widelec — jeżeli się uda to filozof może jeść posiłek,
// - po podniesieniu jednego widelca, filozof czeka na drugi widelec, nie odkłada pierwszego widelca jeżeli drugi widelec jest zajęty,
// - nie można dopuścić do sytuacji, że każdy z filozofów podniesie np. swój pracy widelec, wówczas nastąpi blokada.

package zad03;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    static final int NUM_PHILOSOPHERS = 5;
    static final Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        //  Inicjalizacja semaforów dla każdego widelca
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        //  Uruchomienie wątków - filozofów
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            new Philosopher(i).start();
        }
    }
}

class Philosopher extends Thread {
    private final int id;
    private final Random random = new Random();

    public Philosopher(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) { //  Filozof żyje wiecznie
            think();
            eat();
        }
    }

    private void think() {
        System.out.println("Filozof " + id + " myśli.");
        randomDelay();
    }

    private void eat() {
        int leftFork = id;
        int rightFork = (id + 1) % Main.NUM_PHILOSOPHERS;

        // Zapobiegamy zakleszczeniu - najpierw zawsze podnosimy widelec o niższym numerze
        int firstFork = Math.min(leftFork, rightFork);
        int secondFork = Math.max(leftFork, rightFork);

        try {
            System.out.println("Filozof " + id + " próbuje podnieść widelec " + firstFork + ".");
            Main.forks[firstFork].acquire();
            System.out.println("Filozof " + id + " podniósł widelec " + firstFork + ".");

            System.out.println("Filozof " + id + " próbuje podnieść widelec " + secondFork + ".");
            Main.forks[secondFork].acquire();
            System.out.println("Filozof " + id + " podniósł widelec " + secondFork + ".");

            // Oba widelce zdobyte - je
            System.out.println("Filozof " + id + " je.");
            randomDelay();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //  Odkłada widelce
            Main.forks[firstFork].release();
            Main.forks[secondFork].release();
            System.out.println("Filozof " + id + " odłożył widelce " + firstFork + " i " + secondFork + ".");
        }
    }

    private void randomDelay() {
        try {
            Thread.sleep(random.nextInt(1000) + 500); //  Opóźnienie 500-1500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
