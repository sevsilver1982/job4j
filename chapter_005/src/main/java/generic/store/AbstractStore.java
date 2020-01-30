package generic.store;

import generic.SimpleArray;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> simpleArray;

    public AbstractStore(int size) {
        simpleArray = new SimpleArray<T>(size);
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
    public T findById(String id) {
        Iterator<T> iterator = simpleArray.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            T model = iterator.next();
            if (model.getId().equals(id)) {
                return model;
            }
        }
        return null;
    }

}