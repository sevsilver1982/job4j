package multithreading.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void offer(T value) {
        queue.offer(value);
        this.notifyAll();
    }

    public synchronized T poll() {
        T result;
        while ((result = queue.poll()) == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            this.notifyAll();
        }
        return result;
    }

}