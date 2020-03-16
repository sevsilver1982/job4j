package inout.examples;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseURL {

    public static void main(String[] args) {
        String url = "http://localhost:9000/?msg=1234567890&qwe=321";
        Map<String, String> result =
                Stream.of(url.split("\\?")[1])
                .flatMap(params -> Stream.of(params.split("&")))
                .flatMap(param -> Stream.of(param.split("=")))
                .collect(
                        Collectors.toMap(
                                p -> p, p1 -> p1)
                        );
        result.forEach((s1, s2) -> System.out.println(s1 + " = " + s2));
    }

}
