package list;

public interface SimpleContainer<E> extends Iterable<E> {

    public void add(E value);

    public E get(int index);

}