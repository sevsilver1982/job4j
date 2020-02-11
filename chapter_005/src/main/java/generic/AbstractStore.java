package generic;

import simple.SimpleArray;

import java.util.Iterator;
import java.util.Optional;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private final SimpleArray<T> simpleArray;

    public AbstractStore(int size) {
        simpleArray = new SimpleArray<T>(size);
    }

    public SimpleArray<T> getItems() {
        return simpleArray;
    }

    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        Iterator<T> iterator = simpleArray.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            if (iterator.next().getId().equals(id)) {
                simpleArray.set(i, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Iterator<T> iterator = simpleArray.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            if (iterator.next().getId().equals(id)) {
                simpleArray.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<T> findById(String id) {
        Iterator<T> iterator = simpleArray.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            T model = iterator.next();
            if (model.getId().equals(id)) {
                return Optional.of(model);
            }
        }
        return Optional.empty();
    }

}