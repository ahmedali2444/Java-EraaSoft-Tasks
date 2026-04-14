import java.util.Scanner;

public class NumberCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt = sc.next();
        try {
            int num = Integer.parseInt(txt);
            System.out.println(num);
        } catch (NumberFormatException e) {
            System.out.println("that is not a valid number");
        }
    }
}
