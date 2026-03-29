import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows = input.nextInt();
        for (int row = rows; row > 0; row--) {
            for (int column = 0; column < row; column++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
