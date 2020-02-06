package list.container;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainerIterable<E> implements ContainerIterable<E> {
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
            private int expectedModCount = modCount;
            private SimpleContainer.Node<E> position = simpleContainer.getFirst();
            SimpleContainer.Node<E> tmp = position;

            @Override
            public boolean hasNext() {
                return tmp.next != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                tmp = position;
                position = position.next;
                return (E) tmp.data;
            }
        };
    }

}