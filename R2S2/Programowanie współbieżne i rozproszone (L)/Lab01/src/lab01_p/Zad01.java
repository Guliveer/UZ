// Napisz program typu HelloWorld i uruchom napisaną aplikację przy użyciu środowiska IntelliJ IDEA.
// Dodaj do programu wyświetlanie strzałki zbudowanej z gwiazdek.

/*
        *
       ***
      *****
     *******
    *********
   ***********
        *
        *
        *
        *
*/

package lab01_p;

public class Zad01 {
    public static void main(String[] args) {
        int n = 6; // wysokość grotu
        for (int i = 1; i <= n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k < (i * 2); k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < 3; i++) { // dolna część strzałki
            for (int j = 0; j < n - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
    }
}
