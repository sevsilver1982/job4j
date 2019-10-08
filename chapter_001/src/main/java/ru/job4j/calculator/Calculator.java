package ru.job4j.calculator;

/**
 * Калькулятор
 * @author Суворов Евгений
 * @version 1.0
 */
public class Calculator {

    /**
     * Main
     * @param args аргументы
     */
    public static void main(String[] args) {
        add(1, 1);
        div(4, 2);
        multiply(2, 1);
        subtrack(10, 5);
    }

    /**
     * Сложение
     * @param first первый параметр
     * @param second второй параметр
     */
    public static void add(double first, double second) {
        double result =  first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Деление
     * @param first первый параметр
     * @param second второй параметр
     */
    public static void div(double first, double second) {
        double result =  first / second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Умножение
     * @param first первый параметр
     * @param second второй параметр
     */
    public static void multiply(double first, double second) {
        double result =  first * second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Вычитание
     * @param first первый параметр
     * @param second второй параметр
     */
    public static void subtrack(double first, double second) {
        double result =  first - second;
        System.out.println(first + " - " + second + " = " + result);
    }

}
