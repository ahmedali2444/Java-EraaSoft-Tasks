import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int limit = input.nextInt();
        for (int value = 2; value <= limit; value++) {
            boolean isPrime = true;
            for (int divisor = 2; divisor < value; divisor++) {
                if (value % divisor == 0 && value != divisor) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.print(value + " ");
            }
        }
    }
}
