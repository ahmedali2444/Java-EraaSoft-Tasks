public class Solution {
    int count;
    int[] numbers;

    public Solution(int count, int[] numbers) {
        this.count = count;
        this.numbers = numbers;
    }

    public void solve() {
        int evenCount = 0;
        int oddCount = 0;
        int positiveCount = 0;
        int negativeCount = 0;
        for (int index = 0; index < count; index++) {
            int currentNumber = numbers[index];
            if (currentNumber % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            if (currentNumber > 0) {
                positiveCount++;
            } else if (currentNumber < 0) {
                negativeCount++;
            }
        }
        System.out.println("Even: " + evenCount);
        System.out.println("Odd: " + oddCount);
        System.out.println("Positive: " + positiveCount);
        System.out.println("Negative: " + negativeCount);
    }
}
