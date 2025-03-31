// Bazując na wykładzie (wykład 2, slajdy Sprawdzanie działania zarządcy zadań) napisz program
// sprawdzający działanie zarządcy zadań w zależności od wartości priorytetów przydzielonych wątkom
// (metoda setPriority()). Uruchom kilka wątków i zmieniaj ustawienia priorytetów wątków. Wątek
// powinien być obciążony przez minimum 2-3 sekundy. Można w tym celu wykonywać dowolne
// obliczenia na wylosowanych liczbach np. wpętli 100 000 000 razy (mnożenie, dzielenie,pierwiastkowanie),
// aby zasymulować jego obciążenie. Jak zmienia się działanie wątków w zależności od różnych wartości priorytetów?

package lab04_p.zad02.result;

public class HeavyComputation implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 3000; // Run for at least 3 seconds
        double result = 0;
        while (System.currentTimeMillis() < endTime) {
            for (int i = 0; i < 100_000_000; i++) {
                result += Math.sqrt(i) * Math.random();
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished with result: " + result);
    }
}