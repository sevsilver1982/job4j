package tracker.input;

public interface Input {
    String askString(String question);
    int askInt(String question);
    int askInt(String question, int max);
}