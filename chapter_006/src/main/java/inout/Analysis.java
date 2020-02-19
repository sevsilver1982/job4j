package inout;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

public class Analysis {

    public void writeTarget(PrintWriter writer, String str) {
        writer.println(str);
    }

    public void unavailable(Reader source, PrintStream target) {
        AtomicReference<String> beginTime = new AtomicReference<>("");
        try (
                BufferedReader reader = new BufferedReader(source);
                PrintWriter writer = new PrintWriter(target)
        ) {
            reader.lines().forEach(line -> {
                String[] tmp = line.split(" ");
                String status = tmp[0];
                String time = tmp[1];
                if (beginTime.get().equals("") && (status.equals("400") || status.equals("500"))) {
                    beginTime.set(time);
                }
                if (!beginTime.get().equals("") && (status.equals("200") || status.equals("300"))) {
                    writeTarget(writer, String.format("%s;%s", beginTime.get(), time));
                    beginTime.set("");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analise = new Analysis();
        try {
            analise.unavailable(
                    new InputStreamReader(new FileInputStream(".\\chapter_006\\server.log")),
                    new PrintStream(".\\chapter_006\\unavailable.csv")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}