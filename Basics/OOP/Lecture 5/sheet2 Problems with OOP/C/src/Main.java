import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        int[] numbers = new int[count];
        for (int index = 0; index < count; index++) {
            numbers[index] = input.nextInt();
        }
        Solution taskSolution = new Solution(count, numbers);
        taskSolution.solve();
    }
}
