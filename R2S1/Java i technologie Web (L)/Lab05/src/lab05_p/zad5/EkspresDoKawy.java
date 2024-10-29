package lab05_p.zad5;

public class EkspresDoKawy {
    private final Grzalka grzalkaDoWody;
    private final Grzalka grzalkaDoMleka;
    private final Pompa pompaWody;
    private final Pompa pompaMleka;
    private final Zbiornik zbiornikNaWode;
    private final Zbiornik zbiornikNaMleko;
    private final Zbiornik zbiornikNaKawe;
    private final Zbiornik zbiornikNaCukier;
    private final Mlynek mlynek;

    public EkspresDoKawy() {
        this.grzalkaDoWody = new Grzalka();
        this.grzalkaDoMleka = new Grzalka();
        this.pompaWody = new Pompa();
        this.pompaMleka = new Pompa();
        this.zbiornikNaWode = new Zbiornik("wody", 1000, "ml");
        this.zbiornikNaMleko = new Zbiornik("mleka", 500, "ml");
        this.zbiornikNaKawe = new Zbiornik("kawy", 100, "g");
        this.zbiornikNaCukier = new Zbiornik("cukru", 200, "g");
        this.mlynek = new Mlynek();
    }

    public void przygotujKawe(Kawa kawa) {
        System.out.println("Przygotowuję kawę: " + kawa.getNazwa());

        try {
            // Symulacja mielenia kawy
            mlynek.zmielKawa();
            pasekLadowania(5);

            // Symulacja zużywania wody i podgrzewania
            System.out.println("Podgrzewanie wody...");
            grzalkaDoWody.ustawTemperature(kawa.getTemperaturaWody());
            pasekLadowania(3);

            zbiornikNaWode.zuzyj(kawa.getIloscWody());
            pompaWody.wlaczPompe("woda");
            pasekLadowania(4);

            // Jeśli dodawane jest mleko, również podgrzewamy i zużywamy mleko
            if (kawa.isCzyMleko()) {
                System.out.println("Podgrzewanie mleka...");
                grzalkaDoMleka.ustawTemperature(kawa.getTemperaturaMleka());
                pasekLadowania(3);

                zbiornikNaMleko.zuzyj(kawa.getIloscMleka());
                pompaMleka.wlaczPompe("mleko");
                pasekLadowania(4);
            }

            // Zużycie cukru
            if (kawa.getIloscCukru() > 0) {
                System.out.println("Dodawanie cukru...");
                zbiornikNaCukier.zuzyj(kawa.getIloscCukru());
                pasekLadowania(2);
            }

            // Końcowe komunikaty
            System.out.println("Kawa gotowa: " + kawa.getNazwa());

        } catch (InterruptedException e) {
            System.out.println("Błąd podczas przygotowywania kawy: " + e.getMessage());
        }
    }

    // Pasek ładowania
    private void pasekLadowania(int sekundy) throws InterruptedException {
        System.out.print("[");
        for (int i = 0; i < sekundy * 10; i++) {
            System.out.print("=");
            Thread.sleep(100);  // Opóźnienie 100 ms dla każdego kroku (1/10 sekundy)
        }
        System.out.println("]");
    }
}
