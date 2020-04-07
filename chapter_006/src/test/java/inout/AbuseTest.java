package inout;

import inout.examples.Abuse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbuseTest {

    @TempDir
    public File file;

    @Test
    public void drop() throws IOException {
        File source = new File(file, "source.txt");
        File target = new File(file, "target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude ");
        }
        Abuse.drop(source.getPath(), target.getPath(), List.of("foolish"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertEquals(
                "hello dude ",
                rsl.toString()
        );
    }

}