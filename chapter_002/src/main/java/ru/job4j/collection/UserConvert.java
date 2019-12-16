package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserConvert {

    static class User {
        private int id;
        private String name;
        private String city;

        public User(int id, String name, String city) {
            this.id = id;
            this.name = name;
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name)
                    && Objects.equals(city, user.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, city);
        }
    }

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> userMap = new HashMap<>();
        for (User user : list) {
            userMap.put(user.id, user);
        }
        return userMap;
    }

    public static void main(String[] args) {
        UserConvert userConvert = new UserConvert();
        List<User> list = Arrays.asList(
                new User(1, "user1", "city1"),
                new User(2, "user2", "city2"),
                new User(3, "user3", "city3"),
                new User(4, "user4", "city4")
        );
        HashMap<Integer, User> userMap = userConvert.process(list);
        for (User user : userMap.values()) {
            System.out.println(String.format("id: %s; name: %s; city: %s", user.id, user.name, user.city));
        }
    }

}
