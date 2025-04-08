package lab04_p.zad03;

import com.sun.management.OperatingSystemMXBean;

import java.io.*;
import java.lang.management.ManagementFactory;

public class GenFile {
    public static void writeToFile(String content) {
        String filePath = "src/lab04_p/zad03/opis.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println(content); // Print to console
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSystemInfo(StringBuilder resultBuilder) {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String osVersion = System.getProperty("os.version");
        long totalMemory = osBean.getTotalPhysicalMemorySize() / (1024 * 1024); // in MB

        String processorName = getProcessorName();

        resultBuilder.append("Processor: ").append(processorName).append("\n")
                .append("Number of Cores: ").append(Runtime.getRuntime().availableProcessors()).append("\n")
                .append("Operating System: ").append(System.getProperty("os.name")).append(" ").append(osVersion).append("\n")
                .append("RAM: ").append(totalMemory).append(" MB\n\n")
                .append("Execution Times:\n");
    }

    private static String getProcessorName() {
        String os = System.getProperty("os.name").toLowerCase();
        String command;
        if (os.contains("win")) {
            command = "wmic cpu get name";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            command = "sysctl -n machdep.cpu.brand_string";
        } else {
            return "Unknown";
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (os.contains("win")) {
                reader.readLine(); // Skip the header line
            }
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    public static void writeExecutionTimes(StringBuilder resultBuilder, int threadCount, long duration) {
        duration /= 1_000_000; // display in ms
        resultBuilder.append(threadCount).append(" threads: ").append(duration).append(" ms\n");
    }
}