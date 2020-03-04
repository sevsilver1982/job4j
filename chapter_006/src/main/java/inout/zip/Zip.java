package inout.zip;

import inout.Search;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void pack(String root, Predicate<File> conditions, Consumer<File> actions, String output) {
        List<File> files = new Search().files(root, conditions);
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)))) {
            files.forEach(file -> {
                try {
                    zos.putNextEntry(new ZipEntry(
                            file.getPath().startsWith("/") ? file.getPath().substring(1) : file.getPath()
                                    .replaceAll("\\\\", "/")
                                    .replaceAll(root, "")
                    ));
                    zos.write(new BufferedInputStream(new FileInputStream(file)).readAllBytes());
                    zos.closeEntry();
                    actions.accept(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args arguments = new Args(args);

        Predicate<File> conditions = (file ->
                !file.isDirectory()
                        && arguments.getExtensions().stream()
                        .anyMatch(o -> file.getPath().endsWith(o))
        );
        Consumer<File> actions = (file ->
                System.out.println(file.getPath())
        );
        new Zip().pack(
                arguments.getDirectory(),
                conditions,
                actions,
                arguments.getOutput()
        );
    }

}