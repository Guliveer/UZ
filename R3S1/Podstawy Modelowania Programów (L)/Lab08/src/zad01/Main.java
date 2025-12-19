package zad01;

import java.util.Scanner;

/**
 * Aplikacja konsolowa symulująca maszynę stanów.
 *
 * NOWY Diagram stanów (cykliczny - bez stanów końcowych):
 *
 * A -> B [liczba > 5]      (wymaga liczby)
 * A -> C [liczba = 5]      (wymaga liczby)
 * A -> E [liczba < 5]      (wymaga liczby)
 * B -> C [tekst = "Ala"]   (wymaga tekstu)
 * B -> D [tekst != "Ala"]  (wymaga tekstu)
 * C -> E [liczba = 4]      (wymaga liczby)
 * C -> F [liczba != 4]     (wymaga liczby)
 * D -> C []                (bezwarunkowe - dowolna wartość)
 * E -> D []                (bezwarunkowe - dowolna wartość)
 * F -> A []                (bezwarunkowe - powrót do początku)
 *
 * Użytkownik wpisuje wartość, system automatycznie:
 * - Próbuje sparsować jako liczbę → ustawia zmienną liczba
 * - Jeśli nie jest liczbą → ustawia zmienną tekst
 * Po ustawieniu wartości, stan sprawdza wszystkie przejścia i wykonuje pierwsze pasujące.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║       SYMULATOR MASZYNY STANÓW - Wzorzec State Pattern         ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Diagram stanów (CYKLICZNY):                                    ║");
        System.out.println("║                                                                ║");
        System.out.println("║   A -> B [liczba > 5]       (wymaga liczby)                    ║");
        System.out.println("║   A -> C [liczba = 5]       (wymaga liczby)                    ║");
        System.out.println("║   A -> E [liczba < 5]       (wymaga liczby)                    ║");
        System.out.println("║   B -> C [tekst = \"Ala\"]    (wymaga tekstu)                    ║");
        System.out.println("║   B -> D [tekst != \"Ala\"]   (wymaga tekstu)                    ║");
        System.out.println("║   C -> E [liczba = 4]       (wymaga liczby)                    ║");
        System.out.println("║   C -> F [liczba != 4]      (wymaga liczby)                    ║");
        System.out.println("║   D -> C []                 (bezwarunkowe)                     ║");
        System.out.println("║   E -> D []                 (bezwarunkowe)                     ║");
        System.out.println("║   F -> A []                 (bezwarunkowe - powrót do A)       ║");
        System.out.println("║                                                                ║");
        System.out.println("║ Wpisz 'quit' lub 'exit' aby zakończyć                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        try (Scanner scanner = new Scanner(System.in)) {
            Context context = new Context(scanner);
            context.run();
        }
        
        System.out.println("\nDziękujemy za korzystanie z symulatora!");
    }
}