package lab06_p;

import java.util.concurrent.TimeUnit;

public class TemperatureSensor {
    private double currentTemperature; // Pole przechowujące aktualną temperaturę

    public TemperatureSensor() {
        this.currentTemperature = 20.0; // Początkowa temperatura wody w °C
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double temperature) {
        this.currentTemperature = temperature;
    }

    /**
     * Metoda sprawdzająca, czy osiągnięto docelową temperaturę
     * @param targetTemperature docelowa temperatura, którą chcemy osiągnąć
     * @return true, jeśli aktualna temperatura jest większa lub równa docelowej, false w przeciwnym razie
     */
    public boolean checkTemperature(double targetTemperature) {
        return currentTemperature >= targetTemperature;
    }

    // Symulacja wzrostu temperatury wody
    public void heatWater(double targetTemperature) throws InterruptedException {
        while (currentTemperature < targetTemperature) {
            currentTemperature += 1.0; // Stopniowy wzrost temperatury (można dostosować)
            TimeUnit.MILLISECONDS.sleep(500); // Czas oczekiwania dla symulacji
            ControlPanel.show("Current temperature: " + currentTemperature + "°C");
        }
    }
}
