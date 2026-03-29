import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int startValue = 1;
        int rows = input.nextInt();
        for (int row = 1; row <= rows; row++) {
            for (int number = startValue; number <= startValue + 2; number++) {
                System.out.print(number + " ");
            }
            System.out.println("PUM");
            startValue += 4;
        }
    }
}
