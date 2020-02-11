package list;

import simple.Node;
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
            private int index = 0;
            private int expectedModCount = modCount;
            private Node<E> position = simpleList.getFirst();

            @Override
            public boolean hasNext() {
                return index < simpleList.getSize();
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                Node<E> tmp = position;
                position = position.getNext();
                return tmp.getData();
            }
        };
    }

}