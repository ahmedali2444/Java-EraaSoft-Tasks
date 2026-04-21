package level4;

public class StudentById {
    private final int id;
    private final String email;

    public StudentById(int id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StudentById other)) {
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
        return "StudentById{id=" + id + ", email='" + email + "'}";
    }
}
