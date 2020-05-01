package multithreading.examples;

import java.io.*;
import java.util.Arrays;

public class ParseFile {
    private File file;

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() {
        try (InputStream i = new FileInputStream(getFile())) {
            byte[] data = new byte[i.available()];
            int bytesRead = i.read(data, 0, data.length);
            return new String(Arrays.copyOf(data, bytesRead));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getContentWithoutUnicode() {
        String output = "";
        try (InputStream i = new FileInputStream(getFile())) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(getFile())) {
            o.write(content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ParseFile parseFile = new ParseFile();
        new Thread(() -> {
            parseFile.setFile(new File("C:/soft/1.txt"));
            System.out.println(parseFile.getContent());
        }).start();
        new Thread(() -> {
            parseFile.setFile(new File("C:/soft/1.txt"));
            String str = parseFile.getContent();
            parseFile.setFile(new File("C:/soft/2.txt"));
            parseFile.saveContent(str);
        }).start();
    }

}