package zad01;

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView widok;

    CalculatorController(CalculatorModel model, CalculatorView widok) {
        this.model = model;
        this.widok = widok;
        this.widok.zarejestrujCzyszczenie(new ObslugaCzyszczenia());
        this.widok.zarejestrujDodawanie(new ObslugaDodawania());
        this.widok.zarejestrujOdejmowanie(new ObslugaOdejmowania());
        this.widok.zarejestrujMnozenie(new ObslugaMnozenia());
        this.widok.zarejestrujDzielenie(new ObslugaDzielenia());
        widok.pokazLacznaWartosc(model.getLacznaWartosc());
    }

    class ObslugaCzyszczenia implements Zdarzenie {
        @Override
        public void uruchom() {
            model.resetuj();
            widok.pokazLacznaWartosc(model.getLacznaWartosc());
        }
    }

    class ObslugaDodawania implements Zdarzenie {
        @Override
        public void uruchom() {
            int skladnik = 0;
            try {
                skladnik = widok.pobierzLiczbe();
                model.dodajDo(skladnik);
                widok.pokazLacznaWartosc(model.getLacznaWartosc());
            } catch (NumberFormatException _) {
                widok.pokazBlad(" Kontroler :: Zła liczba : ’" + skladnik + "’");
            }
        }
    }

    class ObslugaOdejmowania implements Zdarzenie {
        @Override
        public void uruchom() {
            int odjemna = 0;
            try {
                odjemna = widok.pobierzLiczbe();
                model.odejmijOd(odjemna);
                widok.pokazLacznaWartosc(
                        model.getLacznaWartosc());
            } catch (NumberFormatException _) {
                widok.pokazBlad(" Kontroler :: Zła liczba : ’" + odjemna + "’");
            }
        }
    }

    class ObslugaMnozenia implements Zdarzenie {
        @Override
        public void uruchom() {
            int mnoznik = 0;
            try {
                mnoznik = widok.pobierzLiczbe();
                model.pomnozPrzez(mnoznik);
                widok.pokazLacznaWartosc(
                        model.getLacznaWartosc());
            } catch (NumberFormatException _) {
                widok.pokazBlad(" Kontroler :: Zła liczba : ’" + mnoznik + "’");
            }
        }
    }

    class ObslugaDzielenia implements Zdarzenie {
        @Override
        public void uruchom() {
            int dzielnik = 1;
            try {
                dzielnik = widok.pobierzLiczbe();
                model.podzielPrzez(dzielnik);
                widok.pokazLacznaWartosc(
                        model.getLacznaWartosc());
            } catch (NumberFormatException _) {
                widok.pokazBlad(" Kontroler :: Zła liczba : ’" + dzielnik + "’");
            } catch (ArithmeticException _) {
                widok.pokazBlad(" Kontroler :: Dzielenie przez zero !");
            }
        }
    }
}
