package items;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private UUID id;
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Item(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return this.id == null && this.name == null;
    }

    @Override
    public String toString() {
        return String.format("id: %s; name: %s", this.getId(), this.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}