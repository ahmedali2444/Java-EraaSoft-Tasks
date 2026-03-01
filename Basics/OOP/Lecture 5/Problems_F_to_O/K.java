package Problems_F_to_O;


class ProblemK{
    private int num1;
    private int num2;
    private int num3;

    public ProblemK(int num1 , int num2 , int num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public void showResult(){
        int min = Math.min(num1 , num2);
        min = Math.min(min , num3);

        int max = Math.max(num1 , num2);
        max = Math.max(max , num3);

        System.out.println(min + " " + max);
    }
}
public class K {
    public static void main(String[] args){

        ProblemK Pk = new ProblemK(12 ,2 , 1);
        Pk.showResult();
    }
}
