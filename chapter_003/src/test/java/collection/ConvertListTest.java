package collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertListTest {

    @Test
    public void convertTest() {
        assertThat(
                new ConvertList().convert(Arrays.asList(
                        new int[]{1, 2},
                        new int[]{3, 4, 5, 6},
                        new int[]{7},
                        new int[]{8, 9, 10, 11, 12, 13}
                        )
                ),
                is(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13))
        );
    }

    @Test
    public void when7ElementsThen9() {
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(
                new ConvertList().toArray(List.of(1, 2, 3, 4, 5, 6, 7), 3),
                is(expect)
        );
    }
}