package inout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void prepare() throws IOException {
        folder.newFolder("dir1");
        folder.newFile("dir1\\file1.txt");
        folder.newFile("dir1\\file2.doc");
        folder.newFile("dir1\\file3.exe");
        folder.newFolder("dir1\\dir2");
        folder.newFile("dir1\\dir2\\file4.txt");
        folder.newFile("dir1\\dir2\\file5.doc");
        folder.newFile("dir1\\dir2\\file6.exe");
        folder.newFolder("dir1\\dir2\\dir3");
        folder.newFile("dir1\\dir2\\dir3\\file7.txt");
        folder.newFile("dir1\\dir2\\dir3\\file8.doc");
        folder.newFile("dir1\\dir2\\dir3\\file9.exe");
    }

    @Test
    public void files() {
        String root = folder.getRoot().toString();
        List<String> actual =
                new Search().files(root, List.of("exe", "txt")).stream()
                        .map(File::getAbsolutePath)
                        .collect(Collectors.toList());
        List<String> expected = List.of(
                String.format("%s%s", root, "\\dir1\\file2.doc"),
                String.format("%s%s", root, "\\dir1\\dir2\\file5.doc"),
                String.format("%s%s", root, "\\dir1\\dir2\\dir3\\file8.doc")
        );
        assertThat(actual.toString(), is(expected.toString()));
    }

}