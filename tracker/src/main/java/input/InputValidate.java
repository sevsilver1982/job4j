package input;

public class InputValidate implements IInput {
    private final IInput input;

    public InputValidate(IInput input) {
        this.input = input;
    }

    @Override
    public String askString(String question) {
        return input.askString(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again");
            }
        } while (invalid);
        return value;
    }

    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println(String.format("Please select key from menu [0..%s]", max));
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again");
            }
        } while (invalid);
        return value;
    }
}