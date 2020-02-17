package inout;

import org.junit.Test;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalysisTest {

    @Test
    public void unavailable() {
        Analysis analise = new Analysis();
        try {
            String actual = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                    .add("200 10:56:01")
                    .add("500 10:57:01")
                    .add("400 10:58:01")
                    .add("200 10:59:01")
                    .add("500 11:01:02")
                    .add("200 11:02:02")
                    .toString();
            InputStream inputStream = new ByteArrayInputStream(actual.getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            analise.unavailable(new InputStreamReader(inputStream), new PrintStream(out));
            String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                    .add("10:57:01;10:59:01")
                    .add("11:01:02;11:02:02")
                    .toString();
            assertThat(new String(out.toByteArray()), is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}