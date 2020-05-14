package multithreading.examples;

import java.util.concurrent.atomic.AtomicBoolean;

public class Switcher {
    private final AtomicBoolean trigger = new AtomicBoolean(false);

    public void init() throws InterruptedException {
        Thread first = new Thread(() -> {
            while (true) {
                if (trigger.compareAndSet(false, true)) {
                    System.out.println("Thread A");
                    synchronized (this) {
                        this.notifyAll();
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread second = new Thread(() -> {
            while (true) {
                if (trigger.compareAndSet(true, false)) {
                    System.out.println("Thread B");
                    synchronized (this) {
                        this.notifyAll();
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }

    public static void main(String[] args) throws InterruptedException {
        Switcher switcher = new Switcher();
        switcher.init();
    }

}