package simple;

public class Node<E> {
    private E data;
    private Node<E> prev;
    private Node<E> next;

    public E getData() {
        return this.data;
    }

    public Node<E> getPrev() {
        return this.prev;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node(E data) {
        this.data = data;
    }
}