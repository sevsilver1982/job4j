package pseudo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    @Test
    public void whenDrawSquare() {
        assertEquals(
                new StringBuilder()
                        .append("+++++++\n")
                        .append("+     +\n")
                        .append("+     +\n")
                        .append("+++++++").toString(),
                new Square().draw()
        );
    }

    @Test
    public void whenDrawTriangle() {
        assertEquals(
                new StringBuilder()
                        .append("   +\n")
                        .append("  + +\n")
                        .append(" +   +\n")
                        .append("+++++++").toString(),
                new Triangle().draw()
        );
    }

}