public class Solution {
    int limit;
    int targetSum;

    public Solution(int limit, int targetSum) {
        this.limit = limit;
        this.targetSum = targetSum;
    }

    public void solve() {
        int count = 0;
        for (int first = 0; first <= limit; first++) {
            for (int second = 0; second <= limit; second++) {
                int third = targetSum - (first + second);
                if (third >= 0 && third <= limit) {
                    count++;
                }
            }
        }
        System.out.print(count);
    }
}
