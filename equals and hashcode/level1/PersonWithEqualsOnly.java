package level1;

public class PersonWithEqualsOnly {
    private final int id;
    private final String name;

    public PersonWithEqualsOnly(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonWithEqualsOnly other)) {
            return false;
        }
        return id == other.id;
    }

    @Override
    public String toString() {
        return "PersonWithEqualsOnly{id=" + id + ", name='" + name + "'}";
    }
}
