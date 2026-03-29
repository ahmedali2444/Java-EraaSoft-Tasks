import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        for (int caseIndex = 1; caseIndex <= testCases; caseIndex++) {
            int oddSum = 0;
            int firstNumber = input.nextInt();
            int secondNumber = input.nextInt();
            for (int value = Math.min(firstNumber, secondNumber) + 1; value < Math.max(firstNumber, secondNumber); value++) {
                if (value % 2 != 0) {
                    oddSum += value;
                }
            }
            System.out.println(oddSum);
        }
    }
}
