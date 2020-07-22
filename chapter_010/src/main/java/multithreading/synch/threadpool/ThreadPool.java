package multithreading.synch.threadpool;

import multithreading.synch.SimpleBlockingQueue;

import java.util.concurrent.CountDownLatch;

public class ThreadPool {
    private final SimpleBlockingQueue<Thread> threads = new SimpleBlockingQueue<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final int threadCount;
    final CountDownLatch count;
    volatile boolean stop = false;

    public ThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.count = new CountDownLatch(threadCount);
    }

    public void submit(Runnable job) {
        tasks.offer(job);
    }

    public void init() {
        for (int i = 1; i <= threadCount; i++) {
            threads.offer(newMainThread());
        }
    }

    public void start() {
        do {
            try {
                threads.poll().start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (threads.size() < threadCount) {
                threads.offer(newMainThread());
            }
            System.out.println(
                    Thread.activeCount()
            );
        } while (true);
    }

    public Thread newMainThread() {
        return new Thread(() -> {
            //System.out.println(Thread.currentThread().getName());
            try {
                Runnable runnable = tasks.poll();
                if (runnable != null) {
                    runnable.run();
                }
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() {
        System.out.println("123");
        /*try {
            count.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
    }

    public static void main(String[] args) {
        final int threadsCount = 5; // Runtime.getRuntime().availableProcessors();
        final int tasksCount = 10;

        ThreadPool threadPool = new ThreadPool(threadsCount);
        threadPool.init();

        for (int i = 1; i <= tasksCount; i++) {
            int taskNumber = i;
            threadPool.submit(() -> {
                System.out.println(String.format("process task %s ", taskNumber));
            });
        }

        threadPool.start();

        threadPool.shutdown();
    }

}