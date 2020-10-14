package tracker.input;

public class StubInput extends InputConsole {
    private final String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(answers[position++]);
    }

    @Override
    public String askString(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
