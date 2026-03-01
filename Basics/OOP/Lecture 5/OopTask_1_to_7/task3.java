package OopTask_1_to_7;

class SumThreeNumbers{
    SumThreeNumbers(int num1 , int num2 , int num3){
        if(num1 %2 ==0 && num2 % 2 ==0 && num3 % 2 ==0){
            System.out.println(num1 + num2 + num3);
        }
        else
            System.out.println("numbers should be even");
    }
}
public class task3 {
    public static void main(String[] args){
        SumThreeNumbers s = new SumThreeNumbers( 1 , 2 , 3);

        SumThreeNumbers s2 = new SumThreeNumbers( 2 , 2 , 2);
    }
}
