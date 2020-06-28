package multithreading.synch.threadpool;

import org.junit.jupiter.api.Test;

class ThreadPoolTest {

    @Test
    void demo() throws InterruptedException {
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