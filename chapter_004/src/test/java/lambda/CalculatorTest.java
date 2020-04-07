package lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void whenAdd1Until3() {
        List<Double> buffer = new ArrayList<>();
        new Calculator().multiple(
                0, 3, 1,
                MathUtil::add,
                buffer::add
        );
        assertEquals(
                Arrays.asList(1D, 2D, 3D),
                buffer
        );
    }

}