import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

public class StreamUsage {

    public static void main(String[] args) throws IOException {

        // 10 случайных чисел
        new Random().ints(1, 10+1)
                .distinct()
                .limit(10)
                .sorted()
                .boxed()
                .forEach(System.out::println);

        IntStream
                .iterate(1, n -> n + 1)
                .takeWhile(n -> n <= 5)
                .forEachOrdered (System.out::println);


        //Files.lines(Path.of(""))
        //.map();



    }

}