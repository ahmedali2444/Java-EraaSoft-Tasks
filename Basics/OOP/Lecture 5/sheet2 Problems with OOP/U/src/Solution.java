public class Solution {
    long limit;
    long minSum;
    long maxSum;

    public Solution(long limit, long minSum, long maxSum) {
        this.limit = limit;
        this.minSum = minSum;
        this.maxSum = maxSum;
    }

    public void solve() {
        long total = 0;
        for (int value = 1; value <= limit; value++) {
            int currentNumber = value;
            long digitsSum = 0;
            while (currentNumber != 0) {
                int digit = currentNumber % 10;
                digitsSum += digit;
                currentNumber /= 10;
            }
            if (digitsSum >= minSum && digitsSum <= maxSum) {
                total += value;
            }
        }
        System.out.print(total);
    }
}
