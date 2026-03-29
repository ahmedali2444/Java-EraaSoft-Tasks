public class Solution {
    long testCases;
    long[] values;

    public Solution(long testCases, long[] values) {
        this.testCases = testCases;
        this.values = values;
    }

    public void solve() {
        long factorial = 1;
        for (int index = 0; index < testCases; index++) {
            long currentValue = values[index];
            for (int current = 1; current <= currentValue; current++) {
                factorial *= current;
            }
            System.out.println(factorial);
            factorial = 1;
        }
    }
}
