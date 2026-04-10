package level1;

public class Person2 {
    int id;
    String name;
    
    public Person2(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person2 other = (Person2) obj;
        return this.id == other.id;
    }
}
