package lambda;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncTest {

    @Test
    public void linear() {
        assertEquals(
                List.of(11D, 13D, 15D),
                new MathUtil().diapason(
                        5,
                        8,
                        x -> 2 * x + 1
                )
        );
    }

    @Test
    public void quadratic() {
        assertEquals(
                List.of(25D, 36D, 49D),
                new MathUtil().diapason(
                        5,
                        8,
                        x -> x * x
                )
        );
    }

    @Test
    public void logarithmic() {
        assertEquals(
                List.of(1.791759469228055, 1.9459101490553132, 2.0794415416798357),
                new MathUtil().diapason(
                        5,
                        8,
                        x -> Math.log(x + 1)
                )
        );
    }

}