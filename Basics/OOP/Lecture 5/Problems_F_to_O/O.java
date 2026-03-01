package Problems_F_to_O;


class ProblemO{
    private String text;
    private String num1;
    private String num2;
    private char op ;
    public ProblemO(String text){
        this.text = text;
        num1 = "";
        num2 = "";
    }

    public void showResult(){
        boolean f = true;
        for(var ch : text.toCharArray()){
            if("-*+/".contains(String.valueOf(ch))){
                op = ch;
                f = false;
            }else{
                if(f)
                    num1 += ch;
                else
                    num2 += ch;
            }
        }
        if (op == '+')
            System.out.println(Integer.parseInt(num1) + Integer.parseInt(num2));
        else if (op == '-')
            System.out.println(Integer.parseInt(num1) - Integer.parseInt(num2));
        else if (op == '*')
            System.out.println(Integer.parseInt(num1) * Integer.parseInt(num2));
        else
            System.out.println(((double) Integer.parseInt(num1) / Integer.parseInt(num2)));
    }
}
public class O {

    public static void main(String[] args){

        new ProblemO("3+4").showResult();
        new ProblemO("3-4").showResult();
        new ProblemO("3*4").showResult();
        new ProblemO("3/4").showResult();

    }
}
