package lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FuncTest {

    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        return IntStream
                .range(start, end)
                .mapToObj(i -> func.apply((double) i))
                .collect(Collectors.toList());
    };

    @Test
    public void linear() {
        List<Double> result = diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void quadratic() {
        List<Double> result = diapason(5, 8, x -> x * x);
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(result, is(expected));
    }

    @Test
    public void logarithmic() {
        List<Double> result = diapason(5, 8, x -> Math.log(x + 1));
        List<Double> expected = Arrays.asList(1.791759469228055, 1.9459101490553132, 2.0794415416798357);
        assertThat(result, is(expected));
    }

}