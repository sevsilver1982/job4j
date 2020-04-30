package multithreading.examples;

public class ConsoleProgress implements Runnable {
    private final char[] progress = new char[] {
      '―', '\\', '|', '/'
    };

    @Override
    public void run() {
        int step = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                System.out.printf(String.format("\r load: %s", progress[step]));
                step = step == 3 ? 0 : step + 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }

}