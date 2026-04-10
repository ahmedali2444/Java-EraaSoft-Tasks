package level4;

public class Car {
    String plateNum;
    String color;
    
    public Car(String plateNum, String color) {
        this.plateNum = plateNum;
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car other = (Car) obj;
        return this.plateNum.equals(other.plateNum);
    }
    
    @Override
    public int hashCode() {
        return plateNum.hashCode();
    }
}
