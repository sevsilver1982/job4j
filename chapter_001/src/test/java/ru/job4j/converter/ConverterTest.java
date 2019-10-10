package ru.job4j.converter;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {
    @Test
    public void rub2eur() {
        double in = 140;
        double expected = 2;
        double actual = Converter.rub2eur(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void eur2rub() {
        double in = 1;
        double expected = 70;
        double actual = Converter.eur2rub(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void rub2usd() {
        double in = 120;
        double expected = 2;
        double actual = Converter.rub2usd(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void usd2rub() {
        double in = 130;
        double expected = 7800;
        double actual = Converter.usd2rub(in);
        Assert.assertEquals(expected, actual, 0);
    }

}
