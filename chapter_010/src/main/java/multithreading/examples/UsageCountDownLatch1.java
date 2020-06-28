package multithreading.examples;

import java.util.concurrent.CountDownLatch;

public class UsageCountDownLatch1 {
    private final CountDownLatch countDownLatch;
    private final int threadCount;
    volatile boolean stop = false;

    public UsageCountDownLatch1(int threadCount) {
        this.threadCount = threadCount;
        this.countDownLatch = new CountDownLatch(threadCount);
    }

    public void start() {
        for (int i = 0; i < threadCount; i++) {
            int n = i;
            new Thread(() -> {
                System.out.println(n + " start");
                try {
                    Thread.sleep(n * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(n + " end");
                countDownLatch.countDown();
            }).start();
        }
        try {
            System.out.println("begin wait");
            countDownLatch.await();
            System.out.println("end wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsageCountDownLatch1 usageCountDownLatch = new UsageCountDownLatch1(5);
        usageCountDownLatch.start();
    }

}