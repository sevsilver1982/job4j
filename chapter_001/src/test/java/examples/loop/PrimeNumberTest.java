package examples.loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeNumberTest {

    @Test
    public void when0() {
        assertEquals(
                0,
                new PrimeNumber().calc(0)
        );
    }

    @Test
    public void when1() {
        assertEquals(
                0,
                new PrimeNumber().calc(1)
        );
    }

    @Test
    public void when2() {
        assertEquals(
                1,
                new PrimeNumber().calc(2)
        );
    }

    @Test
    public void when3() {
        assertEquals(
                2,
                new PrimeNumber().calc(3)
        );
    }

    @Test
    public void when4() {
        assertEquals(
                2,
                new PrimeNumber().calc(4)
        );
    }

    @Test
    public void when5() {
        assertEquals(
                3,
                new PrimeNumber().calc(5)
        );
    }

    @Test
    public void when6() {
        assertEquals(
                3,
                new PrimeNumber().calc(6)
        );
    }

    @Test
    public void when7() {
        assertEquals(
                4,
                new PrimeNumber().calc(7)
        );
    }

    @Test
    public void when8() {
        assertEquals(
                4,
                new PrimeNumber().calc(8)
        );
    }

    @Test
    public void when9() {
        assertEquals(
                4,
                new PrimeNumber().calc(9)
        );
    }

    @Test
    public void when10() {
        assertEquals(
                4,
                new PrimeNumber().calc(10)
        );
    }

    @Test
    public void when11() {
        assertEquals(
                5,
                new PrimeNumber().calc(11)
        );
    }

    @Test
    public void when12() {
        assertEquals(
                5,
                new PrimeNumber().calc(12)
        );
    }

    @Test
    public void when13() {
        assertEquals(
                6,
                new PrimeNumber().calc(13)
        );
    }

    @Test
    public void when14() {
        assertEquals(
                6,
                new PrimeNumber().calc(14)
        );
    }

    @Test
    public void when15() {
        assertEquals(
                6,
                new PrimeNumber().calc(15)
        );
    }

    @Test
    public void when16() {
        assertEquals(
                6,
                new PrimeNumber().calc(16)
        );
    }

    @Test
    public void when17() {
        assertEquals(
                7,
                new PrimeNumber().calc(17)
        );
    }

    @Test
    public void when18() {
        assertEquals(
                7,
                new PrimeNumber().calc(18)
        );
    }

    @Test
    public void when19() {
        assertEquals(
                8,
                new PrimeNumber().calc(19)
        );
    }

    @Test
    public void when20() {
        assertEquals(
                8,
                new PrimeNumber().calc(20)
        );
    }

}