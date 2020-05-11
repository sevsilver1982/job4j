package examples.loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitnessTest {

    @Test
    public void whenIvanGreatNik() {
        assertEquals(
                0,
                new Fitness().calc(95, 90)
        );
    }

    @Test
    public void whenIvanLessByOneNik() {
        assertEquals(
                1,
                new Fitness().calc(90, 95)
        );
    }

    @Test
    public void whenIvanLessByFewNik() {
        assertEquals(
                2,
                new Fitness().calc(50, 90)
        );
    }

}