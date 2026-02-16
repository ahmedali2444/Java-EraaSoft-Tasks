package OopTask2;

class P2{


    void printVariables(int intVar , long longVar , char charVar , float floatVar , double doubleVar ){
        System.out.println(intVar);
        System.out.println( longVar);
        System.out.println(charVar);
        System.out.println(floatVar);
        System.out.println(doubleVar);
    }

}

public class Problem2 {
    public static void main(String[] args){

        P2 p2 = new P2();
        int a = 10;
        long b = 10000000000L;
        char c = 'A';
        float d = 3.14f;
        double e = 9.876;
        p2.printVariables(a, b, c, d, e);
    }
}
