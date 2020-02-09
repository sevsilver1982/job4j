package list;

import simple.SimpleList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> implements ContainerIterable<E> {
    private SimpleList<E> simpleList = new SimpleList<>();
    private int modCount = 0;

    public void add(E value) {
        simpleList.add(value);
        modCount++;
    }

    public E get(int index) {
        return simpleList.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private SimpleList.Node<E> position = simpleList.getFirst();
            private SimpleList.Node<E> tmp = position;

            @Override
            public boolean hasNext() {
                return tmp.getNext() != null;
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
                position = position.getNext();
                return tmp.getData();
            }
        };
    }

}