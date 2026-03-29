import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();
        int gcdValue = 0;
        for (int divisor = 1; divisor <= 1000; divisor++) {
            if (firstNumber % divisor == 0 && secondNumber % divisor == 0) {
                if (divisor > gcdValue) {
                    gcdValue = divisor;
                }
            }
        }
        System.out.print(gcdValue);
    }
}
