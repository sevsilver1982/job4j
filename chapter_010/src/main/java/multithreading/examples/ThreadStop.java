package multithreading.examples;

public class ThreadStop {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}