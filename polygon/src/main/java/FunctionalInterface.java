import java.util.function.*;
import java.util.stream.Stream;

public class FunctionalInterface {

    public static void main(String[] args) {
        Predicate<Integer> predicate = i -> i >= 5;
        System.out.println(predicate.test(3));

        Consumer<String> consumer = s -> System.out.println(String.format("Consumer: %s", s));
        consumer.accept("consumer");

        Function<Integer, String> function1 = i -> String.format("result = %s", i * 200);
        System.out.println(String.format("Function1: %s", function1.apply(5)));

        Function<Integer, Integer> function2 = i -> i * 100;
        System.out.println(String.format("Function2: %s", function2.apply(5)));

        BiFunction<Integer, Integer, String> biFunction = (i1, i2) -> String.format("result = %s", i1 * i2);
        System.out.println(String.format("BiFunction: %s", biFunction.apply(5, 5)));

        Supplier<Integer> supplier = () -> 3;
        System.out.println(String.format("Supplier: %s", supplier.get()));

        UnaryOperator<String> unaryOperator = i -> i + "10";
        System.out.println(String.format("UnaryOperator: %s", unaryOperator.apply("5D")));

        BinaryOperator<Integer> binaryOperator = (i1, i2) -> i1 * i2;
        System.out.println(String.format("BinaryOperator: %s", binaryOperator.apply(10, 10)));

        System.out.println(Stream.of(1, 2, 3, 4, 5).reduce((i1, i2) -> {
            System.out.println(i1 + " " + i2);
            return i1 + i2;
        }).get());
    }

}