package OopTask2;

class P1{

    String Text = "Hello, ";
    void PrintHello(String name){
        System.out.println(Text + name);
    }

}

public class Problem1 {
    public static void main(String[] args){

        P1 p1 = new P1();
        p1.PrintHello("ahmed");
    }
}
