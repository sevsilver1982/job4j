package simple;

public class SimpleQueue<E> {
    private SimpleList<E> simpleList = new SimpleList<>();

    public int getSize() {
        return simpleList.getSize();
    }

    public E poll() {
        return simpleList.delete(0);
    }

    public void push(E value) {
        simpleList.add(value);
    };

}