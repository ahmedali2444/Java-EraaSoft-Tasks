import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        for (int caseIndex = 1; caseIndex <= testCases; caseIndex++) {
            long number = input.nextLong();
            int power = 0;
            long result = 0;
            while (number != 0) {
                int bit = (int) (number % 2);
                number /= 2;
                if (bit == 1) {
                    result += (long) Math.pow(2, power);
                    power++;
                }
            }
            System.out.println(result);
        }
    }
}
