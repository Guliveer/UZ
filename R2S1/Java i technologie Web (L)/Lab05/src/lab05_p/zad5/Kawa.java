package lab05_p.zad5;

public class Kawa {
    private final String nazwa;
    private double iloscWody;
    private double iloscMleka;
    private double iloscKawy;
    private double iloscCukru;
    private double temperaturaWody;
    private double temperaturaMleka;
    private boolean czyMleko;

    public Kawa(String nazwa, double iloscWody, double iloscMleka, double iloscKawy, double iloscCukru, double temperaturaWody, double temperaturaMleka, boolean czyMleko) {
        this.nazwa = nazwa;
        this.iloscWody = iloscWody;
        this.iloscMleka = iloscMleka;
        this.iloscKawy = iloscKawy;
        this.iloscCukru = iloscCukru;
        this.temperaturaWody = temperaturaWody;
        this.temperaturaMleka = temperaturaMleka;
        this.czyMleko = czyMleko;
    }

    // Gettery i Settery
    public String getNazwa() {
        return nazwa;
    }

    public double getIloscWody() {
        return iloscWody;
    }

    public void setIloscWody(double iloscWody) {
        this.iloscWody = iloscWody;
    }

    public double getIloscMleka() {
        return iloscMleka;
    }

    public void setIloscMleka(double iloscMleka) {
        this.iloscMleka = iloscMleka;
    }

    public double getIloscKawy() {
        return iloscKawy;
    }

    public void setIloscKawy(double iloscKawy) {
        this.iloscKawy = iloscKawy;
    }

    public double getIloscCukru() {
        return iloscCukru;
    }

    public void setIloscCukru(double iloscCukru) {
        this.iloscCukru = iloscCukru;
    }

    public double getTemperaturaWody() {
        return temperaturaWody;
    }

    public void setTemperaturaWody(double temperaturaWody) {
        this.temperaturaWody = temperaturaWody;
    }

    public double getTemperaturaMleka() {
        return temperaturaMleka;
    }

    public void setTemperaturaMleka(double temperaturaMleka) {
        this.temperaturaMleka = temperaturaMleka;
    }

    public boolean isCzyMleko() {
        return czyMleko;
    }

    public void setCzyMleko(boolean czyMleko) {
        this.czyMleko = czyMleko;
    }
}