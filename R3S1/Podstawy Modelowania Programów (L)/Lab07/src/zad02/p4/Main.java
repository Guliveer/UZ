package zad02.p4;

/**
 * Główna klasa demonstrująca funkcję horror z wzorcem Fluent Builder
 */
public class Main {
    public static void main(String[] args) {
        HorrorFunction horrorFunction = new HorrorFunction();
        
        // Poprawne wywołanie z Fluent Builder
        System.out.println("=== Poprawne wywołanie z Fluent Builder ===");
        try {
            HorrorParameters params = new HorrorParameters.Builder()
                .withDividend(10)
                .withDivisor(3)
                .withValueForSquareRoot(16.0)
                .withLabelBeforeDivision("Etykieta przed dzieleniem")
                .withLabelBeforeSquareRoot("Etykieta przed pierwiastkiem")
                .build();
            
            horrorFunction.horror(params);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        
        System.out.println();
        
        // Wywołanie z brakującym parametrem - powinno rzucić wyjątek
        System.out.println("=== Wywołanie z brakującym parametrem (bez divisor) ===");
        try {
            HorrorParameters paramsIncomplete = new HorrorParameters.Builder()
                .withDividend(10)
                // brak withDivisor()
                .withValueForSquareRoot(16.0)
                .withLabelBeforeDivision("Etykieta przed dzieleniem")
                .withLabelBeforeSquareRoot("Etykieta przed pierwiastkiem")
                .build();
            
            horrorFunction.horror(paramsIncomplete);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        
        System.out.println();
        
        // Wywołanie z pustym builderem - powinno rzucić wyjątek
        System.out.println("=== Wywołanie z pustym builderem ===");
        try {
            HorrorParameters emptyParams = new HorrorParameters.Builder().build();
            horrorFunction.horror(emptyParams);
        } catch (MissingParameterException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}