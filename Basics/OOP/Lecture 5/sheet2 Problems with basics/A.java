import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int limit = input.nextInt();
        for (int value = 1; value <= limit; value++) {
            System.out.println(value);
        }
    }
}
