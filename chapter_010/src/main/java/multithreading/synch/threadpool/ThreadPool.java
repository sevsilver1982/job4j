package multithreading.synch.threadpool;

import multithreading.synch.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ThreadPool {
    CountDownLatch countDownLatch;
    private final List<Thread> threadList = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool(int threadCount) {
        countDownLatch = new CountDownLatch(threadCount);
        for (int i = 1; i <= threadCount; i++) {
            Thread thread = new Thread(() -> {
                try {
                    tasks.poll().run();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    countDownLatch.countDown();
                    Thread.currentThread().interrupt();
                }
            });
            threadList.add(thread);
            thread.start();
        }
    }

    public void add(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}