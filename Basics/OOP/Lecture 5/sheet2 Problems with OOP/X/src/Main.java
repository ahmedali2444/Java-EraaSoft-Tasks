import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        long[] values = new long[testCases];
        for (int index = 0; index < testCases; index++) {
            values[index] = input.nextLong();
        }
        Solution taskSolution = new Solution(testCases, values);
        taskSolution.solve();
    }
}
