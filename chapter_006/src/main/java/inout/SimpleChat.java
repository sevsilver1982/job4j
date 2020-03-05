package inout;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Supplier;

public class SimpleChat {
    private ChatLogger logger;
    private Scanner scanner;
    private String answers;
    private Mode chatMode = Mode.NORMAL;

    private class ChatLogger {
        private PrintStream out;
        private DateTimeFormatter formatter;

        public ChatLogger(PrintStream out) {
            this.out = out;
            this.formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        }

        public void log(String msg) {
            out.printf("%s : %s\n",  formatter.format(LocalDateTime.now()), msg);
        }
    }

    private enum Mode {
        NORMAL("продолжить"),
        SILENT("стоп"),
        EXIT("закончить"),
        DEFAULT("");

        String value;

        Mode(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }
    }

    public SimpleChat(InputStream in, PrintStream out, String answers, PrintStream logout) {
        this.scanner = new Scanner(in);
        this.answers = answers;
        this.logger = new ChatLogger(logout);
        talk(out);
    }

    private Supplier<String> question = () -> {
        if (!scanner.hasNext()) {
            throw new IllegalStateException();
        }
        String result = scanner.nextLine().trim().toLowerCase();
        logger.log(result);
        return result;
    };

    private Supplier<String> answer = () -> {
        String result = "";
        try {
            BufferedReader read = new BufferedReader(new FileReader(answers));
            result = read.lines().skip(
                    (int) (Math.random() * new BufferedReader(new FileReader(answers)).lines().count())
            ).findFirst().get();
            logger.log(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    };

    private Mode getMode(String command) {
        return Arrays.stream(Mode.values())
                .filter(type -> type.getValue().equals(command))
                .findFirst()
                .orElse(Mode.DEFAULT);
    }

    private void process(PrintStream out, Supplier<String> question, Supplier<String> answer) {
        switch (getMode(question.get())) {
            case NORMAL:
                chatMode = Mode.NORMAL;
                break;
            case SILENT:
                chatMode = Mode.SILENT;
                break;
            case EXIT:
                chatMode = Mode.EXIT;
                break;
            default:
                break;
        }
        if (chatMode == Mode.NORMAL) {
            out.println(answer.get());
        }
    }

    private void talk(PrintStream out) {
        while (chatMode != Mode.EXIT) {
            process(out, question, answer);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new SimpleChat(System.in, System.out, "c:/soft/answers.txt", new PrintStream(new File("c:/soft/chat.log")));
    }

}