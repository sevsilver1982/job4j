package ru.job4j.condition;

public class SqArea {

    public static double square(double p, double k) {
        double L = p / 3;
        double h = L / k;
        double s = L * h;
        return s;
    }

    public static void main(String[] args) {
        double result1 = square(6, 2);
        System.out.println(" p = 6, k = 2, s = 2, real = " + result1);

        double result2 = square(5, 9);
        System.out.println(" p = 4, k = 1, s = 1, real = " + result2);
    }

}
