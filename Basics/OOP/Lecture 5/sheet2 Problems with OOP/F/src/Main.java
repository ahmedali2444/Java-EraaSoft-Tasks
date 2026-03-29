import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        Solution taskSolution = new Solution(number);
        taskSolution.solve();
    }
}
