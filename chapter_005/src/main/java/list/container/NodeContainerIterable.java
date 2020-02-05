package list.container;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeContainerIterable<E> implements ContainerIterable<E> {
    private SimpleContainer<E> simpleContainer = new SimpleContainer<>();
    private int modCount = 0;

    public void add(E value) {
        simpleContainer.add(value);
        modCount++;
    }

    public E get(int index) {
        return simpleContainer.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < simpleContainer.getSize();
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) simpleContainer.get(pos++);
            }
        };
    }

}