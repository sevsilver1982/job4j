package multithreading.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class FileDownload {
    private static final Integer DEFAULT_CONTROL_TIME = 1000;
    private static final Integer DEFAULT_BUFFER_LENGTH = 1024;
    private final int bufferLength;
    private final String in;
    private final String out;
    private final Integer rate;
    private Integer total = 0;
    private Integer bytesPerSecond = 0;
    private Thread threadDownload;

    public FileDownload(String in, String out, int rate) {
        this.in = in;
        this.out = out;
        this.rate = rate;
        this.bufferLength = Math.min(DEFAULT_BUFFER_LENGTH, rate);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer totalBytesRead) {
        this.total = totalBytesRead;
    }

    public Integer getBytesPerSecond() {
        return bytesPerSecond;
    }

    public void setBytesPerSecond(Integer bytesPerSecond) {
        this.bytesPerSecond = bytesPerSecond;
    }

    public void downloadRateControl() {
        do {
            int before = getTotal();
            try {
                sleep(DEFAULT_CONTROL_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setBytesPerSecond(getTotal() - before);
        } while (threadDownload.getState() != Thread.State.TERMINATED);
    }

    public void download() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(in).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(out)
        ) {
            byte[] dataBuffer = new byte[bufferLength];
            int bytesRead;
            int total;
            int pause;
            while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                total = getTotal();
                setTotal(total + bytesRead);
                pause = rate <= 0 ? 1 : DEFAULT_CONTROL_TIME * bufferLength / rate;
                System.out.print(
                        String.format(
                                "\rload: %s; %s bsec; %s ms; state: %s",
                                total,
                                getBytesPerSecond(),
                                pause,
                                threadDownload.getState()
                        )
                );
                sleep(pause);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        threadDownload = new Thread(this::download);
        threadDownload.start();
        new Thread(this::downloadRateControl).start();
    }

    public static void main(String[] args) {
        Args arguments = new Args(args);
        new FileDownload(
                arguments.getUrl(),
                arguments.getOutput(),
                arguments.getRate() * 1024
        ).start();
    }

}