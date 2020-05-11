package multithreading.synch;

import multithreading.synch.SimpleBlockingQueue;

import java.util.stream.IntStream;

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
            IntStream.range(0, 10).forEach(value -> {
                queue.offer(value);
                System.out.println(String.format("producer offer element %s", value));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            System.out.println("producer end");
            consumer.interrupt();
        });
        consumer.start();
        producer.start();
    }

}