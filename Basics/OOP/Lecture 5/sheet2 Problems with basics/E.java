import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        int maxValue = 0;
        for (int index = 1; index <= count; index++) {
            long currentValue = input.nextLong();
            if (currentValue > maxValue) {
                maxValue = (int) currentValue;
            }
        }
        System.out.print(maxValue);
    }
}
