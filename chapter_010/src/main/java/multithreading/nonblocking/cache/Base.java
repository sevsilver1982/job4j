package multithreading.nonblocking.cache;

import java.util.Objects;

public class Base implements Comparable<Base> {
    final int id;
    int version;

    public Base(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Base model) {
        return Integer.compare(id, model.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    @Override
    public String toString() {
        return String.format("id=%s, version=%s", id, version);
    }

}