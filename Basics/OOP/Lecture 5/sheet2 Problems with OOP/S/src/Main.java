import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        int[] firstNumbers = new int[testCases];
        int[] secondNumbers = new int[testCases];
        for (int index = 0; index < testCases; index++) {
            firstNumbers[index] = input.nextInt();
            secondNumbers[index] = input.nextInt();
        }
        Solution taskSolution = new Solution(testCases, firstNumbers, secondNumbers);
        taskSolution.solve();
    }
}
