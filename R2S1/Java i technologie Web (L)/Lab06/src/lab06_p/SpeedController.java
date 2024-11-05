package lab06_p;

public class SpeedController {
    private int spinSpeed;

    public void setSpinSpeed(int rpm) {
        this.spinSpeed = rpm;
        ControlPanel.showStatus("Setting spin speed to " + spinSpeed + " RPM...");
    }

    public void startSpinning() {
        ControlPanel.showStatus("Spinning to " + spinSpeed + " RPM...");
        // Symulacja wirowania
        ControlPanel.loading(5000);
    }

    public void stopSpinning() {
        ControlPanel.showStatus("Stopping the drum...");
        // Symulacja zatrzymania wirowania
        ControlPanel.loading(5500);
        ControlPanel.show("Drum stopped.");
    }
}
