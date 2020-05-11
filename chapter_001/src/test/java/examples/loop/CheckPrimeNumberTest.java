package examples.loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckPrimeNumberTest {

    @Test
    public void when0() {
        assertFalse(
                new CheckPrimeNumber().check(0)
        );
    }

    @Test
    public void when1() {
        assertFalse(
                new CheckPrimeNumber().check(1)
        );
    }

    @Test
    public void when2() {
        assertTrue(
                new CheckPrimeNumber().check(2)
        );
    }

    @Test
    public void when3() {
        assertTrue(
                new CheckPrimeNumber().check(3)
        );
    }

    @Test
    public void when4() {
        assertFalse(
                new CheckPrimeNumber().check(4)
        );
    }

    @Test
    public void when5() {
        assertTrue(
                new CheckPrimeNumber().check(5)
        );
    }

    @Test
    public void when6() {
        assertFalse(
                new CheckPrimeNumber().check(6)
        );
    }

    @Test
    public void when7() {
        assertTrue(
                new CheckPrimeNumber().check(7)
        );
    }

    @Test
    public void when8() {
        assertFalse(
                new CheckPrimeNumber().check(8)
        );
    }

    @Test
    public void when9() {
        assertFalse(
                new CheckPrimeNumber().check(9)
        );
    }

    @Test
    public void when10() {
        assertFalse(
                new CheckPrimeNumber().check(10)
        );
    }

    @Test
    public void when11() {
        assertTrue(
                new CheckPrimeNumber().check(11)
        );
    }

    @Test
    public void when12() {
        assertFalse(
                new CheckPrimeNumber().check(12)
        );
    }

    @Test
    public void when13() {
        assertTrue(
                new CheckPrimeNumber().check(13)
        );
    }

    @Test
    public void when14() {
        assertFalse(
                new CheckPrimeNumber().check(14)
        );
    }

    @Test
    public void when15() {
        assertFalse(
                new CheckPrimeNumber().check(15)
        );
    }

    @Test
    public void when16() {
        assertFalse(
                new CheckPrimeNumber().check(16)
        );
    }

    @Test
    public void when17() {
        assertTrue(
                new CheckPrimeNumber().check(17)
        );
    }

    @Test
    public void when18() {
        assertFalse(
                new CheckPrimeNumber().check(18)
        );
    }

    @Test
    public void when19() {
        assertTrue(
                new CheckPrimeNumber().check(19)
        );
    }

    @Test
    public void when20() {
        assertFalse(
                new CheckPrimeNumber().check(20)
        );
    }

}