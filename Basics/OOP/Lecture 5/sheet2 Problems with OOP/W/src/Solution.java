public class Solution {
    int rows;

    public Solution(int rows) {
        this.rows = rows;
    }

    public void solve() {
        int starsCount = 1;
        int leadingSpaces = rows - 1;
        for (int row = 1; row <= rows; row++) {
            for (int space = leadingSpaces; space > 0; space--) {
                System.out.print(" ");
            }
            leadingSpaces--;
            for (int star = 1; star <= starsCount; star++) {
                System.out.print("*");
            }
            System.out.println();
            starsCount += 2;
        }
        starsCount -= 2;
        int innerSpaces = 1;
        for (int row = 1; row <= rows; row++) {
            for (int space = 1; space < innerSpaces; space++) {
                System.out.print(" ");
            }
            innerSpaces++;
            for (int star = starsCount; star > 0; star--) {
                System.out.print("*");
            }
            starsCount -= 2;
            System.out.println();
        }
    }
}
