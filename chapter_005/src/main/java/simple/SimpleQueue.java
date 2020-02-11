package simple;

import java.util.NoSuchElementException;

public class SimpleQueue<E> {
    private SimpleList<E> leftStack = new SimpleList<>();
    private SimpleList<E> rightStack = new SimpleList<>();
    private int size = 0;

    public int getSize() {
        return size;
    }

    public E poll() {
        if (rightStack.getSize() == 0) {
            if (leftStack.getSize() > 0) {
                while (leftStack.getSize() > 0) {
                    rightStack.add(leftStack.delete(0));
                }
            } else {
                throw new NoSuchElementException();
            }
        }
        size--;
        return rightStack.delete(0);
    }

    public void push(E value) {
        size++;
        leftStack.add(value);
    };

}