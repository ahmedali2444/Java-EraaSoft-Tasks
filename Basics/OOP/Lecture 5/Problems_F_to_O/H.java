package Problems_F_to_O;


import OopTask1.Main;

class ProblemH{
    private int num1;
    private int num2;

    public ProblemH(int num1 , int num2){
        this.num1 =num1;
        this.num2 = num2;
    }

    public void ShowFloorCeilRound(){
        double d = (double) num1 / num2;
        double fl = Math.floor(d);
        double ce = Math.ceil(d);
        double ro = Math.round(d);
        System.out.println("floor " + num1 + " / " + num2 + " = " + (int)fl );
        System.out.println("ceil " + num1 + " / " + num2 + " = " + (int)ce );
        System.out.println("round " + num1 + " / " + num2 + " = " + (int)ro );
    }
}

public class H {
    public static void main(String[] args){
        ProblemH Ph = new ProblemH(10 , 3);
        Ph.ShowFloorCeilRound();
    }
}

