package inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

public class Analysis {

    public void unavailable(String source, String target) {
        AtomicReference<String> beginTime = new AtomicReference<>("");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(source));
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
                    writer.println(String.format("%s;%s", beginTime.get(), time));
                    beginTime.set("");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}