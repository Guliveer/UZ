import java.util.Random;

public class Main {
    private static final int[] threads = {1, 2, 3, 4, 5, 6, 7, 8, 20, 100, 500};
    private static final int ARRAY_SIZE = 175_000_000;

    private static final int[] intArray = new int[ARRAY_SIZE];
    private static final double[] doubleArray = new double[ARRAY_SIZE];

    private static int globalIntMin = Integer.MAX_VALUE;
    private static int globalIntMax = Integer.MIN_VALUE;
    private static double globalDoubleMin = Double.MAX_VALUE;
    private static double globalDoubleMax = Double.MIN_VALUE;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Initialize arrays
        for (int i = 0; i < ARRAY_SIZE; i++) {
            intArray[i] = random.nextInt();
            doubleArray[i] = random.nextDouble();
        }

        for (int threadCount : threads) {
            System.out.println("\nMeasuring execution time for int array with " + threadCount + " threads...");
            measureExecutionTime(threadCount, true);

            System.out.println("\nMeasuring execution time for double array with " + threadCount + " threads...");
            measureExecutionTime(threadCount, false);
        }
    }

    private static void measureExecutionTime(int threadCount, boolean isIntArray) throws InterruptedException {
        long[] times = new long[3];

        for (int i = 0; i < 3; i++) {
            long startTime = System.nanoTime();

            if (isIntArray) {
                globalIntMin = Integer.MAX_VALUE;
                globalIntMax = Integer.MIN_VALUE;
                findMinMaxWithThreads(threadCount, intArray);
            } else {
                globalDoubleMin = Double.MAX_VALUE;
                globalDoubleMax = Double.MIN_VALUE;
                findMinMaxWithThreads(threadCount, doubleArray);
            }

            long endTime = System.nanoTime();
            times[i] = endTime - startTime;
            System.out.println("Execution time " + (i + 1) + ": " + times[i] / 1_000_000 + " ms");
        }

        long averageTime = (times[0] + times[1] + times[2]) / 3;
        System.out.println("Average execution time: " + averageTime / 1_000_000 + " ms");
    }

    private static void findMinMaxWithThreads(int threadCount, int[] array) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        int chunkSize = array.length / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? array.length : start + chunkSize;
            threads[i] = new Thread(new IntMinMaxTask(array, start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static void findMinMaxWithThreads(int threadCount, double[] array) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        int chunkSize = array.length / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? array.length : start + chunkSize;
            threads[i] = new Thread(new DoubleMinMaxTask(array, start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    static class IntMinMaxTask implements Runnable {
        private final int[] array;
        private final int start;
        private final int end;

        IntMinMaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int localMin = Integer.MAX_VALUE;
            int localMax = Integer.MIN_VALUE;

            for (int i = start; i < end; i++) {
                if (array[i] < localMin) {
                    localMin = array[i];
                }
                if (array[i] > localMax) {
                    localMax = array[i];
                }
            }

            synchronized (Main.class) {
                if (localMin < globalIntMin) {
                    globalIntMin = localMin;
                }
                if (localMax > globalIntMax) {
                    globalIntMax = localMax;
                }
            }
        }
    }

    static class DoubleMinMaxTask implements Runnable {
        private final double[] array;
        private final int start;
        private final int end;

        DoubleMinMaxTask(double[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            double localMin = Double.MAX_VALUE;
            double localMax = Double.MIN_VALUE;

            for (int i = start; i < end; i++) {
                if (array[i] < localMin) {
                    localMin = array[i];
                }
                if (array[i] > localMax) {
                    localMax = array[i];
                }
            }

            synchronized (Main.class) {
                if (localMin < globalDoubleMin) {
                    globalDoubleMin = localMin;
                }
                if (localMax > globalDoubleMax) {
                    globalDoubleMax = localMax;
                }
            }
        }
    }
}