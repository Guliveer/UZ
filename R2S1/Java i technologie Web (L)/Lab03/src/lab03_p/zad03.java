// Losujemy liczbę z zakresu od 1 do 200. Zadaniem użytkownika jest podanie właściwej
// wylosowanej liczby. Jeżeli wczytana liczba jest mniejsza od wylosowanej użytkownik
// zostanie o tym poinformowany, tak samo jak w przypadku podania wartości wyższej.
// W przypadku odgadnięcia wartości wylosowanej liczby użytkownik otrzyma
// odpowiedni komunikat.

package lab03_p;

import java.util.Scanner;

public class zad03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = (int) (Math.random() * 200 + 1);
        int guess;
        do {
            System.out.print("Enter number: ");
            guess = scanner.nextInt();
            if (guess < number) {
                System.out.println("Number is too small");
            } else if (guess > number) {
                System.out.println("Number is too big");
            }
        } while (guess != number);
        System.out.println("Congratulations! You guessed the number!");
    }
}