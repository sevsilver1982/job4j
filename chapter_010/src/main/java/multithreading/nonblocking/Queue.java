package multithreading.nonblocking;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> temp = new Node<>(value);
        if (head == null) {
            head = temp;
            tail = temp;
            return;
        }
        tail.next = temp;
        tail = tail.next;
    }

    public T poll() {
        Node<T> temp = head;
        if (head == null) {
            return null;
        }
        head = head.next;
        return temp.value;
    }

    private static final class Node<T> {
        final T value;
        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }

    }

}