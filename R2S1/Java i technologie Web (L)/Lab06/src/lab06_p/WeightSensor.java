package lab06_p;

public class WeightSensor {
    private int weight; // Waga załadunku w gramach

    public void readWeight() {
        // Symulacja odczytu wagi
        this.weight = 5000; // przykładowa wartość w gramach
        ControlPanel.show("Load weight: " + weight + " grams");
    }

    public boolean isWeightSafe() {
        // Maksymalna waga dla bębna to np. 7 kg (7000 gramów)
        return weight <= 7000;
    }

    public double measureWeight() {
        readWeight();
        return weight / 1000.0; // Zwracanie wagi w kg
    }
}
