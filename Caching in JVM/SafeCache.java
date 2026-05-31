package org.example;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCache {
    private final AtomicInteger dbLoadCount = new AtomicInteger(0);

    private final LoadingCache<Integer, String> cache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .recordStats()
            .build(key -> {
                dbLoadCount.incrementAndGet();
                try { Thread.sleep(500); } catch (InterruptedException e) {}
                return "Value_" + key;
            });

    public String get(int key) {
        return cache.get(key);  // защита от stampede уже внутри
    }

    public static void main(String[] args) throws Exception {
        SafeCache cache = new SafeCache();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> cache.get(42));
        }
        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("DB load count: " + cache.dbLoadCount.get()); // 1
        System.out.println("Hit rate: " + cache.cache.stats().hitRate());
    }
}
