package inout;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public List<File> seekBy(String root, List<String> ext) {
        return null;
    }

    public void pack(String root, String target, List<String> ext) {
        try (
                ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))
        ) {
            List<File> files = new Search().files(root, ext);
            for (File file : files) {
                zos.putNextEntry(new ZipEntry(file.getName()));
                zos.write(new BufferedInputStream(new FileInputStream(file)).readAllBytes());
                zos.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().pack("C:\\soft\\workspace\\job4j\\chapter_006\\", "c:\\soft\\1.zip", List.of("exe", "txt"));
    }

}