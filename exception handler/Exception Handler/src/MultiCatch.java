public class MultiCatch {
    public static void main(String[] args) {
        try {
            String str = null;
            System.out.println(str.length());
            int res = 10 / 0;
        } catch (NullPointerException e) {
            System.out.println("got a null pointer error");
        } catch (ArithmeticException e) {
            System.out.println("got an arithmetic error");
        }
    }
}
