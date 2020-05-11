package multithreading.synch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() || queue.size() > 0) {
                try {
                    System.out.println(String.format("consumer poll element %s", queue.poll()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("consumer end");
        });
        Thread producer = new Thread(() -> {
            IntStream.range(0, 10).forEach(value -> {
                queue.offer(value);
                System.out.println(value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            consumer.interrupt();
            System.out.println("producer end");
        });
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final List<Integer> buffer = new ArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(queue::offer));
        producer.start();
        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() || queue.size() > 0) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), buffer);
    }

}