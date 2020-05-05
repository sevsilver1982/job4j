package multithreading.queue;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i != 10 ; i++) {
                queue.offer(i);
                System.out.println(i);
            }
            System.out.println("producer end");
        });
        Thread consumer = new Thread(() -> {
            while (producer.getState() != Thread.State.TERMINATED || queue.size() != 0) {
                System.out.println(String.format("consumer poll element %s", queue.poll()));
            }
            System.out.println("consumer end");
        });
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }

}