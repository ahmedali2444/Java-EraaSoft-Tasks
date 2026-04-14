public class FinallyCheck {
    public static void main(String[] args) {
        try {
            int res = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("caught the error");
        } finally {
            System.out.println("finally always runs no matter what");
        }
    }
}
