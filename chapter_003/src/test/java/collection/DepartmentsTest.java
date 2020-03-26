package collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DepartmentsTest {

    @Test
    public void whenMissed() {
        assertThat(
                Departments.fillGaps(List.of("k1/sk1")).stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList()),
                is(List.of("k1", "k1/sk1"))
        );
    }

    @Test
    public void whenNonChange() {
        assertThat(
                Departments.fillGaps(List.of("k1", "k1/sk1")).stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList()),
                is(List.of("k1", "k1/sk1"))
        );
    }

}