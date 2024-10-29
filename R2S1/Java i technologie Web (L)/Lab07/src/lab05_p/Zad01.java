// Oliwer Pawelski, 24INF-SP/A

// Utwórz klasę Kalkulator, która będzie posiadała konstruktory w wersjach 1
// agrumentowej, 4 agrumentowej i 7 argumentowej oraz metodę liczącą i zwracająca
// wynik operacji matematycznej. Dodatkowo konstruktor kopiujący klasy zawierający
// informacje o liczbach wykorzystanych do wykonywania operacji.

package lab05_p;

public class Zad01 {
    public static void main(String[] args) {
        // Przykład użycia klasy Kalkulator
        Kalkulator kalk1 = new Kalkulator(5);
        System.out.println("Wynik dodawania: " + kalk1.oblicz("dodaj"));

        Kalkulator kalk2 = new Kalkulator(5, 3, 2, 1);
        System.out.println("Wynik odejmowania: " + kalk2.oblicz("odejmij"));

        Kalkulator kalk3 = new Kalkulator(2, 3, 4, 5, 6, 7, 8);
        System.out.println("Wynik mnożenia: " + kalk3.oblicz("pomnóż"));

        Kalkulator kalk4 = new Kalkulator(kalk3);
        System.out.println("Wynik dzielenia: " + kalk4.oblicz("podziel"));
    }

    public static class Kalkulator {
        private final double[] liczby;

        // Konstruktor 1-argumentowy
        public Kalkulator(double a) {
            liczby = new double[1];
            liczby[0] = a;
        }

        // Konstruktor 4-argumentowy
        public Kalkulator(double a, double b, double c, double d) {
            liczby = new double[4];
            liczby[0] = a;
            liczby[1] = b;
            liczby[2] = c;
            liczby[3] = d;
        }

        // Konstruktor 7-argumentowy
        public Kalkulator(double a, double b, double c, double d, double e, double f, double g) {
            liczby = new double[7];
            liczby[0] = a;
            liczby[1] = b;
            liczby[2] = c;
            liczby[3] = d;
            liczby[4] = e;
            liczby[5] = f;
            liczby[6] = g;
        }

        // Konstruktor kopiujący
        public Kalkulator(Kalkulator inny) {
            this.liczby = new double[inny.liczby.length];
            System.arraycopy(inny.liczby, 0, this.liczby, 0, inny.liczby.length);
        }

        // Metoda licząca
        public double oblicz(String operacja) {
            double wynik = 0;

            switch (operacja) {
                case "dodaj":
                    for (double liczba : liczby) {
                        wynik += liczba;
                    }
                    break;
                case "odejmij":
                    wynik = liczby[0];
                    for (int i = 1; i < liczby.length; i++) {
                        wynik -= liczby[i];
                    }
                    break;
                case "pomnóż":
                    wynik = 1;
                    for (double liczba : liczby) {
                        wynik *= liczba;
                    }
                    break;
                case "podziel":
                    if (liczby.length > 0) {
                        wynik = liczby[0];
                        for (int i = 1; i < liczby.length; i++) {
                            if (liczby[i] != 0) {
                                wynik /= liczby[i];
                            } else {
                                throw new ArithmeticException("Nie można dzielić przez zero.");
                            }
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Nieznana operacja.");
            }
            return wynik;
        }
    }
}
