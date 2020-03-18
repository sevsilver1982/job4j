package inout.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogger {
    private OutputStream out;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    public SimpleLogger(OutputStream out) {
        this.out = out;
    }

    public OutputStream getOutput() {
        return out;
    }

    public void write(String msg) {
        try {
            out.write(String.format("%s  %s",  formatter.format(LocalDateTime.now()), msg).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(String msg) {
        try {
            out.write(String.format("%s  %s\n",  formatter.format(LocalDateTime.now()), msg).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}