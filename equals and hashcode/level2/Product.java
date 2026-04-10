package level2;

public class Product {
    String code;
    String name;
    
    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return code.equals(other.code);
    }
    
    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
