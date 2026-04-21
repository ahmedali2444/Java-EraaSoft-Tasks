package level4;

import java.util.Objects;

public class StudentByEmail {
    private final int id;
    private final String email;

    public StudentByEmail(int id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StudentByEmail other)) {
            return false;
        }
        return Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "StudentByEmail{id=" + id + ", email='" + email + "'}";
    }
}
