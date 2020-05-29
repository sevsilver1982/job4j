package multithreading.examples;

import java.util.concurrent.CountDownLatch;

public class UsageCountDownLatch {
    private final CountDownLatch countDownLatch;
    private final int threadCount;

    public UsageCountDownLatch(int threadCount) {
        this.threadCount = threadCount;
        this.countDownLatch = new CountDownLatch(threadCount);
    }

    public void start() {
        for (int i = 0; i < threadCount; i++) {
            int n = i;
            new Thread(() -> {
                try {
                    System.out.println(n + " start");
                    Thread.sleep(n * 100);
                    System.out.println(n + " end");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        UsageCountDownLatch usageCountDownLatch = new UsageCountDownLatch(10);
        usageCountDownLatch.start();
    }

}