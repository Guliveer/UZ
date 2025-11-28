package zad01.p3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Program demonstracyjny testujący thread-safe Singleton
 * Sprawdza czy wszystkie wątki otrzymują tę samą instancję
 * i demonstruje działanie metod biznesowych w środowisku współbieżnym
 */
public class Main {
    
    // Zbiór do przechowywania wszystkich uzyskanych instancji
    private static final Set<ThreadSafeSingleton> instancje = ConcurrentHashMap.newKeySet();
    
    public static void main(String[] args) {
        System.out.println("=== Test Thread-Safe Singleton ===\n");
        
        // Test 1: Sprawdzenie czy wszystkie wątki otrzymują tę samą instancję
        testJedynościInstancji();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 2: Test metod biznesowych w środowisku współbieżnym
        testMetodBiznesowych();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 3: Test wydajności
        testWydajności();
    }
    
    /**
     * Test sprawdzający czy wszystkie wątki otrzymują tę samą instancję Singleton
     */
    private static void testJedynościInstancji() {
        System.out.println("TEST 1: Sprawdzanie jedyności instancji");
        System.out.println("Tworzę 10 wątków próbujących uzyskać instancję Singleton...\n");
        
        final int liczbaWątków = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(liczbaWątków);
        
        // Tworzenie wątków
        for (int i = 0; i < liczbaWątków; i++) {
            final int nrWątku = i + 1;
            new Thread(() -> {
                try {
                    // Wszystkie wątki czekają na sygnał startu
                    startLatch.await();
                    
                    // Próba uzyskania instancji Singleton
                    ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
                    instancje.add(singleton);
                    
                    System.out.println("Wątek " + nrWątku + " uzyskał instancję: " + singleton.hashCode());
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }, "TestThread-" + nrWątku).start();
        }
        
        // Rozpoczęcie testu - wszystkie wątki startują jednocześnie
        startLatch.countDown();
        
        try {
            // Czekanie na zakończenie wszystkich wątków
            endLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Analiza wyników
        System.out.println("\n--- WYNIKI TESTU JEDYNOŚCI ---");
        System.out.println("Liczba utworzonych wątków: " + liczbaWątków);
        System.out.println("Liczba unikalnych instancji: " + instancje.size());
        
        if (instancje.size() == 1) {
            System.out.println("✅ TEST PRZESZEDŁ: Wszystkie wątki otrzymały tę samą instancję!");
            ThreadSafeSingleton singleton = instancje.iterator().next();
            System.out.println("Hash instancji: " + singleton.hashCode());
        } else {
            System.out.println("❌ TEST NIE PRZESZEDŁ: Utworzono więcej niż jedną instancję!");
        }
    }
    
    /**
     * Test metod biznesowych w środowisku współbieżnym
     */
    private static void testMetodBiznesowych() {
        System.out.println("TEST 2: Testowanie metod biznesowych");
        System.out.println("Testowanie współbieżnego dostępu do metod biznesowych...\n");
        
        final int liczbaWątków = 5;
        ExecutorService executor = Executors.newFixedThreadPool(liczbaWątków);
        CountDownLatch latch = new CountDownLatch(liczbaWątków);
        
        // Każdy wątek wykonuje różne operacje na Singleton
        for (int i = 0; i < liczbaWątków; i++) {
            final int nrWątku = i + 1;
            executor.submit(() -> {
                try {
                    ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
                    
                    // Wykonanie różnych operacji
                    singleton.wykonajOperację("Operacja-" + nrWątku);
                    
                    // Zwiększenie licznika kilka razy
                    for (int j = 0; j < 3; j++) {
                        singleton.zwiększLicznik();
                        Thread.sleep(50); // Krótka pauza
                    }
                    
                    System.out.println("Wątek " + nrWątku + " - " + singleton.getInfo());
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            latch.await();
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Sprawdzenie końcowego stanu
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
        System.out.println("\n--- WYNIKI TESTU METOD BIZNESOWYCH ---");
        System.out.println("Końcowy stan: " + singleton.getInfo());
        System.out.println("Oczekiwana wartość licznika: " + (liczbaWątków * 3));
        System.out.println("Rzeczywista wartość licznika: " + singleton.getLicznik());
        
        if (singleton.getLicznik() == liczbaWątków * 3) {
            System.out.println("✅ TEST PRZESZEDŁ: Metody biznesowe działają poprawnie!");
        } else {
            System.out.println("❌ TEST NIE PRZESZEDŁ: Problemy z synchronizacją metod biznesowych!");
        }
    }
    
    /**
     * Test wydajności - sprawdza jak szybko można uzyskać instancję
     */
    private static void testWydajności() {
        System.out.println("TEST 3: Test wydajności");
        System.out.println("Mierzenie czasu uzyskiwania instancji...\n");
        
        final int liczbaWywołań = 1000000;
        
        // Test wydajności po inicjalizacji
        long startTime = System.nanoTime();
        
        for (int i = 0; i < liczbaWywołań; i++) {
            ThreadSafeSingleton.getInstance();
        }
        
        long endTime = System.nanoTime();
        long czasWNanosekundach = endTime - startTime;
        double czasWMilisekundach = czasWNanosekundach / 1_000_000.0;
        
        System.out.println("--- WYNIKI TESTU WYDAJNOŚCI ---");
        System.out.println("Liczba wywołań getInstance(): " + liczbaWywołań);
        System.out.println("Całkowity czas: " + String.format("%.2f", czasWMilisekundach) + " ms");
        System.out.println("Średni czas na wywołanie: " + String.format("%.2f", czasWNanosekundach / (double) liczbaWywołań) + " ns");
        System.out.println("✅ Implementacja jest wydajna - brak synchronizacji w getInstance()");
    }
}