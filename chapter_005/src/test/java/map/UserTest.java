package map;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void mapTest() {
        User user1 = new User("user1", 0, Calendar.getInstance());
        User user2 = new User("user1", 0, Calendar.getInstance());

        Map<User, Object> map = new HashMap<>();
        map.put(user1, user1);
        map.put(user2, user2);

        System.out.println(map);
    }

}