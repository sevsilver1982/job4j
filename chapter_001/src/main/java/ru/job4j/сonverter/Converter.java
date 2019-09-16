package ru.job4j.сonverter;

public class Converter {

    public static void main(String[] args) {
        double in;
        double expected;
        double out;
        boolean passed;

        in = 140;
        expected = 2;
        out = RUB2EUR(in);
        passed = expected == out;
        System.out.println(in + " RUB = 2 EUR; test result: " + passed);
        System.out.println();

        in = 1;
        expected = 70;
        out = EUR2RUB(in);
        passed = expected == out;
        System.out.println(in + " EUR = 70 RUB; test result : " + passed);
        System.out.println();

        in = 120;
        expected = 2;
        out = RUB2USD(in);
        passed = expected == out;
        System.out.println(in + " RUB = 4 USD; test result : " + passed);
        System.out.println();

        in = 130;
        expected = 7800;
        out = USD2RUB(in);
        passed = expected == out;
        System.out.println(in + " USD = 4 RUB; test result : " + passed);
        System.out.println();
    }

    public static double RUB2EUR(double value) {
        double x = value / 70;
        System.out.println(value + " RUB = " + String.valueOf(x) + " EUR");
        return x;
    }

    public static double RUB2USD(double value) {
        double x = value / 60;
        System.out.println(value + " RUB = " + x + " USD");
        return x;
    }

    public static double EUR2RUB(double value) {
        double x = value * 70;
        System.out.println(value + " EUR = " + x + " RUB");
        return x;
    }

    public static double USD2RUB(double value) {
        double x = value * 60;
        System.out.println(value + " USD = " + x + " RUB");
        return x;
    }

}
