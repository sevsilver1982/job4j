package lambda;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FuncTest {

    @Test
    public void linear() {
        assertThat(
                new MathUtil().diapason(5, 8, x -> 2 * x + 1),
                is(List.of(11D, 13D, 15D))
        );
    }

    @Test
    public void quadratic() {
        assertThat(
                new MathUtil().diapason(5, 8, x -> x * x),
                is(List.of(25D, 36D, 49D)));
    }

    @Test
    public void logarithmic() {
        List<Double> result = new MathUtil().diapason(5, 8, x -> Math.log(x + 1));
        List<Double> expected = List.of(1.791759469228055, 1.9459101490553132, 2.0794415416798357);
        assertThat(result, is(expected));
    }

}