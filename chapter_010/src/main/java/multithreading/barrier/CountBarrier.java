package multithreading.barrier;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        this.count++;
        monitor.notifyAll();
    }

    public synchronized void await() {
        if (count == total) {
            monitor.notifyAll();
        } else {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread thread1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                    countBarrier.await();
                    System.out.println("notify thread1");
                },
                "thread1");
        thread1.start();
        Thread thread2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                    countBarrier.await();
                    System.out.println("notify thread2");
                },
                "thread2");
        thread2.start();
        Thread thread3 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                    countBarrier.await();
                    System.out.println("notify thread3");
                },
                "thread3");
        thread3.start();
    }

}