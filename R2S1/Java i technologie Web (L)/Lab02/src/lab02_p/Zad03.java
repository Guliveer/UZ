// Opracuj kod służący do konwersji prędkości samochodu z mil na km/h

package lab02_p;

public class Zad03 {
    // colors variable declaration
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_COLOR = "\u001B[36m";

    // main program
    public static void main(String[] args) {
        // ask for mph
        System.out.print("Podaj prędkość w mph: ");
        double mph = new java.util.Scanner(System.in).nextDouble();

        // convert mph to km/h
        double convertingFactor = 1.609344;
        System.out.println(ANSI_COLOR + mph + ANSI_RESET + "mph -> " + ANSI_COLOR + (mph * convertingFactor) + ANSI_RESET + "km/h");
    }
}
