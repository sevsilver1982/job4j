package examples.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitTest {

    @Test
    public void manWeight() {
        assertEquals(
                96.6,
                Fit.manWeight(184)
        );
    }

    @Test
    public void womanWeight() {
        assertEquals(
                80.5,
                Fit.womanWeight(180)
        );
    }

}