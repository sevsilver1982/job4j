package examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ConvertListTest {

    @Test
    public void convertTest() {
        assertEquals(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
                new ConvertList().convert(
                        List.of(
                                new int[] {1, 2},
                                new int[] {3, 4, 5, 6},
                                new int[] {7},
                                new int[] {8, 9, 10, 11, 12, 13}
                                )
                )
        );
    }

    @Test
    public void when7ElementsThen9() {
        assertArrayEquals(
                new int[][] {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 0, 0}
                        },
                new ConvertList().toArray(
                        List.of(1, 2, 3, 4, 5, 6, 7),
                        3
                )
        );
    }

}