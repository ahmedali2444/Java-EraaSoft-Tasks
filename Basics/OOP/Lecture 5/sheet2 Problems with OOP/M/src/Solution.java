public class Solution {
    int start;
    int end;

    public Solution(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void solve() {
        int foundCount = 0;
        for (int currentNumber = start; currentNumber <= end; currentNumber++) {
            int copyNumber = currentNumber;
            boolean isLucky = true;
            while (copyNumber != 0) {
                if (copyNumber % 10 != 4 && copyNumber % 10 != 7) {
                    isLucky = false;
                }
                copyNumber /= 10;
            }
            if (isLucky) {
                System.out.print(currentNumber + " ");
                foundCount++;
            }
        }
        if (foundCount == 0) {
            System.out.print("-1");
        }
    }
}
