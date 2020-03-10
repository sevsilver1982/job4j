package inout.logger;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogger {
    private PrintStream out;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    public SimpleLogger(PrintStream out) {
        this.out = out;
    }

    public String log(String msg) {
        String result = String.format("%s : %s",  formatter.format(LocalDateTime.now()), msg);
        out.println(result);
        return result;
    }

}