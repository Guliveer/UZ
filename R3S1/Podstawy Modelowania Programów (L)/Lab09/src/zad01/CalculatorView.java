package zad01;

import java.util.Scanner;

public class CalculatorView {
    Scanner klawiatura = new Scanner(System.in);
    Zdarzenie zainteresowanyCzyszczeniem;
    Zdarzenie zainteresowanyDodawaniem;
    Zdarzenie zainteresowanyOdejmowanie;
    Zdarzenie zainteresowanyMnozeniem;
    Zdarzenie zainteresowanyDzielenie;

    CalculatorView() {
        zainteresowanyCzyszczeniem = null;
        zainteresowanyDodawaniem = null;
        zainteresowanyOdejmowanie = null;
        zainteresowanyMnozeniem = null;
        zainteresowanyDzielenie = null;
    }

    void zarejestrujDodawanie(Zdarzenie zdarzenie) {
        zainteresowanyDodawaniem = zdarzenie;
    }

    void zarejestrujOdejmowanie(Zdarzenie zdarzenie) {
        zainteresowanyOdejmowanie = zdarzenie;
    }

    void zarejestrujMnozenie(Zdarzenie zdarzenie) {
        zainteresowanyMnozeniem = zdarzenie;
    }

    void zarejestrujDzielenie(Zdarzenie zdarzenie) {
        zainteresowanyDzielenie = zdarzenie;
    }

    void zarejestrujCzyszczenie(Zdarzenie zdarzenie) {
        zainteresowanyCzyszczeniem = zdarzenie;
    }

    public void menu() {
        int opcja;
        do {
            System.out.println("\n View :: Wybierz opcje :");
            System.out.println(" View :: 0 - wyjscie");
            System.out.println(" View :: 9 - czyszczenie");
            System.out.println(" View :: 1 - dodawanie");
            System.out.println(" View :: 2 - odejmowanie");
            System.out.println(" View :: 3 - mnozenie");
            System.out.println(" View :: 4 - dzielenie");
            System.out.print(" View :: Wybor -> ");

            opcja = klawiatura.nextInt();
            switch (opcja) {
                case 0:
                    break;
                case 9:
                    zainteresowanyCzyszczeniem.uruchom();
                    break;
                case 1:
                    zainteresowanyDodawaniem.uruchom();
                    break;
                case 2:
                    zainteresowanyOdejmowanie.uruchom();
                    break;
                case 3:
                    zainteresowanyMnozeniem.uruchom();
                    break;
                case 4:
                    zainteresowanyDzielenie.uruchom();
                    break;
                default:
                    System.err.println(" View :: Bledna opcja ");
            }
        } while (opcja != 0);
        System.out.println(" View :: Koniec programu !");
    }

    int pobierzLiczbe() {
        System.out.print(" View :: Podaj wartosc :");
        return klawiatura.nextInt();
    }

    void pokazLacznaWartosc(int lacznaWartosc) {
        System.out.println(" View :: Laczna war. :" + lacznaWartosc);
    }

    void pokazBlad(String errMessage) {
        System.err.println(" View :: Blad :" + errMessage);
    }
}
