import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int start = input.nextInt();
        int end = input.nextInt();
        Solution taskSolution = new Solution(start, end);
        taskSolution.solve();
    }
}
