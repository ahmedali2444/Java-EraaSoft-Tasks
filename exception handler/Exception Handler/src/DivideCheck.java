import java.util.Scanner;

public class DivideCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        try {
            int ans = x / y;
            System.out.println(ans);
        } catch (ArithmeticException e) {
            System.out.println("you cannot divide by zero");
        }
    }
}
