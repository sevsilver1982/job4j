package inout;

import java.io.*;
import java.util.Scanner;

public class Chat {
    private final static String COMMAND_CONTINUE = "продолжить";
    private final static String COMMAND_STOP = "стоп";
    private final static String COMMAND_EXIT = "закончить";
    private final Scanner scanner;
    private final String fileAnswers;

    public Chat(InputStream inputStream, PrintStream printStream, String fileAnswers) throws FileNotFoundException {
        this.scanner = new Scanner(inputStream);
        this.fileAnswers = fileAnswers;
        talk(printStream);
    }

    public String getRandomAnswer(String fileAnswers) throws FileNotFoundException {
        long count = new BufferedReader(new FileReader(fileAnswers)).lines().count();
        long i = (int) (Math.random() * count);
        BufferedReader read = new BufferedReader(new FileReader(fileAnswers));
        return read.lines().skip(i).findFirst().get();
    }

    public String read() {
        if (!scanner.hasNext()) {
            scanner.nextLine();
            throw new IllegalStateException();
        }
        return scanner.nextLine();
    }

    public boolean process(String cmd, PrintStream printStream) throws FileNotFoundException {
        switch (cmd.trim().toLowerCase()) {
            case (COMMAND_CONTINUE):
                break;
            case (COMMAND_STOP):
                break;
            case (COMMAND_EXIT):
                return false;
            default:
                printStream.println(getRandomAnswer(fileAnswers));
                break;
        }
        return true;
    }

    public void talk(PrintStream printStream) throws FileNotFoundException {
        boolean loop = true;
        while (loop) {
            loop = process(read(), printStream);
        }
    }

    public static void main(String[] args) throws IOException {
        new Chat(System.in, System.out, "c:/soft/answers.txt");
    }

}