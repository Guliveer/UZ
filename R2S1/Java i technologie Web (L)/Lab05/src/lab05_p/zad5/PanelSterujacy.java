package lab05_p.zad5;

import java.util.Scanner;

public class PanelSterujacy {
    private final EkspresDoKawy ekspres;

    public PanelSterujacy(EkspresDoKawy ekspres) {
        this.ekspres = ekspres;
    }

    public void wybierzKawe() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz kawę:");
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.println("3. Cappuccino");
        System.out.println("4. Americano");
        System.out.println("5. Mocha");

        int wybor = scanner.nextInt();
        Kawa kawa = null;

        switch (wybor) {
            case 1:
                kawa = new Kawa("Espresso", 50, 0, 18, 0, 90, 0, false);
                break;
            case 2:
                kawa = new Kawa("Latte", 50, 200, 18, 10, 90, 65, true);
                break;
            case 3:
                kawa = new Kawa("Cappuccino", 50, 150, 18, 5, 90, 65, true);
                break;
            case 4:
                kawa = new Kawa("Americano", 100, 0, 18, 0, 85, 0, false);
                break;
            case 5:
                kawa = new Kawa("Mocha", 50, 150, 18, 10, 90, 65, true);
                break;
            default:
                System.out.println("Niepoprawny wybór!");
                return;
        }

        // Pozwól użytkownikowi dostosować ilość składników
        System.out.println("Wprowadź ilość kawy (w gramach): ");
        kawa.setIloscKawy(scanner.nextDouble());

        System.out.println("Czy chcesz dodać mleko? (T/N)");
        char mlekoWybor = scanner.next().toUpperCase().charAt(0);
        if (mlekoWybor == 'T') {
            kawa.setCzyMleko(true);
            System.out.println("Wprowadź ilość mleka (w ml): ");
            kawa.setIloscMleka(scanner.nextDouble());

            System.out.println("Wprowadź temperaturę mleka: ");
            kawa.setTemperaturaMleka(scanner.nextDouble());
        } else {
            kawa.setCzyMleko(false);
        }

        System.out.println("Wprowadź ilość cukru (w gramach): ");
        kawa.setIloscCukru(scanner.nextDouble());

        ekspres.przygotujKawe(kawa);

        System.out.println();
    }
}
