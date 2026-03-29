import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        int limit = input.nextInt();
        int targetSum = input.nextInt();
        for (int first = 0; first <= limit; first++) {
            for (int second = 0; second <= limit; second++) {
                int third = targetSum - (first + second);
                if (third >= 0 && third <= limit) {
                    count++;
                }
            }
        }
        System.out.print(count);
    }
}
