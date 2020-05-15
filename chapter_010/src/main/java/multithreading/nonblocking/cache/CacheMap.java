package multithreading.nonblocking.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class CacheMap implements Cache {
    private final ConcurrentHashMap<Integer, Base> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void add(Base model) {
        concurrentHashMap.put(model.id, model);
    }

    @Override
    public void update(Base model) throws OptimisticException {
        concurrentHashMap.computeIfPresent(model.id, (id, base) -> {
            Base currentObject = concurrentHashMap.get(model.id);
            if (base.version != currentObject.version) {
                throw new OptimisticException();
            }
            base.version++;
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