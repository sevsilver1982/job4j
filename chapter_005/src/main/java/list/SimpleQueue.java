package list;

public class SimpleQueue<E> {
    private SimpleContainer<E> simpleContainer = new SimpleContainer<>();

    public int getSize() {
        return simpleContainer.getSize();
    }

    public E poll() {
        return simpleContainer.delete(0);
    }

    public void push(E value) {
        simpleContainer.add(value);
    };

}