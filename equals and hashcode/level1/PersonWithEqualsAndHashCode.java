package level1;

public class PersonWithEqualsAndHashCode {
    private final int id;
    private final String name;

    public PersonWithEqualsAndHashCode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonWithEqualsAndHashCode other)) {
            return false;
        }
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "PersonWithEqualsAndHashCode{id=" + id + ", name='" + name + "'}";
    }
}
