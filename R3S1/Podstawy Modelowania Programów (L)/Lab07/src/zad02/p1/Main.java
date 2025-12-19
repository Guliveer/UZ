package zad02.p1;

/**
 * Główna klasa demonstrująca funkcję horror z 5 argumentami
 */
public class Main {
    public static void main(String[] args) {
        HorrorFunction horrorFunction = new HorrorFunction();
        
        // Przykładowe wywołanie funkcji horror
        int a = 10;
        int b = 3;
        double x = 16.0;
        String nazwa1 = "Pierwsza nazwa";
        String nazwa2 = "Druga nazwa";
        
        System.out.println("=== Wywołanie funkcji horror ===");
        horrorFunction.horror(a, b, x, nazwa1, nazwa2);
    }
}