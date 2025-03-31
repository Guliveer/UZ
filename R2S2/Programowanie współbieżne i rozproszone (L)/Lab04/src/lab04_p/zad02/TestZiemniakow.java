// Bazując na wykładzie (wykład 2, slajdy Sprawdzanie działania zarządcy zadań, i.e." Base.java") napisz program
// sprawdzający działanie zarządcy zadań w zależności od wartości priorytetów przydzielonych wątkom
// (metoda setPriority()). Uruchom kilka wątków i zmieniaj ustawienia priorytetów wątków. Wątek
// powinien być obciążony przez minimum 2-3 sekundy. Można w tym celu wykonywać dowolne
// obliczenia na wylosowanych liczbach np. wpętli 100 000 000 razy (mnożenie, dzielenie,pierwiastkowanie),
// aby zasymulować jego obciążenie. Jak zmienia się działanie wątków w zależności od różnych wartości priorytetów?

package lab04_p.zad02;

public class TestZiemniakow {
    public static void main(String[] args) {
        DzialajacyZiemniak1 dZ = new DzialajacyZiemniak1();
        new Thread(dZ, "Pierwszy ziemniak").start();
        new Thread(dZ, "Drugi ziemniak").start();
    }
}