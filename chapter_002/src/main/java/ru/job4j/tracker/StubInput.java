package ru.job4j.tracker;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public int askInt(String question) {
        return 0;
    }

    @Override
    public String askString(String question) {
        return answers[position];
    }

    @Override
    public int askInt(String question, int max) {
        return askInt(question);
    }
}
