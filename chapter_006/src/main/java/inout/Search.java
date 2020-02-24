package inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> ext) {
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
                if (ext.contains(
                        currentFile.getPath()
                        .substring(currentFile.getPath().lastIndexOf(".") + 1)
                )) {
                    result.add(currentFile);
                }
            }
        }
        return result;
    }

}