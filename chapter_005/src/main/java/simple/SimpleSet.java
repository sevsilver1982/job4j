package simple;

import list.ArrayListContainer;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private ArrayListContainer<E> arrayListContainer = new ArrayListContainer<E>();

    public int getSize() {
        return arrayListContainer.getSize();
    }

    public void add(E data) {
        for (int i = 0; i < arrayListContainer.getSize(); i++) {
            if (arrayListContainer.get(i).equals(data)) {
                return;
            }
        }
        arrayListContainer.add(data);
    }

    @Override
    public Iterator<E> iterator() {
        return arrayListContainer.iterator();
    }

}