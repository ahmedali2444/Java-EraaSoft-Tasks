package level2;

import java.util.Objects;

public class PersonByName {
    private final int id;
    private final String name;

    public PersonByName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonByName other)) {
            return false;
        }
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }
}
