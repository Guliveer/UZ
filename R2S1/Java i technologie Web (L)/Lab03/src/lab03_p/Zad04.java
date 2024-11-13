// Oliwer Pawelski, 24INF-SP/A

// Opracuj kod realizujący wczytywanie kolejnych liczb do momentu gdy podana
// zostanie wartość 5. Wyświetlić iloczyn podanych liczb.

package lab03_p;

import java.util.Scanner;

public class Zad04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int product = 1;
        int number;
        do {
            System.out.print("Enter number: ");
            number = scanner.nextInt();
			// Uncomment below to include 5 in the product
			
            // if (number == 5) {
            //     break;
            // }
            product *= number;
        } while (number != 5);
        System.out.println("Product: " + product);
    }
}
