package OopTask2;

class P3{

    P3(long num1 , long num2){
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
    }
}

public class Problem3 {
    public static void main(String[] args){

        P3 p3 = new P3( 5 , 2);

    }
}
