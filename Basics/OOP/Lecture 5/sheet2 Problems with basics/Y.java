import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstValue = 0;
        int secondValue = 1;
        int count = input.nextInt();
        if (count == 0 || count == 1) {
            System.out.print(firstValue);
        } else {
            System.out.print(firstValue + " " + secondValue + " ");
            for (int index = 2; index < count; index++) {
                int nextValue = firstValue + secondValue;
                System.out.print(nextValue + " ");
                firstValue = secondValue;
                secondValue = nextValue;
            }
        }
    }
}
