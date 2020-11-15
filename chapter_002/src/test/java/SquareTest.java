import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pseudo.Square;
import pseudo.Triangle;

public class SquareTest {

    @Test
    public void whenDrawSquare() {
        Assertions.assertEquals(
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
        Assertions.assertEquals(
                new StringBuilder()
                        .append("   +\n")
                        .append("  + +\n")
                        .append(" +   +\n")
                        .append("+++++++").toString(),
                new Triangle().draw()
        );
    }

}