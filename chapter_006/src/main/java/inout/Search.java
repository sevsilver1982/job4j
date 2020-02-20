package inout;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent, List<String> exts) {
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
                if (!exts.contains(
                        currentFile.getAbsolutePath()
                        .substring(
                                currentFile.getAbsolutePath().lastIndexOf(".") + 1
                        )
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