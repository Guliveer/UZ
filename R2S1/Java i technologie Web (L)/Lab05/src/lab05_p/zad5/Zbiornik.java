package lab05_p.zad5;

public class Zbiornik {
    private final String zawartosc;
    private final String jednostka;
    private double poziom;

    public Zbiornik(String zawartosc, double poziom, String jednostka) {
        this.zawartosc = zawartosc;
        this.poziom = poziom;
        this.jednostka = jednostka;
    }

    public double getPoziom() {
        return poziom;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void zuzyj(double ilosc) {
        if (ilosc > poziom) {
            throw new RuntimeException("Za mało " + zawartosc);
        }
        poziom -= ilosc;
        System.out.println("Zużyto " + ilosc + " " + jednostka + " " + zawartosc);
    }

    public void dodaj(double ilosc) {
        poziom += ilosc;
        System.out.println("Dodano " + ilosc + " " + jednostka + " " + zawartosc);
    }

    @Override
    public String toString() {
        return "Zbiornik na " + zawartosc + ": " + poziom + " " + jednostka;
    }
}
