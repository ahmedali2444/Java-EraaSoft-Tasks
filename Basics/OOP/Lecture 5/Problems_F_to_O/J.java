package Problems_F_to_O;


class ProblemJ{

    private int num1;
    private int num2;

    public ProblemJ(int num1 , int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    public void showResult(){
        if(num1 % num2 == 0)
            System.out.println("Multiples");
        else if (num2 % num1 == 0)
            System.out.println("Multiples");
        else
            System.out.println("No Multiples");
    }
}
public class J {
    public static void main(String[] args){

        ProblemJ Pj = new ProblemJ(12 ,2);
        Pj.showResult();

        ProblemJ Pj2 = new ProblemJ(2 ,12);
        Pj2.showResult();

        ProblemJ Pj3 = new ProblemJ(10 ,3);
        Pj3.showResult();
    }
}
