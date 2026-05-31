package org.example;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DangerousCache {
    private final ConcurrentHashMap<Integer, String> cache = new ConcurrentHashMap<>();
    private final AtomicInteger dbLoadCount = new AtomicInteger(0);


    private String loadFromDb(int key) {
        dbLoadCount.incrementAndGet();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        return "Value_" + key;
    }

    public String get(int key) {
        String value = cache.get(key);
        if (value == null) {
            value = loadFromDb(key);
            cache.put(key, value);
        }
        return value;
    }

    public static void main(String[] args) throws Exception {
        DangerousCache cache = new DangerousCache();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> cache.get(42));
        }
        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("DB load count: " + cache.dbLoadCount.get());

    }
}