public class Solution {
    int count;
    long[] numbers;

    public Solution(int count, long[] numbers) {
        this.count = count;
        this.numbers = numbers;
    }

    public void solve() {
        int maxValue = 0;
        for (int index = 0; index < count; index++) {
            long currentValue = numbers[index];
            if (currentValue > maxValue) {
                maxValue = (int) currentValue;
            }
        }
        System.out.print(maxValue);
    }
}
