// Oliwer Pawelski, 24INF-SP/A

// W salonie samochodowym zaoferowano sprzedaż leasingową pojazdów. Napisz kod,
// który policzy miesięczne zobowiązanie z uwzględnieniem odsetek i wpłaconego kapitału własnego.
// Dane wejściowe to maksymalna kwota: 300 tyś PLN, liczba rat do 96.
// Oprocentowanie wynosi:
//        do 24 miesięcy 2%
//        do 48 miesięcy 3%
//        do 60 miesięcy 4%
//        do 72 miesięcy 5%
//        do 96 miesięcy 6%

package lab02_p;

import java.util.Scanner;

public class Zad06 {
    private static double calculateInterestRate(int months) {
        if (1 < months && months <= 24) return 0.02;
        if (24 < months && months <= 48) return 0.03;
        if (48 < months && months <= 60) return 0.04;
        if (60 < months && months <= 72) return 0.05;
        if (72 < months && months <= 96) return 0.06;
        throw new IllegalArgumentException("Liczba miesięcy poza zakresem (1 - 96).");
    }

    private static double calculateMonthlyPayment(double totalPrice, double ownContribution, int months) {
        return ((totalPrice - ownContribution) * (1 + calculateInterestRate(months))) / months;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dane wejściowe
        double maxPrice = 300000; // 300 tyś PLN
        System.out.print("Podaj kwotę całkowitą (PLN): ");
        double price = scanner.nextDouble();

        if (price > maxPrice) {
            throw new IllegalArgumentException("Kwota całkowita przekracza maksymalną (" + maxPrice + ")");
        }

        System.out.print("Podaj kwotę kapitału własnego (PLN): ");
        double ownContribution = scanner.nextDouble();
        System.out.print("Podaj liczbę miesięcy spłaty (max 96): ");
        int months = scanner.nextInt();

        // Obliczenie miesięcznego zobowiązania
        double monthlyPayment = calculateMonthlyPayment(price, ownContribution, months);

        // Wynik
        System.out.printf("Miesięczna rata leasingowa wynosi: %.2f PLN%n", monthlyPayment);
    }
}
