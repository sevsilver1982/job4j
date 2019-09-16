package ru.job4j.converter;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.сonverter.Converter;

public class ConverterTest {
    @Test
    public void RUB2EUR() {
        double in = 140;
        double expected = 2;
        double actual = Converter.RUB2EUR(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void EUR2RUB() {
        double in = 1;
        double expected = 70;
        double actual = Converter.EUR2RUB(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void RUB2USD() {
        double in = 120;
        double expected = 2;
        double actual = Converter.RUB2USD(in);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void USD2RUB() {
        double in = 130;
        double expected = 7800;
        double actual = Converter.USD2RUB(in);
        Assert.assertEquals(expected, actual, 0);
    }

}
