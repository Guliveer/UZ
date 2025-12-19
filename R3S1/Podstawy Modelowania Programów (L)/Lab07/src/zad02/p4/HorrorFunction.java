package zad02.p4;

/**
 * Klasa zawierająca funkcję horror z 5 argumentami oraz wersję z obiektem parametrów
 */
public class HorrorFunction {
    
    /**
     * Oryginalna funkcja horror z 5 argumentami
     */
    public void horror(int a, int b, double x, String nazwa1, String nazwa2) {
        System.out.println(nazwa1);
        System.out.println((double) a / b);
        System.out.println(nazwa2);
        System.out.println(Math.sqrt(x));
    }
    
    /**
     * Nowa funkcja horror przyjmująca obiekt parametrów z sensownymi nazwami pól.
     * Wywołuje oryginalną funkcję horror.
     * 
     * @param params obiekt zawierający wszystkie parametry funkcji
     */
    public void horror(HorrorParameters params) {
        horror(
            params.getDividend(),
            params.getDivisor(),
            params.getValueForSquareRoot(),
            params.getLabelBeforeDivision(),
            params.getLabelBeforeSquareRoot()
        );
    }
}