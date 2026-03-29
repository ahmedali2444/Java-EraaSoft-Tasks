import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long total = 0;
        long limit = input.nextLong();
        long minSum = input.nextLong();
        long maxSum = input.nextLong();
        for (int value = 1; value <= limit; value++) {
            int currentNumber = value;
            long digitsSum = 0;
            while (currentNumber != 0) {
                int digit = currentNumber % 10;
                digitsSum += digit;
                currentNumber /= 10;
            }
            if (digitsSum >= minSum && digitsSum <= maxSum) {
                total += value;
            }
        }
        System.out.print(total);
    }
}
