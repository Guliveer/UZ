// Napisz program, który znajduje wszystkie liczby pierwsze w zakresie od 2 do N (gdzie N jest liczbą
// zdefiniowaną w kodzie). Zadanie rozwiąż w dwóch wariantach (w jednym projekcie w IntelliJ):
//
// - dla jednego wątku,
// - dla czterech wątków.
//
// Mierz czas wykonania zadania dla obu wariantów. Sprawdź ile razy szybciej wykonywane są
// obliczenia wielowątkowe. Upewnij się, że wynik końcowy (lista liczb pierwszych) jest poprawny
// i identyczny w obu wariantach. Zadanie zrealizować z użyciem ExecutorService do zarządzania
// wątkami.

package zad03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = 2_500_000; // Define the range limit
        int threads = 8; // Number of threads for multi-threaded approach

        // Single-threaded approach
        long startSingle = System.currentTimeMillis();
        List<Integer> primesSingleThread = findPrimesSingleThread(N);
        long endSingle = System.currentTimeMillis();
        System.out.println("Single-threaded time: " + (endSingle - startSingle) + " ms");

        // Multi-threaded approach
        long startMulti = System.currentTimeMillis();
        List<Integer> primesMultiThread = findPrimesMultiThread(N, threads);
        long endMulti = System.currentTimeMillis();
        System.out.println("Multi-threaded time: " + (endMulti - startMulti) + " ms");

        // Verify results
        System.out.println("Results are identical: " + primesSingleThread.equals(primesMultiThread));
    }

    // Single-threaded prime number finder
    private static List<Integer> findPrimesSingleThread(int N) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    // Multi-threaded prime number finder
    private static List<Integer> findPrimesMultiThread(int N, int threadCount) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Callable<List<Integer>>> tasks = new ArrayList<>();
        int range = N / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * range + 2;
            int end = (i == threadCount - 1) ? N : (i + 1) * range + 1;
            tasks.add(() -> {
                List<Integer> primes = new ArrayList<>();
                for (int j = start; j <= end; j++) {
                    if (isPrime(j)) {
                        primes.add(j);
                    }
                }
                return primes;
            });
        }

        List<Integer> primes = new ArrayList<>();
        List<Future<List<Integer>>> results = executor.invokeAll(tasks);
        for (Future<List<Integer>> result : results) {
            primes.addAll(result.get());
        }

        executor.shutdown();
        return primes;
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
