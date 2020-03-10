package inout.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class FunctionWriter implements Function<String, String> {

    @Override
    public String apply(String fileName) {
        String result = "";
        try {
            BufferedReader read = new BufferedReader(new FileReader(fileName));
            result = read.lines().skip(
                    (int) (Math.random() * new BufferedReader(new FileReader(fileName)).lines().count())
            ).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}