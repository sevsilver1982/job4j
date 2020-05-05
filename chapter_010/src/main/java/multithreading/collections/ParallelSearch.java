package multithreading.collections;

import multithreading.queue.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() || queue.size() > 0) {
                try {
                    System.out.println(String.format("consumer poll element %s", queue.poll()));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        final Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(i);
                System.out.println(String.format("producer offer element %s", i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("producer end");
            consumer.interrupt();
        });
        consumer.start();
        producer.start();
    }

}