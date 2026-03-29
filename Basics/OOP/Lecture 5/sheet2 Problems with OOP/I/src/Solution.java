public class Solution {
    long number;

    public Solution(long number) {
        this.number = number;
    }

    public void solve() {
        long reversedNumber = 0;
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
