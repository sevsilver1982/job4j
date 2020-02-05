package list.stack;

public interface StackIterable<E> extends Iterable<E> {
    void push(E value);
    E poll(int index);
}
