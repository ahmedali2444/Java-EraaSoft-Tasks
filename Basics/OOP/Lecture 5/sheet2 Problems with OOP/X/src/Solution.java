public class Solution {
    int testCases;
    long[] values;

    public Solution(int testCases, long[] values) {
        this.testCases = testCases;
        this.values = values;
    }

    public void solve() {
        for (int index = 0; index < testCases; index++) {
            long number = values[index];
            int power = 0;
            long result = 0;
            while (number != 0) {
                int bit = (int) (number % 2);
                number /= 2;
                if (bit == 1) {
                    result += (long) Math.pow(2, power);
                    power++;
                }
            }
            System.out.println(result);
        }
    }
}
