package sort;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

public class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        assertThat(
                new StringCompare().compare("Ivanov", "Ivanov"),
                is(0)
        );
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        assertThat(
                new StringCompare().compare("Ivanov", "Ivanova"),
                lessThan(0)
        );
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        assertThat(
                new StringCompare().compare("Petrov", "Ivanova"),
                greaterThan(0)
        );
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        assertThat(
                new StringCompare().compare("Petrov", "Patrov"),
                greaterThan(0)
        );
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        assertThat(
                new StringCompare().compare("Patrova", "Petrov"),
                lessThan(0)
        );
    }

}