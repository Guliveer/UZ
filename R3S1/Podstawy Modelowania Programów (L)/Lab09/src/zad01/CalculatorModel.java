package zad01;

public class CalculatorModel {
    private static final int WARTOSC_POCZATKOWA = 1;
    private int lacznaWartosc;

    CalculatorModel() {
        lacznaWartosc = WARTOSC_POCZATKOWA;
    }

    public void resetuj() {
        lacznaWartosc = WARTOSC_POCZATKOWA;
    }

    public void dodajDo(int operand) {
        lacznaWartosc += operand;
    }

    public void odejmijOd(int operand) {
        lacznaWartosc -= operand;
    }

    public void pomnozPrzez(int operand) {
        lacznaWartosc *= operand;
    }

    public void podzielPrzez(int operand) {
        lacznaWartosc /= operand;
    }

    public int getLacznaWartosc() {
        return lacznaWartosc;
    }
}
