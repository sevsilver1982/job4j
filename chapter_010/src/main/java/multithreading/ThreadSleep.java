package multithreading;

public class ThreadSleep {

    public static void main(String[] args) {
        new Thread(
                () -> {
                    System.out.println("Start loading...");
                    for (int i = 0; i <= 100; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.printf("\rLoading: %s", + i);
                    }
                    System.out.println("\nLoaded");
                }).start();
    }

}