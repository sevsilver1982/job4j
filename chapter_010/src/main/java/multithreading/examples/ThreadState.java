package multithreading.examples;

import java.util.ArrayList;
import java.util.List;

public class ThreadState {

    public static void main(String[] args) {
        /**
         * Collect threads
         */
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(
                    new Thread(
                            () -> {
                                try {
                                    System.out.println(
                                            String.format("begin %s %s", Thread.currentThread().getName(), Thread.currentThread().getState())
                                    );
                                    Thread.sleep(1000);
                                    System.out.println(
                                            String.format("end %s %s", Thread.currentThread().getName(), Thread.currentThread().getState())
                                    );
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            })
            );
        }

        /**
         * Run all threads
         */
        for (Thread thread : threadList) {
            thread.start();
        }

        /**
         * Wait until all threads terminated
         */
        boolean allThreadTerminated;
        do {
            allThreadTerminated = true;
            for (Thread thread : threadList) {
                System.out.println(
                        String.format("%s %s", thread.getName(), thread.getState())
                );
                if (thread.getState() != Thread.State.TERMINATED) {
                    allThreadTerminated = false;
                }
            }
        } while (!allThreadTerminated);

        System.out.println("all threads terminated");
    }

}