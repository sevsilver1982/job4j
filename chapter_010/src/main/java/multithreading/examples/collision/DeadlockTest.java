package multithreading.examples.collision;

public class DeadlockTest {
    private static final String RESOURCE_1 = "resource1";
    private static final String RESOURCE_2 = "resource2";

    public void init() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (RESOURCE_1) {
                System.out.println("Thread 1: locked resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (RESOURCE_2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (RESOURCE_2) {
                System.out.println("Thread 2: locked resource 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (RESOURCE_1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockTest deadlockTest = new DeadlockTest();
        deadlockTest.init();
    }

}