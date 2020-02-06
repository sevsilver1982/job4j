package list;

public interface StackIterable<E> extends Iterable<E> {
    void push(E value);
    E poll(int index);
}
