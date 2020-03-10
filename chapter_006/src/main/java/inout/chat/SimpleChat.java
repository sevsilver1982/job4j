package inout.chat;

import inout.logger.SimpleLogger;

import java.io.*;

public class SimpleChat {
    private Inout inout;
    private InputStream in;
    private PrintStream out;
    //private answersFile;
    private SimpleLogger logger;

    public SimpleChat(InputStream in, PrintStream out, InputStream answers, PrintStream log) {
        this.in = in;
        this.out = out;
        this.logger = new SimpleLogger(log);
    }

    public void start() {
        inout = new Inout(in, out);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new SimpleChat(
                System.in,
                System.out,
                new FileReader("c:/soft/answers.txt"),
                new PrintStream(new File("c:/soft/chat.log"))
        ).start();
    }

}