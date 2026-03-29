public class Solution {
    int rows;

    public Solution(int rows) {
        this.rows = rows;
    }

    public void solve() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column <= row; column++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
