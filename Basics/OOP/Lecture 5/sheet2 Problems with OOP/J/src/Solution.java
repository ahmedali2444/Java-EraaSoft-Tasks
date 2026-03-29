public class Solution {
    int limit;

    public Solution(int limit) {
        this.limit = limit;
    }

    public void solve() {
        for (int value = 2; value <= limit; value++) {
            boolean isPrime = true;
            for (int divisor = 2; divisor < value; divisor++) {
                if (value % divisor == 0 && value != divisor) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.print(value + " ");
            }
        }
    }
}
