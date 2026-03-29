import java.util.Scanner;

public class Solution {
    Scanner input;
    int firstValue;
    int secondValue;

    public Solution(Scanner input, int firstValue, int secondValue) {
        this.input = input;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public void solve() {
        while (firstValue > 0 && secondValue > 0) {
            int total = 0;
            for (int number = Math.min(firstValue, secondValue); number <= Math.max(firstValue, secondValue); number++) {
                System.out.print(number + " ");
                total += number;
            }
            System.out.println("sum =" + total);
            firstValue = input.nextInt();
            secondValue = input.nextInt();
        }
    }
}
