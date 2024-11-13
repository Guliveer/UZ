package lab06_p;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WashingMachine {
    private final Heater heater;
    private final Drum drum;
    private final WaterPump waterPump;
    private final ControlPanel controlPanel;
    private final SpeedController speedController;
    private final PressureSensor pressureSensor;
    private final TemperatureSensor temperatureSensor;
    private final WaterLevelSensor waterLevelSensor;
    private final WeightSensor weightSensor;
    private final FilterSensor filterSensor; // Sensor drożności filtra
    private final TouchInterface touchInterface;

    public WashingMachine() {
        this.heater = new Heater();
        this.drum = new Drum();
        this.waterPump = new WaterPump();
        this.controlPanel = new ControlPanel();
        this.speedController = new SpeedController();
        this.pressureSensor = new PressureSensor();
        this.temperatureSensor = new TemperatureSensor();
        this.waterLevelSensor = new WaterLevelSensor();
        this.weightSensor = new WeightSensor();
        this.filterSensor = new FilterSensor();
        this.touchInterface = new TouchInterface();
    }

    public void start() {
        System.out.print("Press [Enter] to power on the washing machine...");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("%s", TextColor.BLACK);
            scanner.nextLine();
            System.out.printf("%s", TextColor.DEFAULT);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        controlPanel.turnOn();
        ControlPanel.show("Select a program or customize settings.");

        WashingProgram program = touchInterface.selectProgram(); // Wybór programu
        ControlPanel.showStatus("Starting selected program...");
        runProgram(program);
    }

    public void stop() {
        ControlPanel.showStatus("Stopping the washing machine...");
        waterPump.drainWater();
        speedController.stopSpinning();
        playSound("src/lab06_p/sounds/washing_finish.wav"); // Odtwarzanie dźwięku na zakończenie
        System.out.println("Program finished.");
        controlPanel.turnOff();
    }

    public void stop(boolean emergency) {
        ControlPanel.showStatus("Stopping the washing machine...");
        waterPump.drainWater();
        speedController.stopSpinning();
        if (!emergency) {
            playSound("src/lab06_p/sounds/washing_finish.wav"); // Odtwarzanie dźwięku na zakończenie
            ControlPanel.showError("Program has been interrupted.");
        } else {
            ControlPanel.show("Program finished.");
        }
        controlPanel.turnOff();
    }

    private void runProgram(WashingProgram program) {
        // Sprawdzanie wagi załadunku
        double loadWeight = weightSensor.measureWeight();
        if (loadWeight > 8.0) { // Przykład maksymalnej wagi załadunku w kg
            ControlPanel.showError("Overload detected! Please reduce load.");
            stop(true);
            return;
        }

        // Pompowanie wody i sprawdzanie poziomu
        ControlPanel.showStatus("Pumping water...");
        waterPump.pumpWater();
        if (!waterLevelSensor.checkWaterLevel()) {
            ControlPanel.showError("Insufficient water level. Check water supply.");
            stop(true);
            return;
        }

        // Sprawdzanie ciśnienia wody
        pressureSensor.isPressureSafe();

        // Ustawianie temperatury i sprawdzanie jej osiągnięcia
        heater.setTemperature(program.getTemperature());
        heater.heatWater(temperatureSensor);
        if (!temperatureSensor.checkTemperature(program.getTemperature())) {
            ControlPanel.showError("Unable to reach target temperature.");
            stop(true);
            return;
        }

        // Sprawdzanie drożności filtra
        if (!filterSensor.isFilterClear()) {
            ControlPanel.showWarning("Filter needs cleaning. Reduced water flow detected.");
            filterSensor.cleanFilter();
        }

        // Wyważanie bębna przed wirowaniem
        ControlPanel.showStatus("Balancing load in drum...");
        drum.balance();

        // Pranie wstępne
        ControlPanel.show("Pre-wash for " + program.getPreWashTime() + " minutes.");
        simulateProcess(program.getPreWashTime());

        // Pranie zasadnicze
        ControlPanel.show("Main wash for " + program.getMainWashTime() + " minutes.");
        simulateProcess(program.getMainWashTime());

        // Dodatkowe płukanie, jeśli jest włączone
        if (program.hasExtraRinsing()) {
            ControlPanel.showStatus("Extra rinsing...");
            simulateProcess(5); // Przykładowy czas płukania
        }

        // Ustawienie prędkości wirowania
        ControlPanel.showStatus("Spinning at " + program.getSpinSpeed() + " RPM...");
        speedController.setSpinSpeed(program.getSpinSpeed());
        speedController.startSpinning();

        // Zakończenie programu
        stop();
    }

    // Metoda do symulacji działania procesu (pranie wstępne, zasadnicze, płukanie)
    private void simulateProcess(int durationInMinutes) {
        try {
            // Skrócony czas dla symulacji
            TimeUnit.MILLISECONDS.sleep(durationInMinutes * 1000L / 4);
        } catch (InterruptedException e) {
            ControlPanel.showError("Process interrupted: " + e.getMessage());
        }
    }

    // Metoda do odtwarzania dźwięku z pliku .wav
    private void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Pobranie długości trwania utworu w milisekundach
            long duration = clip.getMicrosecondLength() / 1000;

            // Ustawienie głośności
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float currentVolume = volumeControl.getValue(); // Bieżąca głośność
            float newVolume = currentVolume + 10.0f; // Zwiększenie głośności o 6 dB (można dostosować)

            // Ograniczenie wartości głośności do maksymalnej
            if (newVolume > volumeControl.getMaximum()) {
                newVolume = volumeControl.getMaximum();
            }
            volumeControl.setValue(newVolume);

            clip.start();
            ControlPanel.showStatus("Finishing...");

            // Opóźnienie przed zakończeniem metody
            TimeUnit.MILLISECONDS.sleep(duration+1000);
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}
