package multithreading.nonblocking.cache;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheMapTest {

    @Test
    void add() {
        CacheMap cacheMap = new CacheMap();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            int id = i;
            threadList.add(
                    new Thread(() -> cacheMap.add(new Base(id)))
            );
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Map<Integer, Base> expected = new TreeMap<>();
        cacheMap.getMap().forEach(expected::put);
        assertEquals(
                expected,
                cacheMap.getMap()
        );
    }

    @Test
    void update() {
        CacheMap cacheMap = new CacheMap();
        Base base = new Base(1);
        cacheMap.add(base);
        cacheMap.update(base);
        assertEquals(
                1,
                base.version
        );
        cacheMap.update(base);
        assertEquals(
                2,
                base.version
        );
    }

    @Test
    void delete() {
        CacheMap cacheMap = new CacheMap();
        Base base1 = new Base(1);
        Base base2 = new Base(2);
        Base base3 = new Base(3);
        cacheMap.add(base1);
        cacheMap.add(base2);
        cacheMap.add(base3);
        assertEquals(
                3,
                cacheMap.getMap().size()
        );
        cacheMap.delete(base3);
        assertEquals(
                2,
                cacheMap.getMap().size()
        );
        assertEquals(
                Map.of(
                        1, new Base(1),
                        2, new Base(2)
                ),
                cacheMap.getMap()
        );
    }

    @Test
    public void whenThrowException() {
        CacheMap cacheMap = new CacheMap();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int id = i;
            threadList.add(
                    new Thread(() -> cacheMap.add(new Base(id)))
            );
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadList.clear();
        OptimisticException optimisticException = new OptimisticException();
        AtomicReference<Exception> ex = new AtomicReference<>(optimisticException);
        for (int i = 1; i <= 10; i++) {
            threadList.add(
                    new Thread(() -> {
                        try {
                            cacheMap.update(new Base(1));
                        } catch (OptimisticException e) {
                            ex.set(e);
                        }
                    })
            );
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertEquals(
                optimisticException.getClass(),
                ex.get().getClass()
        );
    }

}