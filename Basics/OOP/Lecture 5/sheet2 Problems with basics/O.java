import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows = input.nextInt();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column <= row; column++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
