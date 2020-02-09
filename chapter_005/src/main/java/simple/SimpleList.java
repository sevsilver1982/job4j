package simple;

import java.util.NoSuchElementException;

public class SimpleList<E> {
    private Node<E> first = null;
    private Node<E> last = null;
    private int size;

    public Node<E> getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first;
    }

    public Node<E> getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Add element
     * @param data
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        if (last == null) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.prev = last;
        }
        last = newLink;
        this.size++;
    }

    /**
     * Delete element by index
     * @param index
     * @return
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

    public E deleteLast() {
        if (size == 0 || last == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> tmp = last;
        if (last.prev == null) {
            first = null;
            last = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }
        this.size--;
        return tmp.data;
    }

    /**
     * Get element by index
     * @param index
     * @return
     */
    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Node class
     * @param <E>
     */
    public static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        public E getData() {
            return data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        Node(E data) {
            this.data = data;
        }
    }

}