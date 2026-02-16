package OopTask2;

class P5{
    private double PI =  3.141592653;
    void showArea(double r){
        System.out.println(PI * (r*r));
    }
}

public class Problem5 {
    public static void main(String[] args){
        P5 p5 = new P5();
        p5.showArea(2);
    }
}
