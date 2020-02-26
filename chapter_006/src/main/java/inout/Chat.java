package inout;

import java.util.Scanner;

public class Chat {
    private Scanner scanner = new Scanner(System.in);

    public String read() {
        if (!scanner.hasNext()) {
            scanner.nextLine();
            throw new IllegalStateException();
        }
        return scanner.nextLine();
    }

    public boolean process(String cmd) {
        switch (cmd.trim().toLowerCase()) {
            case ("продолжить"):
                break;
            case ("стоп"):
                break;
            case ("закончить"):
                return false;
            default:
                break;
        }
        return true;
    }

    public void start() {
        boolean loop = true;
        while (loop) {
            loop = process(read());
        }
    }

    public static void main(String[] args) {
        new Chat().start();
    }

}