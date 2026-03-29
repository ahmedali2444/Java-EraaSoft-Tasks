import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int foundCount = 0;
        int start = input.nextInt();
        int end = input.nextInt();
        for (int currentNumber = start; currentNumber <= end; currentNumber++) {
            int copyNumber = currentNumber;
            boolean isLucky = true;
            while (copyNumber != 0) {
                if (copyNumber % 10 != 4 && copyNumber % 10 != 7) {
                    isLucky = false;
                }
                copyNumber /= 10;
            }
            if (isLucky) {
                System.out.print(currentNumber + " ");
                foundCount++;
            }
        }
        if (foundCount == 0) {
            System.out.print("-1");
        }
    }
}
