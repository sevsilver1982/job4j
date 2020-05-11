package examples.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeTest {

    @Test
    public void whenBothEmpty() {
        assertArrayEquals(
                new int[0],
                new Merge().merge(
                        new int[0],
                        new int[0]
                )
        );
    }

    @Test
    public void whenAscOrder() {
        assertArrayEquals(
                new int[] {1, 2, 3, 4},
                new Merge().merge(
                        new int[] {1, 2},
                        new int[] {3, 4}
                        )
        );
    }

    @Test
    public void whenLeftLess() {
        assertArrayEquals(
                new int[] {1, 2, 3, 3, 4},
                new Merge().merge(
                        new int[] {1, 2, 3},
                        new int[] {3, 4}
                        )
        );
    }

    @Test
    public void whenLeftGreat() {
        assertArrayEquals(
                new int[] {1, 2, 3, 4, 4},
                new Merge().merge(
                        new int[] {1, 2},
                        new int[] {3, 4, 4}
                        )
        );
    }

    @Test
    public void whenLeftEmpty() {
        assertArrayEquals(
                new int[] {1, 3, 4, 4},
                new Merge().merge(
                        new int[] {},
                        new int[] {1, 3, 4, 4}
                        )
        );
    }

}