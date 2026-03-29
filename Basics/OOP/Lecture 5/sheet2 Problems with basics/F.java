import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        for (int multiplier = 1; multiplier <= 12; multiplier++) {
            System.out.println(number + " * " + multiplier + " = " + number * multiplier);
        }
    }
}
