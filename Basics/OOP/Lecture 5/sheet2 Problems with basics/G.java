import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long testCases = input.nextLong();
        long factorial = 1;
        for (int caseIndex = 1; caseIndex <= testCases; caseIndex++) {
            long value = input.nextLong();
            for (int current = 1; current <= value; current++) {
                factorial *= current;
            }
            System.out.println(factorial);
            factorial = 1;
        }
    }
}
