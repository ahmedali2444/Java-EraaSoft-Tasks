public class Solution {
    int number;

    public Solution(int number) {
        this.number = number;
    }

    public void solve() {
        for (int divisor = 1; divisor <= number; divisor++) {
            if (number % divisor == 0) {
                System.out.println(divisor);
            }
        }
    }
}
