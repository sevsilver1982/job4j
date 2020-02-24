package inout;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    //private
    private List<String> ext;

    public void addDirectory(ZipOutputStream zos, File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if(file.isDirectory()) {
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
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            /*if (arg.equals("-d")) {
                directory
            }*/
        }
        //Zip zip = new Zip();
        //zip.pack("./chapter_006/", "1.zip", List.of("exe", "txt"));
    }

}