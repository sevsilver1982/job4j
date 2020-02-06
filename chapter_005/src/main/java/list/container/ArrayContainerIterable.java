package list.container;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayContainerIterable<E> implements ContainerIterable<E> {
    final int growElements = 100;
    private Object[] container = new Object[growElements];
    private int size = 0;
    private int modCount = 0;

    public ArrayContainerIterable() {
    }

    public int getSize() {
        return size;
    }

    public void add(E value) {
        if (size == container.length - 1) {
            Object[] tmp = new Object[container.length + growElements];
            System.arraycopy(container, 0, tmp, 0, size);
            container = tmp;
        }
        container[size++] = value;
        modCount++;
    };

    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) container[index];
    };

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[pos++];
            }
        };
    }

}