import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        int evenCount = 0;
        int oddCount = 0;
        int positiveCount = 0;
        int negativeCount = 0;
        for (int index = 1; index <= count; index++) {
            int currentNumber = input.nextInt();
            if (currentNumber % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            if (currentNumber > 0) {
                positiveCount++;
            } else if (currentNumber < 0) {
                negativeCount++;
            }
        }
        System.out.println("Even: " + evenCount);
        System.out.println("Odd: " + oddCount);
        System.out.println("Positive: " + positiveCount);
        System.out.println("Negative: " + negativeCount);
    }
}
