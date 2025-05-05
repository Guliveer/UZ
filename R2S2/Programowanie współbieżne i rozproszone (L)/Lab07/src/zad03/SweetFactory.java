// Zmodyfikuj zad. 2 w taki sposób, aby fabryka słodyczy funkcjonowała w następujący sposób:
//
// - Cukiernik produkuje ciastka i kładzie je na taśmie produkcyjnej — cukierników jest wielu,
//
// - Pakowacz bierze dwa ciastka z taśmy produkcyjnej, zawija je w sreberko, po czym umieszcza
//   zawinięte dwa ciastka na drugiej taśmie produkcyjnej (zawinięte dwa ciastka traktowane są na
//   drugiej taśmie jako jeden produkt) — pakowaczy jest wiele,
//
// - z drugiej taśmy produkcyjnej zawinięte ciastka są pobierane przez Paleciarza (paleciarzy moze
//   być wiele), który po pobraniu czterech zawiniętych ciastek z drugiej taśmy produkcyjnej, pakuje je
//   na jedną paletę (4 zawinięte ciastka na jednej palecie). Po zapakowaniu, pełna paleta
//   umieszczana jest w magazynie wyrobów gotowych (magazyn o nieskończonej pojemności).
//   Wprowadzaj opóźnienia pracy cukiernika, pakowacza i paleciarza, wyświetlaj stosowne
//   komunikaty. Wykorzystaj kolejkę blokującą ArrayBlockingQueue.

package zad03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SweetFactory {
    private static final int BELT_CAPACITY = 5; // Capacity of each belt
    private static final int COOKIES_TODO = 96; // Total cookies to produce
    private static final int NUM_BAKERS = 3; // Number of bakers
    private static final int NUM_PACKERS = 2; // Number of packers
    private static final int NUM_PALLETIZERS = 1; // Number of palletizers

    public static void main(String[] args) {
        BlockingQueue<Integer> firstBelt = new ArrayBlockingQueue<>(BELT_CAPACITY);
        BlockingQueue<String> secondBelt = new ArrayBlockingQueue<>(BELT_CAPACITY);
        BlockingQueue<String> warehouse = new ArrayBlockingQueue<>(1000);
        AtomicInteger cookieCounter = new AtomicInteger(1);

        // Start bakers
        for (int i = 1; i <= NUM_BAKERS; i++) {
            new Thread(new Baker(firstBelt, cookieCounter, COOKIES_TODO), "Cukiernik-" + i).start();
        }

        // Start packers
        for (int i = 1; i <= NUM_PACKERS; i++) {
            new Thread(new Packer(firstBelt, secondBelt), "Pakowacz-" + i).start();
        }

        // Start palletizers
        for (int i = 1; i <= NUM_PALLETIZERS; i++) {
            new Thread(new Palletizer(secondBelt, warehouse), "Paleciarz-" + i).start();
        }
    }
}