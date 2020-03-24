package ru.job4j.converter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    @Test
    public void rub2eur() {
        double in = 140;
        double expected = 2;
        double actual = Converter.rub2eur(in);
        assertEquals(expected, actual);
    }

    @Test
    public void eur2rub() {
        double in = 1;
        double expected = 70;
        double actual = Converter.eur2rub(in);
        assertEquals(expected, actual);
    }

    @Test
    public void rub2usd() {
        double in = 120;
        double expected = 2;
        double actual = Converter.rub2usd(in);
        assertEquals(expected, actual);
    }

    @Test
    public void usd2rub() {
        double in = 130;
        double expected = 7800;
        double actual = Converter.usd2rub(in);
        assertEquals(expected, actual);
    }

}
