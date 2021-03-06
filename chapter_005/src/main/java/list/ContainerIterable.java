package list;

public interface ContainerIterable<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
