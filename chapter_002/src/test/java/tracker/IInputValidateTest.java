package tracker;

import org.junit.jupiter.api.Test;
import tracker.input.InputValidate;
import tracker.input.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IInputValidateTest {

    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        InputValidate input = new InputValidate(
                new StubInput(new String[] {"invalid", "0"})
        );
        input.askInt("Enter", 1);
        assertThat(
                mem.toString(),
                is(String.format("Please enter validate data again%n"))
        );
        System.setOut(out);
    }

}
