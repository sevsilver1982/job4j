package multithreading.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASQueue<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public void add(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> newTail;
        while (true) {
            if (head.compareAndSet(null, temp)) {
                tail.set(temp);
                return;
            }
            newTail = tail.get();
            newTail.next = temp;
            if (tail.compareAndSet(newTail, temp)) {
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