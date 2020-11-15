import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pseudo.Paint;
import pseudo.Square;
import pseudo.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(
                new StringBuilder()
                        .append("+++++++\n")
                        .append("+     +\n")
                        .append("+     +\n")
                        .append("+++++++")
                        .append(System.lineSeparator())
                        .toString(),
                out.toString()
        );
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertEquals(
                new StringBuilder()
                        .append("   +\n")
                        .append("  + +\n")
                        .append(" +   +\n")
                        .append("+++++++")
                        .append(System.lineSeparator())
                        .toString(),
                new String(out.toByteArray())
        );
        System.setOut(stdout);
    }

}