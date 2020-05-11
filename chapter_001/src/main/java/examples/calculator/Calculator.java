package examples.calculator;

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
        System.out.println(1 + " + " + 1 + " = " + add(1, 1));
        System.out.println(1 + " + " + 1 + " + " + 1 + " = " + add(1, 1, 1));
        System.out.println(4 + " / " + 2 + " = " + div(4, 2));
        System.out.println(2 + " * " + 1 + " = " + multiply(2, 1));
        System.out.println(10 + " - " + 5 + " = " + subtrack(10, 5));
    }

    /**
     * Сложение двух чисел
     * @param first первый параметр
     * @param second второй параметр
     */
    public static double add(double first, double second) {
        return first + second;
    }

    /**
     * Сложение трёх чисел
     * @param first первый параметр
     * @param second второй параметр
     * @param third третий параметр
     */
    public static double add(double first, double second, double third) {
        return add(first, add(second, third));
    }

    /**
     * Деление
     * @param first первый параметр
     * @param second второй параметр
     */
    public static double div(double first, double second) {
        return first / second;
    }

    /**
     * Умножение
     * @param first первый параметр
     * @param second второй параметр
     */
    public static double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Вычитание
     * @param first первый параметр
     * @param second второй параметр
     */
    public static double subtrack(double first, double second) {
        return first - second;
    }

}
