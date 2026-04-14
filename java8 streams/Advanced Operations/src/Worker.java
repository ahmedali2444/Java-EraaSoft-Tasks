public class Worker {
    String nm, dep;
    int age;
    double pay;

    Worker(String nm, int age, String dep, double pay) {
        this.nm = nm;
        this.age = age;
        this.dep = dep;
        this.pay = pay;
    }

    public String getNm() { return nm; }
    public double getPay() { return pay; }

    public String toString() { return nm + "(" + pay + ")"; }
}
