package lab06_p;

public class Heater {
    private int targetTemperature;

    public void setTemperature(int temperature) {
        this.targetTemperature = temperature;
    }

    public void heatWater(TemperatureSensor sensor) {
        ControlPanel.showStatus("Heating water to " + targetTemperature + "°C...");
        // Symulacja procesu grzania
        ControlPanel.loading(7000);
        sensor.setCurrentTemperature(targetTemperature);
        ControlPanel.showSuccess("Water heated to " + targetTemperature + "°C");
    }
}

