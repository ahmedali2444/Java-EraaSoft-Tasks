package level2;

import java.util.Objects;

public class PersonByIdAndName {
    private final int id;
    private final String name;

    public PersonByIdAndName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonByIdAndName other)) {
            return false;
        }
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }
}
