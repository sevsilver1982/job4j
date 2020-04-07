package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        assertTrue(
                new StringCompare().compare("Ivanov", "Ivanov") == 0
        );
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        assertTrue(
                new StringCompare().compare("Ivanov", "Ivanova") < 0
        );
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        assertTrue(
                new StringCompare().compare("Petrov", "Ivanova") > 0
        );
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        assertTrue(
                new StringCompare().compare("Petrov", "Patrov") > 0
        );
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        assertTrue(
                new StringCompare().compare("Patrova", "Petrov") < 0
        );
    }

}