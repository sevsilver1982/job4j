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
            last.setNext(newLink);
            newLink.setPrev(last);
        }
        last = newLink;
        size++;
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
        Node<E> res = first;
        Node<E> prv = first;
        Node<E> nxt = first;
        if (index == 0) {
            first = first.getNext();
        } else {
            for (int i = 0; i < index; i++) {
                prv = res;
                res = res.getNext();
                nxt = res.getNext();
            }
            prv.setNext(nxt);
        }
        this.size--;
        return res.getData();
    }

    public E deleteLast() {
        if (size == 0 || last == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> tmp = last;
        if (last.getPrev() == null) {
            first = null;
            last = null;
        } else {
            last.getPrev().setNext(null);
            last = last.getPrev();
        }
        this.size--;
        return tmp.getData();
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
        Node<E> res = first;
        for (int i = 0; i < index; i++) {
            res = res.getNext();
        }
        return res.getData();
    }

}