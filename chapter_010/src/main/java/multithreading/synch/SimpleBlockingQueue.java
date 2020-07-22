package multithreading.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void offer(T value) {
        queue.offer(value);
        this.notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T result = queue.poll();
        if (result == null) {
            this.wait();
        }
        this.notifyAll();
        return result;
    }

    /*public synchronized T poll() throws InterruptedException {
        T result;
        while ((result = queue.poll()) == null) {
            this.wait();
        }
        return result;
    }*/

}