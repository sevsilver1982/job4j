package multithreading.queue;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread threadConsumer1 = new Thread(() -> {
            System.out.println(String.format("%s start", Thread.currentThread().getName()));
            System.out.println(String.format("%s : poll element %s", Thread.currentThread().getName(), queue.poll()));
            System.out.println(String.format("%s end", Thread.currentThread().getName()));
        }, "consumer1");
        Thread threadConsumer2 = new Thread(() -> {
            System.out.println(String.format("%s start", Thread.currentThread().getName()));
            System.out.println(String.format("%s : poll element %s", Thread.currentThread().getName(), queue.poll()));
            System.out.println(String.format("%s end", Thread.currentThread().getName()));
        }, "consumer2");
        Thread threadConsumer3 = new Thread(() -> {
            System.out.println(String.format("%s start", Thread.currentThread().getName()));
            System.out.println(String.format("%s : poll element %s", Thread.currentThread().getName(), queue.poll()));
            System.out.println(String.format("%s end", Thread.currentThread().getName()));
        }, "consumer3");
        Thread threadProducer = new Thread(() -> {
            try {
                System.out.println("producer begin");
                queue.offer(1);
                System.out.println("produce 1");
                sleep(3000);
                queue.offer(2);
                System.out.println("produce 2");
                sleep(3000);
                queue.offer(3);
                System.out.println("produce 3");
                System.out.println("producer end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadConsumer1.start();
        threadConsumer2.start();
        threadConsumer3.start();
        threadProducer.start();
        threadConsumer1.join();
        threadConsumer2.join();
        threadConsumer3.join();
        threadProducer.join();
    }

}