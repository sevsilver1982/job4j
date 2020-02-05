package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListNodeContainer<E> implements Iterable<E> {
    private SimpleNodeList<E> simpleNodeList = new SimpleNodeList<>();

    public void add(E value) {
        simpleNodeList.add(value);
    }

    public E get(int index) {
        return simpleNodeList.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < simpleNodeList.getSize();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) simpleNodeList.get(pos++);
            }
        };
    }

}