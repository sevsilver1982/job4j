package collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class DepDescCompTest {

    @Test
    public void compare() {
        assertThat(
                new DepDescComp().compare("K2/SK1/SSK2", "K2/SK1/SSK1"),
                lessThan(0)
        );
    }

}
