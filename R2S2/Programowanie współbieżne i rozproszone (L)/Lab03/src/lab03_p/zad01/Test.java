// Napisz jedną klasę Watek dziedziczącą po klasie Thread. Zaimplementuj metodę run(). W metodzie
// run() wyświetlaj liczby od 1 do 10 oraz nazwę wątku (w pętli for 10 razy). W klasie Test utworzyć i
// uruchomić 3 wątki (metoda start). Nadać wątkom nazwy korzystając z metody setName. W wątku,
// czyli w metodzie run() wyświetlać jego nazwę (getName) oraz licznik pętli. Uruchomić program
// kilkakrotnie, zaobserwować zachowanie się wątków. Dodaj losowe opóźnienie w zakresie od O do
// 100ms w pętli wątków i obserwuj zachowanie się wątków. Następnie dodaj dodatkowe opóźnienie
// 200ms w pętli tylko jednego wątku — ponownie uruchom kilkukrotnie program i obserwuj jego
// działanie.
// UWAGA. Przykładowe, częściowe rozwiązanie jest na końcu instrukcji — sięgamy po nie
// w ostateczności, jeżeli nie umiemy sobie poradzić z zadaniem.

package lab03_p.zad01;

public class Test {
    public static void main(String[] args) {
        Watek watek1 = new Watek();
        Watek watek2 = new Watek();
        Watek watek3 = new Watek();

        watek1.setName("T1");
        watek2.setName("T2");
        watek3.setName("T3");

        watek1.start();
        watek2.start();
        watek3.start();
    }
}
