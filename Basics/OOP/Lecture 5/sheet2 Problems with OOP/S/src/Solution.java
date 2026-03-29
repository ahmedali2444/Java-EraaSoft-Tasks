public class Solution {
    int testCases;
    int[] firstNumbers;
    int[] secondNumbers;

    public Solution(int testCases, int[] firstNumbers, int[] secondNumbers) {
        this.testCases = testCases;
        this.firstNumbers = firstNumbers;
        this.secondNumbers = secondNumbers;
    }

    public void solve() {
        for (int index = 0; index < testCases; index++) {
            int oddSum = 0;
            int firstValue = firstNumbers[index];
            int secondValue = secondNumbers[index];
            for (int value = Math.min(firstValue, secondValue) + 1; value < Math.max(firstValue, secondValue); value++) {
                if (value % 2 != 0) {
                    oddSum += value;
                }
            }
            System.out.println(oddSum);
        }
    }
}
