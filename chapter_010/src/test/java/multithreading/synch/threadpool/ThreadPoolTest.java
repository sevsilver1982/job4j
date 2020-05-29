package multithreading.synch.threadpool;

import org.junit.jupiter.api.Test;

class ThreadPoolTest {

    @Test
    void demo() {
        ThreadPool threadPool = new ThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            threadPool.add(() -> {
                System.out.println(String.format("task %s", finalI));
                try {
                    Thread.sleep(finalI * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

}