package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {

    @Test
    public void processTest() {
        UserConvert userConvert = new UserConvert();
        List<UserConvert.User> list = Arrays.asList(
                new UserConvert.User(1,"user1", "city1"),
                new UserConvert.User(2,"user2", "city2"),
                new UserConvert.User(3,"user3", "city3"),
                new UserConvert.User(4,"user4", "city4")
        );
        HashMap<Integer, UserConvert.User> userMap = userConvert.process(list);

        HashMap<Integer, UserConvert.User> expect = new HashMap<>();
        expect.put(1, new UserConvert.User(1,"user1", "city1"));
        expect.put(2, new UserConvert.User(2,"user2", "city2"));
        expect.put(3, new UserConvert.User(3,"user3", "city3"));
        expect.put(4, new UserConvert.User(4,"user4", "city4"));

        assertThat(
                expect.entrySet().containsAll(userMap.entrySet()) &&
                        expect.entrySet().containsAll(userMap.entrySet()),
                is(true));
    }

}