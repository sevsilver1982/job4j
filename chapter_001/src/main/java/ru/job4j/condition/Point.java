package ru.job4j.condition;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point point2) {
        return Math.sqrt(Math.pow(point2.x - this.x, 2) + Math.pow(point2.y - this.y, 2));
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        double result;

        Point point1 = new Point(0, 0);
        Point point2 = new Point(2, 0);

        result = distance(0, 0, 2, 0);
        System.out.println("result (0, 0) to (2, 0) = " + result);

        result = point1.distance(point2);
        System.out.println("result (0, 0) to (2, 0) = " + result);
    }
}
