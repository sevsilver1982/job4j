package pseudo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaintTest {
    private static PrintStream stdout = System.out;
    private static ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeAll
    public static void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }

    @AfterAll
    public static void backOutput() {
        System.setOut(stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                out.toString(),
                is(
                        new StringBuilder()
                                .append("+++++++\n")
                                .append("+     +\n")
                                .append("+     +\n")
                                .append("+++++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +\n")
                                .append("  + +\n")
                                .append(" +   +\n")
                                .append("+++++++")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}