package inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exists) {
        File rootDir = new File(parent);
        List<File> result = new ArrayList<>();
        Deque<File> fileTree = new ArrayDeque<>(
                Arrays.asList(rootDir.listFiles())
        );
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.poll();
            if (currentFile.isDirectory()) {
                fileTree.addAll(
                        Arrays.asList(Objects.requireNonNull(currentFile.listFiles()))
                );
            } else {
                if (exists.contains(
                        currentFile.getPath()
                        .substring(
                                currentFile.getPath().lastIndexOf(".") + 1
                        )
                )) {
                    result.add(currentFile);
                }
            }
        }
        return result;
    }

}