package org.example;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WriteThroughWithNull {
    private static final Object NULL_MARKER = new Object();
    private final Cache<Integer, Object> cache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();
    private final ConcurrentHashMap<Integer, String> database = new ConcurrentHashMap<>();
    private final AtomicInteger dbCalls = new AtomicInteger(0);

    private String loadFromDb(int id) {
        dbCalls.incrementAndGet();
        System.out.println("-->Вызов БД, id=" + id);
        return database.get(id);
    }

    public void updateUser(int id, String name) {
        if (name == null) {
            database.remove(id);
            cache.put(id, NULL_MARKER);
        } else {
            database.put(id, name);
            cache.put(id, name);
        }
    }

    public String readUser(int id) {
        Object cached = cache.getIfPresent(id);
        if (cached == NULL_MARKER) return null;
        if (cached != null) return (String) cached;

        String value = loadFromDb(id);
        cache.put(id, value == null ? NULL_MARKER : value);
        return value;
    }

    public int getDbCalls() { return dbCalls.get(); }

    public static void main(String[] args) {
        WriteThroughWithNull ex = new WriteThroughWithNull();

        ex.updateUser(1, "Ал");
        System.out.println(ex.readUser(1));

        ex.updateUser(1, null);
        System.out.println(ex.readUser(1));

        System.out.println(ex.readUser(999));
        System.out.println(ex.readUser(999));

        System.out.println("колечество вызовов БД: " + ex.getDbCalls());
    }
}