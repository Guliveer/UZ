// Wczytaj kilka (x) liczb i wyznacz wartości min, max, średnią, oraz ilość podanych liczb.

package lab02_p;

public class Zad04 {
    public static void main(String[] args){
        // ask for x until pressed Return key

        int x;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0;

        System.out.println("Wcisnij 'Enter' aby zakończyć");
        while (true) {
            System.out.print("Podaj liczbę: ");
            String input = new java.util.Scanner(System.in).nextLine();
            if (input.equalsIgnoreCase("")) {
                break;
            }
            x = Integer.parseInt(input);
            min = Math.min(min, x);
            max = Math.max(max, x);
            sum += x;
            count++;
        }

        System.out.println();
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        if (count > 0) {
            System.out.println("Średnia: " + (double) sum / count);
        } else {
            System.out.println("Średnia: n/a");
        }
        System.out.println("Ilość: " + count);
    }
}
