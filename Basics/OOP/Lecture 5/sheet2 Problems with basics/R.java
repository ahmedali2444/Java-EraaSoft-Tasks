import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstValue = input.nextInt();
        int secondValue = input.nextInt();
        while (firstValue > 0 && secondValue > 0) {
            int total = 0;
            for (int number = Math.min(firstValue, secondValue); number <= Math.max(firstValue, secondValue); number++) {
                System.out.print(number + " ");
                total += number;
            }
            System.out.println("sum =" + total);
            firstValue = input.nextInt();
            secondValue = input.nextInt();
        }
    }
}
