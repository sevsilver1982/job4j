package generic.store;

import generic.SimpleArray;

public abstract class AbstractStore<T extends Base> extends SimpleArray<T> implements Store<T> {

    public AbstractStore(int size) {
        super(size);
    }

    @Override
    public void add(T model) {
        super.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = super.getIndex(findById(id));
        if (index >= 0) {
            super.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = super.getIndex(findById(id));
        if (index >= 0) {
            super.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T model : toList()) {
            if (model.getId().equals(id)) {
                return model;
            }
        }
        return null;
    }

}