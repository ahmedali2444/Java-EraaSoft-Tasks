public class Staff {
    String nm, dep;
    int age;
    double pay;

    Staff(String nm, int age, String dep, double pay) {
        this.nm = nm;
        this.age = age;
        this.dep = dep;
        this.pay = pay;
    }

    public int getAge() { return age; }
    public String getDep() { return dep; }
    public double getPay() { return pay; }
}
