import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        long[] numbers = new long[count];
        for (int index = 0; index < count; index++) {
            numbers[index] = input.nextLong();
        }
        Solution taskSolution = new Solution(count, numbers);
        taskSolution.solve();
    }
}
