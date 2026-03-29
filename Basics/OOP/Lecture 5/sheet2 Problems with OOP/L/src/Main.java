import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();
        Solution taskSolution = new Solution(firstNumber, secondNumber);
        taskSolution.solve();
    }
}
