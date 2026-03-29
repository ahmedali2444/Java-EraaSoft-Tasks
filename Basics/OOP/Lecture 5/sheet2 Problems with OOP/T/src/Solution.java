public class Solution {
    int rows;

    public Solution(int rows) {
        this.rows = rows;
    }

    public void solve() {
        int starsCount = 0;
        for (int row = 0; row < rows; row++) {
            for (int space = rows - 1; space > row; space--) {
                System.out.print(" ");
            }
            for (int star = 0; star <= starsCount; star++) {
                System.out.print("*");
            }
            System.out.println();
            starsCount += 2;
        }
    }
}
