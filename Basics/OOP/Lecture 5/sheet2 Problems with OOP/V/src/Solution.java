public class Solution {
    int rows;

    public Solution(int rows) {
        this.rows = rows;
    }

    public void solve() {
        int startValue = 1;
        for (int row = 1; row <= rows; row++) {
            for (int number = startValue; number <= startValue + 2; number++) {
                System.out.print(number + " ");
            }
            System.out.println("PUM");
            startValue += 4;
        }
    }
}
