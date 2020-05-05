package multithreading.queue;

import org.junit.jupiter.api.Test;

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
            for (int i = 0; i != 10 ; i++) {
                queue.offer(i);
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            consumer.interrupt();
            System.out.println("producer end");
        });
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }

}