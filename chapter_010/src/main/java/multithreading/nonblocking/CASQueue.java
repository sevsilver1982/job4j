package multithreading.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASQueue<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void add(T value) {
        final Node<T> tail = new Node<>(value);
        while (true) {
            tail.next = head.get();
            if (head.compareAndSet(tail.next, tail)) {
                return;
            }
        }
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        while (true) {
            ref = head.get();
            if (ref == null) {
                return null;
            }
            temp = head.get().next;
            if (head.compareAndSet(ref, temp)) {
                return ref.value;
            }
        }
    }

    private static final class Node<T> {
        final T value;
        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }

    }

}