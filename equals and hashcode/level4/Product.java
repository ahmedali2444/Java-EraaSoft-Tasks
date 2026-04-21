package level4;

public class Product {
    private final String code;
    private final double price;

    public Product(String code, double price) {
        this.code = code;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product other)) {
            return false;
        }
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "Product{code='" + code + "', price=" + price + "}";
    }
}
