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

    public String log(String msg) {
        String result = String.format("%s : %s",  formatter.format(LocalDateTime.now()), msg);
        try {
            out.write(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}