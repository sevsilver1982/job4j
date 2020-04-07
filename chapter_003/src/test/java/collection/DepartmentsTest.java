package collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentsTest {

    @Test
    public void whenMissed() {
        assertEquals(
                List.of("k1", "k1/sk1"),
                Departments.fillGaps(
                        List.of("k1/sk1")
                ).stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void whenNonChange() {
        assertEquals(
                List.of("k1", "k1/sk1"),
                Departments.fillGaps(
                        List.of("k1", "k1/sk1")
                ).stream()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList())
        );
    }

}