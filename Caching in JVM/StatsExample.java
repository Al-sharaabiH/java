package org.example;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;

public class StatsExample {
    public static void main(String[] args) {
        LoadingCache<Integer, String> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .recordStats()
                .build(key -> "Value_" + key);

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.cleanUp();
        cache.get(1);

        CacheStats stats = cache.stats();
        System.out.println("Hit count: " + stats.hitCount());
        System.out.println("Miss count: " + stats.missCount());
        System.out.println("Hit rate: " + stats.hitRate());
        System.out.println("Eviction count: " + stats.evictionCount());
    }
}