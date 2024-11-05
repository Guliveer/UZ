package lab06_p;

public class Drum {
    private int weight; // Waga załadowanych ubrań w gramach

    public void loadClothes(int weight) {
        this.weight = weight;
        ControlPanel.show("Clothes loaded with weight: " + weight + " grams.");
    }

    public void balance() {
        ControlPanel.showStatus("Balancing the drum...");
        ControlPanel.loading(3000);
        // Symulacja procesu wyważania
    }

    public int getWeight() {
        return weight;
    }
}
