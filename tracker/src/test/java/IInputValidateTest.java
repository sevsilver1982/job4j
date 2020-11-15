import input.InputValidate;
import input.StubInput;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IInputValidateTest {

    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        new InputValidate(
                new StubInput(new String[] {"invalid", "0"})
        ).askInt("Enter", 1);
        assertEquals(
                String.format("Please enter validate data again%n"),
                mem.toString()
        );
        System.setOut(out);
    }

}