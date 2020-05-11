package examples.loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageTest {

    @Test
    public void when1Year() {
        assertEquals(
                1,
                new Mortgage().year(1000, 1200, 1)
        );
    }

    @Test
    public void when2Year() {
        assertEquals(
                2,
                new Mortgage().year(100, 120, 50)
        );
    }

}