import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        for (int divisor = 1; divisor <= number; divisor++) {
            if (number % divisor == 0) {
                System.out.println(divisor);
            }
        }
    }
}
