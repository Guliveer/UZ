// Korzystając z materiałów z wykładów, przepisz i uruchom przykład I z wykładu nr 2 (slajdy nr 5, 6, 7).
// Nie korzystamy wówczas z synchronizacji. Wartości tnum oraz count w klasie TestWatkow podajemy
// jako parametry metody main() w opcjach projektu Run>Edit Configurations>Program Arguments

package lab03_p.zad02;

public class TestWatkow {
    public static void main(String[] args) {
        int tnum = Integer.parseInt(args[0]); // liczba wątków
        int count = Integer.parseInt(args[1]); // liczba powtórzeń pętli w run()

        // Tworzymy obiekt klasy balance
        LicznikWatkow licznik = new LicznikWatkow();

        // Tworzymy i uruchamiamy wątki
        WatekLiczacy[] thread = new WatekLiczacy[tnum]; // tablica wątków
        for (int i = 0; i < tnum; i++) {
            thread[i] = new WatekLiczacy("Watek" + (i + 1), licznik, count);
        }

        // czekaj na zakończenie wszystkich wątków
        try {
            for (int i = 0; i < tnum; i++) {thread[i].join();}
        } catch (InterruptedException exc) { System.exit(1); }
        System.out.println("Koniec programu");
    }
}

// [Q&A]

//? [Q] Czy wątki zawsze kończą się z wynikiem 0?
//* [A] Nie, wątki nie zawsze kończą się z wynikiem 0, ponieważ metoda `policzMnie()` nie jest zsynchronizowana.

// ---

//? [Q] Wprowadź w metodzie policzMnie między wierszami zwiększającym i zmniejszającym wartość
//? wartosckrytyczna metodę sleep — co teraz można zauważyć?
//* [A] Można zauważyć, że wątki częściej kończą się z wynikiem różnym od zera,
//* ponieważ dodanie metody sleep zwiększa szansę na wystąpienie warunku wyścigu.

// ---

//? [Q] Wprowadź w metodzie policzMnie mechanizm synchronizacji (patrz slajd 15). Uruchom program i
//? poczekaj cierpliwie (lub zmniejsz wartość count). Co teraz można zaobserwować? Jak zmienia się
//? zachowanie wątków dla różnych wartości tnum oraz cout? Z jakim wynikiem teraz wątki
//? kończą pracę? Czy dodanie synchronizacji wpływa na czas działania programu?
//? Uwaga. Wyjaśnienie zachowania się wątków jest w treści wykładu 2 (slajdy 8 — 15).
//* [A] Po wprowadzeniu mechanizmu synchronizacji w metodzie `policzMnie`, wątki kończą się z wynikiem 0,
//* ponieważ dostęp do `wartoscKrytyczna` jest teraz chroniony przed jednoczesnym dostępem przez wiele wątków.
//* Dodanie synchronizacji eliminuje warunek wyścigu, co zapewnia poprawność działania programu.
//* Jednakże, dodanie synchronizacji może wpłynąć na czas działania programu, ponieważ wątki muszą teraz czekać
//* na dostęp do zsynchronizowanej sekcji kodu.
