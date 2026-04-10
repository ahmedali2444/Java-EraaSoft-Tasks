package level2;

public class StudentWithMethods {
    int id;
    String name;
    
    public StudentWithMethods(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentWithMethods other = (StudentWithMethods) obj;
        return this.id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
