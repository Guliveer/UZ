// Zmodyfikować (rozwinąć) zadanie 1. W programie zdefiniować dwie stałe, określające: a) liczbę osób
// mieszkających w mieszkaniu, b) liczbę telefonów w mieszkaniu. Zmodyfikować program w ten
// sposób, aby w zależności od wartości tych stałych, program prawidłowo wykonywał sekcję krytyczną.
// Jeżeli stała liczba telefonów jest równa np. 2, oznacza to, że jednocześnie dwie osoby mogą
// rozmawiać przez telefon. W celu uproszczenia zadania nie rozróżniamy z którego telefonu korzysta
// dana osoba. Wskazówka — nie należy zmieniać liczby semaforów użytych w programie, a jedynie
// wartość semafora.

package zad02;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    // Stałe
    private static final int NUM_PEOPLE = 3;    // Liczba osób w mieszkaniu
    private static final int NUM_PHONES = 2;    // Liczba telefonów w mieszkaniu

    public static void main(String[] args) {
        Semaphore telefon = new Semaphore(NUM_PHONES); // Semafor z liczbą dostępnych telefonów

        for (int i = 1; i <= NUM_PEOPLE; i++) {
            new Person("Osoba_" + i, telefon).start();
        }
    }
}

class Person extends Thread {
    private final Semaphore telefon;
    private final Random random = new Random();

    public Person(String name, Semaphore telefon) {
        super(name);
        this.telefon = telefon;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            czytajKsiazke();
            pijHerbate();
            rozmawiajPrzezTelefon();
            pracujPrzyKomputerze();
        }
    }

    private void czytajKsiazke() {
        System.out.println(getName() + " czyta książkę.");
        randomDelay();
    }

    private void pijHerbate() {
        System.out.println(getName() + " pije herbatę.");
        randomDelay();
    }

    private void rozmawiajPrzezTelefon() {
        try {
            System.out.println(getName() + " próbuje skorzystać z telefonu...");
            telefon.acquire(); // Wejście do sekcji krytycznej (blokada telefonu)
            System.out.println(getName() + " rozmawia przez telefon.");
            randomDelay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(getName() + " kończy rozmowę telefoniczną.");
            telefon.release(); // Zwolnienie telefonu
        }
    }

    private void pracujPrzyKomputerze() {
        System.out.println(getName() + " pracuje przy komputerze.");
        randomDelay();
    }

    private void randomDelay() {
        try {
            Thread.sleep(random.nextInt(1000) + 500); // Losowe opóźnienie od 500ms do 1500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}