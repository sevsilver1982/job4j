package examples.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DefragTest {

    @Test
    public void notFirstNull() {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        assertArrayEquals(
                new String[] {"I", "wanna", "be", "compressed", null, null, null},
                Defrag.compress(input)
        );
    }

    @Test
    public void firstNull() {
        String[] input = {null, "I", "wanna", null, "be", null, "compressed"};
        String[] compressed = Defrag.compress(input);
        assertArrayEquals(
                new String[] {"I", "wanna", "be", "compressed", null, null, null},
                Defrag.compress(input)
        );
    }

}