package exam;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analyze {
    private final List<User> previous;
    private final List<User> current;

    public Analyze(List<User> previous, List<User> current) {
        this.previous = previous;
        this.current = current;
    }

    public Info diff() {
        Info info = new Info();
        Map<Integer, User> map = current.stream().collect(Collectors.toMap(User::getId, user -> user));
        previous.forEach(user -> {
            User removed = map.remove(user.getId());
            if (removed == null) {
                info.deleted++;
            } else {
                if (!removed.equals(user)) {
                    info.changed++;
                }
            }
        });
        info.added = map.size();

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
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info() {
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return String.format("Info{added=%s, changed=%s, deleted=%s}", added, changed, deleted);
        }

    }

}