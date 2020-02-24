package inout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private Analysis analise = new Analysis();

    @Test
    public void unavailable() throws IOException {
        File tmp = folder.newFile("server.log");
        try (PrintWriter out = new PrintWriter(tmp)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = folder.newFile("unavailable.csv");
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