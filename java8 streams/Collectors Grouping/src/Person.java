public class Person {
    String nm, dep;
    double mark;

    Person(String nm, String dep, double mark) {
        this.nm = nm;
        this.dep = dep;
        this.mark = mark;
    }

    public String getDep() { return dep; }

    public String toString() { return nm; }
}
