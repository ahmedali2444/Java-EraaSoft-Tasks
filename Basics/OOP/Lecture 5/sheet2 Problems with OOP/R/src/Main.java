import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstValue = input.nextInt();
        int secondValue = input.nextInt();
        Solution taskSolution = new Solution(input, firstValue, secondValue);
        taskSolution.solve();
    }
}
