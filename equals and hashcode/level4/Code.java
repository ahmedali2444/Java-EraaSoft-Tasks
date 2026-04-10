package level4;

public class Code {
    String plateNum;
    String color;
    
    public Code(String plateNum, String color) {
        this.plateNum = plateNum;
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Code other = (Code) obj;
        return this.plateNum.equals(other.plateNum);
    }
    
    @Override
    public int hashCode() {
        return plateNum.hashCode();
    }
}
