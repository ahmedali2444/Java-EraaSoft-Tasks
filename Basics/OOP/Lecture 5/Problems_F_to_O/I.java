package Problems_F_to_O;


class ProblemI{
    private int num1;
    private int num2;

    public ProblemI(int num1 , int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    public void showResult(){
        if (num1 >= num2)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
public class I {

    public static void main(String[] args){

        ProblemI Pi = new ProblemI(10 , 4);
        Pi.showResult();

        ProblemI Pi2 = new ProblemI(3 , 4);
        Pi2.showResult();
    }
}
