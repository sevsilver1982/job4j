package inout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalysisTest {
    private Analysis analise = new Analysis();

    @TempDir
    public File file;

    @Test
    public void unavailable() throws IOException {
        File tmp = new File(file, "server.log");
        try (PrintWriter out = new PrintWriter(tmp)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = new File(file, "unavailable.csv");
        analise.unavailable(tmp.getPath(), target.getPath());
        StringJoiner actual = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getPath()))) {
            reader.lines().forEach(actual::add);
        }
        StringJoiner expected = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("10:57:01;10:59:01")
                .add("11:01:02;11:02:02");

        assertThat(actual.toString(), is(expected.toString()));
    }

}