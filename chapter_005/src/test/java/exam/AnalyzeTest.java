package exam;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzeTest {

    @Test
    void diffEqualsTest() {
        assertEquals(
                new Analyze(
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        ),
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        )
                ).diff(),
                new Analyze.Info(0, 0, 0)
        );
    }

    @Test
    void diffAddedTest() {
        assertEquals(
                new Analyze(
                        List.of(),
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        )
                ).diff(),
                new Analyze.Info(5, 0, 0)
        );
    }

    @Test
    void diffChangedTest() {
        assertEquals(
                new Analyze(
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        ),
                        List.of(
                                new Analyze.User(1, "user_11"),
                                new Analyze.User(2, "user_22"),
                                new Analyze.User(3, "user_33"),
                                new Analyze.User(4, "user_44"),
                                new Analyze.User(5, "user_55")
                        )
                ).diff(),
                new Analyze.Info(0, 5, 0)
        );
    }

    @Test
    void diffDeletedTest() {
        assertEquals(
                new Analyze(
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        ),
                        List.of()
                ).diff(),
                new Analyze.Info(0, 0, 5)
        );
    }

    @Test
    void diffAllTest() {
        assertEquals(
                new Analyze(
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(2, "user_2"),
                                new Analyze.User(3, "user_3"),
                                new Analyze.User(4, "user_4"),
                                new Analyze.User(5, "user_5")
                        ),
                        List.of(
                                new Analyze.User(1, "user_1"),
                                new Analyze.User(4, "user_44"),
                                new Analyze.User(5, "user_55"),
                                new Analyze.User(6, "user_6"),
                                new Analyze.User(7, "user_7")
                        )
                ).diff(),
                new Analyze.Info(2, 2, 2)
        );
    }

}