package collection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        int[][] input = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        assertThat(
                new ConvertMatrix2List().toList(input),
                is(List.of(1, 2, 3, 4, 5, 6))
        );
    }
}
