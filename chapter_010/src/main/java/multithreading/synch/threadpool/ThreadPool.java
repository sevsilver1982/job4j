package multithreading.synch.threadpool;

import multithreading.synch.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;


public class ThreadPool {
    private final List<Thread> threadList = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors())
                .mapToObj(this::apply)
                .forEach(e -> {
                    threadList.add(e);
                    e.start();
                });
    }

    private Thread apply(int i) {
        return new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void add(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        boolean allThreadsIsInterrupted;
        do {
            threadList.forEach(thread -> {
                if (!thread.isInterrupted()) {
                    thread.interrupt();
                }
            });
            allThreadsIsInterrupted = true;
            for (int i = 0; i < threadList.size(); i++) {
                if (threadList.get(i).getState() != Thread.State.TERMINATED) {
                    allThreadsIsInterrupted = false;
                    System.out.println(i);
                    break;
                }
            }
        } while (!allThreadsIsInterrupted);
    }

}