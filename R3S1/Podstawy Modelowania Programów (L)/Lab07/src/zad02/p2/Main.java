package zad02.p2;

/**
 * Główna klasa demonstrująca funkcję horror z klasą parametrów
 */
public class Main {
    public static void main(String[] args) {
        HorrorFunction horrorFunction = new HorrorFunction();
        
        // Tworzenie obiektu parametrów z sensownymi nazwami
        HorrorParameters params = new HorrorParameters(
            10,                          // dividend (dzielna)
            3,                           // divisor (dzielnik)
            16.0,                        // valueForSquareRoot (wartość do pierwiastka)
            "Etykieta przed dzieleniem", // labelBeforeDivision
            "Etykieta przed pierwiastkiem" // labelBeforeSquareRoot
        );
        
        System.out.println("=== Wywołanie funkcji horror z obiektem parametrów ===");
        horrorFunction.horror(params);
    }
}