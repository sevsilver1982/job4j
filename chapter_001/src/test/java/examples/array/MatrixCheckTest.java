package examples.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixCheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        assertTrue(
                MatrixCheck.isWin(
                        new char[][] {
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                        })
        );
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        assertFalse(
                MatrixCheck.isWin(
                        new char[][] {
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', 'X', ' ', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                                {' ', ' ', 'X', ' ', ' '},
                        })
        );
    }

    @Test
    public void test2() {
        assertTrue(
                MatrixCheck.isWin(
                        new char[][] {
                                {'X', 'X', 'X', 'X', 'X'},
                                {'X', ' ', ' ', ' ', ' '},
                                {'X', ' ', ' ', ' ', ' '},
                                {'X', ' ', ' ', ' ', ' '},
                                {'X', ' ', ' ', ' ', ' '},
                        })
        );
    }

}