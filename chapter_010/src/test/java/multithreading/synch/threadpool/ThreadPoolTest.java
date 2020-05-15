package multithreading.synch.threadpool;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class ThreadPoolTest {

    @Test
    void demo() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        IntStream.rangeClosed(1, 100).<Runnable>mapToObj(i ->
                () -> System.out.println(String.format("task %s", i))
        ).forEach(threadPool::add);
        Thread.sleep(2000);
        IntStream.rangeClosed(101, 200).<Runnable>mapToObj(i ->
                () -> System.out.println(String.format("task %s", i))
        ).forEach(threadPool::add);
        Thread.sleep(2000);
        threadPool.shutdown();
    }

}