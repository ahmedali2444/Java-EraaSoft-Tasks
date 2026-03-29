public class Solution {
    int count;

    public Solution(int count) {
        this.count = count;
    }

    public void solve() {
        int firstValue = 0;
        int secondValue = 1;
        if (count == 0 || count == 1) {
            System.out.print(firstValue);
        } else {
            System.out.print(firstValue + " " + secondValue + " ");
            for (int index = 2; index < count; index++) {
                int nextValue = firstValue + secondValue;
                System.out.print(nextValue + " ");
                firstValue = secondValue;
                secondValue = nextValue;
            }
        }
    }
}
