package lab04_p.zad03;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    private static final int ARRAY_SIZE = 320_000_000;
    private static final int[] array = new int[ARRAY_SIZE];

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt();
        }

        StringBuilder resultBuilder = new StringBuilder();
        GenFile.writeSystemInfo(resultBuilder);

//        int[] threadCounts = {1, 2, 3, 4, 5, 6, 7, 8, 20 ,100, 500};
        int[] threadCounts = {1, 2, 4, 6, 8};
        for (int threadCount : threadCounts) {
            long duration = findMinMaxWithThreads(threadCount);
            GenFile.writeExecutionTimes(resultBuilder, threadCount, duration);
        }

        GenFile.writeToFile(resultBuilder.toString());
    }

    private static long findMinMaxWithThreads(int threadCount) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Future<int[]>[] futures = new Future[threadCount]; // to store results
        int chunkSize = ARRAY_SIZE / threadCount;

        long startTime = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? ARRAY_SIZE : start + chunkSize;
            futures[i] = executor.submit(new MinMaxTask(array, start, end));
        }

        int globalMin = Integer.MAX_VALUE;
        int globalMax = Integer.MIN_VALUE;
        for (Future<int[]> future : futures) {
            int[] result = future.get();
            if (result[0] < globalMin) {
                globalMin = result[0];
            }
            if (result[1] > globalMax) {
                globalMax = result[1];
            }
        }
        long endTime = System.nanoTime();
        executor.shutdown();

        return endTime - startTime;
    }

    static class MinMaxTask implements Callable<int[]> {
        private final int[] array;
        private final int start;
        private final int end;

        MinMaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public int[] call() {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return new int[]{min, max};
        }
    }
}