import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        boolean isPrime = true;
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) {
                System.out.println("NO");
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.print("YES");
        }
    }
}
