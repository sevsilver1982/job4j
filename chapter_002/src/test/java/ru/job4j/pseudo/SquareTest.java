package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SquareTest {

    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(
                new StringBuilder()
                        .append("+++++++\n")
                        .append("+     +\n")
                        .append("+     +\n")
                        .append("+++++++").toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        Triangle square = new Triangle();
        assertThat(square.draw(), is(
                new StringBuilder()
                        .append("   +\n")
                        .append("  + +\n")
                        .append(" +   +\n")
                        .append("+++++++").toString()
                )
        );
    }

}
