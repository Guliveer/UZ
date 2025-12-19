package zad02.p3;

/**
 * Główna klasa demonstrująca funkcję horror z walidacją parametrów
 */
public class Main {
    public static void main(String[] args) {
        HorrorFunction horrorFunction = new HorrorFunction();
        
        // Poprawne wywołanie z wszystkimi parametrami
        System.out.println("=== Poprawne wywołanie z wszystkimi parametrami ===");
        try {
            HorrorParameters params = new HorrorParameters(
                10,                          // dividend (dzielna)
                3,                           // divisor (dzielnik)
                16.0,                        // valueForSquareRoot (wartość do pierwiastka)
                "Etykieta przed dzieleniem", // labelBeforeDivision
                "Etykieta przed pierwiastkiem" // labelBeforeSquareRoot
            );
            horrorFunction.horror(params);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        
        System.out.println();
        
        // Wywołanie z brakującym parametrem - powinno rzucić wyjątek
        System.out.println("=== Wywołanie z brakującym parametrem (null) ===");
        try {
            HorrorParameters paramsWithNull = new HorrorParameters(
                10,                          // dividend (dzielna)
                null,                        // divisor - BRAK!
                16.0,                        // valueForSquareRoot (wartość do pierwiastka)
                "Etykieta przed dzieleniem", // labelBeforeDivision
                "Etykieta przed pierwiastkiem" // labelBeforeSquareRoot
            );
            horrorFunction.horror(paramsWithNull);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        
        System.out.println();
        
        // Wywołanie z brakującym stringiem
        System.out.println("=== Wywołanie z brakującym stringiem (null) ===");
        try {
            HorrorParameters paramsWithNullString = new HorrorParameters(
                10,                          // dividend (dzielna)
                3,                           // divisor (dzielnik)
                16.0,                        // valueForSquareRoot (wartość do pierwiastka)
                null,                        // labelBeforeDivision - BRAK!
                "Etykieta przed pierwiastkiem" // labelBeforeSquareRoot
            );
            horrorFunction.horror(paramsWithNullString);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}