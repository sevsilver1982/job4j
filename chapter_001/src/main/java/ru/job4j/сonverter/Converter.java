package ru.job4j.сonverter;

public class Converter {

    public static void main(String[] args) {
        double in;
        double expected;
        double out;
        boolean passed;

        in = 140;
        expected = 2;
        out = rub2eur(in);
        passed = expected == out;
        System.out.println(in + " RUB = 2 EUR; test result: " + passed);
        System.out.println();

        in = 1;
        expected = 70;
        out = eur2rub(in);
        passed = expected == out;
        System.out.println(in + " EUR = 70 RUB; test result : " + passed);
        System.out.println();

        in = 120;
        expected = 2;
        out = rub2usd(in);
        passed = expected == out;
        System.out.println(in + " RUB = 4 USD; test result : " + passed);
        System.out.println();

        in = 130;
        expected = 7800;
        out = usd2rub(in);
        passed = expected == out;
        System.out.println(in + " USD = 4 RUB; test result : " + passed);
        System.out.println();
    }

    public static double rub2eur(double value) {
        double x = value / 70;
        System.out.println(value + " RUB = " + String.valueOf(x) + " EUR");
        return x;
    }

    public static double rub2usd(double value) {
        double x = value / 60;
        System.out.println(value + " RUB = " + x + " USD");
        return x;
    }

    public static double eur2rub(double value) {
        double x = value * 70;
        System.out.println(value + " EUR = " + x + " RUB");
        return x;
    }

    public static double usd2rub(double value) {
        double x = value * 60;
        System.out.println(value + " USD = " + x + " RUB");
        return x;
    }

}
