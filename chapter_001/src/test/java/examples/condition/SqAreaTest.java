package examples.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqAreaTest {

    @Test
    public void whenp4k1s1() {
        assertEquals(
                1,
                SqArea.square(4, 1)
        );
    }

    @Test
    public void whenp6k2s2() {
        assertEquals(
                2,
                SqArea.square(6, 2)
        );
    }

}