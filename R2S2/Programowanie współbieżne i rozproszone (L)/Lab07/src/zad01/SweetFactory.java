// W fabryce słodyczy Sweet produkuje się ciastka. W fabryce pracują dwie osoby: Cukiernik i
// Pakowacz (cukiernik i pakowacz to dwa osobne wątki). Zadaniem cukiernika jest upieczenie
// (wyprodukowanie) ciastka, zaś zadaniem pakowacza jest zapakowanie ciastka do kartonu. Każde
// ciastko jest takie samo, w programie reprezentowane jako wartość liczbowa typu Integer (zakładamy
// że każde ciastko ma inną wartość — rodzaj numeru seryjnego, moze to być kolejno 1, 2, 3, 4 ....).
//
// Centralnym punktem w fabryce jest taśma produkcyjna. Każde wyprodukowane ciastko trafia na
// taśmę produkcyjną. Z taśmy ciastka pojedynczo są zabierane przez pakowacza, który pakuje ciastko
// do kartonu (zakładamy, że karton ma nieskończoną pojemność). Taśma produkcyjna może
// pomieścić jednocześnie N ciastek. Podczas produkcji żadne ciastko nie może spaść z taśmy — jeżeli
// pakowacz zbyt wolno pakuje, cukiernik musi spowolnić lub zatrzymać swoją pracę (bo taśma jest
// pełna). Jeżeli zaś pakowacz pakuje szybciej niż cukiernik produkuje, wówczas pakowacz oczekuje
// na pracę cukiernika (bo taśma jest pusta).
//
// Napisz program symulujący działanie fabryki Sweet.
// Wprowadzaj opóźnienia pracy cukiernika i pakowacza, wyświetlaj stosowne komunikaty. Wykorzystaj
// kolejkę blokującą ArrayBlockingQueue oraz metody put(), take().

package zad01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SweetFactory {
    private static final int BELT_CAPACITY = 5; // Maksymalna pojemność taśmy produkcyjnej
    private static final BlockingQueue<Integer> productionBelt = new ArrayBlockingQueue<>(BELT_CAPACITY);
    private static final int COOKIE_COUNT = 10; // Liczba ciastek do upieczenia i zapakowania

    public static void main(String[] args) {
        Thread baker = new Thread(new Baker(productionBelt, COOKIE_COUNT));
        Thread packer = new Thread(new Packer(productionBelt, COOKIE_COUNT));

        baker.start();
        packer.start();
    }
}
