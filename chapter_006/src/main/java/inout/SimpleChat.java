package inout;

import java.io.*;
import java.util.Scanner;
import java.util.function.Supplier;

public class SimpleChat {

    private enum Mode {
        NORMAL("продолжить"),
        SILENT("стоп"),
        EXIT("закончить");
        private String value;

        Mode(String value) {
            this.value = value;
        }

        public String getChatMode() {
            return value;
        }
    }

    private final static String CMD_CONTINUE = "продолжить";
    private final static String CMD_SILENT = "стоп";
    private final static String CMD_EXIT = "закончить";
    private Scanner scanner;
    private String answers;

    private Mode chatMode;

    public SimpleChat(InputStream in, PrintStream out, String answers) {
        this.scanner = new Scanner(in);
        this.answers = answers;
        talk(out);
    }

    private Supplier<String> question = () -> {
        if (!scanner.hasNext()) {
            throw new IllegalStateException();
        }
        return scanner.nextLine().trim().toLowerCase();
    };

    private Supplier<String> answer = () -> {
        String result = "";
        try {
            BufferedReader read = new BufferedReader(new FileReader(answers));
            result = read.lines().skip(
                    (int) (Math.random() * new BufferedReader(new FileReader(answers)).lines().count())
            ).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    };

    private boolean listen(PrintStream out, Supplier<String> question, Supplier<String> answer) {
        String answerResult = "";
        String questionResult = question.get();
        /*switch (questionResult) {
            case (Mode.NORMAL):
                Mode.NORMAL;
                break;
            case (CMD_SILENT):
                Mode.setChatMode(Mode.SILENT);
                break;
            case (CMD_EXIT):
                return false;
            default:
                answerResult = answer.get();
                break;
        }*/
        //if (chatMode == Mode.NORMAL) {
            out.println(answerResult);
        //}
        return true;
    }

    private void talk(PrintStream out) {
        boolean result = true;
        while (result) {
            result = listen(out, question, answer);
        }
    }

    public static void main(String[] args) {
        new SimpleChat(System.in, System.out, "c:/soft/answers.txt");
    }

}