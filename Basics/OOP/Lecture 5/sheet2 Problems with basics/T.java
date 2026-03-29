import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int starsCount = 0;
        int rows = input.nextInt();
        for (int row = 0; row < rows; row++) {
            for (int space = rows - 1; space > row; space--) {
                System.out.print(" ");
            }
            for (int star = 0; star <= starsCount; star++) {
                System.out.print("*");
            }
            System.out.println();
            starsCount += 2;
        }
    }
}
