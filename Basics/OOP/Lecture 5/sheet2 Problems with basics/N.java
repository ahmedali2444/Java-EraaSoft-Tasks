import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char symbol = input.next().charAt(0);
        int linesCount = input.nextInt();
        int[] sizes = new int[linesCount + 1];
        for (int line = 1; line <= linesCount; line++) {
            sizes[line] = input.nextInt();
            for (int repeat = 1; repeat <= sizes[line]; repeat++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
