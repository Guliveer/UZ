package lab06_p;

public class PressureSensor {
    private int pressure; // Aktualne ciśnienie wody

    public void readPressure() {
        // Symulacja odczytu ciśnienia
        this.pressure = 30; // przykładowa wartość w kPa
        ControlPanel.show("Water pressure: " + pressure + " kPa");
    }

    public boolean isPressureSafe() {
        // Załóżmy, że bezpieczne ciśnienie to 20-40 kPa
        boolean isSafe = pressure >= 20 && pressure <= 40;
        if (pressure < 20) {
            ControlPanel.showWarning("Low water pressure! Check water supply.");
        } else if (pressure > 40) {
            ControlPanel.showStatus("High water pressure! Reducing water flow.");
        }
        return isSafe;
    }

    public double getPressure() {
        readPressure();
        return pressure;
    }
}
