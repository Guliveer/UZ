package lab06_p;

import java.util.concurrent.TimeUnit;

public class ControlPanel {
    public static void show(String message) {
        System.out.printf("%s%s%s%n", TextColor.DEFAULT, message, TextColor.DEFAULT);
    }

    public static void showStatus(String message) {
        System.out.printf("%s%s%s%n", TextColor.GRAY, message, TextColor.DEFAULT);
    }

    public static void showError(String message) {
        System.out.printf("%sError:%s %s%s%n", TextColor.RED, TextColor.GRAY, message, TextColor.DEFAULT);
    }

    public static void showWarning(String message) {
        System.out.printf("%sWarning:%s %s%s%n", TextColor.YELLOW, TextColor.GRAY, message, TextColor.DEFAULT);
    }

    public static void showSuccess(String message) {
        System.out.printf("%s%s%s%n", TextColor.GREEN, message, TextColor.DEFAULT);
    }

    public static void showInput(String message) {
        System.out.printf("%s%s%s", TextColor.BLUE, message, TextColor.DEFAULT);
    }

    public static void loading(int ms) {
        char loadingChar = '='; // char to display during loading
        double variation = 0.25; // time variation range in percentage
        int loadingLength = 20; // number of loading characters

        System.out.printf("%sLoading: %s[", TextColor.GRAY, TextColor.YELLOW);
        try {
            TimeUnit.MILLISECONDS.sleep(1000); // additional delay for better UX
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < loadingLength; i++) {
            System.out.printf("%s%c%s", TextColor.YELLOW, loadingChar, TextColor.DEFAULT);
            try {
                double timeout = Math.random() * ((ms * (1 + variation)) / loadingLength) + ((ms * variation) / loadingLength);
                TimeUnit.MILLISECONDS.sleep((long) timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%s]%s%n", TextColor.YELLOW, TextColor.DEFAULT);

        try {
            TimeUnit.MILLISECONDS.sleep(1000); // additional delay for better UX
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOn() {
        System.out.printf("%sWashing Machine is now %sON%s%n", TextColor.DEFAULT, TextColor.GREEN, TextColor.DEFAULT);
    }

    public void turnOff() {
        System.out.printf("%sWashing Machine is now %sOFF%s%n", TextColor.DEFAULT, TextColor.RED, TextColor.DEFAULT);
    }
}
