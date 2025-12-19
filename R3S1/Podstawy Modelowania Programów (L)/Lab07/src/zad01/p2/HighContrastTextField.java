package zad01.p2;

/**
 * Implementacja pola tekstowego w trybie wysokiego kontrastu dla niepełnosprawnych
 */
public class HighContrastTextField implements TextField {
    @Override
    public void draw() {
        System.out.println("... z polem tekstowym wysokiego kontrastu");
    }
}