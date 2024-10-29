package lab05_p.zad5;

public class Pompa {
    private double cisnienie;  // ciśnienie w barach

    public Pompa() {
        this.cisnienie = 0;  // Domyślne ciśnienie
    }

    public void wlaczPompe(String typ) {
        System.out.println("Włączam pompę dla: " + typ);
        // Logika włączania pompy
        // Tutaj można dodać symulację pracy pompy
    }

    public void ustawCisnienie(double cisnienie) {
        this.cisnienie = cisnienie;
        System.out.println("Ustawione ciśnienie pompy: " + cisnienie + " barów");
    }

    public double getCisnienie() {
        return cisnienie;
    }
}

