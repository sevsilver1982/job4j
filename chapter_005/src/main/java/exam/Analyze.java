package exam;

import java.util.List;

public class Analyze {
    private final List<User> previous;
    private final List<User> current;

    public Analyze(List<User> previous, List<User> current) {
        this.previous = previous;
        this.current = current;
    }

    public Info diff() {
        Info info = new Info();
        /* add */
        current.forEach(curr -> {
            boolean isExists = false;
            for (User prev : previous) {
                if (prev.getId() == curr.getId()) {
                    isExists = true;
                    break;
                }
            }
            if (!isExists) info.added++;
        });
        /* changed */
        previous.forEach(prev -> {
            boolean isExists = false;
            for (User curr : current) {
                if (curr.getId() == prev.getId()) {
                    if (!curr.getName().equals(prev.getName())) {
                        isExists = true;
                        break;
                    }
                }
            }
            if (isExists) info.changed++;
        });
        /* deleted */
        previous.forEach(prev -> {
            boolean isExists = false;
            for (User curr : current) {
                if (curr.getId() == prev.getId()) {
                    isExists = true;
                    break;
                }
            }
            if (!isExists) info.deleted++;
        });
        return info;
    }

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }

    }

}