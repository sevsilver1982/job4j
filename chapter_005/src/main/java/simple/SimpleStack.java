package simple;

public class SimpleStack<E> {
    private SimpleList<E> simpleList = new SimpleList<>();

    public int getSize() {
        return simpleList.getSize();
    }

    public E poll() {
        return simpleList.delete(simpleList.getSize() - 1);
    }

    public void push(E value) {
        simpleList.add(value);
    };

}