package lab03_p.zad02;

public class LicznikWatkow {
    private int wartoscKrytyczna;
    public synchronized int policzMnie(){
        wartoscKrytyczna++;
        wartoscKrytyczna--;
        return wartoscKrytyczna;
    }
}