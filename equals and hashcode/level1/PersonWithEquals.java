package level1;

public class PersonWithEquals {
    int id;
    String name;
    
    public PersonWithEquals(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonWithEquals other = (PersonWithEquals) obj;
        return this.id == other.id;
    }
}
