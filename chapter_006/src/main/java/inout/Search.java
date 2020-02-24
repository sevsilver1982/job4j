package inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        File rootDir = new File(parent);
        List<File> result = new ArrayList<>();
        Deque<File> fileTree = new ArrayDeque<>(
                List.of(Objects.requireNonNull(rootDir.listFiles()))
        );
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.poll();
            if (currentFile.isDirectory()) {
                fileTree.addAll(
                        List.of(Objects.requireNonNull(currentFile.listFiles()))
                );
            } else {
                if (!exts.contains(currentFile.getPath()
                        .substring(currentFile.getPath().lastIndexOf(".") + 1)
                )) {
                    result.add(currentFile);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new Search()
                .files("C:\\soft\\docs", List.of("exe", "txt"))
                .forEach(file -> System.out.println(file.getAbsolutePath()));
    }

}