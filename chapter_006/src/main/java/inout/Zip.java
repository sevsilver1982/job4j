package inout;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private List<String> ext;

    public void addDirectory(ZipOutputStream zos, File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                addDirectory(zos, file);
            } else {
                if (!ext.contains(
                        file.getPath()
                                .substring(file.getPath().lastIndexOf(".") + 1))) {
                    zos.putNextEntry(new ZipEntry(file.getPath()));
                    zos.write(new BufferedInputStream(new FileInputStream(file)).readAllBytes());
                    zos.closeEntry();
                }
            }
        }
    }

    public void pack(String root, String target, List<String> ext) {
        this.ext = ext;
        try (
                ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)));
        ) {
            File file = new File(root);
            addDirectory(zos, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String directory = "";
        String exclude = "";
        String output = "";

        for (int i = 0; i < args.length; i++) {
            if (i + 1 > args.length) {
                break;
            }

            switch (args[i]) {
                case ("-d"):
                    i++;
                    directory = args[i];
                    break;
                case ("-e"):
                    i++;
                    exclude = args[i];
                    break;
                case ("-o"):
                    i++;
                    output = args[i];
                    break;
                default:
                    break;
            }
        }

        if (directory.isEmpty() || output.isEmpty()) {
            return;
        }

        Zip zip = new Zip();
        zip.pack(
                directory,
                output,
                Arrays.stream(exclude.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
    }

}