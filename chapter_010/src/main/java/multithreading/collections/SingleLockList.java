package multithreading.collections;

import list.ArrayListContainer;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    ArrayListContainer<T> arrayListContainer = new ArrayListContainer<>();

    public synchronized void add(T value) {
        arrayListContainer.add(value);
    }

    public synchronized T get(int index) {
        return arrayListContainer.get(index);
    }

    private List<T> copy() {
        List<T> result = new ArrayList<>();
        arrayListContainer.forEach(result::add);
        return result;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }

}