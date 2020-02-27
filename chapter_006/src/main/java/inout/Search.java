package inout;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    public List<File> files(String parent, Predicate<String> predicate) {
        File rootDir = new File(parent);
        List<File> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>(
                List.of(Objects.requireNonNull(rootDir.listFiles()))
        );
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.poll();
            if (currentFile.isDirectory()) {
                fileTree.addAll(
                        List.of(Objects.requireNonNull(currentFile.listFiles()))
                );
            } else {
                if (predicate.test(currentFile.getPath())) {
                    result.add(currentFile);
                }
            }
        }
        return result;
    }

}