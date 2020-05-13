package examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConvertTest {

    @Test
    public void processTest() {
        assertEquals(
                Map.of(
                        1, new UserConvert.User(1, "user1", "city1"),
                        2, new UserConvert.User(2, "user2", "city2"),
                        3, new UserConvert.User(3, "user3", "city3"),
                        4, new UserConvert.User(4, "user4", "city4")
                ).entrySet(),
                new UserConvert().process(
                        List.of(
                                new UserConvert.User(1, "user1", "city1"),
                                new UserConvert.User(2, "user2", "city2"),
                                new UserConvert.User(3, "user3", "city3"),
                                new UserConvert.User(4, "user4", "city4")
                        )
                ).entrySet()
        );
    }

}