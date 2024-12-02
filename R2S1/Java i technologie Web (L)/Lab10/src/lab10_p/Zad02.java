// Oliwer Pawelski, 24INF-SP/A
//
// Napisz aplikację generującą logi na dowolnie zadany przez siebie temat
// (np. generator logu oceny filmu będzie wyglądał następująco: id_filmu, id_uzytkownika,
// ocena, data_glosowania). Zapis informacji do pliku należy zrealizować wielowątkowo
// z użyciem dwóch metod: poprzez dziedziczenie klasy Thread, oraz poprzez
// implementację interfejsu Runnable. Następie wielowątkowo odczytać z pliku
// informacje w nim zawarte i wyświetlić po 10 linii dla każdego wątku.

package lab10_p;

import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Zad02 {
    static final String ANSI_GRAY = "\u001B[90m";
    static final String ANSI_RESET = "\u001B[0m";
    private static final String LOG_FILE = "logs.txt";
    private static final int NUM_THREADS = 3;
    private static final int NUM_LOGS = 50;

    public static void main(String[] args) throws InterruptedException {
        // Wątki zapisujące logi
        LogWriterThread writer1 = new LogWriterThread("Thread-1");
        Thread writer2 = new Thread(new LogWriterRunnable("Thread-2"));

        writer1.start();
        writer2.start();

        writer1.join();
        writer2.join();

        // Dodanie logów przez dodatkowy wątek
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> writeLogs("Thread-3", NUM_LOGS / NUM_THREADS));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Wątki odczytujące logi
        ExecutorService readExecutor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_THREADS; i++) {
            int startLine = i * (NUM_LOGS / NUM_THREADS);
            readExecutor.submit(new LogReaderRunnable(startLine, 10));
        }
        readExecutor.shutdown();
        readExecutor.awaitTermination(1, TimeUnit.MINUTES);
    }

    // Funkcja zapisująca logi do pliku
    private static synchronized void writeLogs(String threadName, int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            for (int i = 0; i < count; i++) {
                String log = String.format("%s: id_filmu=%d, id_uzytkownika=%d, ocena=%d, data=%s",
                        threadName, i, i + 100, new Random().nextInt(10) + 1, new Date());
                writer.write(log);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu logów: " + e.getMessage());
        }
    }

    // Wątek zapisujący logi przez dziedziczenie Thread
    static class LogWriterThread extends Thread {
        private final String threadName;

        public LogWriterThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            writeLogs(threadName, NUM_LOGS / NUM_THREADS);
        }
    }

    // Wątek zapisujący logi przez implementację Runnable
    static class LogWriterRunnable implements Runnable {
        private final String threadName;

        public LogWriterRunnable(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            writeLogs(threadName, NUM_LOGS / NUM_THREADS);
        }
    }

    // Wątek odczytujący logi
    static class LogReaderRunnable implements Runnable {
        private final int startLine;
        private final int lineCount;

        public LogReaderRunnable(int startLine, int lineCount) {
            this.startLine = startLine;
            this.lineCount = lineCount;
        }

        // Funkcja odczytująca logi z pliku
        void readLogs(int startLine, int lineCount) {
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
                int currentLine = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    if (currentLine >= startLine && currentLine < startLine + lineCount) {
                        System.out.println(ANSI_GRAY + Thread.currentThread().getName() + " odczytał: " + ANSI_RESET + line);
                    }
                    currentLine++;
                }
            } catch (IOException e) {
                System.err.println("Błąd odczytu logów: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            readLogs(startLine, lineCount);
        }
    }
}
