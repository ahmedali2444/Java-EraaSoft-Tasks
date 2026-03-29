import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCases = input.nextInt();
        for (int caseIndex = 1; caseIndex <= testCases; caseIndex++) {
            String text = input.next();
            int textLength = text.length();
            for (int index = textLength - 1; index >= 0; index--) {
                System.out.print(text.charAt(index) + " ");
            }
            System.out.println();
        }
    }
}
