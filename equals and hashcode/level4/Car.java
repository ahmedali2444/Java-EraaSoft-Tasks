package level4;

public class Car {
    private final String plateNumber;
    private final String color;

    public Car(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Car other)) {
            return false;
        }
        return plateNumber.equals(other.plateNumber);
    }

    @Override
    public int hashCode() {
        return plateNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Car{plateNumber='" + plateNumber + "', color='" + color + "'}";
    }
}
