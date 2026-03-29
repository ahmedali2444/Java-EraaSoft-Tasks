import java.util.Scanner;

public class Solution {
    Scanner input;

    public Solution(Scanner input) {
        this.input = input;
    }

    public void solve() {
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
