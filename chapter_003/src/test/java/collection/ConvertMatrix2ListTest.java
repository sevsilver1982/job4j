package collection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        assertEquals(
                List.of(1, 2, 3, 4, 5, 6),
                new ConvertMatrix2List().toList(
                        new int[][] {
                                {1, 2},
                                {3, 4},
                                {5, 6}
                        })
        );
    }

}