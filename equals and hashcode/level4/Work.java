package level4;

public class Work {
    int empId;
    String email;
    
    public Work(int empId, String email) {
        this.empId = empId;
        this.email = email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Work other = (Work) obj;
        return this.empId == other.empId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(empId);
    }
}
