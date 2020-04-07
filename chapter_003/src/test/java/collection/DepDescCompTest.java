package collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepDescCompTest {

    @Test
    public void compare() {
        assertTrue(
                new DepDescComp().compare("K2/SK1/SSK2", "K2/SK1/SSK1") < 0
        );
    }

}