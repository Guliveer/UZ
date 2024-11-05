package lab06_p;

import java.util.Random;

public class FilterSensor {
    private boolean isClear;

    public FilterSensor() {
        // Inicjalizacja stanu filtra, zakładamy, że filtr jest czysty na początku
        this.isClear = true;
    }

    public boolean isFilterClear() {
        // Symulacja stanu filtra - w prawdziwej aplikacji tu byłyby realne dane
        // np. oparte na liczbie cykli lub losowe dla symulacji
        isClear = new Random().nextBoolean(); // Losowe przypisanie stanu filtra
        return isClear;
    }

    public void cleanFilter() {
        ControlPanel.showStatus("Cleaning the filter...");
        ControlPanel.loading(5000);
        // Ustawienie stanu filtra jako czysty
        isClear = true;
        ControlPanel.showSuccess("Filter has been cleaned.");
    }
}
