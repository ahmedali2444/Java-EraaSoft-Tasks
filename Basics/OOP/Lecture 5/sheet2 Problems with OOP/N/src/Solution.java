public class Solution {
    char symbol;
    int linesCount;
    int[] sizes;

    public Solution(char symbol, int linesCount, int[] sizes) {
        this.symbol = symbol;
        this.linesCount = linesCount;
        this.sizes = sizes;
    }

    public void solve() {
        for (int line = 1; line <= linesCount; line++) {
            for (int repeat = 1; repeat <= sizes[line]; repeat++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
