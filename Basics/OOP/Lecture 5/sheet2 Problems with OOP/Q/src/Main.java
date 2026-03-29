import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        String[] words = new String[testCases];
        for (int index = 0; index < testCases; index++) {
            words[index] = input.next();
        }
        Solution taskSolution = new Solution(testCases, words);
        taskSolution.solve();
    }
}
