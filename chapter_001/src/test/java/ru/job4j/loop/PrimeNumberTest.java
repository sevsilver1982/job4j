package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PrimeNumberTest {

    @Test
    public void when0() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(0);
        assertThat(rsl, is(0));
    }

    @Test
    public void when1() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(1);
        assertThat(rsl, is(0));
    }

    @Test
    public void when2() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(2);
        assertThat(rsl, is(1));
    }

    @Test
    public void when3() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(3);
        assertThat(rsl, is(2));
    }

    @Test
    public void when4() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(4);
        assertThat(rsl, is(2));
    }

    @Test
    public void when5() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(5);
        assertThat(rsl, is(3));
    }

    @Test
    public void when6() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(6);
        assertThat(rsl, is(3));
    }

    @Test
    public void when7() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(7);
        assertThat(rsl, is(4));
    }

    @Test
    public void when8() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(8);
        assertThat(rsl, is(4));
    }

    @Test
    public void when9() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(9);
        assertThat(rsl, is(4));
    }

    @Test
    public void when10() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(10);
        assertThat(rsl, is(4));
    }

    @Test
    public void when11() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(11);
        assertThat(rsl, is(5));
    }

    @Test
    public void when12() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(12);
        assertThat(rsl, is(5));
    }

    @Test
    public void when13() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(13);
        assertThat(rsl, is(6));
    }

    @Test
    public void when14() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(14);
        assertThat(rsl, is(6));
    }

    @Test
    public void when15() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(15);
        assertThat(rsl, is(6));
    }

    @Test
    public void when16() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(16);
        assertThat(rsl, is(6));
    }

    @Test
    public void when17() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(17);
        assertThat(rsl, is(7));
    }

    @Test
    public void when18() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(18);
        assertThat(rsl, is(7));
    }

    @Test
    public void when19() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(19);
        assertThat(rsl, is(8));
    }

    @Test
    public void when20() {
        PrimeNumber prime = new PrimeNumber();
        int rsl = prime.calc(20);
        assertThat(rsl, is(8));
    }

}
