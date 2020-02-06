package list;

import list.SimpleContainer;

public class SimpleStack<E> {
    private SimpleContainer<E> simpleContainer = new SimpleContainer<>();

    public int getSize() {
        return simpleContainer.getSize();
    }

    public E poll() {
        return simpleContainer.delete(simpleContainer.getSize() - 1);
    }

    public void push(E value) {
        simpleContainer.add(value);
    };

}