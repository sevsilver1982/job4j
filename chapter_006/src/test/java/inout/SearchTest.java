package inout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest {

    @TempDir
    public static Path folder;
    public static File file;

    @BeforeAll
    public static void prepare() throws IOException {
        Files.createDirectory(folder.resolve("dir1"));
        Files.createFile(folder.resolve("dir1/file1.txt"));
        Files.createFile(folder.resolve("dir1/file2.doc"));
        Files.createFile(folder.resolve("dir1/file3.exe"));
        Files.createDirectory(folder.resolve("dir1/dir2/"));
        Files.createFile(folder.resolve("dir1/dir2/file4.txt"));
        Files.createFile(folder.resolve("dir1/dir2/file5.doc"));
        Files.createFile(folder.resolve("dir1/dir2/file6.exe"));
        Files.createDirectory(folder.resolve("dir1/dir2/dir3/"));
        Files.createFile(folder.resolve("dir1/dir2/dir3/file7.txt"));
        Files.createFile(folder.resolve("dir1/dir2/dir3/file8.doc"));
        Files.createFile(folder.resolve("dir1/dir2/dir3/file9.exe"));
    }

    @Test
    public void searchFiles() {
        String root = folder.toString().replaceAll("\\\\", "/");

        Predicate<File> predicate = file ->
                !file.isDirectory() && file.getPath().endsWith(".txt") || file.getPath().endsWith(".doc");

        List<String> actual =
                new Search().files(root, predicate).stream()
                        .map(file -> file.getPath().replaceAll("\\\\", "/"))
                        .collect(Collectors.toList());

        List<String> expected = List.of(
                String.format("%s%s", root, "/dir1/dir2/dir3/file7.txt"),
                String.format("%s%s", root, "/dir1/dir2/dir3/file8.doc"),
                String.format("%s%s", root, "/dir1/dir2/file4.txt"),
                String.format("%s%s", root, "/dir1/dir2/file5.doc"),
                String.format("%s%s", root, "/dir1/file1.txt"),
                String.format("%s%s", root, "/dir1/file2.doc")
        );

        assertThat(
                actual.containsAll(expected)
                        && expected.containsAll(actual)
                        && actual.size() == expected.size(),
                is(true)
        );
    }

    @Test
    public void searchDirs() {
        String root = folder.toString();

        Predicate<File> predicate = File::isDirectory;

        List<String> actual =
                new Search().files(root, predicate).stream()
                        .map(File::getPath)
                        .collect(Collectors.toList());

        List<String> expected = List.of(
                String.format("%s%s", root, File.separator + "dir1"),
                String.format("%s%s", root, File.separator + "dir1" + File.separator + "dir2"),
                String.format("%s%s", root, File.separator + "dir1" + File.separator + "dir2" + File.separator + "dir3")
        );

        assertThat(
                actual.containsAll(expected)
                        && expected.containsAll(actual)
                        && actual.size() == expected.size(),
                is(true)
        );
    }

}