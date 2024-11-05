package lab06_p;

import java.util.concurrent.TimeUnit;

public class WashingProgram {
    private final int temperature;
    private final int preWashTime;
    private final int mainWashTime;
    private final boolean extraRinsing;
    private final int spinSpeed;

    public WashingProgram(int temperature, int preWashTime, int mainWashTime, boolean extraRinsing, int spinSpeed) {
        ControlPanel.showStatus("Starting washing program...");
        this.temperature = temperature;
        this.preWashTime = preWashTime;
        this.mainWashTime = mainWashTime;
        this.extraRinsing = extraRinsing;
        this.spinSpeed = spinSpeed;
        ControlPanel.loading(3000);
    }

    // Statyczne metody dla gotowych programów prania
    public static WashingProgram dailyWash() {
        return new WashingProgram(40, 10, 30, false, 800); // Przykład: 40°C, krótki czas
    }

    public static WashingProgram delicateWash() {
        return new WashingProgram(30, 5, 20, true, 600); // Przykład: delikatne pranie z niską prędkością
    }

    public static WashingProgram expressWash() {
        return new WashingProgram(20, 0, 15, false, 1000); // Przykład: szybkie pranie
    }

    public static WashingProgram heavyDutyWash() {
        return new WashingProgram(60, 15, 45, true, 1200); // Przykład: pranie ciężkie z dodatkowym płukaniem
    }

    public static WashingProgram ecoWash() {
        return new WashingProgram(30, 10, 25, true, 700); // Przykład: ekologiczne pranie
    }

    public int getTemperature() {
        wait(1000);
        return temperature;
    }

    public int getPreWashTime() {
        wait(800);
        return preWashTime;
    }

    public int getMainWashTime() {
        wait(1200);
        return mainWashTime;
    }

    public boolean hasExtraRinsing() {
        wait(500);
        return extraRinsing;
    }

    public int getSpinSpeed() {
        wait(1000);
        return spinSpeed;
    }

    void wait(int ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            ControlPanel.showError("Process interrupted: " + e.getMessage());
        }
    }
}
