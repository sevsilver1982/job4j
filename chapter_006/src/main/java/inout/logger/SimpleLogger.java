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

    public void log(String msg) {
        out.printf("%s : %s\n",  formatter.format(LocalDateTime.now()), msg);
    }
}
