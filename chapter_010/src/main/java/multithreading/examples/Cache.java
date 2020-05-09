package multithreading.examples;

import java.util.concurrent.atomic.AtomicReference;

public final class Cache {
    private static AtomicReference<Cache> cache = new AtomicReference<>();

    private static AtomicReference<Cache> instOf() {
        cache.compareAndSet(null, new Cache());
        return cache;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> System.out.println(instOf())).start();
        }
    }

}