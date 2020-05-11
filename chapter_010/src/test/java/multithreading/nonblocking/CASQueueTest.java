package multithreading.nonblocking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CASQueueTest {

    @Test
    public void when10AddThen10Poll() throws InterruptedException {
        CASQueue<Integer> queue = new CASQueue<>();
        Thread thread1 = new Thread(() -> queue.add(1));
        Thread thread2 = new Thread(() -> queue.add(2));
        Thread thread3 = new Thread(() -> queue.add(3));
        Thread thread4 = new Thread(() -> queue.add(4));
        Thread thread5 = new Thread(() -> queue.add(5));
        Thread thread6 = new Thread(() -> queue.add(6));
        Thread thread7 = new Thread(() -> queue.add(7));
        Thread thread8 = new Thread(() -> queue.add(8));
        Thread thread9 = new Thread(() -> queue.add(9));
        Thread thread10 = new Thread(() -> queue.add(10));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
        List<Integer> result = new ArrayList<>();
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.add(queue.poll());
        result.sort(Comparator.naturalOrder());
        assertEquals(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                result
        );
    }

}