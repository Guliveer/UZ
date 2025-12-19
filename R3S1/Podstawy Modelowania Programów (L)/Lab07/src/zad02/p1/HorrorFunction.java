package zad02.p1;

/**
 * Klasa zawierająca funkcję horror z 5 argumentami
 */
public class HorrorFunction {
    
    /**
     * Funkcja horror wypisująca na konsoli:
     * - wartość nazwa1
     * - iloraz a/b
     * - wartość nazwa2
     * - pierwiastek kwadratowy z x
     * 
     * @param a pierwszy argument całkowity (dzielna)
     * @param b drugi argument całkowity (dzielnik)
     * @param x argument zmiennoprzecinkowy (do pierwiastka)
     * @param nazwa1 pierwszy string
     * @param nazwa2 drugi string
     */
    public void horror(int a, int b, double x, String nazwa1, String nazwa2) {
        System.out.println(nazwa1);
        System.out.println((double) a / b);
        System.out.println(nazwa2);
        System.out.println(Math.sqrt(x));
    }
}