public class Solution {
    int limit;

    public Solution(int limit) {
        this.limit = limit;
    }

    public void solve() {
        for (int value = 1; value <= limit; value++) {
            System.out.println(value);
        }
    }
}
