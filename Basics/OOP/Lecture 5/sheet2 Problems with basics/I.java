import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long reversedNumber = 0;
        long number = input.nextLong();
        long originalNumber = number;
        while (number != 0) {
            reversedNumber = (reversedNumber * 10) + (number % 10);
            number /= 10;
        }
        if (originalNumber == reversedNumber) {
            System.out.println(reversedNumber);
            System.out.print("YES");
        } else {
            System.out.println(reversedNumber);
            System.out.print("NO");
        }
    }
}
