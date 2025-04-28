// W mieszkaniu z jednym telefonem żyją trzy osoby: rodzice z dzieckiem. Każda z osób wykonuje
// w ciągu dnia w pętli (5x) czynności: czytanie książki, picie herbaty, rozmowa telefoniczna, praca przy
// komputerze (wyświetlać komunikaty na ekranie). W trakcie dnia osoby te chcą skorzystać z aparatu
// telefonicznego. Zaimplementować rozwiązanie problemu dostępu do telefonu (wyświetlać stosowne
// komunikaty) tak, aby dwie osoby na raz nie mogły skorzystać z telefonu i porozmawiać. Każda
// z osób reprezentuje inny wątek. Wprowadzić w wybranych miejscach losowe opóźnienie w wątkach.
// Kod każdego wątku może być identyczny. Wystarczy więc jedna klasa typu Wątek oraz nadanie
// wątkom nazw, np: Mama, Tata, Dziecko. Prowadzenie rozmowy jest równoznaczne z wykonywaniem
// sekcji krytycznej przez dany wątek. Jak wiadomo, dwa wątki nie mogą jednocześnie przebywać w
// sekcji krytycznej. Wątki należy zsynchronizować przy wykorzystaniu semaforów (nie używać
// synchronized).

package zad01;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore telefon = new Semaphore(1); // Semafor pozwala tylko jednej osobie na rozmowę
        Person mama = new Person("Mama", telefon);
        Person tata = new Person("Tata", telefon);
        Person dziecko = new Person("Dziecko", telefon);

        mama.start();
        tata.start();
        dziecko.start();
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
            telefon.acquire(); // Wejście do sekcji krytycznej
            System.out.println(getName() + " rozmawia przez telefon.");
            randomDelay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(getName() + " kończy rozmowę telefoniczną.");
            telefon.release(); // Wyjście z sekcji krytycznej
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
