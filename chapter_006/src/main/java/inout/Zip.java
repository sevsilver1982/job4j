package inout;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public List<File> seekBy(String root, List<String> ext) {
        Search search = new Search();
        return search.files(root, ext);
    }

    public void pack(File source, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {

            zos.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zos.write(out.readAllBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File target = new File("C:/soft/workspace/job4j/chapter_006/pom.zip");

        Zip zip = new Zip();
        zip.seekBy("C:\\soft\\workspace\\job4j\\chapter_006\\", List.of("exe", "txt"))
                //.forEach(file -> System.out.println(file));
                .forEach(file -> zip.pack(file, target));
    }

}