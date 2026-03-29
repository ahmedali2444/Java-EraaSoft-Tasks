public class Solution {
    int testCases;
    String[] words;

    public Solution(int testCases, String[] words) {
        this.testCases = testCases;
        this.words = words;
    }

    public void solve() {
        for (int index = 0; index < testCases; index++) {
            String text = words[index];
            int textLength = text.length();
            for (int charIndex = textLength - 1; charIndex >= 0; charIndex--) {
                System.out.print(text.charAt(charIndex) + " ");
            }
            System.out.println();
        }
    }
}
