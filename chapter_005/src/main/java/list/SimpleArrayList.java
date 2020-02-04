package list;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first = null;
    private Node<E> last = null;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> tmp = last;
        Node<E> newLink = new Node<>(data);
        last = newLink;
        if (tmp == null) {
            first = newLink;
        } else {
            tmp.next = newLink;
        }
        this.size++;
    }

    /**
     * Реализовать метод удаления элемента в списке.
     */
    public E delete(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = this.first;
        Node<E> prv = this.first;
        Node<E> nxt = this.first;
        if (index == 0) {
            this.first = first.next;
        } else {
            for (int i = 0; i < index; i++) {
                prv = result;
                result = result.next;
                nxt = result.next;
            }
            prv.next = nxt;
        }
        this.size--;
        return result.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

}