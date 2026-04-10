package level4;

public class Employ {
    int empId;
    String email;
    
    public Employ(int empId, String email) {
        this.empId = empId;
        this.email = email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employ other = (Employ) obj;
        return this.empId == other.empId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(empId);
    }
}
