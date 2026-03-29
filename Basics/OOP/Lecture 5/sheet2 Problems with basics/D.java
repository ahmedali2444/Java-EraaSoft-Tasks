import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int password = input.nextInt();
            if (password != 1999) {
                System.out.println("Wrong");
            } else {
                System.out.println("Correct");
                break;
            }
        }
    }
}
