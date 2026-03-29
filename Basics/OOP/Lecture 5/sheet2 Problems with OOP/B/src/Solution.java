public class Solution {
    int limit;

    public Solution(int limit) {
        this.limit = limit;
    }

    public void solve() {
        for (int evenValue = 2; evenValue <= limit; evenValue += 2) {
            System.out.println(evenValue);
        }
        if (limit < 2) {
            System.out.println("-1");
        }
    }
}
