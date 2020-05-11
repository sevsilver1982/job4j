package examples.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TurnTest {

    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        assertArrayEquals(
                new int[] {2, 6, 1, 4},
                new Turn().back(
                        new int[] {4, 1, 6, 2}
                        )
        );
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        assertArrayEquals(
                new int[] {5, 4, 3, 2, 1},
                new Turn().back(
                        new int[] {1, 2, 3, 4, 5}
                        )
        );
    }

}