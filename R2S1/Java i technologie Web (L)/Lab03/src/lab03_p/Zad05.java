// Oliwer Pawelski, 24INF-SP/A

// Po wczytaniu nieujemnej liczby całkowitej wyświetlone zostaną wszystkie wartości
// potęgi liczby 3 mniejsze niż wartość wczytana.

package lab03_p;

import java.util.Scanner;

public class Zad05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = scanner.nextInt();

        int power = 1;
        int i = 0;
        while (power < number) {
            power = (int) Math.pow(3, i);
            if (power < number) {
                System.out.println(power);
            }
            i++;
        }
    }
}
