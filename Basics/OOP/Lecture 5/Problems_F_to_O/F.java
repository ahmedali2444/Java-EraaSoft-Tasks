package Problems_F_to_O;


class ProblemF{
    private  int num1;
    private int num2;

    public ProblemF(int num1 , int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public void ShowLastDigitSum(){
        int d1 = num1 % 10;
        int d2 = num2 % 10;
        int sum = d1 + d2;
        System.out.println("SUM: " + sum);
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}
public class F {
    public static void main(String[] args){

        ProblemF Pf = new ProblemF( 13 , 12);
        Pf.ShowLastDigitSum();
    }
}
