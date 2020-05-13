package multithreading.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class FileDownload implements Runnable {
    private static final Integer DEFAULT_CONTROL_TIME = 1000;
    private static final Integer DEFAULT_BUFFER_LENGTH = 1024;
    private final int bufferLength;
    private final String in;
    private final String out;
    private final Integer rate;

    public FileDownload(String in, String out, int rate) {
        this.in = in;
        this.out = out;
        this.rate = rate;
        this.bufferLength = Math.min(DEFAULT_BUFFER_LENGTH, rate);
    }

    @Override
    public void run() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(in).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(out)
        ) {
            byte[] dataBuffer = new byte[bufferLength];
            int bytesRead;
            int bytesReadTotal = 0;

            long timeBefore = 0;
            long timeAfter = 0;
            long timeTotal = 0;
            long bytesPerSecond = 0;
            long bytesPerSecondCounter = 0;
            long pause = DEFAULT_CONTROL_TIME;

            while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                timeBefore = System.currentTimeMillis();
                if (timeTotal >= DEFAULT_CONTROL_TIME) {
                    pause = rate <= 0 ? 1 : DEFAULT_CONTROL_TIME * bufferLength / rate;
                    bytesPerSecond = bytesPerSecondCounter;
                    bytesPerSecondCounter = 0;
                    timeTotal = 0;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesReadTotal += bytesRead;
                bytesPerSecondCounter += bytesRead;
                System.out.print(
                        String.format(
                                "\rtotalBytesRead: %s; bytesPerSecond=%s; pause=%s; state: %s",
                                bytesReadTotal,
                                bytesPerSecond,
                                pause,
                                Thread.currentThread().getState()
                        )
                );
                sleep(pause);
                timeAfter = System.currentTimeMillis();
                timeTotal = timeTotal + (timeAfter - timeBefore);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void download() {
        new Thread(this).start();
    }

    public static void main(String[] args) {
        Args arguments = new Args(args);
        new FileDownload(
                arguments.getUrl(),
                arguments.getOutput(),
                arguments.getRate() * 1024
        ).download();
    }

}