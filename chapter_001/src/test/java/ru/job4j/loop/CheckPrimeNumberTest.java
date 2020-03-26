package ru.job4j.loop;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckPrimeNumberTest {

    @Test
    public void when0() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(0);
        assertThat(rsl, is(false));
    }

    @Test
    public void when1() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(1);
        assertThat(rsl, is(false));
    }

    @Test
    public void when2() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(2);
        assertThat(rsl, is(true));
    }

    @Test
    public void when3() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(3);
        assertThat(rsl, is(true));
    }

    @Test
    public void when4() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(4);
        assertThat(rsl, is(false));
    }

    @Test
    public void when5() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(5);
        assertThat(rsl, is(true));
    }

    @Test
    public void when6() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(6);
        assertThat(rsl, is(false));
    }

    @Test
    public void when7() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(7);
        assertThat(rsl, is(true));
    }

    @Test
    public void when8() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(8);
        assertThat(rsl, is(false));
    }

    @Test
    public void when9() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(9);
        assertThat(rsl, is(false));
    }

    @Test
    public void when10() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(10);
        assertThat(rsl, is(false));
    }

    @Test
    public void when11() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(11);
        assertThat(rsl, is(true));
    }

    @Test
    public void when12() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(12);
        assertThat(rsl, is(false));
    }

    @Test
    public void when13() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(13);
        assertThat(rsl, is(true));
    }

    @Test
    public void when14() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(14);
        assertThat(rsl, is(false));
    }

    @Test
    public void when15() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(15);
        assertThat(rsl, is(false));
    }

    @Test
    public void when16() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(16);
        assertThat(rsl, is(false));
    }

    @Test
    public void when17() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(17);
        assertThat(rsl, is(true));
    }

    @Test
    public void when18() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(18);
        assertThat(rsl, is(false));
    }

    @Test
    public void when19() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(19);
        assertThat(rsl, is(true));
    }

    @Test
    public void when20() {
        CheckPrimeNumber prime = new CheckPrimeNumber();
        boolean rsl = prime.check(20);
        assertThat(rsl, is(false));
    }

}
