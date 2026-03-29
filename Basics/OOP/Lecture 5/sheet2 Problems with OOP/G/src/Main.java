import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long testCases = input.nextLong();
        long[] values = new long[(int) testCases];
        for (int index = 0; index < testCases; index++) {
            values[index] = input.nextLong();
        }
        Solution taskSolution = new Solution(testCases, values);
        taskSolution.solve();
    }
}
