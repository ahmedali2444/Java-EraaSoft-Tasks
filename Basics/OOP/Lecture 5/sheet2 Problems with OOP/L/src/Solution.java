public class Solution {
    int firstNumber;
    int secondNumber;

    public Solution(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public void solve() {
        int gcdValue = 0;
        for (int divisor = 1; divisor <= 1000; divisor++) {
            if (firstNumber % divisor == 0 && secondNumber % divisor == 0) {
                if (divisor > gcdValue) {
                    gcdValue = divisor;
                }
            }
        }
        System.out.print(gcdValue);
    }
}
