import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int limit = input.nextInt();
        Solution taskSolution = new Solution(limit);
        taskSolution.solve();
    }
}
