package examples.condition;

public class Triangle {
    private Point first;
    private Point second;
    private Point third;

    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }

    /**
     * Расчет периметра треугольника по длинам сторон.
     * Формула: (a + b + c) / 2
     *
     * @param a расстояние между точками a b
     * @param b расстояние между точками a c
     * @param c расстояние между точками b c
     * @return Периметр.
     */
    public double perimeter(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    /**
     * Расчет площади треугольника.
     * Формула: * √ p *(p - ab) * (p - ac) * (p - bc)
     * где √ - корень квадратный, для извлечения корня использовать метод Math.sqrt().
     *
     * @return Вернуть площадь, если треугольник существует или -1.
     */
    public double area() {
        double rsl = -1;
        double a = this.first.distance(this.second);
        double b = this.second.distance(this.third);
        double c = this.third.distance(this.first);
        double p = perimeter(a, b, c);
        if (this.exist(a, b, c)) {
            rsl = Math.round((Math.sqrt(p * (p - a) * (p - b) * (p - c))) * 100.0) / 100.0;
        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     * @param a Длина от точки a b.
     * @param b Длина от точки a c.
     * @param c Длина от точки b c.
     * @return
     */
    private boolean exist(double a, double b, double c) {
        return (a + b > c) && (b + c > a) && (a + c > b);
    }

    public static void main(String[] args) {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(10, 0);

        Triangle triangle = new Triangle(p1, p2, p3);
        triangle.area();
    }
}
