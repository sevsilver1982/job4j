package inout;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    public List<File> files(String root, Predicate<File> predicate) {
        List<File> result = new ArrayList<>();
        Queue<File> directory = new PriorityQueue<>();
        Collections.addAll(directory, Objects.requireNonNull(new File(root).listFiles()));
        while (!directory.isEmpty()) {
            File file = directory.poll();
            if (file.isDirectory()) {
                Collections.addAll(directory, Objects.requireNonNull(file.listFiles()));
            }
            if (predicate.test(file)) {
                result.add(file);
            }
        }
        return result;
    }

}