public class Solution {
    int number;

    public Solution(int number) {
        this.number = number;
    }

    public void solve() {
        boolean isPrime = true;
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) {
                System.out.println("NO");
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            System.out.print("YES");
        }
    }
}
