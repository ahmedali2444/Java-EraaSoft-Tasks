public class Kid {
    String nm, dep;
    double mark;

    Kid(String nm, String dep, double mark) {
        this.nm = nm;
        this.dep = dep;
        this.mark = mark;
    }

    public double getMark() { return mark; }

    public String toString() { return nm + "(" + mark + ")"; }
}
