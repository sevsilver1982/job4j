package collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {

    @Test
    public void processTest() {
        HashMap<Integer, UserConvert.User> userMap = new UserConvert().process(
                List.of(
                        new UserConvert.User(1, "user1", "city1"),
                        new UserConvert.User(2, "user2", "city2"),
                        new UserConvert.User(3, "user3", "city3"),
                        new UserConvert.User(4, "user4", "city4")
                )
        );
        Map<Integer, UserConvert.User> expect = Map.of(
                1, new UserConvert.User(1, "user1", "city1"),
                2, new UserConvert.User(2, "user2", "city2"),
                3, new UserConvert.User(3, "user3", "city3"),
                4, new UserConvert.User(4, "user4", "city4")
        );

        assertThat(
                expect.entrySet().containsAll(userMap.entrySet())
                        && expect.entrySet().containsAll(userMap.entrySet()),
                is(true));
    }

}