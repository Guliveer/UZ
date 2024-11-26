// Oliwer Pawelski, 24INF-SP/A

// Korzystając ze strumieni Javy napisz program realizujący odczyt, oraz zapis dowolnych
// danych dla pliku tekstowego *.txt i *.csv.

package lab07_p;

import java.io.*;

public class Zad02 {
    public static void main(String[] args) {
        String txtFilePath = "dane.txt";
        String csvFilePath = "dane.csv";

        System.out.println("Chcesz użyć domyślnych danych? (tak/nie)");
        String response = new java.util.Scanner(System.in).nextLine();
        if (response.equalsIgnoreCase("tak") || response.equalsIgnoreCase("t") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
            // Zapis danych do pliku tekstowego
            writeTextFile(txtFilePath, "To jest przykładowy tekst do pliku tekstowego.");

            // Zapis danych do pliku CSV
            writeCsvFile(csvFilePath, new String[][]{
                    {"Imię", "Nazwisko", "Wiek"},
                    {"Anna", "Kowalska", "28"},
                    {"Jan", "Nowak", "34"}
            });

        } else if (response.equalsIgnoreCase("nie") || response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
            System.out.println("Podaj zawartość pliku tekstowego:");
            String textData = new java.util.Scanner(System.in).nextLine();
            System.out.println("Podaj zawartość pliku CSV:");
            System.out.println("Podaj dane w formacie: Imię,Nazwisko,Wiek");
            System.out.println("Aby zakończyć wprowadzanie danych, wpisz 'koniec'.");
            StringBuilder csvData = new StringBuilder();
            String line;
            while (!(line = new java.util.Scanner(System.in).nextLine()).equalsIgnoreCase("koniec")) {
                csvData.append(line).append("\n");
            }

            // Zapis danych do plików
            writeTextFile(txtFilePath, textData);
            writeCsvFile(csvFilePath, new String[][]{
                    {"Imię", "Nazwisko", "Wiek"},
                    csvData.toString().split(";")
            });
        } else {
            main(args); // Re-run the program
        }

        // Odczyt danych z plików
        System.out.println("Pliku tekstowy: " + readTextFile(txtFilePath));
        System.out.println("Plik CSV:"); readCsvFile(csvFilePath);
    }

    private static void writeTextFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readTextFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static void writeCsvFile(String filePath, String[][] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readCsvFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
