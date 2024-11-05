package lab06_p;

public class WaterLevelSensor {
    private int waterLevel; // Poziom wody w litrach

    public void readWaterLevel() {
        // Symulacja odczytu poziomu wody
        this.waterLevel = 10; // przykładowa wartość w litrach
    }

    public boolean isWaterLevelSafe() {
        // Załóżmy, że maksymalny poziom to 15 litrów
        return waterLevel <= 15;
    }

    public boolean checkWaterLevel() {
        readWaterLevel();
        return isWaterLevelSafe();
    }
}
