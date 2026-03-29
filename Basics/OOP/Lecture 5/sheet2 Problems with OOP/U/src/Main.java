import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long limit = input.nextLong();
        long minSum = input.nextLong();
        long maxSum = input.nextLong();
        Solution taskSolution = new Solution(limit, minSum, maxSum);
        taskSolution.solve();
    }
}
