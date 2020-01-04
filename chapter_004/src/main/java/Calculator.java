import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public void multiple(int start, int finish, int value, BiFunction<Integer, Integer, Double> function, Consumer<Double> consumer) {
        for (int index = start; index < finish; index++) {
            consumer.accept(function.apply(value, index));
        }
    }

    public static void main(String[] args) {
        /*Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                },
                result -> System.out.println(result)
        );*/

        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 1,
                MathUtil::add,
                System.out::println
        );
    }

}