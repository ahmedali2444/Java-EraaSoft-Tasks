public class Solution {
    int number;

    public Solution(int number) {
        this.number = number;
    }

    public void solve() {
        for (int multiplier = 1; multiplier <= 12; multiplier++) {
            System.out.println(number + " * " + multiplier + " = " + number * multiplier);
        }
    }
}
