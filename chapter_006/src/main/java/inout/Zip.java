package inout;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static List<String> exclude;
    private static String directory = "";
    private static String output = "";

    static class InvalidParametersException extends Exception {
        public InvalidParametersException() {
            super();
        }
    }

    public void zipDirectory(ZipOutputStream zos, File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println(file.getPath());
                zipDirectory(zos, file);
            } else {
                if (!exclude.contains(
                        file.getPath().substring(file.getPath().lastIndexOf(".") + 1))
                ) {
                    System.out.println(file.getPath());
                    String entryName = file.getPath()
                            .replaceAll("\\\\", "/")
                            .replaceAll(directory, "");
                    zos.putNextEntry(
                            new ZipEntry(entryName.startsWith("/") ? entryName.substring(1) : entryName)
                    );
                    zos.write(
                            new BufferedInputStream(new FileInputStream(file)).readAllBytes()
                    );
                    zos.closeEntry();
                }
            }
        }
    }

    public void pack() throws Exception {
        if (directory.isEmpty() || output.isEmpty()) {
            throw new InvalidParametersException();
        }
        File root = new File(directory);
        if (!root.isDirectory() || !root.exists()) {
            throw new InvalidParametersException();
        }

        Predicate<File> predicate = file ->
                !file.isDirectory() && file.getPath().endsWith(".txt") || file.getPath().endsWith(".doc");

        List<File> files = new Search().files(directory, predicate);

        try (
                ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)))
        ) {

            for (File file : files) {
                if (file.isDirectory()) {

                    System.out.println(file.getPath());
                    String entryName = file.getPath()
                            .replaceAll("\\\\", "/")
                            .replaceAll(directory, "");
                    zos.putNextEntry(
                            new ZipEntry(entryName.startsWith("/") ? entryName.substring(1) : entryName)
                    );
                    zos.write(
                            new BufferedInputStream(new FileInputStream(file)).readAllBytes()
                    );
                    zos.closeEntry();

                }
            }

            zipDirectory(zos, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            if (i + 1 > args.length) {
                break;
            }
            switch (args[i]) {
                case ("-d"):
                    i++;
                    directory = args[i].replaceAll("\\\\", "/");
                    break;
                case ("-e"):
                    i++;
                    exclude = Arrays.stream(args[i].split(";"))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    break;
                case ("-o"):
                    i++;
                    output = args[i];
                    break;
                default:
                    break;
            }
        }
        new Zip().pack();
    }

}