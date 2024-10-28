// Oliwer Pawelski, 24INF-SP/A

// Napisz program, który narysuje choinkę o wysokości podanej przez użytkownika

package lab02_p;

public class Zad01 {
    public static void main(String[] args) {
        int height;
        // ask for height
        System.out.print("Podaj wysokość choinki: ");
        height = new java.util.Scanner(System.in).nextInt(); // cin << wysokosc

        // draw tree
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
