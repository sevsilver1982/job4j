package multithreading.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {
    private String in;
    private String out;
    private int rate = 200;

    public FileDownload(String in, String out, int rate) {
        this.in = in;
        this.out = out;
        this.rate = rate;
    }


    public void download(String in6, String out, int rate) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.in).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(this.out)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int totalBytesRead = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                totalBytesRead += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                System.out.printf(String.format("\rload: %s", totalBytesRead));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        /*new Thread(
            new FileDownload(
                    "https://youtu.be/9xFYc3KCbHw",
                    "C:/soft/workspace/job4j/video.avi",
                    200
            )
        ).start();*/
    }

}