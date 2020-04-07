package ru.job4j.converter;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    public void rub2eur() {
        assertEquals(
                2,
                Converter.rub2eur(140)
        );
    }

    @Test
    public void eur2rub() {
        assertEquals(
                70,
                Converter.eur2rub(1)
        );
    }

    @Test
    public void rub2usd() {
        assertEquals(
                2,
                Converter.rub2usd(120)
        );
    }

    @Test
    public void usd2rub() {
        assertEquals(
                7800,
                Converter.usd2rub(130)
        );
    }

}