package inout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void prepare() throws IOException {
        folder.newFolder("dir1");
        folder.newFile("dir1/file1.txt");
        folder.newFile("dir1/file2.doc");
        folder.newFile("dir1/file3.exe");
        folder.newFolder("dir1/dir2");
        folder.newFile("dir1/dir2/file4.txt");
        folder.newFile("dir1/dir2/file5.doc");
        folder.newFile("dir1/dir2/file6.exe");
        folder.newFolder("dir1/dir2/dir3");
        folder.newFile("dir1/dir2/dir3/file7.txt");
        folder.newFile("dir1/dir2/dir3/file8.doc");
        folder.newFile("dir1/dir2/dir3/file9.exe");
    }

    @Test
    public void searchFiles() {
        String root = folder.getRoot().toString().replaceAll("\\\\", "/");

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
        String root = folder.getRoot().toString();

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