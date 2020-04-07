import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MachineTest {
    @Test
    public void whenEquals() {
        assertArrayEquals(
                new int[] {},
                new Machine().change(100, 100)
        );
    }

    @Test
    public void when50by35() {
        assertArrayEquals(
                new int[] {10, 5},
                new Machine().change(50, 35)
        );
    }

}