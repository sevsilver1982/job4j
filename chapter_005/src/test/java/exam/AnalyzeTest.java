package exam;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzeTest {

    @Test
    void diff() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "user_1"));
        previous.add(new Analyze.User(2, "user_2"));
        previous.add(new Analyze.User(3, "user_3"));
        previous.add(new Analyze.User(4, "user_4"));
        previous.add(new Analyze.User(5, "user_5"));

        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "user_11"));
        current.add(new Analyze.User(2, "user_2"));
        current.add(new Analyze.User(5, "user_55"));
        current.add(new Analyze.User(6, "user_6"));

        Analyze.Info info = new Analyze.Info();
        info.added = 1;
        info.changed = 2;
        info.deleted = 2;

        assertEquals(new Analyze(previous, current).diff(), info);
    }

}