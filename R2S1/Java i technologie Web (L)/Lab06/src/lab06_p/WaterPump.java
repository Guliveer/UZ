package lab06_p;

public class WaterPump {
    public void pumpWater() {
        ControlPanel.showStatus("Pumping water into the drum...");
        // Symulacja pompowania wody
        ControlPanel.loading(4000);
    }

    public void drainWater() {
        ControlPanel.showStatus("Draining water from the drum...");
        // Symulacja odprowadzania wody
        ControlPanel.loading(10000);
    }
}
