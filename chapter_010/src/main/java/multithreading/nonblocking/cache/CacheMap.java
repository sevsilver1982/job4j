package multithreading.nonblocking.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CacheMap implements Cache {
    private final ConcurrentHashMap<Integer, Base> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void add(Base model) {
        concurrentHashMap.put(model.id, model);
    }

    @Override
    public void update(Base model) throws OptimisticException {
        int oldVersion = concurrentHashMap.get(model.id).version;
        concurrentHashMap.computeIfPresent(model.id, (id, base) -> {
            AtomicInteger version = new AtomicInteger(base.version);
            int nextVersion = base.version++;
            if (!version.compareAndSet(oldVersion, nextVersion)) {
                throw new OptimisticException();
            }
            return base;
        });
    }

    @Override
    public void delete(Base model) {
        concurrentHashMap.remove(model.id);
    }

    public ConcurrentHashMap<Integer, Base> getMap() {
        return new ConcurrentHashMap<>(concurrentHashMap);
    }

    public static void main(String[] args) {
        CacheMap cacheMap = new CacheMap();
        cacheMap.add(new Base(1));
        new Thread(() -> cacheMap.update(new Base(1))).start();
        new Thread(() -> cacheMap.update(new Base(1))).start();
        cacheMap.getMap().forEach((id, base) -> System.out.println(base));
    }

}