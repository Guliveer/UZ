package lab06_p;

import java.util.Scanner;

public class TouchInterface {
    private final Scanner scanner = new Scanner(System.in);

    public WashingProgram selectProgram() {
        System.out.println("Select washing program:");
        System.out.printf("%s1%s | Daily Wash%n", TextColor.PURPLE, TextColor.DEFAULT);
        System.out.printf("%s2%s | Delicate Wash%n", TextColor.PURPLE, TextColor.DEFAULT);
        System.out.printf("%s3%s | Express Wash%n", TextColor.PURPLE, TextColor.DEFAULT);
        System.out.printf("%s4%s | Heavy Duty Wash%n", TextColor.PURPLE, TextColor.DEFAULT);
        System.out.printf("%s5%s | Eco Wash%n", TextColor.PURPLE, TextColor.DEFAULT);
        System.out.printf("%s6%s | Custom Program%n", TextColor.PURPLE, TextColor.DEFAULT);

        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> WashingProgram.dailyWash();
            case 2 -> WashingProgram.delicateWash();
            case 3 -> WashingProgram.expressWash();
            case 4 -> WashingProgram.heavyDutyWash();
            case 5 -> WashingProgram.ecoWash();
            case 6 -> customizeProgram();
            default -> {
                ControlPanel.showError("Invalid choice. Starting Daily Wash program...");
                yield WashingProgram.dailyWash();
            }
        };
    }

    public WashingProgram customizeProgram() {
        ControlPanel.showInput("Set water temperature: ");
        int temperature = scanner.nextInt();

        ControlPanel.showInput("Set pre-wash time (minutes): ");
        int preWashTime = scanner.nextInt();

        ControlPanel.showInput("Set main wash time (minutes): ");
        int mainWashTime = scanner.nextInt();

        ControlPanel.showInput("Enable extra rinsing (true/false): ");
        boolean extraRinsing = scanner.nextBoolean();

        ControlPanel.showInput("Set spinning speed (RPM): ");
        int spinSpeed = scanner.nextInt();

        return new WashingProgram(temperature, preWashTime, mainWashTime, extraRinsing, spinSpeed);
    }
}
