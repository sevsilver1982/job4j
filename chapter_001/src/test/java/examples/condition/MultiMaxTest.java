package examples.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiMaxTest {

    @Test
    public void whenFirstMax() {
        assertEquals(
                5,
                new MultiMax().max(5, 4, 2)
        );
    }

    @Test
    public void whenSecondMax() {
        assertEquals(
                4,
                new MultiMax().max(1, 4, 2)
        );
    }

    @Test
    public void whenThirdMax() {
        assertEquals(
                7,
                new MultiMax().max(1, 4, 7)
        );
    }

}